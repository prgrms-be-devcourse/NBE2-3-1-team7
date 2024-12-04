package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final AdminDAO adminDAO;

    // 물품 생성
    public String insertProduct(ProductCreateRequest productCreateRequest) {
        int result = adminDAO.createProduct(productCreateRequest);
        if (result == 0) {
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }

    // 물품 수정
    public String postProduct(ProductUpdateRequest productUpdateRequest) {
        int result = adminDAO.updateProduct(productUpdateRequest);
        if (result == 0) {
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }

    // 전체 물품 조회
    public List<ProductTO> getAllProducts() {
        return adminDAO.getAllProducts();
    }
}
