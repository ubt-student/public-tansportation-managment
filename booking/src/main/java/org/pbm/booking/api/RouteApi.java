package org.pbm.booking.api;

import lombok.RequiredArgsConstructor;
import org.pbm.booking.domain.model.Route;
import org.pbm.booking.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "booking/route")
@RequiredArgsConstructor
public class RouteApi {

    private final RouteService routeService;

    @GetMapping
    public ResponseEntity<List<Route>> getRoutes() {
        return ResponseEntity.ok(routeService.getRoutes());
    }

    @PostMapping
    public ResponseEntity<Route> saveRoute(@RequestBody Route route) {
        return ResponseEntity.ok(routeService.saveRoute(route));
    }
}
