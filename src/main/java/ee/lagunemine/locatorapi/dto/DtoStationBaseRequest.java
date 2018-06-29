package ee.lagunemine.locatorapi.dto;

import java.util.ArrayList;

public class DtoStationBaseRequest {
    private int baseStationId;
    private ArrayList<DtoStationBaseRequest.DtoMobileStation> mobileStations;

    private class DtoMobileStation {
        private int mobileStationId;
        private double distance;

        public int getMobileStationId() {
            return mobileStationId;
        }

        public void setMobileStationId(int mobileStationId) {
            this.mobileStationId = mobileStationId;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }

    public int getBaseStationId() {
        return baseStationId;
    }

    public void setBaseStationId(int baseStationId) {
        this.baseStationId = baseStationId;
    }

    public ArrayList<DtoMobileStation> getMobileStations() {
        return mobileStations;
    }

    public void setMobileStations(ArrayList<DtoMobileStation> mobileStations) {
        this.mobileStations = mobileStations;
    }
}
