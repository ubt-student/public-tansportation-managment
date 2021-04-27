package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.model.Account;
import project.transportation.publictransportationmanager.service.AccountService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    @ResponseBody
    public String newAcc(@RequestBody List<Account> acc) {
        return accountService.add(acc);
    }

    @GetMapping(value = "/{id}")
    public Optional<Account> getId(@PathVariable(value = "id") Long id) {
        return accountService.get(id);
    }

    @GetMapping()
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Long id) {
        return accountService.delete(id);
    }

    @DeleteMapping()
    @ResponseBody
    public String deleteByBody(@RequestBody List<Account> acc) {
        return accountService.delete(acc);
    }

}
