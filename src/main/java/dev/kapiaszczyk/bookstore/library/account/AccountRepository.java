package dev.kapiaszczyk.bookstore.library.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByLibraryUserId(Long id);
    Object findByNumber(String number);
}
