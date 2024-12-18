package com.hexaware.BookCollection.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.BookCollection.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
