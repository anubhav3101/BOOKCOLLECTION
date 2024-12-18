import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import "./styles.css";

const UpdateBook = ({ updateBook }) => {
  const { isbn } = useParams();
  const navigate = useNavigate();
  const [formData, setFormData] = useState({ title: "", author: "", isbn: "" });

  useEffect(() => {
    // Mock fetching book data based on the ISBN (Replace with an API call)
    const book = { title: "Book 1", author: "Author 1", isbn: isbn };
    setFormData(book);
  }, [isbn]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    updateBook(formData);  // Calling the updateBook function passed as a prop
    navigate("/"); // Redirect to the book list page
  };

  return (
    <div className="container">
      <h1>Update Book</h1>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          placeholder="Title"
          value={formData.title}
          onChange={handleChange}
        />
        <input
          type="text"
          name="author"
          placeholder="Author"
          value={formData.author}
          onChange={handleChange}
        />
        <input
          type="text"
          name="isbn"
          placeholder="ISBN"
          value={formData.isbn}
          onChange={handleChange}
        />
        <button type="submit">Update Book</button>
      </form>
    </div>
  );
};

export default UpdateBook;
