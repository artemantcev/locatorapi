package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.exception.MissingStationMobileException;
import ee.lagunemine.locatorapi.model.StationMobile;

public interface StationService {
    StationMobile getMobileStation(int stationId) throws MissingStationMobileException;
    int createBaseStation();
    void updateMobileStations();
}
