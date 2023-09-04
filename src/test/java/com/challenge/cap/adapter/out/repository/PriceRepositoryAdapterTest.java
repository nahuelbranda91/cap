package com.challenge.cap.adapter.out.repository;

import com.challenge.cap.adapter.out.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class PriceRepositoryAdapterTest {

    @InjectMocks
    private PriceRepositoryAdapter priceRepositoryAdapter;

    @Mock
    private PriceJpaRepository priceJpaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetPrice_Success() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity priceEntity = new PriceEntity();

        when(priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate)).thenReturn(Optional.of(priceEntity));

        Optional<PriceEntity> result = priceRepositoryAdapter.getPrice(applicationDate, productId, brandId);

        verify(priceJpaRepository).findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate);

        assert result.isPresent();

    }

    @Test
    public void testGetPrice_NotFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        Long productId = 12345L;
        Long brandId = 2L;

        when(priceJpaRepository.findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate)).thenReturn(Optional.empty());

        Optional<PriceEntity> result = priceRepositoryAdapter.getPrice(applicationDate, productId, brandId);

        verify(priceJpaRepository).findFirstByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                brandId,
                productId,
                applicationDate,
                applicationDate);

        assert result.isEmpty();
    }
}
