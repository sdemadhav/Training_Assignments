import React from 'react'
import { useState } from 'react';

const DepositCalculator = () => {
 
    const [maturityResult, setMaturityResult] = useState("---");
    return (
        <div className="tab-content active">
        <h3>Deposit Calculator</h3>
        <label>Principal Amount:</label>
        <input type="number" id="depositPrincipal" placeholder="Enter principal amount" />

        <label>Tenure (Years):</label>
        <input type="number" id="depositTenure" placeholder="Enter tenure (max 10 years)" />

        <label>Interest Rate (%):</label>
        <input type="text" defaultValue="7%" disabled />

        <button
            className="calculate-btn"
            
            onClick={() => {
                let principal = parseFloat(document.getElementById("depositPrincipal").value);
                let tenure = parseInt(document.getElementById("depositTenure").value);
                let rate = 7 / 100;
                let maturity = principal * Math.pow(1 + rate, tenure);
                setMaturityResult("₹" + maturity.toFixed(2));
            }}
        >
            Calculate Maturity Amount
        </button>
        <h4>Maturity Amount: <span>{maturityResult}</span></h4>
        </div>
    )
}

export default DepositCalculator;