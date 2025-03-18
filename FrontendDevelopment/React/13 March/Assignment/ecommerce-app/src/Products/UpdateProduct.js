import React, { useState, useEffect } from "react";
import axios from "axios";
import "../Products/UpdateProductStyle.css";

function UpdateProduct({ product, onClose }) {
  const [title, setTitle] = useState(product?.title || "");
  const [price, setPrice] = useState(product?.price || "");
  const [category, setCategory] = useState(product?.category || "");

  useEffect(() => {
    if (product) {
      setTitle(product.title);
      setPrice(product.price);
      setCategory(product.category);
    }
  }, [product]);

  const updateProduct = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:4000/products/${product.id}`, {
        title,
        price,
        category,
      });
      onClose(); 
      window.location.reload();
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };

  return (
    <form onSubmit={updateProduct}>
      <label>
        Title:
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      </label>
      <label>
        Price:
        <input type="number" value={price} onChange={(e) => setPrice(e.target.value)} />
      </label>
      <label>
        Category:
        <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} />
      </label>
      <button type="submit">Update Product</button>
      <button type="button" onClick={onClose}>Cancel</button>
    </form>
  );
}

export default UpdateProduct;
