package com.enigma.kelompok.utils.specification;

import com.enigma.kelompok.model.Stock;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StockSpecification {
    public static Specification<Stock> getStockSpecification(String name, String code, Integer price) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (name != null && !name.isEmpty()) {
                Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
                predicates.add(namePredicate);
            }

            if (code != null && !code.isEmpty()) {
                Predicate codePredicate = criteriaBuilder.like(root.get("code"), "%" + code + "%");
                predicates.add(codePredicate);
            }

            if (price != null) {
                Predicate pricePredicate = criteriaBuilder.equal(root.get("price"), price);
                predicates.add(pricePredicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
