import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

const AdminPersonalForm = () => {
  const { id } = useParams(); // empId
  const navigate = useNavigate();

  const [form, setForm] = useState({
    dateOfBirth: "",
    age: "",
    gender: "",
    address: "",
    employee: { empId: id }
  });

  useEffect(() => {
    api.get(`/personal/admin/${id}`)
      .then(res => {
        if (res.data) setForm(res.data);
      })
      .catch(() => {});
  }, [id]);

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    await api.post("/personal/admin/save", form);
    alert("Personal details saved");
    navigate(`/admin/view/${id}`);
  };

  return (
    <>
      <h2>Personal Details</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr><td>DOB</td><td><input type="date" name="dateOfBirth" value={form.dateOfBirth} onChange={handleChange} /></td></tr>
            <tr><td>Age</td><td><input name="age" value={form.age} onChange={handleChange} /></td></tr>
            <tr><td>Gender</td><td><input name="gender" value={form.gender} onChange={handleChange} /></td></tr>
            <tr><td>Address</td><td><input name="address" value={form.address} onChange={handleChange} /></td></tr>
          </tbody>
        </table>
        <button type="submit">Save</button>
      </form>
    </>
  );
};

export default AdminPersonalForm;
