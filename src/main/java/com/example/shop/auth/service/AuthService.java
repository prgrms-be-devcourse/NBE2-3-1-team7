package com.example.shop.auth.service;

import com.example.shop.auth.RefreshTokenRepository;
import com.example.shop.auth.dto.*;
import com.example.shop.domain.user.RefreshToken;
import com.example.shop.domain.user.Role;
import com.example.shop.domain.user.User;
import com.example.shop.domain.user.UserRepository;
import com.example.shop.global.config.auth.JwtProvider;
import com.example.shop.global.exception.DuplicatedEmailException;
import com.example.shop.global.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignInResponse signIn(SignInRequest signInRequest) {
        Authentication authentication = setAuthentication(signInRequest.getEmail(), signInRequest.getPassword());
        TokenDto tokenDto = jwtProvider.generateToken(authentication);
        //saveRefreshToken(tokenDto.getRefreshToken(), signInRequest.getEmail());
        return SignInResponse.of(tokenDto.getAccessToken(),tokenDto.getGrantType());
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
    private void saveRefreshToken(String tokenValue, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        refreshTokenRepository.save(new RefreshToken(tokenValue, user.getId().toString()));
    }
    private void checkDuplicatedEmail(SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) throw new DuplicatedEmailException();
    }

}
