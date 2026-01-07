import { Link } from "react-router-dom";

const Sidebar = ({ role }) => {
  return (
    <div style={styles.sidebar}>
       <h3 style={{ marginBottom: "20px" }}>EMS</h3>

      {role === "ADMIN" && (
        <>
          <Link to="/admin" style={styles.link}>Employees</Link>
          <Link to="/admin/add" style={styles.link}>Add Employee</Link>

        </>
      )}

      {role === "EMPLOYEE" && (
        <div className="links">
          <Link to="/employee/profile" style={styles.link}>Profile</Link>
          <Link to="/employee/personal" style={styles.link}>Personal</Link>
          <Link to="/employee/professional" style={styles.link}>Professional</Link>
          <Link to="/employee/finance" style={styles.link}>Finance</Link>
          <Link to="/employee/project" style={styles.link}>Project</Link>
        </div>
      )}

      <button onClick={() => {
        localStorage.clear();
        window.location.href = "/";
      }}>
        Logout
      </button>
    </div>
  );
};

const styles = {
  sidebar: {
    width: "230px",
    background: "#0e1757ff",
    color: "#fff",
    minHeight: "100vh",
    padding: "20px",
    display: "flex",
    flexDirection: "column",
    gap: "14px",
  },
  link: {
    color: "#fff",
    textDecoration: "none",
    padding: "8px",
    borderRadius: "5px",
  },
  button: {
    marginTop: "auto",
    padding: "10px",
    background: "#dc2626",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
};


export default Sidebar;
