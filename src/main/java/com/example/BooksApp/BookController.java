package com.example.BooksApp;

import com.example.BooksApp.models.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    @GetMapping("/books/{bookId}")
    public Book findBookById(@PathVariable String bookId) throws IOException {
        Book book=bookService.findBookById(bookId);
        return book;
    }

    @GetMapping("books/category/{category}")
    public List<Book> findBooksByCategory(@PathVariable String category) throws IOException {
        List<Book> books=bookService.findBooksByCategory(category);

        return books;
    }


    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
