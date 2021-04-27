package project.transportation.publictransportationmanager.controller;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.BusDTO;
import project.transportation.publictransportationmanager.service.BookingService;
import project.transportation.publictransportationmanager.service.BusService;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BookingService bookingService;

    @PostMapping("/many")
    @ResponseBody
    public String newacc(@RequestBody List<BusDTO> acc) {
        System.out.println("in controller");
        return busService.add(acc);
    }

    @PostMapping
    @ResponseBody
    public String saveAndUpdateBus(@RequestBody BusDTO busDTO) {
        return busService.saveAndUpdateBus(busDTO);
    }

    @GetMapping(value = "/{id}")
    public BusDTO getId(@PathVariable(value = "id") Long id) {
        return busService.get(id);
    }

    @GetMapping()
    public List<BusDTO> getAll() {
        return busService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Long id) {
        return busService.delete(id);
    }

    @DeleteMapping()
    @ResponseBody
    public String deleteByBody(@RequestBody List<BusDTO> bus) {
        return busService.delete(bus);
    }

    @PostMapping("/{busId}/{from}/{to}")
    @ResponseBody
    public int availableSeats(@PathVariable("busId") Long busId, @PathVariable("from") Long sourceId, @PathVariable("to") Long destinationId, @RequestBody LocalDate dateOfJourney) {
        return busService.availableSeats(busId, sourceId, destinationId, dateOfJourney);
    }
}
