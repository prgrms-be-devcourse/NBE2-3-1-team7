package com.example.shop.admin.controller;



import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products") //디폴트 경로 설정
public class AdminProductController {

    private final AdminProductService adminProductService;

    // 요청을 받으면 데이터 업데이트
    @PutMapping("/{id}")
    public String updateProductById(@PathVariable Long id,@RequestBody ProductUpdateRequest productUpdateRequest) {
            productUpdateRequest.setProductId(id);
            String result=adminProductService.postProduct(productUpdateRequest);

        return "<html><body><h1>Update Result</h1><p>" + result + "</p></body></html>";

    }


}
