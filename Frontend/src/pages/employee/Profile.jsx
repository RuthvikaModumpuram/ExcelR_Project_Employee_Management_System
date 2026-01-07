import { useEffect, useState } from "react";
import api from "../../api/axios";

const Profile = () => {
  const [employee, setEmployee] = useState(null);

  useEffect(() => {
    api.get("/employee/me").then(res => setEmployee(res.data));
  }, []);

  if (!employee) return <p>Loading...</p>;

  return (
    <div>
      <h2>My Profile</h2>
      <p><b>Name:</b> {employee.name}</p>
      <p><b>Email:</b> {employee.email}</p>
      <p><b>Department:</b> {employee.department}</p>
      <p><b>Salary:</b> {employee.salary}</p>
    </div>
  );
};

export default Profile;
