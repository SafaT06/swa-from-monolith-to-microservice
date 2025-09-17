package edu.demo.monolith.web.dto;

public record ProductRequest(String name, String description, double price, int stock) {}
