package com.jr.starbux.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TotalAmountCustomer {

	private String customer;
	private Double total;
}
