import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import BookList from "./BookList";
import BookDetails from "./BookDetails";
import AddBook from "./AddBook";
import UpdateBook from "./UpdateBook";
import DeleteBook from "./DeleteBook";
import "./styles.css";

const App = () => {
  // State to hold books (You can replace this with API calls)
  const [books, setBooks] = useState([
    { title: "Book 1", author: "Author 1", isbn: "12345" },
    { title: "Book 2", author: "Author 2", isbn: "67890" },
  ]);

  // Function to add a new book
  const addBook = (newBook) => {
    setBooks([...books, newBook]);
  };

  // Function to update an existing book
  const updateBook = (updatedBook) => {
    const updatedBooks = books.map((book) =>
      book.isbn === updatedBook.isbn ? updatedBook : book
    );
    setBooks(updatedBooks);
  };

  // Function to delete a book
  const deleteBook = (isbn) => {
    const updatedBooks = books.filter((book) => book.isbn !== isbn);
    setBooks(updatedBooks);
  };

  return (
    <Router>
      <div className="container">
        <Routes>
          <Route path="/" element={<BookList books={books} deleteBook={deleteBook} />} />
          <Route path="/book/:isbn" element={<BookDetails books={books} />} />
          <Route path="/add" element={<AddBook addBook={addBook} />} />
          <Route path="/update/:isbn" element={<UpdateBook updateBook={updateBook} />} />
          <Route path="/delete/:isbn" element={<DeleteBook deleteBook={deleteBook} />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
