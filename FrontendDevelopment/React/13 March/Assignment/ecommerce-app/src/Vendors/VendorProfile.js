import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import "./VendorProfileStyle.css";

function VendorProfile() {
  const { id } = useParams(); // Get the RegisterationId from URL
  const [vendor, setVendor] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:4000/Vendors?RegisterationId=${id}`) 
      .then((res) => {
        if (res.data.length > 0) {
          setVendor(res.data[0]); 
        } else {
          setError("Vendor not found");
        }
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching vendor:", err);
        setError("Failed to load vendor details.");
        setLoading(false);
      });
  }, [id]);

  if (loading) return <p>Loading vendor details...</p>;
  if (error) return <p className="text-red-600">{error}</p>;

  return (
    <div className="vendor-profile">
      <h2>Vendor Profile</h2>
      <p><strong>Name:</strong> {vendor.Name}</p>
      <p><strong>Registration ID:</strong> {vendor.RegisterationId}</p>
      <p><strong>Email:</strong> {vendor.Email}</p>
      <p><strong>Phone:</strong> {vendor.Phone}</p>
      <h3>Address</h3>
      <p><strong>Street:</strong> {vendor.Address.street}</p>
      <p><strong>City:</strong> {vendor.Address.city}</p>
      <p><strong>State:</strong> {vendor.Address.state}</p>
      <p><strong>ZIP:</strong> {vendor.Address.zip}</p>
    </div>
  );
}

export default VendorProfile;
