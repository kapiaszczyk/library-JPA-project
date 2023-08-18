package dev.kapiaszczyk.bookstore.library.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) {
        return new ResponseEntity(accountService.addAccount(account), HttpStatus.OK);
    }

    @RequestMapping("/get")
    public ResponseEntity<Account> getAccount(@RequestParam("id") Long id) {
        return new ResponseEntity(accountService.getAccount(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@RequestParam("id") Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateAccount(@RequestBody Account account) {
        return new ResponseEntity(accountService.updateAccount(account), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {
        return new ResponseEntity(accountService.findAll(), HttpStatus.OK);
    }

}