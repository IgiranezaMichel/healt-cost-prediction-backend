package com.healt_cost_prediction.prediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healt_cost_prediction.modal.Product;
import com.healt_cost_prediction.modal.ProductDetail;
import com.healt_cost_prediction.repository.ProductDetailRepository;
import com.healt_cost_prediction.repository.ProductRepository;
import weka.classifiers.Classifier;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class PredictionService {

    @Autowired
    private ProductDetailRepository productRepository;
    @Autowired
    private ProductRepository pRepository;
    private Classifier model;
    private Instances datasetStructure;

    public void trainModel(String productId,LocalDate fromDate,LocalDate toDate) throws Exception {

        Product productRes=pRepository.findById(UUID.fromString(productId)).orElse(null);
        List<ProductDetail> products = productRepository.findByProductAndManufactureDateAfterAndManufactureDateBefore(productRes,fromDate,toDate);
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("manufactureDate"));
        attributes.add(new Attribute("price"));
        Instances data = new Instances("ProductPrices", attributes, products.size());
        data.setClassIndex(data.numAttributes() - 1);
        for (ProductDetail product : products) {
            Instance instance = new DenseInstance(data.numAttributes());
            instance.setValue(attributes.get(0), product.getManufactureDate().toEpochDay());
            instance.setValue(attributes.get(1), product.getPrice());
            data.add(instance);
        }

        // Train the model
        model = new LinearRegression();
        model.buildClassifier(data);
        datasetStructure = new Instances(data, 0);
    }

    public double predictPrice(LocalDate futureDate) throws Exception {
        Instance instance = new DenseInstance(datasetStructure.numAttributes());
        instance.setDataset(datasetStructure);
        instance.setValue(0, futureDate.toEpochDay());
        return model.classifyInstance(instance);
    }
}
