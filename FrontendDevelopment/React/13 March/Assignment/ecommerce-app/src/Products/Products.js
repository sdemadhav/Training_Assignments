import React, { useState, useEffect } from "react";
import axios from "axios";
import "./ProductStyle.css";
import UpdateProduct from "./UpdateProduct";
import { Link } from "react-router-dom";

function Products() {
  const [products, setProducts] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editId, setEditId] = useState(null);
  const [cart, setCart] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:4000/products")
      .then((res) => {
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

  const handleCloseUpdate = () => {
    setEditId(null);
  };

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:4000/products/${id}`)
      .then(() => {
        setProducts((prevProducts) =>
          prevProducts.filter((product) => product.id !== id)
        );
      })
      .catch((err) => {
        console.error("Error deleting product:", err);
      });
  };

  if (loading) return <p className="text-center">Loading...</p>;
  if (error) return <p className="text-center text-red-600">{error}</p>;

  return (
    <div className="container">
      <h3>Product Details</h3>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Price ($)</th>
            <th>Category</th>
            <th>Actions</th>
            <th>Order</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>{product.title}</td>
              <td>
                {Math.min(...product.vendors.map(vendor => vendor.price))}
              </td>
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
              <td>
                <button className="action-btn order-btn">
                  <Link to={`/products/${product.id}`}>Order</Link>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editId && (
        <UpdateProduct
          product={products.find((p) => p.id === editId)}
          onClose={handleCloseUpdate}
        />
      )}
    </div>
  );
}

export default Products;
