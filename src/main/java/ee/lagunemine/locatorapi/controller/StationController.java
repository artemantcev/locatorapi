package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.dto.DtoStationMobilePosition;
import ee.lagunemine.locatorapi.service.StationService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stations")
class StationController {
    private StationService stationService;
    private ModelMapper mapper;

    public StationController(StationService stationService, ModelMapper mapper) {
        this.stationService = stationService;
        this.mapper = mapper;
    }

    @GetMapping("/position")
    public String getStationMobilePosition() {
        /*DtoStationMobilePosition dto = this.mapper.map(
                this.stationService.getStationMobile(),
                DtoStationMobilePosition.class
        );*/
        //TODO
        return "";
    }

    @PostMapping("/update")
    public String updateStationMobilePosition() {
        //TODO
        return "";
    }
}
