package ee.lagunemine.locatorapi.service;

import ee.lagunemine.locatorapi.calculator.Calculator;
import ee.lagunemine.locatorapi.dto.StationBaseRequestDTO;
import ee.lagunemine.locatorapi.exception.CalculationException;
import ee.lagunemine.locatorapi.model.PositionRecord;
import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;
import ee.lagunemine.locatorapi.repository.PositionRecordRepository;
import ee.lagunemine.locatorapi.repository.StationBaseRepository;
import ee.lagunemine.locatorapi.repository.StationMobileRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {
    private static final String ERROR_LOG_PREFIX_TRANSACTION = "Something went wrong with execution of transaction";

    private Calculator calculator;

    private Logger logger;
    private SessionFactory sessionFactory;
    private StationBaseRepository baseRepository;
    private StationMobileRepository mobileRepository;
    private PositionRecordRepository recordRepository;

    public StationServiceImpl(
            Logger logger,
            Calculator calculator,
            EntityManagerFactory entityManagerFactory,
            StationBaseRepository baseRepository,
            StationMobileRepository mobileRepository,
            PositionRecordRepository recordRepository
    ) {
        this.logger = logger;
        this.calculator = calculator;
        this.baseRepository = baseRepository;
        this.mobileRepository = mobileRepository;
        this.recordRepository = recordRepository;

        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    public int createBaseStationAndGetId(double xCoord, double yCoord) {
        StationBase baseStation = new StationBase(xCoord, yCoord);
        baseRepository.save(baseStation);

        return baseStation.getId();
    }

    private StationMobile createMobileStation(int id) {
        StationMobile mobileStation = new StationMobile();
        mobileStation.setId(id);

        return mobileStation;
    }

    @Override
    public void updateMobileStations(StationBaseRequestDTO requestDTO) throws CalculationException {
        Session session = sessionFactory.openSession();
        StationBase baseStation = this.baseRepository.findById(requestDTO.getStationId()).orElse(null);

        for (StationBaseRequestDTO.DistanceRecordDTO mobileStationData : requestDTO.getMobileStations()) {
            Transaction tx = session.beginTransaction();

            try {
                updateMobileStation(mobileStationData, session, baseStation);
            } catch (CalculationException e) {
                tx.rollback();
                session.close();
                throw e;
            } catch (Exception e) {
                tx.rollback();
                logger.error(ERROR_LOG_PREFIX_TRANSACTION, e);

                continue;
            }

            tx.commit();
        }

        session.close();
    }

    /**
     * Update a particular MobileStation entity.
     *
     * @param mobileStationData DTO object
     * @param session opened Hibernate session object
     * @param baseStation base station entity
     */
    private void updateMobileStation(
            StationBaseRequestDTO.DistanceRecordDTO mobileStationData,
            Session session,
            StationBase baseStation
    ) throws CalculationException {
        StationMobile mobileStation = this.mobileRepository
                .findById(mobileStationData.getStationId()).orElse(null);

        if (mobileStation == null) {
            mobileStation = this.createMobileStation(mobileStationData.getStationId());
        }

        List<PositionRecord> positionRecords = this.recordRepository.findAllByStationMobile(mobileStation);

        PositionRecord newRecord = null;

        for (PositionRecord positionRecord : positionRecords) {
            if (positionRecord.getStationBase().equals(baseStation)) {
                newRecord = positionRecord;
            }
        }

        if (newRecord == null) {
            newRecord = new PositionRecord();
            newRecord.setStationBase(baseStation)
                    .setStationMobile(mobileStation);

            positionRecords.add(newRecord);
        }

        newRecord.setDistance(mobileStationData.getDistance());

        calculator.calculate(positionRecords, mobileStation);

        session.saveOrUpdate(mobileStation);

        // update the record if it existed in the past, do not create a duplicate
        if (newRecord.getId() != null) {
            session.update(newRecord);
        } else {
            session.save(newRecord);
        }
    }

    @Override
    public StationMobile getMobileStation(int stationId) {
        Optional<StationMobile> result = mobileRepository.findById(stationId);

        return result.orElse(null);
    }
}
