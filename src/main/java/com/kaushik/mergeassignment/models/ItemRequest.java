package com.kaushik.mergeassignment.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemRequest {

    @NotBlank
    private String name;

    private String imageUrl;

    @NotBlank
    private String skuId;

    private String description;

    @NotBlank
    private String category;

    private String subCategory;

    @NotBlank
    private String brand;

    private String color;

    @NotBlank
    private String price;

    private int quantity;
}
