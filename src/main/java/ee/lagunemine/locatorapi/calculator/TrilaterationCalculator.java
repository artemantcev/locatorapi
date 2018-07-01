package ee.lagunemine.locatorapi.calculator;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import ee.lagunemine.locatorapi.model.PositionRecord;
import ee.lagunemine.locatorapi.model.StationBase;
import ee.lagunemine.locatorapi.model.StationMobile;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * This calculator is implemented through existing non-linear least squares trilateration library.
 *
 * @author Artemy Antcev
 */
@Component("trilaterationCalculator")
@Primary
public class TrilaterationCalculator implements Calculator {
    private final static int SPECIAL_DISTANCES_NUMBER = 1;
    private final static int DIMENSIONS = 2;
    private final static int X_COORDINATE_INDEX = 0;
    private final static int Y_COORDINATE_INDEX = 1;

    @Override
    public void calculate(List<PositionRecord> recordList, StationMobile mobileStation) {
        int numberOfBaseStations = recordList.size();

        if (numberOfBaseStations == SPECIAL_DISTANCES_NUMBER) {
            handleCaseWithSinglePoint(recordList, mobileStation);
            return;
        }

        double[][] positions = new double[numberOfBaseStations][DIMENSIONS];
        double[] distances = new double[numberOfBaseStations];

        int i = 0;

        for (PositionRecord record : recordList) {
            StationBase baseStation = record.getStationBase();

            positions[i][X_COORDINATE_INDEX] = baseStation.getPositionX();
            positions[i][Y_COORDINATE_INDEX] = baseStation.getPositionY();
            distances[i] = record.getDistance();

            ++i;
        }

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(positions, distances),
                new LevenbergMarquardtOptimizer()
        );

        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        // the center point is an "answer"
        double[] centroid = optimum.getPoint().toArray();

        // and here we have a radius of possible values which L2 norm is considered to be an error value
        RealVector deviationVector = optimum.getSigma(0);

        mobileStation.setLastPositionX(centroid[X_COORDINATE_INDEX]);
        mobileStation.setLastPositionY(centroid[Y_COORDINATE_INDEX]);
        mobileStation.setLastError(deviationVector.getNorm());
    }

    /**
     * In our calculation we have a special case when server knows
     * only about one PositionRecord entry for mobile station.
     * The trilateration library used here cannot handle this scenario without some help.
     *
     * In this case we set the X/Y values with base station data
     * and error value with distance of this single record.
     *
     * @param recordList List with records, expected that there will be only one record
     * @param mobileStation mobile station entity
     */
    private void handleCaseWithSinglePoint(List<PositionRecord> recordList, StationMobile mobileStation) {
        PositionRecord record = recordList.get(0);
        StationBase baseStation = record.getStationBase();

        mobileStation.setLastPositionX(baseStation.getPositionX());
        mobileStation.setLastPositionY(baseStation.getPositionY());
        mobileStation.setLastError(record.getDistance());
    }
}
