import { useNavigate } from "react-router-dom";

const Header = ({ title }) => {
  const navigate = useNavigate();
  const role = localStorage.getItem("role");

  const handleLogout = () => {
    localStorage.clear();
    navigate("/");
  };

  return (
    <div style={styles.header}>
      <h2>{title}</h2>

      <div style={styles.right}>
        <span>{role}</span>
        <button onClick={handleLogout} style={styles.logout}>
          Logout
        </button>
      </div>
    </div>
  );
};

export default Header;

const styles = {
  header: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    padding: "12px 20px",
    background: "#f5f5f5",
    borderBottom: "1px solid #ddd",
  },
  right: {
    display: "flex",
    alignItems: "center",
    gap: "15px",
  },
  logout: {
    background: "#d32f2f",
    color: "#fff",
    border: "none",
    padding: "6px 12px",
    cursor: "pointer",
    borderRadius: "4px",
  },
};
