import React, { useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./NavbarStyle.css";
import { useDispatch, useSelector } from "react-redux";
import { set_login_true } from "../AuthContainer/AuthAction";

function Navbar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const isAdminAuthenticated = useSelector((state) => state.auth.isAdminAuthenticated);

  useEffect(() => {
    if (!isAdminAuthenticated) {
      const alreadyLoggedIn = sessionStorage.getItem("admin_logged_in");
      if (!alreadyLoggedIn) {
        const username = prompt("Enter your username:");
        const password = prompt("Enter your password:");
        if (username === "admin" && password === "admin") {
          dispatch(set_login_true());
          sessionStorage.setItem("admin_logged_in", "true"); 
        } else {
          navigate("/");
        }
      }
    }
  }, [isAdminAuthenticated, dispatch, navigate]); 

  return (
    <nav className="navbar">
      <h2 className="logo">E-Commerce Admin</h2>
      <ul className="nav-links">
        <li><Link to="/">Home</Link></li>
        <li><Link to="/products">Products</Link></li>
        <li><Link to="/vendors">Vendors</Link></li>
        <li><Link to="/cart">My Cart</Link></li>
        <li><Link to="/orders">Orders</Link></li>
        <li><Link to="/stock">Stock</Link></li>
      </ul>
    </nav>
  );
}

export default Navbar;
