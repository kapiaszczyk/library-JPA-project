package dev.kapiaszczyk.bookstore.library.inventory;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private String inventoryId;

    public Inventory() {
    }

    public String getInventoryId() {
        return inventoryId;
    }
}
