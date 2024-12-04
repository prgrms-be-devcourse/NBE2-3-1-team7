package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.mapper.AdminMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;

    public AdminDAO(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    // 기존에 작성된 메서드
    public int updateProduct(ProductUpdateRequest productUpdateRequest) {
        return adminMapper.updateProduct(productUpdateRequest);
    }

    // main 브랜치에서 추가된 메서드
    public List<ProductTO> getAllProducts() {
        return adminMapper.selectAllProduct();
    }

    public int createProduct(ProductCreateRequest productCreateRequest) {
        return adminMapper.insertProduct(productCreateRequest);

    }

}
