package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

    int insertProduct(ProductCreateRequest productCreateRequest);

    int updateProduct(ProductUpdateRequest productUpdateRequest);

    // main 브랜치에서 추가된 메서드
    List<ProductTO> selectAllProduct();
}