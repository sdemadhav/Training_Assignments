import React, { useState, useEffect } from "react";
import axios from "axios";

function UpdateProduct({ product }) {
  const [title, setTitle] = useState(product?.title || "");
  const [price, setPrice] = useState(product?.price || 0);
  const [category, setCategory] = useState(product?.category || "");
  const [image, setImage] = useState(product?.image || "");

  useEffect(() => {
    if (product) {
      setTitle(product.title);
      setPrice(product.price);
      setCategory(product.category);
      setImage(product.image);
    }
  }, [product]);

  const updateProduct = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.put(`http://localhost:4000/products/${product.id}`, {
        title,
        price,
        category,
        image,
      });

      console.log("Product updated:", res.data);
    } catch (error) {
      console.error("Error updating product:", error);
    }
  };

  if (!product) {
    return <p>Loading product...</p>;
  }

  return (
    <form onSubmit={updateProduct}>
      <label>
        Title:
        <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
      </label>
      <label>
        Price:
        <input type="number" value={price} onChange={(e) => setPrice(Number(e.target.value))} />
      </label>
      <label>
        Category:
        <input type="text" value={category} onChange={(e) => setCategory(e.target.value)} />
      </label>
      <label>
        Image:
        <input type="text" value={image} onChange={(e) => setImage(e.target.value)} />
      </label>
      <button type="submit">Update Product</button>
    </form>
  );
}

export default UpdateProduct;
