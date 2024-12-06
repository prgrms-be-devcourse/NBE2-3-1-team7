package com.example.shop.admin.service;

import com.example.shop.admin.dao.AdminDAO;
import com.example.shop.admin.dto.ProductCreateRequest;
import com.example.shop.admin.dto.ProductFilterRequest;
import com.example.shop.admin.dto.ProductUpdateRequest;
import com.example.shop.admin.dto.ProductTO;
import com.example.shop.global.exception.DataInsertFailedException;
import com.example.shop.global.exception.ProductUpdateFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminProductService {

    private final AdminDAO adminDAO;

    // 물품수정
    public int postProduct(ProductUpdateRequest productUpdateRequest) {
        if (productUpdateRequest.getProductId() == null) {
            throw new IllegalArgumentException("상품 ID는 필수값입니다.");
        }
        int result = adminDAO.updateProduct(productUpdateRequest);
        if (result == 0) {
          throw new ProductUpdateFailedException();
        }
        return result;
    }

    // 전체 물품 목록 조회
    public List<ProductTO> getAllProducts() {
        return adminDAO.getAllProducts();
    }


    //물품 생성
    public int insertProduct(ProductCreateRequest productCreateRequest)throws DataInsertFailedException {
        validateProductCreateRequest(productCreateRequest);

        // 데이터베이스 삽입
        int result = adminDAO.createProduct(productCreateRequest);

        // 삽입 실패 처리
        if (result == 0) {

            throw new DataInsertFailedException();
        }
        return result;
    }


    public List<ProductTO> getFilteredProducts(ProductFilterRequest filter) throws IllegalArgumentException{
        // 모든 필수값 검증
        if (filter.getMinQuantity() == null ||
                filter.getMinPrice() == null ||
                filter.getMaxPrice() == null) {
            throw new IllegalArgumentException("모든 필수값을 입력해야 합니다.");
        }

        return adminDAO.getProductByFilter(filter); // DAO 호출
    }


    //입력값 검증 로직
    private void validateProductCreateRequest(ProductCreateRequest productCreateRequest) {
        if (!StringUtils.hasText(productCreateRequest.getProductName())) {
            throw new IllegalArgumentException("상품 이름은 필수값입니다.");
        }
        if (productCreateRequest.getPrice() == null) {
            throw new IllegalArgumentException("가격은 필수값입니다.");
        }
        if (productCreateRequest.getQuantity() == null) {
            throw new IllegalArgumentException("수량은 필수값입니다.");
        }


    }



}





