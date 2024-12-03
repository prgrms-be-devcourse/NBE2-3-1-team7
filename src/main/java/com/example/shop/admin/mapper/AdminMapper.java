package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.ProductUpdateRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {

    int updateProduct(ProductUpdateRequest productUpdateRequest);

}
