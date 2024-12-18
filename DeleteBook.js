import React, { useState } from "react";
import { useParams } from "react-router-dom";
import "./styles.css";

const DeleteBook = () => {
  const { isbn } = useParams();
  const [isDeleted, setIsDeleted] = useState(false);

  const handleDelete = () => {
    console.log(`Book with ISBN ${isbn} deleted`);
    setIsDeleted(true);
  };

  return (
    <div className="container">
      <h1>Delete Book</h1>
      {isDeleted ? (
        <div className="confirmation">
          <p>The book has been deleted successfully.</p>
        </div>
      ) : (
        <div>
          <p>Are you sure you want to delete the book with ISBN: {isbn}?</p>
          <button className="delete" onClick={handleDelete}>
            Delete
          </button>
        </div>
      )}
    </div>
  );
};

export default DeleteBook;
