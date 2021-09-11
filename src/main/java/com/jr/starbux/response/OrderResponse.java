package com.jr.starbux.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

	private ClientResponse client;
	private String address;
	private List<OrderDrinkResponse> drinks;

}
