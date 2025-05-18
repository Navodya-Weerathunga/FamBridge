import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import PregnantWomenService from "../../Service/Pregnant_Women_Service";

function PregnantWomenRequestList() {
    const workingArea = localStorage.getItem("workingArea");
    const [pregnantWomenInfo, setPregnantWomenInfo] = useState([]);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    console.log("Working area: ", workingArea);

    useEffect(() => {
        fetchPregnantWomenRequests();
    }, []);

    const fetchPregnantWomenRequests = async () => {
        try {
            const areas = workingArea.split(",").map(area => area.trim());
            let allResults = [];

            for (const area of areas) {
                const response = await PregnantWomenService.getAllRegistrationRequestDetails(area);
                console.log(`Raw API Response for ${area}:`, response);

                if (!response || typeof response !== 'object' || !Array.isArray(response)) {
                    console.error("Invalid API response for area:", area);
                    continue;
                }

                const objectValues = response.map(value =>
                    value.split(",").map(v => v.trim())
                );

                const mappedData = objectValues.map(value => ({
                    requestId: value[0],
                    firstName: value[1],
                    email: value[2],
                    nic: value[3],
                    phmArea: value[4],
                    assignMidwife: value[5],
                    contactNo: value[6],
                    requestDate: value[7],
                    status: value[8],
                }));

                allResults = [...allResults, ...mappedData];
            }

            setPregnantWomenInfo(allResults);
            console.log("Combined Mapped Data:", allResults);
        } catch (err) {
            console.error("Error fetching data:", err);
            setError("Error fetching data");
        }
    };

    const handleRegister = async (requestId) => {
        const confirm = window.confirm("Are you sure you want to register this person?");
        if (!confirm) return;

        try {
            const response = await PregnantWomenService.register(requestId);
            console.log("Registered successfully:", response);
            alert("Registered successfully!");
            fetchPregnantWomenRequests();
        } catch (error) {
            console.error("Error during registration:", error);
            alert("Error during registration.");
        }
    };

    return (
        <div className="table" style={{ margin: '0%' }}>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <h1 style={{ textAlign: 'center', color: '#B3817A', marginBottom: '2%' }}>
                Pregnant Women Registration Request List
            </h1>

            <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2' style={{ width: '100%', marginLeft: '1%', marginRight: '1%' }}>
                <table className="table">
                    <thead className="thead-dark">
                        <tr>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>ID</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>First Name</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Email</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>NIC</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>PHM Area</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Assign Midwife</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Contact No</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Request Date</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Request Status</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Proof</th>
                            <th style={{ backgroundColor: '#B3817A' }}></th>
                            <th style={{ backgroundColor: '#B3817A' }}></th>
                        </tr>
                    </thead>

                    <tbody>
                        {pregnantWomenInfo && pregnantWomenInfo.length > 0 ? (
                            pregnantWomenInfo.map((pregnantWomenRequest, index) => (
                                <tr key={pregnantWomenRequest.requestId || index}>
                                    <th scope="row">{pregnantWomenRequest.requestId}</th>
                                    <td>{pregnantWomenRequest.firstName}</td>
                                    <td>{pregnantWomenRequest.email}</td>
                                    <td>{pregnantWomenRequest.nic}</td>
                                    <td>{pregnantWomenRequest.phmArea}</td>
                                    <td>{pregnantWomenRequest.assignMidwife}</td>
                                    <td>{pregnantWomenRequest.contactNo}</td>
                                    <td>{pregnantWomenRequest.requestDate}</td>
                                    <td>{pregnantWomenRequest.status}</td>
                                    <td>
                                        <Link to={`/PregnancyProof/${pregnantWomenRequest.requestId}`}>
                                            <button style={{
                                                backgroundColor: '#B03788',
                                                borderColor: '#B03788',
                                                borderRadius: '10px',
                                                color: 'white',
                                                fontWeight: 'bold'
                                            }}>View</button>
                                        </Link>
                                    </td>
                                    <td>
                                        <button style={{
                                            backgroundColor: '#3580F0',
                                            borderColor: '#3580F0',
                                            borderRadius: '10px',
                                            color: 'white',
                                            fontWeight: 'bold'
                                        }}>Send</button>
                                    </td>
                                    <td>
                                        {pregnantWomenRequest.status !== "Registered" && (
                                            <button
                                                onClick={() => handleRegister(pregnantWomenRequest.requestId)}
                                                style={{
                                                    backgroundColor: '#FC8D7C',
                                                    borderColor: '#FC8D7C',
                                                    borderRadius: '10px',
                                                    color: 'white',
                                                    fontWeight: 'bold'
                                                }}
                                            >
                                                Register
                                            </button>
                                        )}
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="12" style={{ textAlign: "center" }}>
                                    No Request found.
                                </td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default PregnantWomenRequestList;
