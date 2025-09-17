package edu.demo.monolith.repo;

import edu.demo.monolith.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { }
