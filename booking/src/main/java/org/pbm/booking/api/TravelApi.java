package org.pbm.booking.api;

import lombok.RequiredArgsConstructor;
import org.pbm.booking.domain.model.Travel;
import org.pbm.booking.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "booking/travel")
@RequiredArgsConstructor
public class TravelApi {

    private final TravelService travelService;

    @GetMapping
    public List<Travel> getTravels() {
        return travelService.getTravels();
    }

    @GetMapping("{municipalityIdFrom}/{municipalityIdTo}")
    public List<Travel> getTravelsByMunicipalityId(@PathVariable String municipalityIdFrom, @PathVariable String municipalityIdTo){
        return  travelService.getTravelsByMunicipalityId(Long.valueOf(municipalityIdFrom), Long.valueOf(municipalityIdTo));
    }

    @PostMapping
    public ResponseEntity<String> registerTravel(@RequestBody Travel travel) {
        travelService.registerTravel(travel);
        return ResponseEntity.ok("{}");
    }
}
