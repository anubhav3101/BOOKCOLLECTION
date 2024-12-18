import React from "react";
import { Link } from "react-router-dom";
import "./styles.css";

const BookList = ({ books, deleteBook }) => {
  return (
    <div className="container">
      <h1>Book List</h1>
      <ul>
        {books.map((book) => (
          <li key={book.isbn}>
            <div>
              <strong>{book.title}</strong> by {book.author} (ISBN: {book.isbn})
            </div>
            <div>
              <Link to={`/book/${book.isbn}`}>View</Link> |{" "}
              <Link to={`/update/${book.isbn}`}>Update</Link> |{" "}
              <Link to={`/delete/${book.isbn}`} onClick={() => deleteBook(book.isbn)}>
                Delete
              </Link>
            </div>
          </li>
        ))}
      </ul>
      <Link to="/add">Add New Book</Link>
    </div>
  );
};

export default BookList;
