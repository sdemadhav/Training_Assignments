import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { Link } from "react-router-dom";
import { removeFromCart } from "./ReduxContainer/CartActions";



function Cart() {

  const cartItems = useSelector(state => state.cart.cartItems);
  const dispatch = useDispatch();

  const handleRemove = (id, vendorRegId) => {
    dispatch(removeFromCart(id, vendorRegId));
  };

  return (
    <div className="container">
      <h3>Shopping Cart</h3>
      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <table>
          <thead>
            <tr>
              <th>Product</th>
              <th>Vendor</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Total</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {cartItems.map((item) => (
              <tr key={`${item.id}-${item.vendor.vendorRegId}`}>
                <td>{item.title}</td>
                <td>{item.vendor.name}</td>
                <td>${item.vendor.price}</td>
                <td>{item.quantity}</td>
                <td>${(item.vendor.price * item.quantity).toFixed(2)}</td>
                <td>
                  <button onClick={() => handleRemove(item.id, item.vendor.vendorRegId)}>Remove</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
      <div style={{ marginTop: "20px" }}>
        <Link to="/products">
          <button>Back to Products</button>
        </Link>
        {cartItems.length > 0 && (
          <Link to="/checkout">
            <button style={{ marginLeft: "10px" }}>Proceed to Checkout</button>
          </Link>
        )}
      </div>
    </div>
  );
}

export default Cart;
