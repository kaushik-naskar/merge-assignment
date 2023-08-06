package com.kaushik.mergeassignment.controllers;

import com.kaushik.mergeassignment.entities.ItemEntity;
import com.kaushik.mergeassignment.models.*;
import com.kaushik.mergeassignment.services.ItemService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/item")
public class ItemControllers {

    private final ItemService itemService;

    public ItemControllers(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping
    public ResponseEntity<GenericModel<BasicResponse>> createItem(@RequestBody @Valid GenericModel< @Valid ItemRequest> genericRequest) {
        ItemEntity item = itemService.createItem(genericRequest.getData());
        return new ResponseEntity<>(new GenericModel<>(new BasicResponse(item.getId(), "Item created successfully.")), null, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GenericModel<ItemListResponse>> getItems(@RequestParam(name = "name", required = false) String name,
                                                                   @RequestParam(name = "page", required = false) Integer page,
                                                                   @RequestParam(name = "size", required = false) Integer size) {
        ItemListResponse listResponse = itemService.getItems(name, Objects.isNull(size)? Pageable.unpaged():PageRequest.of(page, size, Sort.by("name")));
        return ResponseEntity.ok(new GenericModel<>(listResponse));
    }
}
