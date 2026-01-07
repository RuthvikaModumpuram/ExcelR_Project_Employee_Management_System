import { Routes, Route } from "react-router-dom";
import Sidebar from "../../components/Sidebar";
import Header from "../../components/Header";

import Profile from "./Profile";
import Personal from "./Personal";
import Professional from "./Professional";
import Finance from "./Finance";
import Project from "./Project";

const EmployeeDashboard = () => {
  return (
    <div style={{ display: "flex" }}>
      {/* LEFT SIDEBAR */}
      <Sidebar role="EMPLOYEE" />

      {/* RIGHT CONTENT */}
      <div style={{ flex: 1 }}>
        {/* TOP HEADER */}
        <Header title="Employee Dashboard" />

        {/* PAGE CONTENT */}
        <div style={{ padding: "20px" }}>
          <Routes>
            <Route path="/" element={<Profile />} />
            <Route path="profile" element={<Profile />} />
            <Route path="personal" element={<Personal />} />
            <Route path="professional" element={<Professional />} />
            <Route path="finance" element={<Finance />} />
            <Route path="project" element={<Project />} />
          </Routes>
        </div>
      </div>
    </div>
  );
};

export default EmployeeDashboard;
