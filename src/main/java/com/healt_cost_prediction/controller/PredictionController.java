package com.healt_cost_prediction.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.healt_cost_prediction.dto.PredictionDTO;
import com.healt_cost_prediction.prediction.PredictionService;
import java.text.DecimalFormat;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/predict")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @GetMapping("product")
    public PredictionDTO predict(
            @RequestParam String productId,@RequestParam String fromDate,@RequestParam String toDate,
            @RequestParam String futureDate) throws Exception {
        LocalDate future = LocalDate.parse(futureDate);
        LocalDate toDate1 = LocalDate.parse(toDate);
        LocalDate fromDate1 = LocalDate.parse(fromDate);
        predictionService.trainModel(productId,fromDate1,toDate1);
        DecimalFormat sDecimalFormat=new DecimalFormat("###.#");
        double result= predictionService.predictPrice(future);
        var resultValue= Math.abs(result);
        return new PredictionDTO(sDecimalFormat.format(resultValue), fromDate, toDate);
    }
}