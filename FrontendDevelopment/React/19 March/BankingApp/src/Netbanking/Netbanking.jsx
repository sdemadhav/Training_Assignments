import React, { useState } from "react";
import "./NetbankingStyle.css"; 
import axios from "axios";
import {useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { set_login_true } from "../ReduxContainer/AuthContainer/AuthAction";

function Netbanking() {
  const [customerId, setCustomerId] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const handleSubmit = async (e) => {
    console.log("coming in handleSubmit")
    e.preventDefault();
    if (!customerId || !password) {
      setError("Both fields are required.");
      return;
    }
    setError("");

    try {
      console.log("coming in try")
      const response = await axios.post("http://localhost:8080/login", { customerId, password })
      console.log("coming after response")

      if (response.data) {
        const loggedInUser = await axios.get(`http://localhost:8080/customer/${customerId}`)
        console.log(loggedInUser.data);
        
        dispatch(set_login_true(loggedInUser.data))
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
