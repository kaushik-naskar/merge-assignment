package com.kaushik.mergeassignment.services;

import com.kaushik.mergeassignment.entities.InventoryEntity;
import com.kaushik.mergeassignment.entities.ItemEntity;
import com.kaushik.mergeassignment.mappers.ItemMapper;
import com.kaushik.mergeassignment.models.ItemListResponse;
import com.kaushik.mergeassignment.models.ItemRequest;
import com.kaushik.mergeassignment.models.ItemResponse;
import com.kaushik.mergeassignment.repositories.InventoryRepository;
import com.kaushik.mergeassignment.repositories.ItemRepository;
import com.kaushik.mergeassignment.repositories.specifications.ItemSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    private final InventoryRepository inventoryRepository;

    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository, InventoryRepository inventoryRepository) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public ItemEntity createItem(ItemRequest item) {
        itemRepository.findBySkuId(item.getSkuId()).ifPresent((existingItem) -> {
            throw new IllegalArgumentException("Item with skuId " + item.getSkuId() + " already exists");
        });
        ItemEntity itemEntity = createItemEntity(item);

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setQuantity(item.getQuantity());
        inventoryRepository.save(inventoryEntity);
        itemEntity.setInventory(inventoryEntity);
        return itemRepository.save(itemEntity);
    }

    private ItemEntity createItemEntity(ItemRequest item) {
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setName(item.getName());
        itemEntity.setSkuId(item.getSkuId());
        itemEntity.setPrice(item.getPrice());
        itemEntity.setBrand(item.getBrand());
        itemEntity.setCategory(item.getCategory());
        itemEntity.setColor(item.getColor());
        itemEntity.setDescription(item.getDescription());
        itemEntity.setImageUrl(item.getImageUrl());
        itemEntity.setSubCategory(item.getSubCategory());
        return itemEntity;
    }

    public ItemListResponse getItems(String name, Pageable pageable) {

        Page<ItemEntity> items = itemRepository.findAll(ItemSpecification.nameLike(name), pageable);
        List<ItemResponse> itemResponses = items.stream().map(item -> createItemResponse(item, item.getInventory())).collect(Collectors.toList());
        return new ItemListResponse(itemResponses, items.getTotalElements(), (long)items.getTotalPages(), (long) items.getNumber(), (long) items.getSize());
    }

    private ItemResponse createItemResponse(ItemEntity item, InventoryEntity inventory) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .skuId(item.getSkuId())
                .quantity(inventory.getQuantity())
                .price(item.getPrice())
                .brand(item.getBrand())
                .category(item.getCategory())
                .color(item.getColor())
                .description(item.getDescription())
                .imageUrl(item.getImageUrl())
                .subCategory(item.getSubCategory())
                .build();
    }
}
