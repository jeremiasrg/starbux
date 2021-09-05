package com.jr.starbux.service;

import org.springframework.stereotype.Service;

import com.jr.starbux.model.Topping;
import com.jr.starbux.repository.ToppingRepository;

@Service
public class ToppingService extends BaseService<Topping, Long, ToppingRepository>{

}
