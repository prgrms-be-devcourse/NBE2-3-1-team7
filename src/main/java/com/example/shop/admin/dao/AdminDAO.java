package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;

    // 물품 생성
    public int createProduct(ProductCreateRequest productCreateRequest) {
        return adminMapper.insertProduct(productCreateRequest);
    }

    // 물품 수정
    public int updateProduct(ProductUpdateRequest productUpdateRequest) {
        return adminMapper.updateProduct(productUpdateRequest);
    }

    // 전체 물품 조회
    public List<ProductTO> getAllProducts() {
        return adminMapper.selectAllProduct();
    }
}
