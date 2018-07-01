package ee.lagunemine.locatorapi.calculator;

import ee.lagunemine.locatorapi.exception.CalculationException;
import ee.lagunemine.locatorapi.model.PositionRecord;
import ee.lagunemine.locatorapi.model.StationMobile;

import java.util.List;

/**
 * This functional interface is intended for trilateration calculators.
 *
 * @author Artemy Antcev
 */
public interface Calculator {
    /**
     * Calculate the data based on position records.
     *
     * @param recordList map of PositionRecord entities
     * @param mobileStation mobile station entity which should be updated
     */
    void calculate(List<PositionRecord> recordList, StationMobile mobileStation) throws CalculationException;
}
