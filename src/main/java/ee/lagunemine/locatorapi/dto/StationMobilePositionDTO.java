package ee.lagunemine.locatorapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Data transfer object for mobile station position response.
 * One may think that it's redundant, but we have to keep the response format away from model.
 *
 * @author Artemy Antcev
 */
public class StationMobilePositionDTO {
    @JsonProperty("stationId")
    private int mobileStationId;

    @JsonProperty("xCoord")
    private double positionX;

    @JsonProperty("yCoord")
    private double positionY;

    @JsonProperty("error")
    private double error;

    public int getMobileStationId() {
        return mobileStationId;
    }

    public void setMobileStationId(int mobileStationId) {
        this.mobileStationId = mobileStationId;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }
}
