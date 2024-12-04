package com.example.shop.auth.service;

import com.example.shop.auth.repository.EmailCodeRepository;
import com.example.shop.auth.repository.RefreshTokenRepository;
import com.example.shop.auth.dto.*;
import com.example.shop.domain.user.RefreshToken;
import com.example.shop.domain.user.Role;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.EmailSender;
import com.example.shop.global.config.auth.JwtProvider;
import com.example.shop.global.exception.DuplicatedEmailException;
import com.example.shop.global.exception.RefreshTokenExpiredException;
import com.example.shop.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.shop.auth.service.EmailCodeUtil.authCodeTemplate;
import static com.example.shop.auth.service.EmailCodeUtil.generateEmailCode;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailCodeRepository emailCodeRepository;
    private final EmailSender emailSender;


    public AccessTokenResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = setAuthentication(signInRequest.getEmail(), signInRequest.getPassword());
        TokenDto tokenDto = jwtProvider.generateToken(authentication);
        deleteAndSaveRefreshToken(tokenDto.getRefreshToken(), getMemberIdByEmail(signInRequest.getEmail()));
        return new AccessTokenResponse(tokenDto.getAccessToken(),tokenDto.getGrantType());
    }

    @Transactional
    public SignUpResponse signUp(SignUpRequest signUpRequest, Role role) {
        checkDuplicatedEmail(signUpRequest);
        // 이메일 인증 추가
        userRepository.save(SignUpRequest.toEntity(signUpRequest.getEmail()
                , passwordEncoder.encode(signUpRequest.getPassword()), signUpRequest.getName(),role));
        return new SignUpResponse(signUpRequest.getEmail(), signUpRequest.getName());
    }


    private Authentication setAuthentication(String email, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuth = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
    private void deleteAndSaveRefreshToken(String tokenValue, Long memberId) {
        refreshTokenRepository.deleteByMemberId(memberId);
        refreshTokenRepository.save(new RefreshToken(memberId, tokenValue));
    }

    private void checkDuplicatedEmail(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) throw new DuplicatedEmailException();
    }

    public void sendAuthCode(String receivedMail) {
        String authCode = generateEmailCode();
        String subject = "[DevCourse Team 7] 안녕하세요 이메일 인증 번호를 확인해주세요.";
        String content = authCodeTemplate(authCode);

        emailSender.sendMail(receivedMail, subject, content);

        emailCodeRepository.save(receivedMail, authCode, 3 * 60L);
    }

    public boolean verifyAuthCode(EmailCodeRequest emailCodeRequest) {
        return emailCodeRepository.findAuthCode(emailCodeRequest.getEmail())
                .filter(code -> code.equals(emailCodeRequest.getAuthCode()))  // 인증 코드 비교
                .map(code -> {
                    emailCodeRepository.deleteByKey(emailCodeRequest.getEmail());
                    return Boolean.TRUE;
                })
                .orElse(Boolean.FALSE);  // 값이 없거나 인증 코드가 다르면 false 반환
    }

    public AccessTokenResponse reissue(String bearerToken) {
        String accessToken = bearerToken.substring(7);
        Long memberId = getMemberIdByAccessToken(accessToken);

        validateTokens(memberId); // 리프레시 토큰 존재여부, 액세스토큰 id 일치여부 확인
        Authentication authentication = jwtProvider.getAuthentication(bearerToken.substring(7));
        TokenDto tokenDto = jwtProvider.generateToken(authentication); //토큰 재발급

        deleteAndSaveRefreshToken(tokenDto.getRefreshToken(),memberId);
        return new AccessTokenResponse(tokenDto.getAccessToken(),tokenDto.getGrantType());
    }

    public Long getMemberIdByAccessToken(String accessToken) {
        return userRepository.findByEmail(jwtProvider.parseClaims(accessToken).getSubject())
                .orElseThrow(UserNotFoundException::new)
                .getId();
    }
    public Long getMemberIdByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new)
                .getId();
    }
    private void validateTokens(Long memberId) {
        // TODO: 커밋 후 pull 받고 getCurrent 활용해서 accessToken 검증 추가하기
        refreshTokenRepository.findByMemberId(memberId).orElseThrow(RefreshTokenExpiredException::new);
    }


}
