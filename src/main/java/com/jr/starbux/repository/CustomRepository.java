package com.jr.starbux.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jr.starbux.response.MostUsedToppingsDrinks;
import com.jr.starbux.response.TotalAmountCustomer;

@Repository
public class CustomRepository {



    private EntityManager em;



    public List<MostUsedToppingsDrinks> mostUsedToppingsDrinks() {
        String query = "SELECT t.name AS topping, COUNT(od.id) AS total from Drink d "
                + "INNER JOIN  OrderDrink od ON d.id = od.drinkId "
                + "INNER JOIN  OrderDrinkTopping odt ON od.id = odt.orderId "
                + "INNER JOIN  Topping t ON t.id = odt.toppingId " + "WHERE d.id = 1 " + "AND od.active = true "
                + "AND odt.active = true " + "GROUP BY d.name, t.name " + "ORDER BY total DESC ";
        Query q = em.createQuery(query);

        List<Object[]> resultQuery = q.getResultList();
        return resultQuery.stream().map(v -> new MostUsedToppingsDrinks(String.valueOf(v[0]), (Long) v[1]))
                .collect(Collectors.toList());
    }

    public List<TotalAmountCustomer> totalAmountCustomer() {

        String query = "SELECT c.name, sum(od.drinkUnitPrice) + sum(odt.toppingUnitPrice) AS total "
                + "FROM Order o " 
                + "INNER JOIN  Client c ON c.id = o.clientId "
                + "INNER JOIN  OrderDrink od ON o.id = od.orderId "
                + "LEFT JOIN  OrderDrinkTopping odt ON odt.orderDrinkId = od.id "
                + "LEFT JOIN  Topping t ON t.id = odt.toppingId "
                + "GROUP BY c.name " + "ORDER BY 2 desc ";

        Query q = em.createQuery(query);

        List<Object[]> resultQuery = q.getResultList();

        return resultQuery.stream().map(v -> new TotalAmountCustomer(String.valueOf(v[0]), (Double) v[1]))
                .collect(Collectors.toList());
    }

}
