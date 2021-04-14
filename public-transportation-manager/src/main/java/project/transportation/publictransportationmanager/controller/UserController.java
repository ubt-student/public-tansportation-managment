package project.transportation.publictransportationmanager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.model.Useri;
import project.transportation.publictransportationmanager.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Useri>> getAllUsers(){
        List<Useri> users = userService.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Useri> getEmployeeById(@PathVariable("id") Long id){
        Useri useri = userService.findUserById(id);
        return new ResponseEntity<>(useri, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Useri> addUser(@RequestBody Useri useri){
        Useri newUseri = userService.addUser(useri);
        return new ResponseEntity<>(newUseri, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Useri> updateUser(@RequestBody Useri useri){
        Useri updateUser = userService.updateUser(useri);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Useri> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
