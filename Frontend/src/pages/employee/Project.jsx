import { useEffect, useState } from "react";
import api from "../../api/axios";

const Project = () => {
  const [project, setProject] = useState(null);

  useEffect(() => {
    api.get("/project/me")
      .then(res => setProject(res.data))
      .catch(() => alert("Failed to load project details"));
  }, []);

  if (!project) return <p>Loading...</p>;

  return (
    <div>
      <h2>Project Details</h2>
      <p><b>Project Code:</b> {project.projectCode}</p>
      <p><b>Client:</b> {project.clientName}</p>
      <p><b>Start Date:</b> {project.startDate}</p>
      <p><b>End Date:</b> {project.endDate}</p>
      <p><b>Reporting Manager:</b> {project.reportingManager}</p>
    </div>
  );
};

export default Project;
