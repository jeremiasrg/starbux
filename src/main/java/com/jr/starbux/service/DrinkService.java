package com.jr.starbux.service;

import org.springframework.stereotype.Service;

import com.jr.starbux.entity.Drink;
import com.jr.starbux.repository.DrinkRepository;

@Service
public class DrinkService extends BaseService<Drink, Long, DrinkRepository> {

}
