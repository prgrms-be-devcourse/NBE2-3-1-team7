package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.ProductCreateRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {



     int insertProduct(ProductCreateRequest productCreateRequest);
}
