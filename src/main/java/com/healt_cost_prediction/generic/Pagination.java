package com.healt_cost_prediction.generic;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagination<T>{
private List<T>content;
private int pageNumber;
private int pageSize;
}
