package com.test.bootrestbook.services;

import com.test.bootrestbook.dao.BookRepository;
import com.test.bootrestbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookService {
    @Autowired
    public BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> list = (List<Book>)this.bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id){
        Book book = null;
        try {
            book = this.bookRepository.findById(id);
        }catch(Exception e){
            e.getStackTrace();
        }
        return book;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public void deleteBook(int id){
        this.bookRepository.deleteById(id);

    }

    public void updateBook(Book book, int id){
        book.setId(id);
        this.bookRepository.save(book);
    }
}
