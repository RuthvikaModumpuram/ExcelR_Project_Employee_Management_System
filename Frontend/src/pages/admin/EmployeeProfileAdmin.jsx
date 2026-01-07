import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import api from "../../api/axios";
import { useNavigate } from "react-router-dom";



const EmployeeProfileAdmin = () => {
  const navigate = useNavigate();

  const { id } = useParams();

  const [employee, setEmployee] = useState(null);
  const [personal, setPersonal] = useState(null);
  const [professional, setProfessional] = useState(null);
  const [finance, setFinance] = useState(null);
  const [project, setProject] = useState(null);


  useEffect(() => {
  const fetchAll = async () => {
    try {
      const emp = await api.get("/admin/employees");
      const selected = emp.data.find(e => e.empId === Number(id));
      setEmployee(selected);

      const p1 = await api.get(`/personal/admin/${id}`);
      const p2 = await api.get(`/professional/admin/${id}`);
      const p3 = await api.get(`/finance/admin/${id}`);
      const p4 = await api.get(`/project/admin/${id}`);

      setPersonal(p1.data);
      setProfessional(p2.data);
      setFinance(p3.data);
      setProject(p4.data);
    } catch (err) {
      console.error(err);
    }
  };

  fetchAll();
}, [id]);


  if (!employee) return <p>Loading...</p>;

  return (
    <div>
      <h2>Employee Full Profile</h2>

      <Section title="Basic Profile">
        <Row label="Name" value={employee.name} />
        <Row label="Email" value={employee.email} />
        <Row label="Department" value={employee.department} />
        <Row label="Salary" value={employee.salary} />
      </Section>

      <Section title="Personal">
  {personal ? (
    <>
      <Row label="DOB" value={personal.dateOfBirth} />
      <Row label="Gender" value={personal.gender} />
      <Row label="Address" value={personal.address} />
      <Row label="Age" value={personal.age} />
    </>
  ) : (
    <p style={{ color: "gray" }}>No personal details added yet</p>
  )}

  <button
    style={btnStyle}
    onClick={() => navigate(`/admin/personal/${id}`)}
  >
    {personal ? "Update Personal" : "Add Personal"}
  </button>
</Section>


      <Section title="Professional">
  {professional ? (
    <>
      <Row label="Office City" value={professional.officeCity} />
      <Row label="Manager" value={professional.reportingManager} />
      <Row label="Office Phone" value={professional.officePhone} />
      <Row label="HR Name" value={professional.hrName} />
    </>
  ) : (
    <p style={{ color: "gray" }}>No professional details added yet</p>
  )}

  <button
    style={btnStyle}
    onClick={() => navigate(`/admin/professional/${id}`)}
  >
    {professional ? "Update Professional" : "Add Professional"}
  </button>
</Section>


<Section title="Finance">
  {finance ? (
    <>
      <Row label="Aadhar Card" value={finance.aadhaarNumber} />
      <Row label="PAN Card" value={finance.panCard} />
      <Row label="Bank" value={finance.bankName} />
      <Row label="Branch" value={finance.branch} />
      <Row label="IFSC" value={finance.ifscCode} />
      <Row label="CTC Breakup" value={finance.ctcBreakup} />
    </>
  ) : (
    <p style={{ color: "gray" }}>No finance details added yet</p>
  )}

  <button
    style={btnStyle}
    onClick={() => navigate(`/admin/finance/${id}`)}
  >
    {finance ? "Update Finance" : "Add Finance"}
  </button>
</Section>



     <Section title="Project">
  {project ? (
    <>
      <Row label="Project Code" value={project.projectCode} />
      <Row label="Start Date" value={project.startDate} />
      <Row label="End Date" value={project.endDate} />
      <Row label="Reporting Manager" value={project.reportingManager} />
      <Row label="Client" value={project.client} />
    </>
  ) : (
    <p style={{ color: "gray" }}>No project details added yet</p>
  )}

  <button
    style={btnStyle}
    onClick={() => navigate(`/admin/project/${id}`)}
  >
    {project ? "Update Project" : "Add Project"}
  </button>
</Section>


    </div>
  );
};

export default EmployeeProfileAdmin;

// helpers
const Section = ({ title, children }) => (
  <div style={{ marginBottom: "20px" }}>
    <h3>{title}</h3>
    {children}
  </div>
);
const btnStyle = {
  marginTop: "10px",
  padding: "6px 12px",
  background: "#1976d2",
  color: "#fff",
  border: "none",
  borderRadius: "4px",
  cursor: "pointer",
};

const Row = ({ label, value }) => (
  <p>
    <b>{label}:</b> {value || "-"}
  </p>
);
