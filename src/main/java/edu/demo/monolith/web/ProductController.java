package edu.demo.monolith.web;

import edu.demo.monolith.domain.Product;
import edu.demo.monolith.repo.ProductRepository;
import edu.demo.monolith.web.dto.ProductRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository products;

    public ProductController(ProductRepository products) { this.products = products; }

    @GetMapping
    public List<Product> all() { return products.findAll(); }

    @PostMapping
    public Product create(@RequestBody ProductRequest req) {
        return products.save(Product.builder()
                .name(req.name())
                .description(req.description())
                .price(req.price())
                .stock(req.stock())
                .build());
    }
}
