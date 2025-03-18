import React, { useState, useEffect } from "react";
import axios from "axios";
import "../Products/ProductStyle.css";
import UpdateVendors from "./UpdateVendors";
import { Link } from "react-router-dom";


function Vendors() {
  const [vendors, setVendors] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const [editId, setEditId] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:4000/Vendors")
      .then((res) => {
        console.log(res.data);
        setVendors(res.data || []);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching vendors:", err);
        setError("Failed to load vendors. Please try again later.");
        setLoading(false);
      });
  }, []);

  const handleUpdate = (id) => {
    setEditId(id);
  };

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:4000/vendors/${id}`)
      .then(() => {
        setVendors((prevVendors) =>
          prevVendors.filter((vendor) => vendor.RegisterationId !== id)
        );
      })
      .catch((err) => {
        console.error("Error deleting vendor:", err);
      });
  };

  const handleCloseUpdate = () => {
    setEditId(null);
  };

  if (loading) return <p className="text-center">Loading...</p>;
  if (error) return <p className="text-center text-red-600">{error}</p>;

  return (
    <div className="container">
      <h3>Vendors</h3>
      <table>
        <thead>
          <tr>
            <th>S.No.</th>
            <th>Name</th>
            <th>Registeration Id</th>
            <th>Email</th>
            <th>City</th>
            <th>Phone</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {vendors.map((vendor) => (
            <tr key={vendor.id}>
              <td>{vendor.id}</td>
              <td>{vendor.Name}</td>
              <td>
                <Link to={`/VendorProfile/${vendor.RegisterationId}`}>
                  {vendor.RegisterationId}
                </Link>
              </td>
              <td>{vendor.Email}</td>
              <td>{vendor.Address.city}</td>
              <td>{vendor.Phone}</td>
              <td>
                <button
                  className="action-btn update-btn"
                  onClick={() => handleUpdate(vendor.RegisterationId)}
                >
                  Update
                </button>
                <button
                  className="action-btn delete-btn"
                  onClick={() => handleDelete(vendor.RegisterationId)}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {editId && <UpdateVendors vendor ={vendors.find((p) => p.RegisterationId === editId)} onClose={handleCloseUpdate} />}
    </div>
  );
}

export default  Vendors;

