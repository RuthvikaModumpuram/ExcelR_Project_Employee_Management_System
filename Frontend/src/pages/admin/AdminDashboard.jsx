import { Routes, Route } from "react-router-dom";
import Sidebar from "../../components/Sidebar";
import Header from "../../components/Header";

import EmployeeList from "./EmployeeList";
import AddEmployee from "./AddEmployee";
import UpdateEmployee from "./UpdateEmployee";
import EmployeeProfileAdmin from "./EmployeeProfileAdmin";
import AdminPersonalForm from "./AdminPersonalForm";
import AdminProfessionalForm from "./AdminProfessionalForm";
import AdminFinanceForm from "./AdminFinanceForm";
import AdminProjectForm from "./AdminProjectForm";

const AdminDashboard = () => {
  return (
    <div style={{ display: "flex" }}>
      {/* LEFT SIDEBAR */}
      <Sidebar role="ADMIN" />

      {/* RIGHT CONTENT */}
      <div style={{ flex: 1 }}>
        {/* TOP HEADER */}
        <Header title="Admin Dashboard" />

        {/* PAGE CONTENT */}
        <div style={{ padding: "20px" }}>
          <Routes>
            <Route path="/" element={<EmployeeList />} />
            <Route path="add" element={<AddEmployee />} />
            <Route path="update/:id" element={<UpdateEmployee />} />
            <Route path="view/:id" element={<EmployeeProfileAdmin />} />
            <Route path="personal/:id" element={<AdminPersonalForm />} />
            <Route path="professional/:id" element={<AdminProfessionalForm />} />
            <Route path="finance/:id" element={<AdminFinanceForm />} />
            <Route path="project/:id" element={<AdminProjectForm />} />

          </Routes>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;
