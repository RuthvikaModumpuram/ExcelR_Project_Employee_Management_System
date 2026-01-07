import { useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";


const AddEmployee = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    name: "",
    email: "",
    phone: "",
    department: "",
    salary: "",
    joiningDate: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await api.post("/admin/add-employee", form);
      alert("Employee added successfully");
      navigate("/admin");
    } catch {
      alert("Failed to add employee");
    }
  };

  return (
    <div>
      <h2>Add Employee</h2>

      <form onSubmit={handleSubmit}>
        <div className="input-group">
        <label>Name</label><input name="name" placeholder="Name" onChange={handleChange} required /><br /><br />
        <label>Email</label><input name="email" placeholder="Email" onChange={handleChange} required /><br /><br />
        <label>Phone</label><input name="phone" placeholder="Phone" onChange={handleChange} /><br /><br />
        <label>Department</label><input name="department" placeholder="Department" onChange={handleChange} /><br /><br />
        <label>Salary</label><input name="salary" placeholder="Salary" type="number" onChange={handleChange} /><br /><br />
        <label>Joining Date</label><input name="joiningDate" type="date" onChange={handleChange} />
        </div>
        <button type="submit">Save</button>
      </form>
    </div>
  );
};

export default AddEmployee;
