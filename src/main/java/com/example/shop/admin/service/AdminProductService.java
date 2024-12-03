package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;

import com.example.shop.admin.dto.ProductUpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor// 서비스 로직 처릴
@Service
public class AdminProductService {

   private final AdminDAO adminDAO;

    public String postProduct(ProductUpdateRequest productUpdateRequest) {
        int result=adminDAO.updateProduct(productUpdateRequest);
        // return adminDAO.updateProduct(productUpdateRequest);
        if(result==0){
            return "정상적으로 입력되지 않았습니다";
        }
        return "정상적으로 입력되었습니다";
    }
    //특정 리스트 갖고오기
}
