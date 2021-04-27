package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.CancellationDTO;
import project.transportation.publictransportationmanager.service.CancellationService;

import java.util.List;

@RestController
@RequestMapping("/cancel")
public class CancellationController {

    @Autowired
    CancellationService cancellationService;

    @GetMapping
    public List<CancellationDTO> getAllCancellation() {
        return cancellationService.getAll();
    }

    @GetMapping("/{cancelId}")
    public String getCancellation(@PathVariable Long cancelId) {
        return cancellationService.getById(cancelId);
    }

    @PostMapping
    @ResponseBody
    public String addCancellation(@RequestBody CancellationDTO cancellationDTO) {
        System.out.println("in add cancel ticket");
        return cancellationService.add(cancellationDTO);
    }

    @DeleteMapping("/all")
    public String deleteAll() {
        return cancellationService.deleteAll();
    }

    @DeleteMapping
    @ResponseBody
    public String deleteCancellation(@RequestBody CancellationDTO cancellationDTO) {
        return cancellationService.delete(cancellationDTO);
    }
}