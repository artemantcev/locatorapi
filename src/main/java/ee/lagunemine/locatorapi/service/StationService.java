package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.calculator.Calculator;
import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    private Calculator calculator;

    public StationService(Calculator calculator) {
        this.calculator = calculator;
    }

    protected StationBase createNewStationBase() {
        return null;
    }

    public void createPositionRecords() {
        //...
        //recalculate data for all given mobileStations
        //call Calculator for each mobile station
    }

    public StationMobile getStationMobile(int stationId) {
        return null;
    }
}
