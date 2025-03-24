import React from "react";
import { Link } from "react-router-dom";
import "./WelcomeStyle.css";

const Welcome = () => {
  return (
    <div className="welcome-container">
      <h1 className="welcome-heading">Welcome to Net Banking</h1>
      <p className="welcome-subheading">
        Avail the following features to manage your account easily
      </p>
      <div className="features-container">
  <div className="feature">
    <i className="fas fa-money-bill-wave"></i>
    <h3>Transfer Money</h3>
    <p>Transfer money to any account in just a few clicks</p>
    <Link to="/transfer">
      <button className="feature-btn">Transfer Money</button>
    </Link>
  </div>

  <div className="feature">
    <h3>Check Balance</h3>
    <p>Check your account balance in real time</p>
    <Link to="/check-balance">
      <button className="feature-btn">Check Balance</button>
    </Link>
  </div>

  <div className="feature">
    <i className="fas fa-file-invoice"></i>
    <h3>Check Mini Statement</h3>
    <p>View your last 5 transactions in a single click</p>
    <Link to="/statement">
      <button className="feature-btn">Check Statement</button>
    </Link>
  </div>
</div>

    </div>
  );
};

export default Welcome;

