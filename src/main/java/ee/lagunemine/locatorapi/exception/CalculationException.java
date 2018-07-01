package ee.lagunemine.locatorapi.exception;

/**
 * Exception class for any errors related to calculation of trilateration algorithm.
 *
 * @author Artem Antcev
 */
public class CalculationException extends Exception {
    private String message;

    public CalculationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
