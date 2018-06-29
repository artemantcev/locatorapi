package ee.lagunemine.locatorapi.calculator;

import ee.lagunemine.locatorapi.model.PositionRecord;

import java.util.List;

public interface Calculator {
    void calculate(List<PositionRecord> recordList);
}
