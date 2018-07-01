package ee.lagunemine.locatorapi.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StationMobile {
    @Id
    private Integer id;

    private double lastPositionX;
    private double lastPositionY;
    private double lastError;

    public Integer getId() {
        return id;
    }

    /**
     * We're getting the mobile station identifiers from outside,
     * so we don't need an automatic generation and have to set id manually.
     *
     * @param id manually selected identifier for a new entity
     */
    public void setId(int id) {
        this.id = id;
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
