package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.dto.StationBaseRequestDTO;
import ee.lagunemine.locatorapi.model.StationMobile;

/**
 * An interface for the business layer handler methods.
 */
public interface StationService {
    /**
     * Return a mobile station JPA entity.
     *
     * @param stationId identifier of StationMobile entity
     * @return JPA entity
     */
    StationMobile getMobileStation(int stationId);

    /**
     *
     * @param xCoord X coordinate of the base station
     * @param yCoord Y coordinate of the base station
     * @return identifier of the new BaseStation entity
     */
    int createBaseStationAndGetId(double xCoord, double yCoord);

    void updateMobileStations(StationBaseRequestDTO requestDTO);
}
