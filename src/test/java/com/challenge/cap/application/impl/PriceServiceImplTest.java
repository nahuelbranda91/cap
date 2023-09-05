package com.challenge.cap.application.impl;

import com.challenge.cap.adapter.out.entity.BrandEntity;
import com.challenge.cap.adapter.out.entity.PriceEntity;
import com.challenge.cap.domain.exception.ResourceNotFoundException;
import com.challenge.cap.domain.model.Price;
import com.challenge.cap.port.out.PriceRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class PriceServiceImplTest {

    private PriceServiceImpl priceService;

    @Mock
    private PriceRepositoryPort priceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        priceService = new PriceServiceImpl(priceRepository, this.objectMapper());
    }

    @Test
    public void testFindPriceByDateTimeAndProduct() {
        PriceEntity priceMock = createPriceMock();

        Mockito.when(priceRepository.getPrice(
                LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L))
                .thenReturn(Optional.of(priceMock));

        Price result = priceService.getPrice(
                LocalDateTime.parse("2020-06-14T10:00:00"), 35455L, 1L);

        Assertions.assertEquals(35455L, result.getProductId());
    }

    @Test
    public void testFindPriceByDateTimeAndProduct_NotFound() {
        Mockito.when(priceRepository.getPrice(
                        LocalDateTime.parse("2020-06-14T10:00:00"), 12345L, 2L))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            priceService.getPrice(
                    LocalDateTime.parse("2020-06-14T10:00:00"), 12345L, 2L);
        });
    }

    private PriceEntity createPriceMock() {
        return PriceEntity.builder()
                .price(35.50)
                .priceList(1L)
                .currency("EUR")
                .brand(BrandEntity.builder().id(1L).name("ZARA").build())
                .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
                .priority(1)
                .productId(35455L)
                .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
                .build();
    }

    private ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

}
