package com.jr.starbux.response;

import java.util.List;

import com.jr.starbux.request.OrderDrinkRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

	private Long id;
	private ClientResponse client;
	private String address;
	private List<OrderDrinkRequest> order;

}
