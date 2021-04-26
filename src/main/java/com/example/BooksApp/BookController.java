package com.example.BooksApp;

import com.example.BooksApp.models.Author;
import com.example.BooksApp.models.Book;
import com.example.BooksApp.models.BookFinal;
import com.example.BooksApp.models.IndustryIdentifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    @GetMapping("/books/{bookIsbn}")
    public BookFinal getBookByIsbn(@PathVariable String bookIsbn) throws IOException {
        Book book= bookService.findBookById(bookIsbn);
        BookFinal bookFinal=bookToBookFinal(book);
        return bookFinal;
    }


    @GetMapping("books/category/{category}")
    public List<BookFinal> getBooksByCategory(@PathVariable String category) throws IOException {
        List<Book> books=bookService.findBooksByCategory(category);
        List<BookFinal> bookFinals=new ArrayList<>();
        for (Book book:books) {
            bookFinals.add(bookToBookFinal(book));
        }
        return bookFinals;
    }

    @GetMapping("books/authors")
    public List<Author> getAuthors() throws IOException {
        return bookService.findAuthorsAndRating();
    }

    @GetMapping("books/volume/{pageNumber}")
    public BookFinal getBookByVolume(@PathVariable int pageNumber) throws IOException {
        Book book= bookService.findBookByPage(pageNumber);
        BookFinal bookFinal=bookToBookFinal(book);
        return bookFinal;
    }

    @GetMapping("books/hours/{hours}/pages/{pages}")
    public List<BookFinal> getBestBooksForMonth(@PathVariable int hours, @PathVariable int pages) throws IOException {
        List<Book> books=bookService.findBestBooksForMonth(hours,pages);
        List<BookFinal> bookFinals=new ArrayList<>();
        for (Book book:books) {
            bookFinals.add(bookToBookFinal(book));
        }
        return bookFinals;
    }

    @GetMapping("books/recently")
    public List<BookFinal> findRecentlyViewedBooks() throws IOException {
        List<Book> books=bookService.findRecentlyViewedBooks();
        List<BookFinal> bookFinals=new ArrayList<>();
        for (Book book:books) {
            bookFinals.add(bookToBookFinal(book));
        }
        return bookFinals;
    }

    public BookFinal bookToBookFinal(Book book){
        BookFinal bookFinal=new BookFinal();

        for(IndustryIdentifier identifier:book.getVolumeInfo().getIndustryIdentifiers()){
            if(identifier.getType().equals("ISBN_13")){
                bookFinal.setIsbn(identifier.getIdentifier()); }
        }
        if(bookFinal.getIsbn().isEmpty()){
            bookFinal.setIsbn(book.getId());
        }
        bookFinal.setTitle(book.getVolumeInfo().getTitle());
        bookFinal.setSubtitle(book.getVolumeInfo().getSubtitle());
        bookFinal.setPublisher(book.getVolumeInfo().getPublisher());
        bookFinal.setPublishedDate(book.getVolumeInfo().getPublishedDate());
        bookFinal.setDescription(book.getVolumeInfo().getDescription());
        bookFinal.setPageCcount(book.getVolumeInfo().getPageCount());
        bookFinal.setThumbnailUrl(book.getVolumeInfo().getImageLinks().getThumbnail());
        bookFinal.setAverageRating(book.getVolumeInfo().getAverageRating());
        bookFinal.setLanguage(book.getVolumeInfo().getLanguage());
        bookFinal.setPreviewLink(book.getVolumeInfo().getPreviewLink());
        bookFinal.setCategories(book.getVolumeInfo().getCategories());
        bookFinal.setAuthors(book.getVolumeInfo().getAuthors());
        return bookFinal;
    }

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
}
