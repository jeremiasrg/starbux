package com.jr.starbux.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrinkResponse {

	private Long id;
	private String name;
	private Double price;

}
