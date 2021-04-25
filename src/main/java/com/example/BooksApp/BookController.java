package com.example.BooksApp;

import com.example.BooksApp.models.Author;
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
    public Book getBookById(@PathVariable String bookId) throws IOException {
        Book book=bookService.findBookById(bookId);
        return book;
    }

    @GetMapping("books/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) throws IOException {
        List<Book> books=bookService.findBooksByCategory(category);

        return books;
    }

    @GetMapping("books/authors")
    public List<Author> getAuthors() throws IOException {
        List<Author> authors=bookService.findAuthorsAndRating();
        return authors;
    }

    @GetMapping("books/volume/{pageNumber}")
    public Book getBookByVolume(@PathVariable int pageNumber) throws IOException {
        Book book=bookService.findBookByPage(pageNumber);
        return book;
    }

    @GetMapping("books/hours/{hours}/pages/{pages}")
    public List<Book> getBestBooksForMonth(@PathVariable int hours, @PathVariable int pages) throws IOException {
        List<Book> books=bookService.findBestBooksForMonth(hours,pages);
        return books;
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
