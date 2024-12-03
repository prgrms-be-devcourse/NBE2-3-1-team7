package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;
    public int createProduct(ProductCreateRequest productCreateRequest) {
         return adminMapper.insertProduct(productCreateRequest);

    }
}
