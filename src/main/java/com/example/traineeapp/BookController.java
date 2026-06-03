package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @GetMapping("/add-book")
    public String addBook(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int authorId) {

        Book book = new Book(id, name, authorId);
        LibraryController.bookList.add(book);

        return "Book added successfully!";
    }

    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return LibraryController.bookList;
    }

    @GetMapping("/find-by-id")
    public Book findById(@RequestParam int id) {

        for (Book b : LibraryController.bookList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }

    @GetMapping("/find-by-name")
    public Book findByName(@RequestParam String name) {

        for (Book b : LibraryController.bookList) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }

    @GetMapping("/search-msg")
    public String searchWithMessage(@RequestParam int id) {

        for (Book b : LibraryController.bookList) {
            if (b.getId() == id) {
                return "Found: " + b.getName();
            }
        }

        return "Sorry, that book ID is not available.";
    }
}