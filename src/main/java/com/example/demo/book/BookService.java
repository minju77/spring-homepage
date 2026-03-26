package com.example.demo.book;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBook(Long id){
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> searchBooks(String keyword){
        return bookRepository.findByTitleContaining(keyword);
    }

}