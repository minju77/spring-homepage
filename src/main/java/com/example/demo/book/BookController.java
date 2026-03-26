package com.example.demo.book;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // 도서 검색 페이지
    @GetMapping("/book/search")
    public String search() {
        return "book/bookSearch";
    }

    // 도서 검색 실행
    @GetMapping("/book/search/result")
    public String searchResult(@RequestParam String keyword, Model model) {

        List<Book> books = bookService.searchBooks(keyword);

        model.addAttribute("books", books);

        return "book/bookList";
    }

 // 도서 목록
    @GetMapping("/book/list")
    public String list(Model model){

        model.addAttribute("books", bookService.getAllBooks());

        return "book/bookList";
    }

    // 도서 상세
    @GetMapping("/book/{id:[0-9]+}")
    public String detail(@PathVariable Long id, Model model) {

        Book book = bookService.getBook(id);

        model.addAttribute("book", book);

        return "book/bookDetail";
    }
}