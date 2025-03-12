import React from 'react'
import { useState } from 'react';

const LoanCalculator = () => {

const [emiResult, setEmiResult] = useState("---");
const [interestRate, setInterestRate] = useState(9);
const [tenure, setTenure] = useState(0);
const [amount, setAmount] = useState(0);

function calculateEMI() {
    let monthlyRate = interestRate / 1200;
    let months = tenure * 12;
    let emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, months)) / (Math.pow(1 + monthlyRate, months) - 1);        
    setEmiResult("₹" + emi.toFixed(2));
}


  return (
    <div className="tab-content active">
      <h3>Loan Calculator</h3>
      <label>Applicant Name:</label>
      <input type="text" placeholder="Enter your name" />

      <label>Loan Type:</label>
      <select id="loanType" onChange={(e) => 
        {
        
        if(e.target.value === "home") {
          setInterestRate(9)
        } else if(e.target.value === "car") {
          setInterestRate(7)
        } else if(e.target.value === "personal") {
          setInterestRate(12)
        }
        
      }}
      >
        <option value="home">Home Loan</option>
        <option value="car">Car Loan</option>
        <option value="personal">Personal Loan</option>
      </select>

      <label>Interest Rate (%):</label>
      <input type="text" id="interestRate" defaultValue="9%" value={interestRate} disabled />

      <label>Tenure (Years):</label>
      <input type="number" id="loanTenure" placeholder="Enter tenure" onChange={(e)=>setTenure(e.target.value)}/>

      <label>Amount:</label>
      <input type="number" id="loanAmount" placeholder="Enter loan amount" onChange={(e)=>setAmount(e.target.value)} />

      <button
        className="calculate-btn" onClick={calculateEMI}>Calculate EMI</button>
      <h4>EMI: <span>{emiResult==0 ? "" : emiResult}</span></h4>
    </div>
  )
}

export default LoanCalculator