package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.StationDTO;
import project.transportation.publictransportationmanager.model.Bus;
import project.transportation.publictransportationmanager.service.StationService;

import java.util.List;

@RestController
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationService stationService;


    @PostMapping
    @ResponseBody
    public String newStations(@RequestBody List<StationDTO> acc) {
        return stationService.add(acc);
    }

    @GetMapping(value = "/available/{id1}/{id2}")
    public List<Bus> getAvailable(@PathVariable(value = "id1") Long source,
                                  @PathVariable(value = "id2") Long destination) {
        return stationService.findBus(source, destination);
    }

    @GetMapping(value = "/{id}")
    public StationDTO getById(@PathVariable(value = "id") Long id) {
        return stationService.get(id);
    }

    @GetMapping()
    public List<StationDTO> getAll() {
        return stationService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Long id) {
        return stationService.delete(id);
    }

    @DeleteMapping()
    @ResponseBody
    public String deleteByBody(@RequestBody List<StationDTO> acc) {
        return stationService.delete(acc);
    }
}
