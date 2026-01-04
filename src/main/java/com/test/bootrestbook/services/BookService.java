package com.test.bootrestbook.services;

import com.test.bootrestbook.dao.BookRepository;
import com.test.bootrestbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {
//    private static List<Book> list = new ArrayList<>();
//
//    static{
//        list.add(new Book(1, "ABC", "ABC"));
//        list.add(new Book(2, "DEF", "ABC"));
//        list.add(new Book(3, "XYZ", "ABC"));
//    }

    @Autowired
    public BookRepository bookRepository;

    public List<Book> getAllBooks(){
        List<Book> list = (List<Book>)this.bookRepository.findAll();
        return list;
    }

    public Book getBookById(int id){
        Book book = null;
        try {
            //book = list.stream().filter(e -> e.getId() == id).findFirst().get();
            book = this.bookRepository.findById(id);
        }catch(Exception e){}
        return book;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
        //list.add(b);
    }

    public void deleteBook(int id){
        //list = list.stream().filter(book->book.id != id).collect(Collectors.toList());
        //return list;
        this.bookRepository.deleteById(id);

    }

    public void updateBook(Book book, int id){
//        list = list.stream().map(b->{
//            if(b.getId() == id){
//                b.setName(book.getName());
//                b.setAuthor(book.getAuthor());
//            }
//            return b;
//        }).collect(Collectors.toList());
        book.setId(id);
        this.bookRepository.save(book);
    }
}
