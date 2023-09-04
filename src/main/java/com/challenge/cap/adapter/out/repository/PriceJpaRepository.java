package com.challenge.cap.adapter.out.repository;

import com.challenge.cap.adapter.out.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {
    Optional<PriceEntity> findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            Long brandId, Long productId, LocalDateTime applicationDate,LocalDateTime applicationDate2);

}

