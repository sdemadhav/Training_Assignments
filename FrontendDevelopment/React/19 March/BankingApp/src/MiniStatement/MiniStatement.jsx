import React, { useState, useEffect } from "react";
import axios from "axios";
import jsPDF from "jspdf";
import "jspdf-autotable";
import "./MiniStatementStyle.css";
import { useSelector } from "react-redux";

const TransactionHistory = () => {

  const [allAccounts, setAllAccounts] = useState([]); 
  const [userAccountIds, setUserAccountIds] = useState([]);  
  const [transactions, setTransactions] = useState([]);  
  const [currentBalance, setCurrentBalance] = useState(0); 
  const [error, setError] = useState(null);


  const loggedInUser = useSelector((state) => state.user);

  useEffect(() => {
    async function fetchTransactions() {
      try {
        if (!loggedInUser) return;
  
        console.log(loggedInUser);
  
 
        const accountResponse = await axios.get(
          `http://localhost:8080/customer-accounts/${loggedInUser.customerId}`
        );
        const accountIds = accountResponse.data.map((acc) => acc.accountId);
        setUserAccountIds(accountIds);
  
        const transactionResponse = await axios.get("http://localhost:8080/transactions/all");
        const allTransactions = transactionResponse.data;
  
  
        const allAccountsResponse = await axios.get("http://localhost:8080/accounts/all");
        setAllAccounts(allAccountsResponse.data);
  
        const userTransactions = allTransactions
          .filter(
            (tx) =>
              accountIds.includes(tx.senderAccountId) ||
              accountIds.includes(tx.receiverAccountId)
          )
          .slice(-5)
          .reverse();
  
        setTransactions(userTransactions);
  

        let balance = accountResponse.data.reduce((sum, acc) => sum + acc.balance, 0);
        setCurrentBalance(balance);
      } catch (err) {
        console.error("Error fetching transactions:", err);
        setError("Failed to load transactions.");
      }
    }
  
    fetchTransactions();
  }, [loggedInUser]);
  


  const downloadPDF = () => {
    const doc = new jsPDF();
    doc.text("Transaction History", 14, 10);
    doc.autoTable({
      startY: 20,
      head: [["Date", "Transaction ID", "Amount"]],
      body: transactions.map((tx) => [
        new Date(tx.createdDatetime).toLocaleString(),
        tx.transactionId,
        userAccountIds.includes(tx.senderAccountId)
          ? `- ₹${tx.amount}`
          : `+ ₹${tx.amount}`,
      ]),
    });
    doc.text(`Current Balance: ₹${currentBalance}`, 14, doc.autoTable.previous.finalY + 10);
    doc.save("Transaction_History.pdf");
  };

  return (
    <div className="transaction-container">
      <h2>Latest Transactions</h2>
      <button className="download-btn" onClick={downloadPDF}>Download as PDF</button>
      {error && <p className="error">{error}</p>}
      <table className="transaction-table">
  <thead>
    <tr>
      <th>Transaction ID</th>
      <th>Type</th>
      <th>Amount</th>
      <th>My Acc ID</th>
      <th>Reference Account ID</th>
      <th>Reference Account Name</th>
      <th>Date</th>
    </tr>
  </thead>
  <tbody>
    {transactions.map((tx) => {
      const isSender = userAccountIds.includes(tx.senderAccountId);
      const myAccountId = isSender ? tx.senderAccountId : tx.receiverAccountId;
      const referenceAccountId = isSender ? tx.receiverAccountId : tx.senderAccountId;
      const referenceAccount = allAccounts.find(acc => acc.accountId === referenceAccountId);

      return (
        <tr key={tx.transactionId}>
          <td>{tx.transactionId}</td>
          <td style={{ color: isSender ? "red" : "green" }}>
            {isSender ? "Debited" : "Credited"}
          </td>
          <td style={{ color: isSender ? "red" : "green" }}>
            {isSender ? `-₹${tx.amount}` : `+₹${tx.amount}`}
          </td>
          <td>{myAccountId}</td>
          <td>{referenceAccountId}</td>
          <td>{referenceAccount ? referenceAccount.customer.name : "Unknown"}</td>
          <td>{new Date(tx.createdDatetime).toLocaleString()}</td>
        </tr>
      );
    })}
  </tbody>
</table>

      <h3>Current Balance: ₹{currentBalance}</h3>
    </div>
  );
};

export default TransactionHistory;
