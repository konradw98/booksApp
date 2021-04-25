package com.example.BooksApp;

import com.example.BooksApp.models.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BookController {
    BookService bookService;

    @GetMapping("/book/{bookId}")
    public Book findBookById(@PathVariable String bookId) throws IOException {
        Book book=bookService.findBookById(bookId);
        return book;
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
