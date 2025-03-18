import React from "react";
import "./HomeStyle.css";
import { Link } from "react-router-dom";

const Home = () => {
  return (
    <div className="container">
  <div className="heroSection">
    <h1 className="heading">Welcome to VendorVerse</h1>
    <p className="subheading">Connecting you to top-rated vendors for the best prices.</p>
    <Link to ="/products">
    <button className="ctaButton">Explore Now</button>
    </Link>
    
  </div>
  <div className="featureSection">
    <h2 className="sectionHeading">Why Choose VendorVerse?</h2>
    <div className="features">
      <div className="feature">
        <h3>🏪Diverse Vendors</h3>
        <p>Shop from thousands of trusted sellers across all categories.</p>
      </div>
      <div className="feature">
        <h3>💸Competeitive Prices</h3>
        <p>Get the best deals on your favorite products.</p>
      </div>
      <div className="feature">
        <h3>✅Quality Assurance</h3>
        <p>Buy with confidence and peace of mind.We have the best ratings from our customers</p>
      </div>
    </div>
  </div>
</div>
  );
};


export default Home;
