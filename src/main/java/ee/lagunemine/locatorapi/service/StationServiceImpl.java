package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.calculator.Calculator;
import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;
import org.springframework.stereotype.Service;

@Service
public class StationServiceImpl implements StationService {
    private Calculator calculator;

    public StationServiceImpl(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public StationBase createBaseStation() {
        return null;
    }

    @Override
    public void updateMobileStations() {
        //...
        //recalculate data for all given mobileStations
        //call Calculator for each mobile station
    }

    @Override
    public StationMobile getMobileStation(int stationId) {
        return null;
    }
}
