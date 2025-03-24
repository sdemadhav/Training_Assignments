import React, { useState } from "react";
import "./NetbankingStyle.css";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function RegisterCustomer() {
  const [formData, setFormData] = useState({
    name: "",
    phoneNo: "",
    email: "",
    age: "",
    password: "",
  });
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { name, phoneNo, email, age, password } = formData;
    if (!name || !phoneNo || !email || !age || !password) {
      setError("All fields are required.");
      return;
    }
    setError("");

    try {
      const response = await axios.post("http://localhost:8080/customer", formData);
      if (response.data) {
        navigate("/login");
      } else {
        setError("Registration failed. Please try again.");
      }
    } catch (err) {
      setError(err.response?.message || "Registration failed. Please try again.");
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Customer Registration</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label>Name</label>
            <input type="text" name="name" placeholder="Enter your name" value={formData.name} onChange={handleChange} />
          </div>
          <div className="input-group">
            <label>Phone Number</label>
            <input type="tel" name="phoneNo" placeholder="Enter your phone number" value={formData.phoneNo} onChange={handleChange} />
          </div>
          <div className="input-group">
            <label>Email</label>
            <input type="email" name="email" placeholder="Enter your email" value={formData.email} onChange={handleChange} />
          </div>
          <div className="input-group">
            <label>Age</label>
            <input type="number" name="age" placeholder="Enter your age" value={formData.age} onChange={handleChange} />
          </div>
          <div className="input-group password-group">
            <label>Password</label>
            <input type={showPassword ? "text" : "password"} name="password" placeholder="Enter your password" value={formData.password} onChange={handleChange} />
            <span className="toggle-password" onClick={() => setShowPassword(!showPassword)}>
              {showPassword ? "🙈" : "👁️"}
            </span>
          </div>
          <button type="submit" className="login-button">Register</button>
        </form>
      </div>
    </div>
  );
}

export default RegisterCustomer;
