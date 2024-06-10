package com.healt_cost_prediction.Mapper;

import java.util.function.Function;
import com.healt_cost_prediction.dto.ProductDetailDTO;
import com.healt_cost_prediction.modal.ProductDetail;
public class ProductDetailMapper implements Function<ProductDetail,ProductDetailDTO>{

    @Override
    public ProductDetailDTO apply(ProductDetail t) {
        return new ProductDetailDTO(t.getId(), t.getProduct(), t.getManufacturer(), t.getManufactureDate(), t.getCountry(), t.getQuantity(), t.getUnit(), t.getPrice(), t.getCurrency(), t.getTimeStamp(),t.getQuality());
    }

}
