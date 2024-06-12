package demo.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private WekaPrediction predictionService;

    @GetMapping("/predict")
    public String Predictions(@RequestParam int age,@RequestParam int year) throws Exception {
        return predictionService.predict(age,year);
    }
}