import { useEffect, useState } from "react";
import api from "../../api/axios";

const Finance = () => {
  const [finance, setFinance] = useState(null);

  useEffect(() => {
    api.get("/finance/me")
      .then(res => setFinance(res.data))
      .catch(() => alert("Failed to load finance details"));
  }, []);

  if (!finance) return <p>Loading...</p>;

  return (
    <div>
      <h2>Finance Details</h2>
      <p><b>Bank Name:</b> {finance.bankName}</p>
      <p><b>Branch:</b> {finance.branch}</p>
      <p><b>IFSC:</b> {finance.ifscCode}</p>
      <p><b>PAN:</b> {finance.panCard}</p>
      <p><b>Aadhar:</b> {finance.aadharCard}</p>
      <p><b>CTC:</b> {finance.ctcBreakup}</p>
    </div>
  );
};

export default Finance;
