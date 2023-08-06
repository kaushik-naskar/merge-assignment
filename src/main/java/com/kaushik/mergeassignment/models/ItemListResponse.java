package com.kaushik.mergeassignment.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemListResponse {

    private List<ItemResponse> items;

    private Long totalItems;

    private Long totalPages;

    private Long currentPage;

    private Long pageSize;
}
