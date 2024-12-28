package com.ust.sales.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ust.sales.model.Sale;

public interface SalesRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT s FROM Sale s WHERE s.saleDate BETWEEN :startDate AND :endDate")
    List<Sale> findSalesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
}
