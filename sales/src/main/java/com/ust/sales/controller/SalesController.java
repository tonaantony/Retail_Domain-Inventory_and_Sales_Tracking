package com.ust.sales.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ust.sales.model.Sale;
import com.ust.sales.service.SalesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SalesController {
    @Autowired
    private SalesService salesService;

    @PostMapping
    public Sale recordSale(@Valid @RequestBody Sale sale) {
        return salesService.recordSale(sale);
    }
}

