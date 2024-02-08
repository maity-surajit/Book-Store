package com.bookstore.store.service;

import com.bookstore.store.entity.Book;
import com.bookstore.store.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public void save(Book b) {
        Optional<Book> bookOptional = bRepo.findById(b.getId());

        if(!bookOptional.isPresent()) {
            bRepo.save(b);
        } else{
            Book book = bookOptional.get();
            book.setName(b.getName());
            book.setAuthor(b.getAuthor());
            book.setPrice(b.getPrice());
            bRepo.save(book);
        }

    }

    public List<Book> getAllBooks() {
        return bRepo.findAllByOrderByIdAsc();
    }

    public Book getBookById(int id) {
        return bRepo.findById(id).get();
    }

    public void deleteBookById(int id) {
        bRepo.deleteById(id);
    }

}
