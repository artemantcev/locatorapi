package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.*;
import ee.lagunemine.locatorapi.service.StationService;
import ee.lagunemine.locatorapi.validator.StationMobileExists;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.annotation.AnnotatedElementUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.*;

@RestController
@Validated
@RequestMapping("/stations")
class StationController {
    private StationService stationService;
    private ModelMapper mapper;
    private Logger logger;

    private final static String ERROR_INTERNAL = "An internal error has occurred";
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
     * @return single-pair map with id.
     */
    @PostMapping("/base/new")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HashMap<String, Integer> createBaseStation() {
        return new HashMap<String, Integer>() {{
            put("newBaseStationId", stationService.createBaseStation());
        }};
    }

    /**
     * A default error handler, catches any exception.
     * Hides the traces and other internal information from end-user, giving easy-to-read JSON instead.
     *
     * @param request request causing a problem.
     * @param e caught exception.
     * @return map with error data (code, error, path, time) which needs to be turned into JSON response.
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Map<String, String> handleDefaultErrors(HttpServletRequest request, Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus annotation = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);

        // override HTTP status codes for some of built-in exceptions
        if (e.getClass().equals(MissingServletRequestParameterException.class)) {
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (e.getClass().equals(ConstraintViolationException.class)) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else if (annotation != null) {
            httpStatus = annotation.value();
        }

        Map<String, String> error = new HashMap<>();

        error.put("code", httpStatus.toString());
        error.put("error", httpStatus != HttpStatus.INTERNAL_SERVER_ERROR ? e.getMessage() : ERROR_INTERNAL);
        error.put("path", request.getRequestURL().toString());
        error.put("time", new Date().toString());

        logger.error(ERROR_LOG_PREFIX, e);

        return error;
    }
}
