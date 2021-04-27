package project.transportation.publictransportationmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.transportation.publictransportationmanager.model.Transaction;
import project.transportation.publictransportationmanager.service.TransactionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    @ResponseBody
    public String newAcc(@RequestBody List<Transaction> acc) {
        System.out.println("in transaction post");
        return transactionService.add(acc);
    }

    @GetMapping(value = "/{id}")
    public Optional<Transaction> getId(@PathVariable(value = "id") Long id) {
        return transactionService.get(id);
    }

    @GetMapping()
    public List<Transaction> getAll() {
        return transactionService.getAll();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public String deleteById(@PathVariable(value = "id") Long id) {
        return transactionService.delete(id);
    }

    @DeleteMapping()
    @ResponseBody
    public String deleteByBody(@RequestBody List<Transaction> acc) {
        return transactionService.delete(acc);
    }

}
