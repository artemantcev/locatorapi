package ee.lagunemine.locatorapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StationBase {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private double positionX;
    private double positionY;

    public StationBase() {}

    public StationBase(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public Integer getId() {
        return id;
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
}
