package com.jr.starbux.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

	private Long id;
	private ClientRequest client;
	private String address;
	private List<OrderDrinkRequest> order;

}
