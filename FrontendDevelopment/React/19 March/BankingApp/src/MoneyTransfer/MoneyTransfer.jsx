import React, { useState, useEffect } from "react";
import "./MoneyTransfer.css";

function MoneyTransfer() {
  const [senderAccounts, setSenderAccounts] = useState([]);
  const [selectedSender, setSelectedSender] = useState("");
  const [receiverAccount, setReceiverAccount] = useState("");
  const [receiverDetails, setReceiverDetails] = useState(null);
  const [amount, setAmount] = useState("");
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  useEffect(() => {

    async function fetchSenderAccounts() {
      try {
        const response = await axios.get(`/getAccounts`);
        setSenderAccounts(response.data.accounts);
      } catch (err) {
        setError("Failed to load accounts");
      }
    }
    fetchSenderAccounts();
  }, []);

  const verifyReceiver = async () => {
    setError("");
    setReceiverDetails(null);
    if (!receiverAccount) {
      setError("Please enter receiver's account number.");
      return;
    }
    try {
      const response = await axios.get(`/verifyReceiver/${receiverAccount}`);
      setReceiverDetails(response.data);
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
    if (isNaN(amount) || amount <= 0) {
      setError("Invalid amount.");
      return;
    }

    const sender = senderAccounts.find(acc => acc.accountNumber === selectedSender);
    if (sender.balance < amount) {
      setError("Insufficient funds.");
      return;
    }

    try {
      const response = await api.post("/transferMoney", {
        senderAccount: selectedSender,
        receiverAccount,
        amount
      });

      if (response.data.success) {
        setSuccessMessage("Money transferred successfully!");
        setAmount("");
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
            <option key={acc.accountNumber} value={acc.accountNumber}>
              {acc.accountNumber} - ₹{acc.balance}
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
          <p><strong>Name:</strong> {receiverDetails.name}</p>
          <p><strong>Bank:</strong> {receiverDetails.bank}</p>
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
