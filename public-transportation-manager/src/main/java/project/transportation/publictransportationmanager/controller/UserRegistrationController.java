package project.transportation.publictransportationmanager.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.dto.UserDTO;
import project.transportation.publictransportationmanager.model.Useri;
import project.transportation.publictransportationmanager.service.RegistrationService;
import project.transportation.publictransportationmanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class UserRegistrationController {

    private final UserService userService;
    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody UserDTO request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping
    public ResponseEntity<List<Useri>> getAllUsers() {
        List<Useri> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Useri> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Useri> updateUser(@RequestBody Useri useri) {
        Useri updateUser = userService.updateUser(useri);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
}
