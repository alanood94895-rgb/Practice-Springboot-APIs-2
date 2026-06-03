package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.example.traineeapp.LibraryController.authorList;
import static com.example.traineeapp.LibraryController.bookList;

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
    @GetMapping("/all-books")
    public List<Book> getAllBooks() {
        return books;

    }
    @GetMapping("/find-by-id")
    public Book findById(
            @RequestParam int id) {

        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }
    @GetMapping("/find-by-name")
    public Book findByName(
            @RequestParam String name){
        for (Book b : books){
            if (b.getName().equalsIgnoreCase(name)){
                return b;
            }
        }
        return null;
    }
    @GetMapping("/search-msg")
    public String searchWithMessage(
            @RequestParam int id) {

        for (Book book : books) {
            if (book.getId() == id) {
                return "Found: " + book.getName();
            }
        }

        return "Sorry, that book ID is not available.";
    }

    @GetMapping("/add-relational-book")
    public String addRelationalBook(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam int authorId) {

        boolean authorExists = false;
        for (Author a : authorList) {
            if (a.getId() == authorId) {
                authorExists = true;
                break;
            }
        }

        if (authorExists) {
            Book book = new Book(id, name, authorId);
            bookList.add(book);
            return "Book added successfully!";
        }

        return "Error: Author ID " + authorId + " does not exist. Book not added.";
    }

}



