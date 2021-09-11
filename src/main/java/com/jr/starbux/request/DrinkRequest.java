package com.jr.starbux.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkRequest {

    private Long id;
    private String name;
    private Double price;
}
