package com.challenge.cap.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Brand {
    private Long id;
    private String name;

    public Brand() {
        // Constructor sin argumentos
    }
}
