package com.example.BooksApp;

import com.example.BooksApp.models.Author;
import com.example.BooksApp.models.Book;
import com.example.BooksApp.models.IndustryIdentifier;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BookService {
    private LRUCache lruCache;
    private final int dayInMonth = 30;


    public Book findBookByIsbn(String bookIsbn) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();

        Book book = new Book();
        for (int i = 0; i < books.size(); i++) {
            for (IndustryIdentifier identifier : books.get(i).getVolumeInfo().getIndustryIdentifiers()) {
                if (identifier.getType().equals("ISBN_13") && identifier.getIdentifier().equals(bookIsbn)) {
                    book = books.get(i);
                }
            }
        }
        if (book.getId() == null) {
            for (Book value : books) {
                if (value.getId().equals(bookIsbn)) {
                    book = value;
                }
            }
        }
        if (book.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        lruCache.refer(book.getId(), new Date().getTime());
        return book;
    }

    public List<Book> findBooksByCategory(String category) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();

        List<Book> foundBooks = new LinkedList<>();
        for (Book book : books) {
            if (book.getVolumeInfo().getCategories() == null) continue;
            if (book.getVolumeInfo().getCategories().contains(category)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }


    public List<Author> findAuthorsAndRating() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();

        ArrayList<String> authorsList = new ArrayList<>();
        List<Author> authors = new ArrayList<>();

        for (Book book : books) {
            if (book.getVolumeInfo().getAuthors() == null) continue;
            for (int j = 0; j < book.getVolumeInfo().getAuthors().size(); j++) {
                if (!authorsList.contains(book.getVolumeInfo().getAuthors().get(j))) {
                    authorsList.add(book.getVolumeInfo().getAuthors().get(j));
                }
            }
        }
        for (String s : authorsList) {
            double authorRatingSum = 0;
            double counter = 0;
            for (Book book : books) {
                if (book.getVolumeInfo().getAuthors() == null) continue;
                if (book.getVolumeInfo().getAuthors().contains(s) && book.getVolumeInfo().getAverageRating() != 0) {
                    authorRatingSum += book.getVolumeInfo().getAverageRating();
                    counter++;
                }
            }
            double averageRating = authorRatingSum / counter;
            Author author = new Author(s, averageRating);
            authors.add(author);
        }
        authors.sort(Comparator.comparing(Author::getRating));

        return authors;
    }

    public Book findBookByPage(int pageNumber) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();

        Book book = new Book();
        for (Book value : books) {
            if (value.getVolumeInfo().getPageCount() > pageNumber) {
                book = value;
                break;
            }
        }
        return book;
    }

    public List<Book> findBestBooksForMonth(int hours, int pages) throws IOException {
        int pagesInMonth = hours * pages * dayInMonth;
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        List<Book> bookList = new ArrayList<>();
        reader.close();
        for (Book value : books) {
            if (value.getVolumeInfo().getPageCount() < pagesInMonth) {
                bookList.add(value);
            }
        }
        double bestRating = 0;
        for (Book value : bookList) {
            if (bestRating < value.getVolumeInfo().getAverageRating()) {
                bestRating = value.getVolumeInfo().getAverageRating();
            }
        }

        for (Iterator<Book> iterator = bookList.iterator(); iterator.hasNext(); ) {
            Book book = iterator.next();
            if (book.getVolumeInfo().getAverageRating() < bestRating) {
                iterator.remove();
            }
        }
        return bookList;

    }

    public List<Book> findRecentlyViewedBooks() throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        List<String> booksIds = (List<String>) lruCache.getDoublyQueue();
        List<Book> recentlyViewedBooks = new ArrayList<>();

        for (Book book : books) {
            if (booksIds.contains(book.getId())) {
                recentlyViewedBooks.add(book);
            }
        }
        return recentlyViewedBooks;
    }

    public BookService(LRUCache lruCache) {
        this.lruCache = lruCache;
    }

}
