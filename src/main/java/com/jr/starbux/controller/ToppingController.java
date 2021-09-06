package com.jr.starbux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Topping;
import com.jr.starbux.repository.ToppingRepository;
import com.jr.starbux.service.ToppingService;

@RestController
@RequestMapping("/topping")
public class ToppingController extends BaseController<Topping, Long, ToppingRepository, ToppingService> {

}
