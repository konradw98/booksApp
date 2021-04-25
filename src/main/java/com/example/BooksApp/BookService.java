package com.example.BooksApp;

import com.example.BooksApp.models.Book;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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

           return book;

    }
}
