package com.jr.starbux.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jr.starbux.model.Drink;
import com.jr.starbux.repository.DrinkRepository;
import com.jr.starbux.service.DrinkService;

@RestController
@RequestMapping("/drink")
public class DrinkController extends BaseController<Drink, Long, DrinkRepository, DrinkService> {

}