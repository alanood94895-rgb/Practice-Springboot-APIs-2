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
    public Book findById(
            @RequestParam int id) {
        for (Book b : LibraryController.bookList) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/find-by-name")
    public Book findByName(
            @RequestParam String name) {

        for (Book b : LibraryController.bookList) {
            if (b.getName().equalsIgnoreCase(name)) {
                return b;
            }
        }
        return null;
    }


    @GetMapping("/search-msg")
    public String searchWithMessage(
            @RequestParam int id) {
        for (Book b : LibraryController.bookList) {
            if (b.getId() == id) {
                return "Found: " + b.getName();
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

        for (Author a : LibraryController.authorList) {
            if (a.getId() == authorId) {
                authorExists = true;
                break;
            }
        }

        if (authorExists) {
            Book book = new Book(id, name, authorId);
            LibraryController.bookList.add(book);
            return "Book added successfully!";
        }

        return "Error: Author ID " + authorId + " does not exist. Book not added.";
    }


    @GetMapping("/author-report")
    public String authorReport(
            @RequestParam String authorName) {
        Author foundAuthor = null;
        for (Author a : LibraryController.authorList) {
            if (a.getName().equalsIgnoreCase(authorName)) {
                foundAuthor = a;
                break;
            }
        }

        if (foundAuthor == null) {
            return "Error: Author not found.";
        }

        StringBuilder report = new StringBuilder();
        report.append("Author Report\n");
        report.append("Name: ").append(foundAuthor.getName()).append("\n");
        report.append("Biography: ").append(foundAuthor.getBiography()).append("\n");
        report.append("Books Written:\n");

        boolean hasBooks = false;

        for (Book b : LibraryController.bookList) {
            if (b.getAuthorId() == foundAuthor.getId()) {
                report.append("- ").append(b.getName()).append("\n");
                hasBooks = true;
            }
        }

        if (!hasBooks) {
            report.append("None");
        }

        return report.toString();
    }
}