package com.bookstore.store.controller;

import com.bookstore.store.entity.Book;
import com.bookstore.store.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService service;


    @GetMapping("/")
    public String home() {
        // Thymeleaf redirect to the home.html page over '/' endpoint.
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        // Thymeleaf redirect to the bookRegister.html page over '/book_register' endpoint.
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBooks() {
        // get all the books from service
        List<Book> list = service.getAllBooks();
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("bookList");
//        mv.addObject("book", list);
        // ModelANDView is a class -> 1st Arg: which webpage I want to show. 2nd Arg: which class I need to show. 3rd Arg: class data as list
        return new ModelAndView("bookList","book", list);
    }
    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String myBooks() {
        return "myBooks";
    }

    @RequestMapping("/delete_book/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        service.deleteBookById(id);
        return "redirect:/available_books";
    }

    @RequestMapping("/update_book/{id}")
    public String updateBook(@PathVariable ("id") int id, Model model) {
        Book b = service.getBookById(id);
        model.addAttribute("book", b);
        return "bookEdit";
    }

}