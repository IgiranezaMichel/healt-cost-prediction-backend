package com.healt_cost_prediction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarchartDTO {
private String label;
private double price;
private int manufactureYear;
}
