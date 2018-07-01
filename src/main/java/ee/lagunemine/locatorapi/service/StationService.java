package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.model.StationMobile;

public interface StationService {
    StationMobile getMobileStation(int stationId);
    int createBaseStation();
    void updateMobileStations();
}
