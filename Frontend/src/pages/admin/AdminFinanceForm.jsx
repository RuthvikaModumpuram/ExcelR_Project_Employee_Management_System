import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

const AdminFinanceForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    bankName: "",
    branch: "",
    ifscCode: "",
    panCard: "",
    aadhaarNumber: "",
    ctcBreakup: "",
    employee: { empId: id }
  });

  useEffect(() => {
    api.get(`/finance/admin/${id}`)
      .then(res => res.data && setForm(res.data))
      .catch(() => {});
  }, [id]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await api.post("/finance/admin/save", form);
    alert("Finance details saved");
    navigate(`/admin/view/${id}`);
  };

  return (
    <>
      <h2>Finance Details</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr><td>Bank</td><td><input name="bankName" value={form.bankName} onChange={handleChange} /></td></tr>
            <tr><td>Branch</td><td><input name="branch" value={form.branch} onChange={handleChange} /></td></tr>
            <tr><td>IFSC</td><td><input name="ifscCode" value={form.ifscCode} onChange={handleChange} /></td></tr>
            <tr><td>PAN</td><td><input name="panCard" value={form.panCard} onChange={handleChange} /></td></tr>
            <tr><td>Aadhaar</td><td><input name="aadhaarNumber" value={form.aadhaarNumber} onChange={handleChange} /></td></tr>
            <tr><td>CTC</td><td><input name="ctcBreakup" value={form.ctcBreakup} onChange={handleChange} /></td></tr>
          </tbody>
        </table>
        <button type="submit">Save</button>
      </form>
    </>
  );
};

export default AdminFinanceForm;
