import React from "react";
import { useParams } from "react-router-dom";
import "./styles.css";

const BookDetails = () => {
  const { isbn } = useParams();

  const book = { title: "Book 1", author: "Author 1", isbn: isbn };

  return (
    <div className="container">
      <h1>Book Details</h1>
      <p><strong>Title:</strong> {book.title}</p>
      <p><strong>Author:</strong> {book.author}</p>
      <p><strong>ISBN:</strong> {book.isbn}</p>
    </div>
  );
};

export default BookDetails;
