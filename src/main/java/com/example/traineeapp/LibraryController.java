package com.example.traineeapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryController {

    public static List<Author> authorList = new ArrayList<>();
    public static List<Book> bookList = new ArrayList<>();


    @GetMapping("/addAuthor")
    public String addAuthor(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String biography) {

        Author author = new Author(id, name, biography);
        authorList.add(author);

        return "Author added successfully!";
    }

    @GetMapping("/allAuthors")
    public List<Author> getAllAuthors() {
        return authorList;
    }

    @GetMapping("/authorReport")
    public String authorReport(
            @RequestParam String authorName) {

        Author foundAuthor = null;
        for (Author a : authorList) {
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

        for (Book b : bookList) {
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