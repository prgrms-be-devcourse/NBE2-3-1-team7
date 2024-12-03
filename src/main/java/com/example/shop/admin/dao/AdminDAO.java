package com.example.shop.admin.dao;


import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO {

    @Autowired
    private AdminMapper adminMapper;

    public int  updateProduct(ProductUpdateRequest productUpdateRequest) {
        return adminMapper.updateProduct(productUpdateRequest);
    }

}
