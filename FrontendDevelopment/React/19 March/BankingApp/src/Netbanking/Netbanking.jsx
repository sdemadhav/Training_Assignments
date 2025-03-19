import React, { useState } from "react";
import "./NetbankingStyle.css"; 
import axios from "axios";
import { Navigate, useNavigate } from "react-router-dom";

function Netbanking() {
  const [customerId, setCustomerId] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!customerId || !password) {
      setError("Both fields are required.");
      return;
    }
    setError("");

    try {

      const response = await axios.post("http://localhost:8080/login", { customerId, password })
      
      if (response.data) {
        navigate('/welcome')
      } else {
        setError(response.data);
      }
    } catch (err) {
      setError(err.response?.message || "Login failed. Please try again.");
    }
  };

  return (
    <div className="login-container">
      <div className="login-box">
        <h2 className="login-title">Netbanking Login</h2>
        {error && <p className="error-message">{error}</p>}
        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <label>Customer ID</label>
            <input
              type="text"
              placeholder="Enter your Customer ID"
              value={customerId}
              onChange={(e) => setCustomerId(e.target.value)}
            />
          </div>
          <div className="input-group password-group">
            <label>Password</label>
            <input
              type={showPassword ? "text" : "password"}
              placeholder="Enter your password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <span
              className="toggle-password"
              onClick={() => setShowPassword(!showPassword)}
            >
              {showPassword ? "🙈" : "👁️"}
            </span>
          </div>
          <button type="submit" className="login-button">
            Login
          </button>
          <p className="forgot-password">Forgot Password?</p>
        </form>
      </div>
    </div>
  );
}

export default Netbanking;
