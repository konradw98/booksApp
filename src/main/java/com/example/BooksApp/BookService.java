package com.example.BooksApp;

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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Service
public class BookService {

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
            Book book= new Book();
            for(int i=0;i<books.size();i++){
                System.out.println("BOOK ID="+books.get(i).getId());
                if(books.get(i).getId().equals(bookId)){
                    book=books.get(i);
                }
            }
            if(book.getId()==null){
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "entity not found"
                );
            }
           return book;
    }

    public List<Book> findBooksByCategory(String category) throws IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/books.json"));
        List<Book> books = Arrays.asList(gson.fromJson(reader, Book[].class));
        reader.close();
        List<Book> foundBooks= new LinkedList<>();
        for(int i=0;i<books.size();i++){
            if(books.get(i).getVolumeInfo().categories==null) continue;
            if(books.get(i).getVolumeInfo().categories.contains(category)){
                foundBooks.add(books.get(i));
            }
        }
        return  foundBooks;

    }
}
