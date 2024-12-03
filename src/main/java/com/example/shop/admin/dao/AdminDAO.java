package com.example.shop.admin.dao;


import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@RequiredArgsConstructor
@Repository
public class AdminDAO {

    private final AdminMapper adminMapper;

    public int  updateProduct(ProductUpdateRequest productUpdateRequest) {
        return adminMapper.updateProduct(productUpdateRequest);
    }

}
