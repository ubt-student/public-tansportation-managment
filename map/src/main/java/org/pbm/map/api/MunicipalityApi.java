package org.pbm.map.api;

import lombok.RequiredArgsConstructor;
import org.pbm.map.domain.model.Municipality;
import org.pbm.map.service.MunicipalityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "map/municipality")
@RequiredArgsConstructor
public class MunicipalityApi {

    private final MunicipalityService municipalityService;

    @GetMapping()
    public ResponseEntity<List<Municipality>> getMunicipalityList(){
        return ResponseEntity.ok((municipalityService.getAllMunicipalities()));
    }

    @GetMapping("geometry")
    public ResponseEntity<String> getGeom(){
        return ResponseEntity.ok(municipalityService.findAllGeometries());
    }

    @GetMapping("geometry/{code}")
    public ResponseEntity<String> getGeomForCode(@PathVariable String code){
        return ResponseEntity.ok(municipalityService.findGeometriesByCode(Integer.valueOf(code)));
    }
}
