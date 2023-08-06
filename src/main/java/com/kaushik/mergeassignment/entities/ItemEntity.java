package com.kaushik.mergeassignment.entities;

import com.kaushik.mergeassignment.models.ItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "item")
public class ItemEntity extends BaseEntity {

    private String name;

    private String imageUrl;

    private String skuId;

    private String description;

    private String category;

    private String subCategory;

    private String brand;

    private String color;

    private String price;

    @OneToOne
    private InventoryEntity inventory;

/*    public ItemEntity(ItemRequest itemRequest) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(itemRequest.getName());
        itemEntity.setImageUrl(itemRequest.getImageUrl());
        itemEntity.setSkuId(itemRequest.getSkuId());
        itemEntity.setDescription(itemRequest.getDescription());
        itemEntity.setCategory(itemRequest.getCategory());
        itemEntity.setSubCategory(itemRequest.getSubCategory());
        itemEntity.setBrand(itemRequest.getBrand());
        itemEntity.setColor(itemRequest.getColor());
        itemEntity.setPrice(itemRequest.getPrice());

    }*/
}
