package ee.lagunemine.locatorapi.repository;

import ee.lagunemine.locatorapi.model.PositionRecord;
import ee.lagunemine.locatorapi.model.StationMobile;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PositionRecordRepository extends CrudRepository<PositionRecord, Integer> {
    List<PositionRecord> findAllByStationMobile(StationMobile mobileStation);
}
