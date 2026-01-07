import { useEffect, useState } from "react";
import api from "../../api/axios";

const Professional = () => {
  const [professional, setProfessional] = useState(null);

  useEffect(() => {
    api.get("/professional/me")
      .then(res => setProfessional(res.data))
      .catch(() => alert("Failed to load professional details"));
  }, []);

  if (!professional) return <p>Loading...</p>;

  return (
    <div>
      <h2>Professional Details</h2>
      <p><b>Office Phone:</b> {professional.officePhone}</p>
      <p><b>Office City:</b> {professional.officeCity}</p>
      <p><b>Reporting Manager:</b> {professional.reportingManager}</p>
      <p><b>HR Name:</b> {professional.hrName}</p>
    </div>
  );
};

export default Professional;
