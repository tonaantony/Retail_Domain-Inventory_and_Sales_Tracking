package com.ust.sales.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ust.sales.dto.Product;
import com.ust.sales.exception.InsufficientStockException;
import com.ust.sales.model.Sale;
import com.ust.sales.repository.SalesRepository;

@Service
public class SalesService {

    @Autowired
    private SalesRepository repository;

    private final WebClient webClient;

    // Constructor injection for WebClient
    public SalesService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:9091/api/inventory").build(); // Base URL for InventoryService
    }

    public Sale recordSale(Sale sale) {
        // Fetch product details from Inventory Service
        Product product = webClient.get()
                .uri("/{productId}", sale.getProductId())
                .retrieve()
                .bodyToMono(Product.class)
                .block(); // Blocking for simplicity; consider a reactive approach for non-blocking

        if (product.getStock() < sale.getQuantity()) {
            throw new InsufficientStockException();
        }

        // Update stock in Inventory Service
        product.setStock(product.getStock() - sale.getQuantity());
        webClient.put()
                .uri("/{productId}", sale.getProductId())
                .bodyValue(product)
                .retrieve()
                .toBodilessEntity()
                .block(); // Blocking for simplicity

        // Record the sale in SalesRepository
        return repository.save(sale);
    }
}
