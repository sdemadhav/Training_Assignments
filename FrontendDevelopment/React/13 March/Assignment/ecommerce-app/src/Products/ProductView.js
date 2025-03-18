import React from "react";
import { useState , useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { addProductToCart, addToCart } from "../ReduxContainer/CartActions";

function ProductView() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [vendors, setVendors] = useState([]);
  const [selectedVendor, setSelectedVendor] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [selectedVendorName, setSelectedVendorName] = useState(null); 

  const cartItems = useSelector(state => state.cartItems);
  const dispatch = useDispatch();

  useEffect(() => {
    axios
      .get(`http://localhost:4000/products?id=${id}`)
      .then((res) => {
        if (res.data.length > 0) {
          setProduct(res.data[0]); 
          setVendors(res.data[0].vendors || []);
        }
      })
      .catch((err) => {
        console.error("Error fetching product details:", err);
      });
  }, [id]);

  useEffect(() => {
    axios
      .get(`http://localhost:4000/Vendors?RegisterationId=${selectedVendor?.vendorRegId}`)
      .then((res) => {
        if (res.data.length > 0) {
          setSelectedVendorName(res.data[0].Name); 
        }
      }
      )
      .catch((err) => {
        console.error("Error fetching Name of vendor details:", err);
      });
    
  }, [selectedVendor?.vendorRegId]);
  
  const handleAddToCart = () => {
    if (!selectedVendor) {
      alert("Please select a vendor.");
      return;
    }
    if (quantity > selectedVendor.stock) {
      alert("Selected quantity exceeds available stock.");
      return;
    }

    dispatch(addToCart({ productId: id, title: product.title, vendor: selectedVendor, quantity }));

    navigate("/cart"); 
  };

  if (!product) return <p>Loading product details...</p>;

  return (
    <div className="container">
      <h3>{product.title}</h3>
      <p><strong>Category:</strong> {product.category}</p>
      <p><strong>Description:</strong> {product.description}</p>
  
      <label>Select Vendor: </label>
      <select 
        onChange={(e) => {
          const selectedVendorId = Number(e.target.value);
          const vendorDetails = vendors.find(v => v.vendorRegId === selectedVendorId);
          setSelectedVendor(vendorDetails);
          console.log(selectedVendor);
        }} 
        defaultValue=""
      >
        <option value="" disabled>Select a vendor</option>
        {vendors.map((vendor) => (
          <option key={vendor.vendorRegId} value={vendor.vendorRegId}>
            {vendor.vendorRegId} - ${vendor.price}
          </option>
        ))}
      </select>
  
      {selectedVendor && (
        <div>
          <p><strong>Vendor:</strong> {selectedVendorName}</p>
          <p><strong>Price:</strong> ${selectedVendor.price}</p>
          <p><strong>Available:</strong> {selectedVendor.stock}</p> 
          <label>Quantity: </label>
          <input
            type="number"
            min="1"
            max={selectedVendor.stock} 
            value={quantity}
            onChange={(e) => setQuantity(Number(e.target.value))}
          />
          <button onClick={handleAddToCart} disabled={!selectedVendor}>
            Add to Cart
          </button>
        </div>
      )}
    </div>
  );
}

export default ProductView;

