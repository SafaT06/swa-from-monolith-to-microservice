package edu.demo.monolith.repo;

import edu.demo.monolith.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
