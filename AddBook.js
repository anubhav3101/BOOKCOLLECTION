import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./styles.css";

const AddBook = ({ addBook }) => {
  const [formData, setFormData] = useState({ title: "", author: "", isbn: "" });
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addBook(formData);  // Calling the addBook function passed as a prop
    navigate("/"); // Redirect to the book list page
  };

  return (
    <div className="container">
      <h1>Add New Book</h1>
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
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
};

export default AddBook;
