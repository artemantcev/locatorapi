package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.calculator.Calculator;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    private Calculator calculator;

    public StationService(Calculator calculator) {
        this.calculator = calculator;
    }

    //public void update
}
