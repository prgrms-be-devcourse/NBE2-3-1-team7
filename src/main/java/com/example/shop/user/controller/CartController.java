package com.example.shop.user.controller;

import com.example.shop.user.dto.AddCartProductRequest;
import com.example.shop.user.dto.CartDetailResponse;
import com.example.shop.user.dto.UpdateCartQuantityRequest;
import com.example.shop.user.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart/products")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Void> addCartProduct(@RequestBody @Valid AddCartProductRequest request) {
        cartService.addCartProduct(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CartDetailResponse>> getCartDetails() {
        List<CartDetailResponse> cartDetails = cartService.getCartDetails();
        return ResponseEntity.ok(cartDetails);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> removeCartProduct(@PathVariable("productId") Long productId) {
        cartService.removeCartProduct(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Void> updateCartQuantity(@PathVariable("productId") Long productId, @RequestBody @Valid UpdateCartQuantityRequest request) {
        cartService.updateCartQuantity(productId, request.getQuantity());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> removeAllCartProducts() {
        cartService.removeAllCartProducts();
        return ResponseEntity.ok().build();
    }
}
