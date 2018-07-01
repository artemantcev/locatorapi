package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.*;
import ee.lagunemine.locatorapi.service.StationService;
import ee.lagunemine.locatorapi.validator.StationMobileExists;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@Validated
@RequestMapping("/stations")
class StationController {
    private StationService stationService;
    private ModelMapper mapper;
    private Logger logger;

    private final static String ERROR_INTERNAL = "An internal error has occurred";
    private final static String ERROR_BAD_REQUEST = "Some of your data seems to be missing or having incorrect format";
    private final static String ERROR_VALIDATION = "Some of your input data has incorrect value(s)";
    private final static String ERROR_LOG_PREFIX = "An exception in controller has been occurred";

    public StationController(StationService stationService, ModelMapper mapper, Logger logger) {
        this.stationService = stationService;
        this.mapper = mapper;
        this.logger = logger;
    }

    /**
     * This route allows base stations to update the positions of mobile stations which are known to them.
     * If the mobile station cannot be found, it will be created in the system's data store automatically.
     *
     * @param requestDto DTO with a complex request mapped to it ({baseStationId, mobileStations:{...}})
     * @return status of the operation
     */
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String update(@Valid @RequestBody StationBaseRequestDTO requestDto) {
        stationService.updateMobileStations(requestDto);

        return "TODO";
    }

    /**
     * This route returns the latest information about position of requested mobile station.
     *
     * @param stationId identifier of mobile station.
     * @return DTO instance containing station identifier, X/Y coordinates and error value.
     */
    @GetMapping("/mobile/find")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public StationMobilePositionDTO getMobileStationPosition(@StationMobileExists Integer stationId) {
        StationMobilePositionDTO responseDto = new StationMobilePositionDTO();
        mapper.map(stationService.getMobileStation(stationId), responseDto);

        return responseDto;
    }

    /**
     * This route creates a new StationBase entity and returns JSON with its id to a user.
     *
     * @param xCoord X coordinate of the base station
     * @param yCoord Y coordinate of the base station
     * @return single-pair map with id.
     */
    @PostMapping("/base/new")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HashMap<String, Integer> createBaseStation(@NotNull Double xCoord, @NotNull Double yCoord) {
        return new HashMap<String, Integer>() {{
            put("newBaseStationId", stationService.createBaseStationAndGetId(xCoord, yCoord));
        }};
    }

    /**
     * A default error handler, catches any exception.
     * Hides the traces and other internal information from end-user, giving easy-to-read JSON instead.
     *
     * @param request request causing a problem.
     * @param e caught exception.
     * @return map with error data
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody
    public Map<String, String> handleInternalErrors(HttpServletRequest request, Exception e) {
        logger.error(ERROR_LOG_PREFIX, e);
        return getWrappedError(ERROR_INTERNAL, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle validation exceptions and return 400 HTTP code.
     *
     * @param request request causing a problem.
     * @param e caught exception.
     * @return map with error data
     */
    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public Map<String, String> handleValidationErrors(HttpServletRequest request, Exception e) {
        return getWrappedError(ERROR_VALIDATION, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle bad request (e.g. malformed request, missing parameter) exceptions and return 400 HTTP code.
     *
     * @param request request causing a problem.
     * @param e caught exception.
     * @return map with error data
     */
    @ExceptionHandler(value = {MissingServletRequestParameterException.class, HttpMessageNotReadableException.class})
    @ResponseBody
    public Map<String, String> handleBadRequestErrors(HttpServletRequest request, Exception e) {
        return getWrappedError(ERROR_BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }

    /**
     * Wrap error data into the map.
     *
     * @param errorMessage exception message
     * @param errorCode HTTP status object
     * @return map with error data (code, error, path, time) which needs to be turned into JSON response.
     */
    private Map<String, String> getWrappedError(String errorMessage, HttpStatus errorCode) {
        Map<String, String> error = new HashMap<>();

        error.put("code", errorCode.toString());
        error.put("error", errorMessage);
        error.put("time", new Date().toString());

        return error;
    }
}
