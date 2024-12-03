package com.example.shop.admin.controller;



import com.example.shop.admin.dto.ProductTO;

import com.example.shop.admin.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products") //디폴트 경로 설정
public class AdminProductController {

    @Autowired
    private  AdminProductService adminProductService;




    @GetMapping //요청을 받으면 전체 리스트 출력
    public List<ProductTO> getProducts()  {


        List<ProductTO> lists=adminProductService.getAllProducts();
        return lists;


    }





}
