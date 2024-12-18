package com.hexaware.BookCollection.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hexaware.BookCollection.Exception.BookAlreadyExistsException;
import com.hexaware.BookCollection.Exception.BookNotFoundException;
import com.hexaware.BookCollection.entity.Book;
import com.hexaware.BookCollection.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return repository.findById(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN: " + isbn));
    }

    @Override
    public Book addBook(Book book) {
        if (repository.existsById(book.getIsbn())) {
            throw new BookAlreadyExistsException("Book with ISBN: " + book.getIsbn() + " already exists.");
        }
        return repository.save(book);
    }

    @Override
    public Book updateBook(String isbn, Book updatedBook) {
        Book existingBook = getBookByIsbn(isbn);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setPublicationYear(updatedBook.getPublicationYear());
        return repository.save(existingBook);
    }

    @Override
    public void deleteBook(String isbn) {
        if (!repository.existsById(isbn)) {
            throw new BookNotFoundException("Cannot delete. Book not found with ISBN: " + isbn);
        }
        repository.deleteById(isbn);
    }
}
