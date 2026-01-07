import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

const UpdateEmployee = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState(null);

  useEffect(() => {
    api.get("/admin/employees").then(res => {
      const emp = res.data.find(e => e.empId === Number(id));
      setForm(emp);
    });
  }, [id]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      await api.put(`/admin/update-employee/${id}`, form);
      alert("Employee updated");
      navigate("/admin");
    } catch {
      alert("Update failed");
    }
  };

  if (!form) return <p>Loading...</p>;

  return (
    <div>
      <h2>Update Employee</h2>
      <form onSubmit={handleUpdate}>
        <div className="input-group">
        <label> Name </label><input name="name" value={form.name} onChange={handleChange} /><br /><br />
        <label> Email </label><input name="email" value={form.email} onChange={handleChange} /><br /><br />
        <label> Phone </label><input name="phone" value={form.phone} onChange={handleChange} /><br /><br />
        <label> Department </label><input name="department" value={form.department} onChange={handleChange} /><br /><br />
        <label> Salary </label><input name="salary" value={form.salary} onChange={handleChange} /><br /><br />
        <label> Joining Date </label><input name="joiningDate" type="date" value={form.joiningDate} onChange={handleChange} /><br /><br />
        </div>
        <button type="submit">Update</button>
      </form>
    </div>
  );
};

export default UpdateEmployee;
