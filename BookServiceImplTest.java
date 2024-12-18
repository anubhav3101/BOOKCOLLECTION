package com.hexaware.BookCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hexaware.BookCollection.Exception.BookAlreadyExistsException;
import com.hexaware.BookCollection.Exception.BookNotFoundException;
import com.hexaware.BookCollection.entity.Book;
import com.hexaware.BookCollection.repository.BookRepository;
import com.hexaware.BookCollection.service.BookServiceImpl;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_ReturnsAllBooks() {
        // Arrange
        Book book1 = new Book("123", "Book One", "Author A", 2021);
        Book book2 = new Book("456", "Book Two", "Author B", 2022);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

     
        List<Book> books = bookService.getAllBooks();

        
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookByIsbn_BookExists_ReturnsBook() {
        
        Book book = new Book("123", "Book One", "Author A", 2021);
        when(bookRepository.findById("123")).thenReturn(Optional.of(book));

        
        Book result = bookService.getBookByIsbn("123");

        
        assertNotNull(result);
        assertEquals("Book One", result.getTitle());
        verify(bookRepository, times(1)).findById("123");
    }

    @Test
    void getBookByIsbn_BookNotFound_ThrowsException() {
       
        when(bookRepository.findById("999")).thenReturn(Optional.empty());

        
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn("999"));
        assertEquals("Book not found with ISBN: 999", exception.getMessage());
        verify(bookRepository, times(1)).findById("999");
    }

    @Test
    void addBook_NewBook_ReturnsSavedBook() {
       
        Book book = new Book("123", "Book One", "Author A", 2021);
        when(bookRepository.existsById("123")).thenReturn(false);
        when(bookRepository.save(book)).thenReturn(book);

        
        Book result = bookService.addBook(book);

        
        assertNotNull(result);
        assertEquals("Book One", result.getTitle());
        verify(bookRepository, times(1)).existsById("123");
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void addBook_BookAlreadyExists_ThrowsException() {
     
        Book book = new Book("123", "Book One", "Author A", 2021);
        when(bookRepository.existsById("123")).thenReturn(true);
        Exception exception = assertThrows(BookAlreadyExistsException.class, () -> bookService.addBook(book));
        assertEquals("Book with ISBN: 123 already exists.", exception.getMessage());
        verify(bookRepository, times(1)).existsById("123");
        verify(bookRepository, never()).save(book);
    }

    @Test
    void updateBook_BookExists_ReturnsUpdatedBook() {
        
        Book existingBook = new Book("123", "Old Title", "Old Author", 2020);
        Book updatedBook = new Book("123", "New Title", "New Author", 2023);
        when(bookRepository.findById("123")).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);

    
        Book result = bookService.updateBook("123", updatedBook);

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
        verify(bookRepository, times(1)).findById("123");
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void updateBook_BookNotFound_ThrowsException() {
       
        Book updatedBook = new Book("123", "New Title", "New Author", 2023);
        when(bookRepository.findById("123")).thenReturn(Optional.empty());

       
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.updateBook("123", updatedBook));
        assertEquals("Book not found with ISBN: 123", exception.getMessage());
        verify(bookRepository, times(1)).findById("123");
        verify(bookRepository, never()).save(updatedBook);
    }

    @Test
    void deleteBook_BookExists_SuccessfulDeletion() {
        
        when(bookRepository.existsById("123")).thenReturn(true);
        doNothing().when(bookRepository).deleteById("123");

      
        bookService.deleteBook("123");

     
        verify(bookRepository, times(1)).existsById("123");
        verify(bookRepository, times(1)).deleteById("123");
    }

    @Test
    void deleteBook_BookNotFound_ThrowsException() {
       
        when(bookRepository.existsById("123")).thenReturn(false);

       
        Exception exception = assertThrows(BookNotFoundException.class, () -> bookService.deleteBook("123"));
        assertEquals("Cannot delete. Book not found with ISBN: 123", exception.getMessage());
        verify(bookRepository, times(1)).existsById("123");
        verify(bookRepository, never()).deleteById("123");
    }
}
