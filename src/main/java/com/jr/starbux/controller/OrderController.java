package com.jr.starbux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Order;
import com.jr.starbux.repository.OrderRepository;
import com.jr.starbux.service.OrderService;

@RestController()
@RequestMapping("/order")
public class OrderController extends BaseController<Order, Long, OrderRepository, OrderService> {

}
