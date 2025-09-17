package edu.demo.monolith.service;

import edu.demo.monolith.domain.*;
import edu.demo.monolith.repo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class OrderService {
    private final ProductRepository products;
    private final OrderRepository orders;
    private final PaymentService payments;

    public OrderService(ProductRepository products, OrderRepository orders, PaymentService payments) {
        this.products = products;
        this.orders = orders;
        this.payments = payments;
    }

    @Transactional
    public Order createOrder(Long userId, java.util.List<Long> productIds, java.util.List<Integer> quantities) {
        if (productIds.size() != quantities.size()) throw new IllegalArgumentException("Mismatched items");
        var items = new ArrayList<OrderItem>();
        double total = 0.0;
        for (int i=0; i<productIds.size(); i++) {
            var p = products.findById(productIds.get(i)).orElseThrow();
            int q = quantities.get(i);
            if (p.getStock() < q) throw new RuntimeException("Insufficient stock for product " + p.getId());
            p.setStock(p.getStock() - q);
            products.save(p);
            var item = OrderItem.builder()
                    .productId(p.getId())
                    .quantity(q)
                    .priceAtOrder(p.getPrice())
                    .build();
            items.add(item);
            total += p.getPrice() * q;
        }
        var order = Order.builder()
                .userId(userId)
                .items(items)
                .totalAmount(total)
                .status("CREATED")
                .build();
        orders.save(order);
        boolean paid = payments.pay(total, userId);
        order.setStatus(paid ? "PAID" : "FAILED");
        return orders.save(order);
    }
}
