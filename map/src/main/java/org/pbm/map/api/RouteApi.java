package org.pbm.map.api;

import lombok.RequiredArgsConstructor;
import org.pbm.map.service.RouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "map/route")
@RequiredArgsConstructor
public class RouteApi {

    private final RouteService routeService;

    @GetMapping("geometry/{id}")
    public ResponseEntity<String> getGeomForId(@PathVariable String id){
        return ResponseEntity.ok(routeService.findGeometriesById(Long.valueOf(id)));
    }

    @PostMapping("geometry/{id}")
    public ResponseEntity<String> saveGeom(@RequestBody String geom, @PathVariable String id){
        routeService.saveGeom(id, geom);
        return ResponseEntity.ok("{}");
    }
}
