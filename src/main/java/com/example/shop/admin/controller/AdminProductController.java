package com.example.shop.admin.controller;


import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private AdminProductService adminProductService;


    @PostMapping
    public ResponseEntity<String> insertProduct(@RequestBody ProductCreateRequest product) {
        String result = adminProductService.insertProduct(product);

        // 입력 성공 여부에 따라 적절한 HTTP 상태 코드 반환
        if ("정상적으로 입력되지 않았습니다".equals(result)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
