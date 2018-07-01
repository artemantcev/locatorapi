package ee.lagunemine.locatorapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class PositionRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "Base station is required to write a record")
    @ManyToOne(fetch = FetchType.EAGER)
    private StationBase stationBase;

    @NotNull(message = "Mobile station is required to write a record")
    @ManyToOne(fetch = FetchType.EAGER)
    private StationMobile stationMobile;

    @NotNull(message = "Distance is required to write a record")
    private double distance;

    public Integer getId() {
        return id;
    }

    public StationBase getStationBase() {
        return stationBase;
    }

    public PositionRecord setStationBase(StationBase stationBase) {
        this.stationBase = stationBase;

        return this;
    }

    public StationMobile getStationMobile() {
        return stationMobile;
    }

    public PositionRecord setStationMobile(StationMobile stationMobile) {
        this.stationMobile = stationMobile;

        return this;
    }

    public double getDistance() {
        return distance;
    }

    public PositionRecord setDistance(double distance) {
        this.distance = distance;

        return this;
    }
}
