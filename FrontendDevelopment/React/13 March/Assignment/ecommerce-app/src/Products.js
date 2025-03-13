import React, { useState, useEffect } from "react";
import axios from "axios";
import "./ProductStyle.css";
import UpdateProduct from "./UpdateProduct";

function Products() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:4000/products")
      .then((res) => {
        console.log(res.data);
        setProducts(res.data || []);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching products:", err);
        setError("Failed to load products. Please try again later.");
        setLoading(false);
      });
  }, []);

  const handleUpdate = (id) => {
    setEditId(id);
  };

  const handleDelete = (id) => {
    console.log("Delete product with ID:", id);
  };

  if (loading) return <p className="text-center">Loading...</p>;
  if (error) return <p className="text-center text-red-600">{error}</p>;

  return (
    <div className="container">
      <h3>Product Details</h3>
      <table>
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Price ($)</th>
            <th>Category</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>
                <button
                  className="action-btn update-btn"
                  onClick={() => {
                    handleUpdate(product.id);
                    const updateProduct = document.getElementById("update-product");
                    if (updateProduct) {
                      updateProduct.scrollIntoView({ behavior: "smooth" });
                    }
                  }}
                >
                  Update
                </button>
              </td>
              <td>{product.title}</td>
              <td>{product.price}</td>
              <td>{product.category}</td>
              <td>
                <button
                  className="action-btn update-btn"
                  onClick={() => handleUpdate(product.id)}
                >
                  Update
                </button>
                <button
                  className="action-btn delete-btn"
                  onClick={() => handleDelete(product.id)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editId && <UpdateProduct product={products.find((p) => p.id === editId)} />}
    </div>
  );
}

export default Products;

