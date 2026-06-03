package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookstoreController {

    private static List<InventoryBook> books = new ArrayList<>();

    @GetMapping("/addInventoryBook")
    public String addInventoryBook(
            @RequestParam int bookId,
            @RequestParam String title,
            @RequestParam double price,
            @RequestParam int stock) {

        InventoryBook book = new InventoryBook(bookId, title, price, stock);
        books.add(book);

        return "Book added successfully!";
    }

    @GetMapping("/checkStock")
    public String checkStock(@RequestParam int id) {
        for (InventoryBook book : books) {
            if (book.getBookId() == id) {
                if (book.getStock() > 0) {
                    return "Book Available! Title: " + book.getTitle()
                            + ", Price: $" + book.getPrice();
                } else {
                    return "Sorry, the book '" + book.getTitle() + "' is sold out.";
                }
            }
        }

        return "No book found with ID " + id;
    }

    @GetMapping("/lowStockReport")

    public String lowStockReport(
            @RequestParam
            int threshold) {
        StringBuilder report = new StringBuilder();
        for (InventoryBook book : books) {
            if (book.getStock() <= threshold) {
                report.append("Title: ")
                        .append(book.getTitle())
                        .append(", Stock: ")
                        .append(book.getStock())
                        .append("\n");
            }
        }

        if (report.length() == 0) {
            return "No books currently need reordering.";
        }

        return report.toString();
    }
}