import React,{useState, useEffect} from "react";
import AdminService from "../../Service/Admin_Service";
import { useNavigate } from "react-router-dom";


function MidwifeList() {
    const [midwifeInfo, setMidwifeInfo] = useState([]); 
    const [error, setError] = useState();

    const navigate = useNavigate();

    useEffect(() => {
        
        fetchMidwifeInfo();
    }, []);

    const fetchMidwifeInfo = async () => {
        try {
            const response = await AdminService.getAllMidwives();
            console.log("API Response:", response);
    
            if (response?.midwifeList && Array.isArray(response.midwifeList)) {
                setMidwifeInfo(response.midwifeList); 
            } else {
                throw new Error("Unexpected API response structure.");
            }
        } catch (err) {
            console.error("Error fetching midwives information:", err);
            setError(err.message || "An error occurred.");
        }
    };

    return(
        <div className="table" style={{margin:'3%'}}>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <h1 style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%'}}>Midwives' Details</h1>

            <div className="input-group mb-5" style={{ display: "flex", justifyContent: 'center' }}>
                <button type="submit" className="button1" 
                    style={{
                        backgroundColor:'#FC8D7C', 
                        borderColor: '#FC8D7C',
                        fontSize: 'large', 
                        width:'8%',
                        padding:'0.25%',
                        borderRadius: '10px',
                        color: 'white',
                        fontWeight: 'bold'}} onClick={() => navigate("/MidwifeRegistration")}>Register</button>
            </div>
            
            <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2' style={{width: '90%', marginLeft: '5%', marginRight: '5%'}}>
            <table className="table">
                <thead className="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">NIC</th>
                    <th scope="col">Medical Council No</th>
                    <th scope="col">Contact No</th>
                    <th scope="col">MOH</th>
                    <th scope="col">Work Area</th>
                    <th scope="col">Type</th>
                    <th scope="col">Registered Date</th>
                </tr>
                </thead>

                <tbody>
                {midwifeInfo && midwifeInfo.length > 0 ? (
                    midwifeInfo.map((midwife, index) => (
                        <tr key={midwife.midwifeId || index}>
                            <th scope="row">{midwife.midwifeId}</th>
                                <td>{midwife.fullName}</td>
                                <td>{midwife.nic}</td>
                                <td>{midwife.medicalCouncilNumber}</td>
                                <td>{midwife.contactNumber}</td>
                                <td>{midwife.mohArea}</td>
                                <td>{Array.isArray(midwife.workingArea) ? midwife.workingArea.join(", ") : midwife.workingArea}</td>
                                <td>{midwife.midwifeType}</td>
                                <td>{midwife.registeredDate}</td>
                        </tr>
                    ))
                    ) : (
                        <tr>
                            <td colSpan="8" style={{ textAlign: "center" }}>
                                No Midwives found.
                            </td>
                        </tr>
                    )}
                </tbody>
            </table>
            </div>
        </div>
    );
}

export default MidwifeList;