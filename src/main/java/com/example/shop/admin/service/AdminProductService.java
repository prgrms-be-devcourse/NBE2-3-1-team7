package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductCreateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AdminProductService {

    @Autowired
    private AdminDAO adminDAO;

    public String insertProduct(ProductCreateRequest productCreateRequest) {
        int result=adminDAO.createProduct( productCreateRequest);
        //   return adminDAO.createProduct( productCreateRequest);
        if(result==0){
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }
}
