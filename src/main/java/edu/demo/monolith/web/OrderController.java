package edu.demo.monolith.web;

import edu.demo.monolith.domain.Order;
import edu.demo.monolith.repo.OrderRepository;
import edu.demo.monolith.service.OrderService;
import edu.demo.monolith.web.dto.CreateOrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orders;

    public OrderController(OrderService orderService, OrderRepository orders) {
        this.orderService = orderService;
        this.orders = orders;
    }

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest req) {
        var productIds = req.items().stream().map(i -> i.productId()).collect(Collectors.toList());
        var quantities = req.items().stream().map(i -> i.quantity()).collect(Collectors.toList());
        var order = orderService.createOrder(req.userId(), productIds, quantities);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> get(@PathVariable Long id) {
        return orders.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
