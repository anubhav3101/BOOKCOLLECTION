package com.hexaware.BookCollection.service;
import java.util.List;

import com.hexaware.BookCollection.entity.Book;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookByIsbn(String isbn);
    Book addBook(Book book);
    Book updateBook(String isbn, Book updatedBook);
    void deleteBook(String isbn);
}
