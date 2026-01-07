import { useEffect, useState } from "react";
import api from "../../api/axios";

const Personal = () => {
  const [personal, setPersonal] = useState(null);

  useEffect(() => {
    api.get("/personal/me")
      .then(res => setPersonal(res.data))
      .catch(() => alert("Failed to load personal details"));
  }, []);

  if (!personal) return <p>Loading...</p>;

  return (
    <div>
      <h2>Personal Details</h2>
      <p><b>Date of Birth:</b> {personal.dateOfBirth}</p>
      <p><b>Age:</b> {personal.age}</p>
      <p><b>Gender:</b> {personal.gender}</p>
      <p><b>Address:</b> {personal.address}</p>
    </div>
  );
};

export default Personal;
