package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

public class BookstoreController {
    private static List<InventoryBook> inventory = new ArrayList<>();
    private List<InventoryBook> books = new ArrayList<>();

    @GetMapping("/addInventoryBook")
    public String addInventoryBook(
            @RequestParam int bookId,
            @RequestParam String title,
            @RequestParam double price,
            @RequestParam int stock) {

        InventoryBook book = new InventoryBook(bookId, title, price, stock);
        BookstoreController.inventory.add(book);

        return "Book added to inventory successfully!";
    }

    @GetMapping("/checkStock")
    public String checkStock(@RequestParam int id) {

        for (InventoryBook book : books) {

            if (book.getBookId() == id) {

                if (book.getStock() > 0) {
                    return "Book Available! Title: "
                            + book.getTitle()
                            + ", Price: $"
                            + book.getPrice();
                } else {
                    return "Sorry, the book '"
                            + book.getTitle()
                            + "' is sold out.";
                }
            }
        }

        return "The bookstore does not carry a book with ID " + id;
    }
}
