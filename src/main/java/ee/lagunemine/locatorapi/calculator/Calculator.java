package ee.lagunemine.locatorapi.calculator;

import ee.lagunemine.locatorapi.model.PositionRecord;

import java.util.List;

/**
 * This functional interface is intended for calculating the coordinates and error based on distances.
 *
 * @author Artemy Antcev
 */
public interface Calculator {
    void calculate(List<PositionRecord> recordList);
}
