package com.example.shop.admin.controller;


import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.service.AdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {


    @Autowired
    private AdminProductService adminProductService;



    @PostMapping
    public String insertProduct(@RequestBody ProductCreateRequest product) {



        String result=adminProductService.insertProduct(product);


        return "<html><body><h1>Update Result</h1><p>" + result + "</p></body></html>";


    }

}
