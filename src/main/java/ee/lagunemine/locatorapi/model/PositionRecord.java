package ee.lagunemine.locatorapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class PositionRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Base station is required to write a record")
    @ManyToOne(fetch = FetchType.EAGER)
    private StationBase stationBase;

    @NotBlank(message = "Mobile station is required to write a record")
    @ManyToOne(fetch = FetchType.EAGER)
    private StationMobile stationMobile;

    @NotBlank(message = "Distance is required to write a record")
    private double distance;

    public Integer getId() {
        return id;
    }

    public StationBase getStationBase() {
        return stationBase;
    }

    public void setStationBase(StationBase stationBase) {
        this.stationBase = stationBase;
    }

    public StationMobile getStationMobile() {
        return stationMobile;
    }

    public void setStationMobile(StationMobile stationMobile) {
        this.stationMobile = stationMobile;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
