package ee.lagunemine.locatorapi.dto;

import ee.lagunemine.locatorapi.validator.StationBaseConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

/**
 * Data transfer object for incoming API messages received from the base stations.
 *
 * @author Artemy Antcev
 */
public class StationBaseMessageDTO {
    @Positive
    @StationBaseConstraint
    private int stationId;

    @NotEmpty
    @Valid
    private List<DistanceRecordDTO> mobileStations;

    private static class DistanceRecordDTO {
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
