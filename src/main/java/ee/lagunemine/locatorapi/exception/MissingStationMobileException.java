package ee.lagunemine.locatorapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Should be thrown when the system doesn't know anything about the mobile station yet.
 *
 * @author Artemy Antcev
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissingStationMobileException extends Exception {
}
