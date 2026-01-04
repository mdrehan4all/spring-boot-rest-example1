package com.test.bootrestbook.controllers;

import com.test.bootrestbook.entities.Book;
import com.test.bootrestbook.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> Books(){
        return ResponseEntity.of(Optional.of(this.bookService.getAllBooks()));
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> book(@PathVariable("id") int id){
        Book book = bookService.getBookById(id);
        if(book == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(200).body(b);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }

    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int id){
        this.bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int id){
        this.bookService.updateBook(book, id);
        return ResponseEntity.ok().body(book);
    }
}
