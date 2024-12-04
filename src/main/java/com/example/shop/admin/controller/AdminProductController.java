package com.example.shop.admin.controller;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 물품 생성
    @PostMapping
    public ResponseEntity<String> insertProduct(@RequestBody ProductCreateRequest product) {
        String result = adminProductService.insertProduct(product);

        if ("정상적으로 입력되지 않았습니다".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 전체 물품 조회
    @GetMapping
    public ResponseEntity<List<ProductTO>> getProducts() {
        List<ProductTO> lists = adminProductService.getAllProducts();

        if (lists.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(lists); // 200 OK
    }

    // 물품 수정
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProductById(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest productUpdateRequest) {

        productUpdateRequest.setProductId(id);
        String result = adminProductService.postProduct(productUpdateRequest);

        if ("정상적으로 입력되었습니다".equals(result)) {
            return ResponseEntity.ok(result); // 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result); // 400 Bad Request
        }
    }
}
