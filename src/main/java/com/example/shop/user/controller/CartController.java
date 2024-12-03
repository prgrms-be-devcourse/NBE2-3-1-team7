package com.example.shop.user.controller;

import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.dto.CartDetailResponse;
import com.example.shop.user.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/products")
    public ResponseEntity<Void> addCartProduct(@RequestBody @Valid AddCartProductRequest request) {
        cartService.addCartProduct(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<CartDetailResponse>> getCartDetails() {
        List<CartDetailResponse> cartDetails = cartService.getCartDetails();
        return ResponseEntity.ok(cartDetails);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> removeCartProduct(@PathVariable("productId") Long productId) {
        cartService.removeCartProduct(productId);
        return ResponseEntity.ok().build();
    }
}
