import React from 'react'
import { Link, useNavigate } from 'react-router-dom';



function Checkout() {

const navigate = useNavigate();
  return (
    <>
    <h1>Checkout</h1>
    <h3>India's biggest online store</h3>
    <hr></hr>
    <Link to="/orders" style={ {marginRight : "10px"} }>
          <button>Successful Payment</button>
    </Link>
    <Link to="/orders">
          <button>Failed Payment</button>
    </Link>
    </>
    
  )
}

export default Checkout;