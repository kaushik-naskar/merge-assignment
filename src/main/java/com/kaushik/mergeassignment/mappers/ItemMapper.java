package com.kaushik.mergeassignment.mappers;

import com.kaushik.mergeassignment.entities.InventoryEntity;
import com.kaushik.mergeassignment.entities.ItemEntity;
import com.kaushik.mergeassignment.models.ItemRequest;
import com.kaushik.mergeassignment.models.ItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    //nse createItemResponse(ItemEntity itemEntity, InventoryEntity inventory);
}
