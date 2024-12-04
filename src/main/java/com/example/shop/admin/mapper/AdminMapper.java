package com.example.shop.admin.mapper;

import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {

     // 물품 생성
     int insertProduct(ProductCreateRequest productCreateRequest);

     // 물품 수정
     int updateProduct(ProductUpdateRequest productUpdateRequest);

     // 전체 물품 조회
     List<ProductTO> selectAllProduct();
}
