package com.healt_cost_prediction.Mapper;

import java.util.function.Function;
import com.healt_cost_prediction.dto.ProductDTO;
import com.healt_cost_prediction.modal.Product;

public class ProductMapper implements Function<Product,ProductDTO>{

    @Override
    public ProductDTO apply(Product t) {
    return new ProductDTO(t.getId(), t.getName(), t.getCategory());
    }

}
