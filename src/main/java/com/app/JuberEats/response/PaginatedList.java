package com.app.JuberEats.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedList<TData> {
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalItems;
    private List<TData> items;
}
