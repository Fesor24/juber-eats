package com.app.JuberEats.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestParams {
    private Integer pageSize = 10;
    private Integer pageNumber = 1;
}
