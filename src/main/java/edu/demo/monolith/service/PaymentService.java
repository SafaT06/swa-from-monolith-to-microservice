package edu.demo.monolith.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    private final Random random = new Random();

    // Simuleer een betaling; 85% kans op succes
    public boolean pay(double amount, Long userId) {
        return random.nextDouble() < 0.85;
    }
}
