package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.StationBaseMessageDTO;
import ee.lagunemine.locatorapi.dto.StationMobilePositionDTO;
import ee.lagunemine.locatorapi.exception.MissingStationMobileException;
import ee.lagunemine.locatorapi.model.StationMobile;
import ee.lagunemine.locatorapi.service.StationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.annotation.AnnotatedElementUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
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

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public Map<String, String> handleDefaultErrors(HttpServletRequest request, Exception e) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ResponseStatus annotation = AnnotatedElementUtils.findMergedAnnotation(e.getClass(), ResponseStatus.class);

        if (annotation != null) {
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

    @PostMapping("/update")
    public String update(@Valid @RequestBody StationBaseMessageDTO message) {
        return "TODO";
    }

    @GetMapping("/mobile/find")
    @ResponseBody
    public StationMobilePositionDTO getMobileStationPosition(
            @RequestParam(value = "stationId") @NotNull Integer id) throws MissingStationMobileException {
        StationMobilePositionDTO response = new StationMobilePositionDTO();

        StationMobile lol = stationService.getMobileStation(id);

        mapper.map(stationService.getMobileStation(id), response);

        return response;
    }

    @PostMapping("/base/new")
    public int createBaseStation() {
        return stationService.createBaseStation();
    }
}
