package com.jr.starbux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.starbux.repository.CustomRepository;
import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Service
public class AdminService {

    @Autowired
    private CustomRepository repository;

    public List<TotalAmountCustomer> totalAmountCustomer(String customerName) {
        return repository.totalAmountCustomer(customerName);
    }

    public List<MostUsedToppingsDrinks> mostUsedToppingsDrinks() {
        return repository.mostUsedToppingsDrinks();
    }
}
