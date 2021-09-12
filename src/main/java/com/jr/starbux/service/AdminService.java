package com.jr.starbux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.starbux.repository.CustomRepository;
import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;


@Service
public class AdminService {

    @Autowired
    private CustomRepository repository;

    public List<TotalAmountCustomer> totalAmountCustomer() {
        return repository.totalAmountCustomer();
    }

    public List<MostUsedToppingsDrinks> mostUsedToppingsDrinks() {
        return repository.mostUsedToppingsDrinks();
    }
}
