package ee.lagunemine.locatorapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StationMobile {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private double lastPositionX;

    private double lastPositionY;

    private double lastError;

    public Integer getId() {
        return id;
    }

    public double getLastPositionX() {
        return lastPositionX;
    }

    public void setLastPositionX(double lastPositionX) {
        this.lastPositionX = lastPositionX;
    }

    public double getLastPositionY() {
        return lastPositionY;
    }

    public void setLastPositionY(double lastPositionY) {
        this.lastPositionY = lastPositionY;
    }

    public double getLastError() {
        return lastError;
    }

    public void setLastError(double lastError) {
        this.lastError = lastError;
    }
}
