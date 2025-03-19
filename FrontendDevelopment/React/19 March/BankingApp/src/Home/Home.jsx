import React from "react";
import "./HomeStyle.css";
import { Link } from "react-router-dom";
import Navbar from "../components/Navbar";

const Home = () => {
  return (
    <div className="container">
      <div className="heroSection">
        <h1 className="heading">Welcome to Pinnacle Banking</h1>
        <p className="subheading">Your trusted partner for seamless financial services.</p>
        <Link to="/netbanking">
          <button className="ctaButton">Explore Net Banking</button>
        </Link>
      </div>
      <div className="featureSection">
        <h2 className="sectionHeading">Why Choose Pinnacle Banking?</h2>
        <div className="features">
          <div className="feature">
            <h3>🚀 Fast Transactions</h3>
            <p>Experience quick and easy transactions with our advanced technology.</p>
          </div>
          <div className="feature">
            <h3>🔒 Secure Banking</h3>
            <p>Your security is our top priority with state-of-the-art encryption.</p>
          </div>
          <div className="feature">
            <h3>💼 Comprehensive Services</h3>
            <p>Access a range of services from loans to investments at your convenience.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;

