package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;

public interface StationService {
    StationMobile getMobileStation(int stationId);
    StationBase createBaseStation();
    void updateMobileStations();
}
