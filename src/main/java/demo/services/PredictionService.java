package demo.services;

import java.util.List;

import moa.classifiers.functions.SGD;
import moa.core.FastVector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yahoo.labs.samoa.instances.Attribute;
import com.yahoo.labs.samoa.instances.DenseInstance;
import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Instances;

import demo.modal.UserPrediction;
import demo.modal.UserPredictionRepository;
@Service
public class PredictionService {
    
    @Autowired
    private UserPredictionRepository repository;

    private SGD sgd;
    private int currentYear;
    private com.yahoo.labs.samoa.instances.InstancesHeader header;

    @SuppressWarnings("unchecked")
    @jakarta.annotation.PostConstruct
    public void init() {
        this.sgd = new SGD();
        this.sgd.prepareForUse();
        this.currentYear = java.time.Year.now().getValue();
        
        // Create attributes
        @SuppressWarnings("rawtypes")
        FastVector attributes = new FastVector();
        attributes.addElement(new Attribute("year"));
        attributes.addElement(new Attribute("artist"));
        attributes.addElement(new Attribute("age"));
        
        Instances dataset = new Instances("UserPrediction", attributes, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);
        header = new com.yahoo.labs.samoa.instances.InstancesHeader(dataset);
        this.sgd.setModelContext(header);
        
        trainModel();
    }

    public void trainModel() {
        List<UserPrediction> data = repository.findAll();
        for (UserPrediction record : data) {
            Instance instance = createInstance(record);
            this.sgd.trainOnInstance(instance);
        }
    }

    public double predictAgeInFiveYears(String artist) {
        DenseInstance instance = new DenseInstance(2);
        instance.setValue(0, currentYear + 5);
        instance.setValue(1, artist.hashCode());
        instance.setDataset(header);
        return this.sgd.getVotesForInstance(instance)[0];
    }

    public void addNewRecord(UserPrediction record) {
        repository.save(record);
        Instance instance = createInstance(record);
        this.sgd.trainOnInstance(instance);
    }
    public void BatchSave(List<UserPrediction> record) {
       List<UserPrediction>response= repository.saveAll(record);
       response.forEach(i->{
        Instance instance = createInstance(i);
        this.sgd.trainOnInstance(instance);
       });
    }
    private Instance createInstance(UserPrediction record) {
        DenseInstance instance = new DenseInstance(3);
        instance.setValue(0, record.getYear());
        instance.setValue(1, record.getArtist().hashCode());
        instance.setValue(2, record.getAge());
        instance.setDataset(header);
        return instance;
    }
}