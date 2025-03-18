import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./Components/Navbar";
import "./App.css";
import Products from "./Products/Products";
import Vendors from "./Vendors/Vendors";
import VendorProfile from "./Vendors/VendorProfile";
import Orders from "./Orders/Orders";
import ProductView from "./Products/ProductView";
import Cart from "./Cart";
import Checkout from "./Checkout";
import Stock from "./Stock";
import Home from "./Home/Home";
import { Provider } from "react-redux";
import store from "./ReduxContainer/Store";

function App() {

  const [cartItems, setCartItems] = useState([]);
  return (
    <Provider store={store}>
    <Router>
      <div className="App">
        <Navbar />
        <Routes>
          <Route path="/products" element={<Products />} />
          <Route path="/vendors" element={<Vendors />} />
          <Route path="/" element={<Home />} />
          <Route path="/orders" element={<Orders />} />
          <Route path="/VendorProfile/:id" element={<VendorProfile />} />
          <Route path="/cart" element={<Cart cartItems={cartItems} setCartItems={setCartItems} />} />
          <Route path="/products/:id" element={<ProductView cartItems={cartItems} setCartItems={setCartItems} />} />
          <Route path="/checkout" element={<Checkout/>} />
          <Route path="/stock" element={<Stock/>} />
        </Routes>
      </div>
    </Router>
    </Provider>
  );
}

export default App;
