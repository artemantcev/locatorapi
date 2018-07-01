package ee.lagunemine.locatorapi.dto;

import ee.lagunemine.locatorapi.validator.StationBaseExists;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Data transfer object for incoming API messages received from the base stations.
 *
 * @author Artemy Antcev
 */
public class StationBaseRequestDTO {
    @StationBaseExists
    private int stationId;

    @NotEmpty
    @Valid
    private List<DistanceRecordDTO> mobileStations;

    public static class DistanceRecordDTO {
        @Positive
        private int stationId;

        @Positive
        private double distance;

        public int getStationId() {
            return stationId;
        }

        public void setStationId(int stationId) {
            this.stationId = stationId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public List<DistanceRecordDTO> getMobileStations() {
        return mobileStations;
    }

    public void setMobileStations(List<DistanceRecordDTO> mobileStations) {
        this.mobileStations = mobileStations;
    }
}
