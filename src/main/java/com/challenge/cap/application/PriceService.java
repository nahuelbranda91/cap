package com.challenge.cap.application;

import com.challenge.cap.domain.model.Price;

import java.time.LocalDateTime;


public interface PriceService {
    Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
