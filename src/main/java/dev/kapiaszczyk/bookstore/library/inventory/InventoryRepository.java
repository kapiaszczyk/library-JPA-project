package dev.kapiaszczyk.bookstore.library.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByLibraryLibraryName(String libraryName);
}
