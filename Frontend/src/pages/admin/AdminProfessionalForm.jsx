import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

const AdminProfessionalForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    officePhone: "",
    officeCity: "",
    reportingManager: "",
    hrName: "",
    employee: { empId: id }
  });

  useEffect(() => {
    api.get(`/professional/admin/${id}`)
      .then(res => res.data && setForm(res.data))
      .catch(() => {});
  }, [id]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await api.post("/professional/admin/save", form);
    alert("Professional details saved");
    navigate(`/admin/view/${id}`);
  };

  return (
    <>
      <h2>Professional Details</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr><td>Office Phone</td><td><input name="officePhone" value={form.officePhone} onChange={handleChange} /></td></tr>
            <tr><td>Office City</td><td><input name="officeCity" value={form.officeCity} onChange={handleChange} /></td></tr>
            <tr><td>Reporting Manager</td><td><input name="reportingManager" value={form.reportingManager} onChange={handleChange} /></td></tr>
            <tr><td>HR Name</td><td><input name="hrName" value={form.hrName} onChange={handleChange} /></td></tr>
          </tbody>
        </table>
        <button type="submit">Save</button>
      </form>
    </>
  );
};

export default AdminProfessionalForm;
