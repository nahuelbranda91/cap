package com.challenge.cap.port.out;

import com.challenge.cap.adapter.out.entity.PriceEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<PriceEntity> getPrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
