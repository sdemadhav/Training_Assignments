import React from "react";
import "./NavbarStyle.css";
import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="navbar">
      <div className="marquee-container">
        <marquee>Pinnacle Banking Services - Secure & Reliable Banking</marquee>
      </div>
      <div className="nav-container">
        <div className="logo">Pinnacle Banking Services</div>
        <ul className="nav-links">
          <li><Link to="/">Home</Link></li>
          <li><Link to="/services">Services</Link></li>
          <li><Link to="/contact">Contact Us</Link></li>
        </ul>
        <div className="reg-log">
        <Link to="/register">
        <button className="login-btn">Register</button>
        </Link>
        <Link to="/login">
        <button className="login-btn">Login</button>
        </Link>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;

