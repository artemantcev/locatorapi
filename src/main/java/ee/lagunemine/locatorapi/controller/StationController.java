package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.StationBaseMessageDTO;
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

    @PostMapping("/position")
    @ResponseBody
    public String getStationMobilePosition(@Valid @RequestBody StationBaseMessageDTO message) {
        return "";
    }

    @PostMapping("/update")
    public String updateStationMobilePosition() {
        //TODO
        return "";
    }
}
