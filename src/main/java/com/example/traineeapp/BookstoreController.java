package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class BookstoreController {
    private static List<InventoryBook> inventory = new ArrayList<>();


    @GetMapping("/add-inventory-book")
    public String addInventoryBook(
            @RequestParam int bookId,
            @RequestParam String title,
            @RequestParam double price,
            @RequestParam int stock) {

        InventoryBook book = new InventoryBook(bookId, title, price, stock);
        BookstoreController.inventory.add(book);

        return "Book added to inventory successfully!";
    }

}

