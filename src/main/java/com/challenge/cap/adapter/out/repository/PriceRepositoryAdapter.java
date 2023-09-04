package com.challenge.cap.adapter.out.repository;

import com.challenge.cap.adapter.out.entity.PriceEntity;
import com.challenge.cap.port.out.PriceRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {
    @Autowired
    PriceJpaRepository priceJpaRepository;
    @Override
    public Optional<PriceEntity> getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceJpaRepository
                .findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        brandId,
                        productId,
                        applicationDate,
                        applicationDate);
    }
}
