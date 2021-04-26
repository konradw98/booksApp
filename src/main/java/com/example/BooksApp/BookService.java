package com.example.BooksApp;

import com.example.BooksApp.models.Author;
import com.example.BooksApp.models.Book;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class BookService {
    LRUCache lruCache;

    public Book findBookById(String bookId) throws IOException {
        // create Gson instance
        Gson gson = new Gson();
        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        // convert JSON array to list of books
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        // print books
        books.forEach(System.out::println);
        // close reader
        reader.close();
        Book book = new Book();
        for (int i = 0; i < books.size(); i++) {
            System.out.println("BOOK ID=" + books.get(i).getId());
            if (books.get(i).getId().equals(bookId)) {
                book = books.get(i);
            }
        }
        if (book.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        }
        lruCache.refer(book.getId(),new Date().getTime());
        return book;
    }

    public List<Book> findBooksByCategory(String category) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();
        List<Book> foundBooks = new LinkedList<>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getVolumeInfo().getCategories() == null) continue;
            if (books.get(i).getVolumeInfo().getCategories().contains(category)) {
                foundBooks.add(books.get(i));
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

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getVolumeInfo().getAuthors() == null) continue;
            for (int j = 0; j < books.get(i).getVolumeInfo().getAuthors().size(); j++) {
                if (!authorsList.contains(books.get(i).getVolumeInfo().getAuthors().get(j))) {
                    authorsList.add(books.get(i).getVolumeInfo().getAuthors().get(j));
                }
            }
        }
        for (int i = 0; i < authorsList.size(); i++) {
            double authorRatingSum = 0;
            double counter = 0;
            for (int j = 0; j < books.size(); j++) {
                if (books.get(j).getVolumeInfo().getAuthors() == null) continue;
                if (books.get(j).getVolumeInfo().getAuthors().contains(authorsList.get(i)) && books.get(j).getVolumeInfo().getAverageRating() != 0) {
                    authorRatingSum += books.get(j).getVolumeInfo().getAverageRating();
                    counter++;

                }
            }
            double averageRating = authorRatingSum / counter;
            Author author = new Author(authorsList.get(i), averageRating);
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
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getVolumeInfo().getPageCount() > pageNumber) {
                book = books.get(i);
                break;
            }
        }

        return book;
    }

    public List<Book> findBestBooksForMonth(int hours, int pages) throws IOException {
        int pagesInMonth = hours * pages * 30;
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        List<Book> bookList = new ArrayList<>();
        reader.close();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getVolumeInfo().getPageCount() < pagesInMonth) {
                bookList.add(books.get(i));
            }
        }
        double bestRating = 0;
        for (int i = 0; i < bookList.size(); i++) {
            if (bestRating < bookList.get(i).getVolumeInfo().getAverageRating()) {
                bestRating = bookList.get(i).getVolumeInfo().getAverageRating();
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
       List<String> booksIds=(List<String>)lruCache.getDoublyQueue();
       List<Book> recentlyViewedBooks= new ArrayList<>();

       for(Book book : books){
           if(booksIds.contains(book.getId())){
               recentlyViewedBooks.add(book);
           }
       }
       return recentlyViewedBooks;
   }

    public BookService(LRUCache lruCache) {
        this.lruCache = lruCache;
    }

}
