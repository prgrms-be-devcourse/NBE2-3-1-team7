package com.example.shop.admin.controller;



import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.service.AdminProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin/products") //디폴트 경로 설정
public class AdminProductController {

    private final AdminProductService adminProductService;

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
