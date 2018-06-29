package ee.lagunemine.locatorapi.controller;

import ee.lagunemine.locatorapi.service.StationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stations")
class StationController {
    private StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/get")
    public String getStationMobilePosition() {
        return "hgfd";
    }

    @PostMapping("/update")
    public String updateStationMobilePosition() {
        return "";
    }
}
