package org.api.java.api__v2_2826502.repositories;

import org.api.java.api__v2_2826502.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductRepository extends CrudRepository<Product, Long> {
}
