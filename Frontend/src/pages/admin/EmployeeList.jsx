import { useEffect, useState } from "react";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  // Fetch employees
  const fetchEmployees = async () => {
    try {
      const res = await api.get("/admin/employees");
      setEmployees(res.data);
    } catch {
      alert("Failed to fetch employees");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  // Delete employee
  const handleDelete = async (id) => {
    const confirmDelete = window.confirm(
      "Are you sure you want to delete this employee?"
    );

    if (!confirmDelete) return;

    try {
      await api.delete(`/admin/delete-employee/${id}`);
      alert("Employee deleted successfully");
      fetchEmployees(); // refresh list
    } catch {
      alert("Delete failed");
    }
  };

  if (loading) return <p>Loading...</p>;

  return (
    <div>
      <h2>Employee List</h2>

      <table style={styles.table}>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Salary</th>
            <th>Joining Date</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {employees.map((emp) => (
            <tr key={emp.empId}>
              <td>{emp.empId}</td>
              <td>{emp.name}</td>
              <td>{emp.email}</td>
              <td>{emp.department}</td>
              <td>{emp.salary}</td>
              <td>{emp.joiningDate}</td>
              <td>
                <button onClick={() => navigate(`/admin/view/${emp.empId}`)} >View</button>
                <button
                  onClick={() => navigate(`/admin/update/${emp.empId}`)}
                  style={styles.editBtn}
                >
                  Edit
                </button>
                <button
                  onClick={() => handleDelete(emp.empId)}
                  style={styles.deleteBtn}
                >
                  Delete
                </button>
              </td>
            </tr>
          ))}

          {employees.length === 0 && (
            <tr>
              <td colSpan="7" style={{ textAlign: "center" }}>
                No employees found
              </td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default EmployeeList;

// -------- styles --------
const styles = {
  table: {
    width: "100%",
    borderCollapse: "collapse",
    border: "1px solid #141212ff",
  },
  editBtn: {
    marginRight: "8px",
    padding: "5px 10px",
    background: "#1976d2",
    color: "#fff",
    border: "none",
    cursor: "pointer",
  },
  deleteBtn: {
    padding: "5px 10px",
    background: "#d32f2f",
    color: "#fff",
    border: "none",
    cursor: "pointer",
  },
};
