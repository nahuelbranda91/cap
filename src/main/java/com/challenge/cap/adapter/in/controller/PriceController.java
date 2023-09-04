package com.challenge.cap.adapter.in.controller;

import com.challenge.cap.adapter.in.dto.PriceDTO;
import com.challenge.cap.application.PriceService;
import com.challenge.cap.port.in.PriceInputPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Validated
public class PriceController implements PriceInputPort {
    private final PriceService priceService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PriceController(PriceService priceService, ObjectMapper objectMapper) {
        this.priceService = priceService;
        this.objectMapper = objectMapper;
    }
    public ResponseEntity<PriceDTO> getPrice(LocalDateTime applicationDate, long productId,long brandId){
        return ResponseEntity.ok(
                objectMapper.convertValue(
                        priceService.getPrice(applicationDate,
                            Long.valueOf(productId),
                            Long.valueOf(brandId)),
                        PriceDTO.class)
        );
    }
}
