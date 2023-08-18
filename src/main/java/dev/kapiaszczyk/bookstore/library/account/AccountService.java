package dev.kapiaszczyk.bookstore.library.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Object addAccount(Account account) {
        return accountRepository.save(account);
    }

    public Object getAccount(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    public Object updateAccount(Account account) {
        return accountRepository.save(account);
    }

    public Object findAll() {
        return accountRepository.findAll();
    }
}
