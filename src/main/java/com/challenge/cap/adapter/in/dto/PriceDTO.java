package com.challenge.cap.adapter.in.dto;

import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDTO(
        Long productId,
        Long brandId,
        Integer priceList,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer priority,
        BigDecimal price,
        String currency
) {
}
