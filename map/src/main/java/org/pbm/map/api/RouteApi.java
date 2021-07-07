package org.pbm.map.api;

import lombok.RequiredArgsConstructor;
import org.pbm.map.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "map/route")
@RequiredArgsConstructor
public class RouteApi {

    private final RouteService routeService;

    @GetMapping("geometry/{id}")
    public ResponseEntity<String> getGeomFoId(@PathVariable String id){
        return ResponseEntity.ok(routeService.findGeometriesById(Long.valueOf(id)));
    }
}
