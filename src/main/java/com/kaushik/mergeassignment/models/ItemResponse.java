package com.kaushik.mergeassignment.models;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long id;

    private String name;

    private String imageUrl;

    private String skuId;

    private String description;

    private String category;

    private String subCategory;

    private String brand;

    private String color;

    private String price;

    private int quantity;
}
