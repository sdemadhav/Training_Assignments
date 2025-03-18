import React from 'react'
import { Link } from 'react-router-dom'

function Header() {
  return (
    <>
    <h1>OUR NATIONAL BANK</h1>
    <div>
        <ul>
            <Link to="/">Home</Link>
            <Link to ="/about">About Us</Link>
            <Link to ="/contact">Contact</Link>
            <Link to ="/netbanking">Netbanking</Link>
            <Link to ="/services">Services</Link>
        </ul>
    </div>
    </>
  )
}

export default Header