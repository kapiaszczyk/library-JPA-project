package dev.kapiaszczyk.bookstore.library.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity<Object> addAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.addAccount(account));
    }

    @RequestMapping("/get")
    public ResponseEntity<Object> getAccount(@RequestParam("id") Long id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @RequestMapping("/getByLibraryUserId")
    public ResponseEntity<Object> getAccountByLibraryUserId(@RequestParam("id") Long id) {
        return ResponseEntity.ok(accountService.getAccountByLibraryUserId(id));
    }

    @RequestMapping("/getByNumber")
    public ResponseEntity<Object> getAccountByNumber(@RequestParam("number") String number) {
        return ResponseEntity.ok(accountService.getAccountByNumber(number));
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteAccount(@RequestParam("id") Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    // TODO: Fix this
    @PutMapping("/update")
    public ResponseEntity updateAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.updateAccount(account));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }

}