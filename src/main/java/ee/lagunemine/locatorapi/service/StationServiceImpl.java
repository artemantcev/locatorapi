package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.calculator.Calculator;
import ee.lagunemine.locatorapi.exception.MissingStationMobileException;
import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;
import ee.lagunemine.locatorapi.repository.PositionRecordRepository;
import ee.lagunemine.locatorapi.repository.StationBaseRepository;
import ee.lagunemine.locatorapi.repository.StationMobileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {
    private Calculator calculator;
    private StationBaseRepository baseRepository;
    private StationMobileRepository mobileRepository;
    private PositionRecordRepository recordRepository;

    public StationServiceImpl(
            Calculator calculator,
            StationBaseRepository baseRepository,
            StationMobileRepository mobileRepository,
            PositionRecordRepository recordRepository
    ) {
        this.calculator = calculator;
        this.baseRepository = baseRepository;
        this.mobileRepository = mobileRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public int createBaseStation() {
        StationBase baseStation = new StationBase();
        baseRepository.save(baseStation);

        return baseStation.getId();
    }

    @Override
    public void updateMobileStations() {
        //...
        //recalculate data for all given mobileStations
        //call Calculator for each mobile station
    }

    @Override
    public StationMobile getMobileStation(int stationId) throws MissingStationMobileException {
        Optional<StationMobile> result = mobileRepository.findById(stationId);

        if (!result.isPresent()) {
            throw new MissingStationMobileException();
        }

        return result.get();
    }
}
