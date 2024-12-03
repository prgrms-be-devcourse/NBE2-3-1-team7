package com.example.shop.admin.dao;

import com.example.shop.admin.dto.ProductTO;
import com.example.shop.admin.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AdminDAO {

    @Autowired
    private AdminMapper adminMapper;

    public List<ProductTO> getAllProducts() {

        return adminMapper.selectAllProduct();
    }


}
