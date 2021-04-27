package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.SeatDTO;
import project.transportation.publictransportationmanager.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<SeatDTO> getAllSeat() {
        System.out.println("in seat get all");
        return seatService.getAll();
    }

    @GetMapping("/{id}")
    public SeatDTO getSeat(@PathVariable Long id) {
        return seatService.get(id);
    }

    @PostMapping
    @ResponseBody
    public String addSeats(@RequestBody SeatDTO seat) {
        return seatService.add(seat);
    }

    @PostMapping("/{id}")
    @ResponseBody
    public String addOrUpdateSeat(@PathVariable Long id, @RequestBody SeatDTO seat) {
        return seatService.addOrUpdateSeat(id, seat);
    }

    @DeleteMapping(value = "/{id}")
    public String deleteSeatById(@PathVariable Long id) {
        return seatService.delete(id);
    }

    @DeleteMapping
    @ResponseBody
    public String deleteSeats(@RequestBody List<SeatDTO> seatDto) {
        return seatService.delete(seatDto);
    }
}
