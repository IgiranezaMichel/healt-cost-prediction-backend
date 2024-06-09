package com.healt_cost_prediction.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private String id;
    private String name;
    private String category;

public ProductDTO(UUID id, String name, String category) {
        this.id = id.toString();
        this.name = name;
        this.category = category;
    }

public ProductDTO(String name, String category) {
    this.name = name;
    this.category = category;
};
}
