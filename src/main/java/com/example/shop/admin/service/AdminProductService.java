package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminProductService {




    @Autowired
    private AdminDAO adminDAO;









    //전체리스트갖고오기
    public List<ProductTO> getAllProducts() {
        return adminDAO.getAllProducts();

    }
}
