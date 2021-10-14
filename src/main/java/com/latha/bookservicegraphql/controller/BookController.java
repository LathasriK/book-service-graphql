package com.latha.bookservicegraphql.controller;

import com.latha.bookservicegraphql.model.Book;
import com.latha.bookservicegraphql.model.BookResponse;
import com.latha.bookservicegraphql.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest/bookstore")
public class BookController {
    @Autowired
    public BookRepo bookService;

    @PostMapping("/addBook")
    public BookResponse addBook(@RequestBody Book book) {
        BookResponse response = new BookResponse();

        if (book.getBookId().isEmpty()) {
            response.setMessage("Cant add book! Id number is empty...");
        } else {
            bookService.save(book);
            response.setMessage("Book added!");
            response.setBook(book);
        }
        return response;
    }

    @GetMapping("/getall")
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/getbook/{bookId}")
    public Optional<Book> getBookById(@PathVariable String bookId) {
        return bookService.findById(bookId);
    }
}