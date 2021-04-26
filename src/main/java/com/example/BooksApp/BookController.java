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
        return bookService.findBookById(bookId);
    }

    @GetMapping("books/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) throws IOException {
        return bookService.findBooksByCategory(category);
    }

    @GetMapping("books/authors")
    public List<Author> getAuthors() throws IOException {
        return bookService.findAuthorsAndRating();
    }

    @GetMapping("books/volume/{pageNumber}")
    public Book getBookByVolume(@PathVariable int pageNumber) throws IOException {
        return bookService.findBookByPage(pageNumber);
    }

    @GetMapping("books/hours/{hours}/pages/{pages}")
    public List<Book> getBestBooksForMonth(@PathVariable int hours, @PathVariable int pages) throws IOException {
        return bookService.findBestBooksForMonth(hours,pages);
    }

    @GetMapping("books/recently")
    public List<Book> findRecentlyViewedBooks() throws IOException {
        return bookService.findRecentlyViewedBooks();
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
