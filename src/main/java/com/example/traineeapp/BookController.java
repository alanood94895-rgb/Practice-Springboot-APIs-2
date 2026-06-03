package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    private static List<Book> books = new ArrayList<>();

    @GetMapping("/add-book")
    public String addBook(
            @RequestParam int id,
            @RequestParam String name) {

        Book book = new Book(id, name);
        books.add(book);

        return "Book added successfully!";
    }
}


