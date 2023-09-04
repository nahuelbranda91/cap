package com.challenge.cap.port.in;

import com.challenge.cap.adapter.in.dto.PriceDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

public interface PriceInputPort {
    @GetMapping("/api/prices")
    ResponseEntity<PriceDTO> getPrice(
            @RequestParam("application_date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime applicationDate,
            @RequestParam("product_id")
            long productId,
            @RequestParam("brand_id") long brandId);
}
