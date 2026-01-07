import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../api/axios";

const AdminProjectForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    projectCode: "",
    startDate: "",
    endDate: "",
    client: "",
    reportingManager: "",
    employee: { empId: id }
  });

  useEffect(() => {
    api.get(`/project/admin/${id}`)
      .then(res => res.data && setForm(res.data))
      .catch(() => {});
  }, [id]);

  const handleChange = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    await api.post("/project/admin/save", form);
    alert("Project details saved");
    navigate(`/admin/view/${id}`);
  };

  return (
    <>
      <h2>Project Details</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <tbody>
            <tr><td>Project Code</td><td><input name="projectCode" value={form.projectCode} onChange={handleChange} /></td></tr>
            <tr><td>Start Date</td><td><input type="date" name="startDate" value={form.startDate} onChange={handleChange} /></td></tr>
            <tr><td>End Date</td><td><input type="date" name="endDate" value={form.endDate} onChange={handleChange} /></td></tr>
            <tr><td>Client</td><td><input name="client" value={form.client} onChange={handleChange} /></td></tr>
            <tr><td>Manager</td><td><input name="reportingManager" value={form.reportingManager} onChange={handleChange} /></td></tr>
          </tbody>
        </table>
        <button type="submit">Save</button>
      </form>
    </>
  );
};

export default AdminProjectForm;
