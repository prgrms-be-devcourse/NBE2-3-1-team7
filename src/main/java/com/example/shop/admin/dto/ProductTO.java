package com.example.shop.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;


@ToString
@Setter
@Getter

public class ProductTO {




    private int product_id;
    private String product_name;
    private String quantity;
    private String price;
    private String image;
    private String description;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


}
