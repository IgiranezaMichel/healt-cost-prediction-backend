package com.healt_cost_prediction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PredictionDTO {
private String predictionResult;
private String fromDate;
private String toDate;
}
