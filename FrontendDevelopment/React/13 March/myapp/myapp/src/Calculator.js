import React, { useState } from "react";
import './CalculatorStyle.css';
import LoanCalculator from "./LoanCalculator";
import DepositCalculator from "./DepositCalculator";

const Calculator = () => {
const [activeTab, setActiveTab] = useState("loan");

  return (
    <div>
      <header>
        <h1>About Our Bank</h1>
        <p>Banking at your doorstep</p>
      </header>

      <div className="tabs">
        <div className={`tab ${activeTab === "loan" ? "active" : ""}`} onClick={() => setActiveTab("loan")}>
          Loan Calculator
        </div>
        <div className={`tab ${activeTab === "deposit" ? "active" : ""}`} onClick={() => setActiveTab("deposit")}>
          Deposit Calculator
        </div>
      </div>

      <div id="calculator-content">
        {activeTab === "loan" ? <LoanCalculator/> : <DepositCalculator />}
      </div>
    </div>
  );
};

export default Calculator;
