package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.StationBaseMessageDTO;
import ee.lagunemine.locatorapi.dto.StationMobilePositionDTO;
import ee.lagunemine.locatorapi.exception.MissingStationMobileException;
import ee.lagunemine.locatorapi.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/stations")
class StationController {
    private StationService stationService;
    private ModelMapper mapper;

    public StationController(StationService stationService, ModelMapper mapper) {
        this.stationService = stationService;
        this.mapper = mapper;
    }

    @PostMapping("/update")
    public String update(@Valid @RequestBody StationBaseMessageDTO message) {
        return "TODO";
    }

    @GetMapping("/mobile/find")
    @ResponseBody
    public StationMobilePositionDTO getMobileStationPosition(int stationId) {
        StationMobilePositionDTO response = new StationMobilePositionDTO();

        try {
            mapper.map(stationService.getMobileStation(stationId), response);
        } catch (MissingStationMobileException e) {

        }

        return response;
    }

    @PostMapping("/base/new")
    public int createBaseStation() {
        return stationService.createBaseStation();
    }
}
