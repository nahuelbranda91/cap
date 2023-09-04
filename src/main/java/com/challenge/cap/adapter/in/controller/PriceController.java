package com.challenge.cap.adapter.in.controller;

import com.challenge.cap.adapter.in.dto.PriceDTO;
import com.challenge.cap.application.PriceService;
import com.challenge.cap.domain.model.Price;
import com.challenge.cap.port.in.PriceInputPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@Validated
public class PriceController implements PriceInputPort {
    private final PriceService priceService;
    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }
    public ResponseEntity<PriceDTO> getPrice(LocalDateTime applicationDate, long productId, long brandId){
        Price price = priceService.getPrice(applicationDate,
                Long.valueOf(productId),
                Long.valueOf(brandId));

        return ResponseEntity.ok(
                new PriceDTO(price.getProductId(),
                        price.getBrand().getName(),
                        applicationDate,
                        price.getPriority(),
                        price.getPrice(),
                        price.getCurrency())
        );
    }
}
