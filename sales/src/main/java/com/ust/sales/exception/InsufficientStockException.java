package com.ust.sales.exception;

public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException() {
        super("insufficient stock");
    }

    public InsufficientStockException(String message) {
        super(message);
    }
    
}
