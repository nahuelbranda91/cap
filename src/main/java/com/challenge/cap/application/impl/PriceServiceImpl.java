package com.challenge.cap.application.impl;

import com.challenge.cap.application.PriceService;
import com.challenge.cap.domain.exception.ResourceNotFoundException;
import com.challenge.cap.domain.model.Price;
import com.challenge.cap.port.out.PriceRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.time.LocalDateTime;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepositoryPort priceRepositoryPort;

    private ObjectMapper objectMapper;

    @Autowired
    public PriceServiceImpl(PriceRepositoryPort priceRepositoryPort) {
        this.priceRepositoryPort = priceRepositoryPort;
    }

    @Autowired
    public void setObjectMapper(@Autowired ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    public Price getPrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.getPrice(applicationDate,productId,brandId)
                .map(priceEntity -> objectMapper.convertValue(priceEntity, Price.class))
                .orElseThrow(()-> new ResourceNotFoundException("Price not found"));
    }
}
