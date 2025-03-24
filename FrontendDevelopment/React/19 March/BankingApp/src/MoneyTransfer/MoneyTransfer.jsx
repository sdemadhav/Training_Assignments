import React, { useState, useEffect } from "react";
import axios from "axios";
import "./MoneyTransferStyle.css";
import { useSelector } from "react-redux";

function MoneyTransfer() {
  const [senderAccounts, setSenderAccounts] = useState([]);
  const [selectedSender, setSelectedSender] = useState("");
  const [receiverAccount, setReceiverAccount] = useState("");
  const [receiverDetails, setReceiverDetails] = useState(null);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  const loggedInUser = useSelector((state) => state.user);

  useEffect(() => {
    async function fetchSenderAccounts() {
      try {
        if (!loggedInUser?.customerId) return;
  
        const response = await axios.get(
          `http://localhost:8080/customer-accounts/${loggedInUser.customerId}`
        );
  
        console.log("API Response:", response.data); 
  
        if (Array.isArray(response.data) && response.data.length > 0) {
          setSenderAccounts(response.data); 
        } else {
          setError("No accounts found.");
        }
      } catch (err) {
        console.error("Error fetching accounts:", err);
        setError("Failed to load accounts.");
      }
    }
  
    fetchSenderAccounts();
  }, [loggedInUser]);
  
  

  const verifyReceiver = async () => {
    setError("");
    setReceiverDetails(null);
    if (!receiverAccount) {
      setError("Please enter receiver's account number.");
      return;
    }
    try {
      const response = await axios.get(`http://localhost:8080/accounts/${receiverAccount}`);
      setReceiverDetails(response.data.customer);
    } catch (err) {
      setError("Receiver account not found.");
    }
  };

  const handleTransfer = async () => {
    setError("");
    setSuccessMessage("");

    if (!selectedSender || !receiverAccount || !amount) {
      setError("All fields are required.");
      return;
    }
    if (isNaN(Number(amount)) || Number(amount) <= 0) {
      setError("Invalid amount.");
      return;
    }

    const sender = senderAccounts.find((acc) => acc.accountId === Number(selectedSender));
    console.log(sender.balance)
    if (!sender) {
      setError("Invalid sender account.");
      return;
    }

    if (sender.balance < Number(amount)) {
      setError("Insufficient funds.");
      return;
    }


    try {
      const response = await axios.post(`http://localhost:8080/transactions`, {
        senderAccountId: selectedSender,
        receiverAccountId: receiverAccount,
        amount: Number(amount),
      });
      
      console.log(response);
      
      if (response.status === 200) {
        setSuccessMessage("Money transferred successfully!");
        setAmount("");
        return;
      } else {
        setError(response.data.message || "Transfer failed.");
      }
    } catch (err) {
      setError("Transaction failed. Please try again.");
    }
  };

  return (
    <div className="transfer-container">
      <h2>Bank Money Transfer</h2>
      {error && <p className="error-message">{error}</p>}
      {successMessage && <p className="success-message">{successMessage}</p>}

      <div className="input-group">
        <label>Sender's Account</label>
        <select value={selectedSender} onChange={(e) => setSelectedSender(e.target.value)}>
          <option value="">Select Account</option>
          {senderAccounts.map((acc) => (
            <option key={acc.accountId} value={acc.accountId}>
            {acc.accountId} - ₹{acc.balance}
          </option>
          ))}
        </select>
      </div>

      <div className="input-group">
        <label>Receiver's Account</label>
        <input
          type="text"
          placeholder="Enter receiver's account number"
          value={receiverAccount}
          onChange={(e) => setReceiverAccount(e.target.value)}
        />
        <button onClick={verifyReceiver}>Verify</button>
      </div>

      {receiverDetails && (
        <div className="receiver-details">
          <p>
            <strong>Name:</strong> {receiverDetails.name}
          </p>
          <p>
            <strong>Bank:</strong> Pinacle Banking Services     {/* {receiverDetails.bank}*/}
          </p>
        </div>
      )}

      {receiverDetails && (
        <div className="input-group">
          <label>Amount</label>
          <input
            type="number"
            placeholder="Enter amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
      )}

      {receiverDetails && (
        <button className="transfer-button" onClick={handleTransfer}>
          Transfer Money
        </button>
      )}
    </div>
  );
}

export default MoneyTransfer;
