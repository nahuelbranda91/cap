package com.challenge.cap.adapter.in.dto;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDTO(
        Long productId,
        String brand,
        LocalDateTime applicationDate,
        Integer priority,
        Double price,
        String currency
) {
}
