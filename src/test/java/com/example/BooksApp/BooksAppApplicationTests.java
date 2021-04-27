package com.example.BooksApp;

import com.example.BooksApp.models.Author;
import com.example.BooksApp.models.Book;
import com.example.BooksApp.models.BookFinal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BooksAppApplicationTests {

    @Autowired
    private BookService service;

    @Autowired
    private BookController controller;

    //BOOK SERVICE TEST
    @Test
    void findBookByIdTest() throws IOException {
        String isbn = "9781575211534";
        String title = "Java unleashed";
        Book book = service.findBookByIsbn(isbn);
        assertEquals(book.getVolumeInfo().getTitle(), title);
    }

    @Test
    void findBookByCategoryTest() throws IOException {
        String category = "Java";
        int volume = 2;
        List<Book> books = service.findBooksByCategory(category);
        assertEquals(books.size(), volume);
    }

    @Test
    void findBookByPageTest() throws IOException {
        int pageNumber = 300;
        Book books = service.findBookByPage(pageNumber);
        String title = "The Religion of Java";
        assertEquals(books.getVolumeInfo().getTitle(), title);
    }

    @Test
    void findBooksForMonthTest() throws IOException {
        int bigNumber1 = 100;
        int bigNumber2 = 100;
        List<Book> books = service.findBestBooksForMonth(bigNumber1, bigNumber2);
        int numOfBooks = 2;
        assertEquals(books.size(), numOfBooks);

    }

    //BOOK CONTROLLER TEST
    @Test
    void getBookByIsbnTest() throws IOException {
        String isbn = "9781575211534";
        String title = "Java unleashed";
        BookFinal book = controller.getBookByIsbn(isbn);
        assertEquals(book.getTitle(), title);
    }

    @Test
    void getBookByCategoryTest() throws IOException {
        String category = "Java";
        int volume = 2;
        List<BookFinal> books = controller.getBooksByCategory(category);
        assertEquals(books.size(), volume);
    }

    @Test
    void getBookByVolumeTest() throws IOException {
        int pageNumber = 300;
        BookFinal books = controller.getBookByVolume(pageNumber);
        String title = "The Religion of Java";
        assertEquals(books.getTitle(), title);
    }

    @Test
    void getBooksForMonthTest() throws IOException {
        int bigNumber1 = 100;
        int bigNumber2 = 100;
        List<BookFinal> books = controller.getBestBooksForMonth(bigNumber1, bigNumber2);
        int numOfBooks = 2;
        assertEquals(books.size(), numOfBooks);

    }

    @Test
    void getAuthorsTest() throws IOException {
        List<Author> authors = controller.getAuthors();
        double lowestRating = 1.0;
        assertEquals(authors.get(0).getRating(), lowestRating);
    }

    @Test
    void getRecentlyViewedBooksTest() throws IOException {
        List<BookFinal> book = controller.getRecentlyViewedBooks();
        assertEquals(book.size(), 0);

    }
}
