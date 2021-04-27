package project.transportation.publictransportationmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.model.Account;
import project.transportation.publictransportationmanager.repo.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * @param acc
     * @return
     */
    public String add(List<Account> acc) {
        System.out.println("in account add");
        for (Account a : acc) {
            accountRepository.save(a);
        }
        return "save completed";
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> get(Long id) {
        System.out.println("Account get");
        return accountRepository.findById(id);
    }

    public String delete(Long id) {
        System.out.println("Account delete");
        accountRepository.deleteById(id);
        return "Successful delete";
    }

    public String delete(List<Account> accounts) {
        System.out.println("Account delete all");
        accountRepository.deleteAll(accounts);
        return "Multiple deletion successful";
    }
}
