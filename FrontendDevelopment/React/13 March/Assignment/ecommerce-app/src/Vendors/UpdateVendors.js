import React, { useState, useEffect } from "react";
import axios from "axios";
import "../Products/UpdateProductStyle.css";

function UpdateVendors({ vendor, onClose }) {
  const[id, setId] = useState("");
  const [name, setName] = useState("");
  const [registrationId, setRegistrationId] = useState("");
  const [email, setEmail] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");

  useEffect(() => {
    if (vendor) {
      setId(vendor.id|| "");
      setName(vendor.Name || "");
      setRegistrationId(vendor.RegisterationId || "");
      setEmail(vendor.Email || "");
      setAddress(vendor.Address || "");
      setPhone(vendor.Phone || "");
      setPassword(vendor.Password || "");
    }
  }, [vendor]);

  const updateVendor = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:4000/Vendors/${vendor.id}`, {
        id,
        name,
        registrationId,
        email,
        address,
        phone,
        password,
      });

      console.log("Vendor updated successfully.");
      onClose(); 
    } catch (error) {
      console.error("Error updating vendor:", error);
      alert("Failed to update vendor. Please try again.");
    }
  };

  const handleReset = () => {
    if (vendor) {
      setId("");
      setName("");
      setRegistrationId("");
      setEmail("");
      setAddress("");
      setPhone("");
      setPassword("");
    }
  };

  if (!vendor) {
    return <p>Loading vendor...</p>;
  }

  return (
    <form onSubmit={updateVendor} className="update-vendor-form">
      <label>
        ID:
        <input type="text" value={id} onChange={(e) => setId(e.target.value)} required />
      </label>
      <label>
        Name:
        <input type="text" value={name} onChange={(e) => setName(e.target.value)} required />
      </label>
      <label>
        Registration ID:
        <input type="text" value={registrationId} onChange={(e) => setRegistrationId(e.target.value)} required />
      </label>
      <label>
        Email:
        <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
      </label>
      <label>
        Address:
        <input type="text" value={address} onChange={(e) => setAddress(e.target.value)} required />
      </label>
      <label>
        Phone:
        <input type="tel" value={phone} onChange={(e) => setPhone(e.target.value)} required />
      </label>
      <label>
        Password:
        <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} required />
      </label>
      <div className="form-buttons">
        <button type="submit">Update Vendor</button>
        <button type="button" onClick={handleReset}>Reset</button>
        <button type="button" onClick={onClose}>Cancel</button>
      </div>
    </form>
  );
}

export default UpdateVendors;
