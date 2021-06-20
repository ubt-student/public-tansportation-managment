package org.pbm.map.api;

import lombok.RequiredArgsConstructor;
import org.pbm.map.service.MunicipalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "map/municipality")
@RequiredArgsConstructor
public class MunicipalityApi {

    private final MunicipalityService municipalityService;

    @GetMapping("geometry")
    public ResponseEntity<String> getGeom(){
        return ResponseEntity.ok(municipalityService.findAllGeometries());
    }
}
