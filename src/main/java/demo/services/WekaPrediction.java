package demo.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.modal.UserPrediction;
import demo.modal.UserPredictionRepository;
import jakarta.annotation.PostConstruct;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import java.util.List;
@Service
public class WekaPrediction {

    @Autowired
    private UserPredictionRepository userPredictionRepository;

    private Classifier model;
    private Instances datasetStructure;

    @PostConstruct
    public void init() throws Exception {
        // Fetch data from the database
        List<UserPrediction> userPredictions = userPredictionRepository.findAll();

        // Define attributes
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("age"));
        attributes.add(new Attribute("year"));
        ArrayList<String> artistValues = new ArrayList<>();
        for (UserPrediction up : userPredictions) {
            if (!artistValues.contains(up.getArtist())) {
                artistValues.add(up.getArtist());
            }
        }
        attributes.add(new Attribute("artist", artistValues));

        // Create dataset
        Instances data = new Instances("UserPredictions", attributes, userPredictions.size());
        data.setClassIndex(data.numAttributes() - 1);

        // Populate dataset
        for (UserPrediction userPrediction : userPredictions) {
            Instance instance = new DenseInstance(data.numAttributes());
            instance.setValue(attributes.get(0), userPrediction.getAge());
            instance.setValue(attributes.get(1), userPrediction.getYear());
            instance.setValue(attributes.get(2), userPrediction.getArtist());
            data.add(instance);
        }

        // Train the model
        model = new J48(); // Using J48 decision tree for classification
        model.buildClassifier(data);

        // Save the dataset structure for future use
        datasetStructure = new Instances(data, 0);
    }

    public String predict(int age, int year) throws Exception {
        // Create a new instance for prediction
        Instance instance = new DenseInstance(datasetStructure.numAttributes());
        instance.setDataset(datasetStructure);
        instance.setValue(0, age);
        instance.setValue(1, year);

        // Make prediction
        double result = model.classifyInstance(instance);
        return datasetStructure.classAttribute().value((int) result);
    }
}
