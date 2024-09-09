package com.compasso.ecommerce_app.core.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.compasso.ecommerce_app.core.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
    public default List<Sale> findByInvoice(String invoive, List<Sale> list){

        List<Sale> listFound = new ArrayList<>();

        for (Sale sale : list) {
            if(sale.getInvoice().equals(invoive)) {
                listFound.add(sale);
            }
        }

        return listFound;

    }

}