package com.healt_cost_prediction.modal;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
 @Id
 @UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
private String category;
@OneToMany(cascade =CascadeType.ALL,targetEntity = ProductDetail.class,mappedBy = "product")
private List<ProductDetail>productDetails;
public Product(String name, String category) {
    this.name = name;
    this.category = category;
}
public Product(UUID id, String name, String category) {
    this.id = id;
    this.name = name;
    this.category = category;
}
}
