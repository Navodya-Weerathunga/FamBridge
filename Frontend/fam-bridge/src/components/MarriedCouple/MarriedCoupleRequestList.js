import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function MarriedCoupleRequestList() {
    const [marriedCoupleInfo, setMarriedCoupleInfo] = useState([]);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    const mohArea = localStorage.getItem("mohArea")
    console.log("MOH: ", mohArea)

    useEffect(() => {
        fetchMarriedCoupleRequests();
    }, [mohArea]);

    const fetchMarriedCoupleRequests = async () => {
        try {
            const response = await MarriedCoupleService.getAllRegistrationRequestDetails(mohArea);
            console.log("Raw API Response:", response);

            if (!response || typeof response !== 'object') {
                console.error("Invalid API response:", response);
                return;
            }

            // Assuming the API returns an array with a string that contains comma-separated values
            const objectValues = response.map(value =>
                value.split(",").map(v => v.trim())
            );

            console.log("Converted Array:", objectValues);

            // Adjust the logic here to handle address field correctly
            const mappedData = objectValues.map(value => {
                const requestId = value[0];
                const firstName = value[1];
                const lastName = value[2];
                const email = value[3];
                const nic = value[4];
                const mohArea = value[5];
                const gramaNiladhariDivision = value[6];
            
                const requestDate = value[value.length - 2];
                const requestStatus = value[value.length - 1];
            
                // Address is everything between index 7 and value.length - 2
                const addressParts = value.slice(7, value.length - 2);
                const fullAddress = addressParts.join(", ");
            
                return {
                    requestId,
                    firstName,
                    lastName,
                    email,
                    nic,
                    mohArea,
                    gramaNiladhariDivision,
                    address: fullAddress,
                    requestDate,
                    requestStatus
                };
            });

            setMarriedCoupleInfo(mappedData);
            console.log("Mapped Data:", mappedData);
        } catch (err) {
            console.error("Error fetching data:", err);
            setError("Error fetching data");
        }
    };

    return (
        <div className="table" style={{ margin: '0%' }}>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <h1 style={{ textAlign: 'center', color: '#B3817A', marginBottom: '2%' }}>Married Couple Registration Request List</h1>

            <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2' style={{ width: '100%', marginLeft: '1%', marginRight: '1%' }}>
                <table className="table">
                    <thead className="thead-dark">
                        <tr>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>ID</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>First Name</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Last Name</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>NIC</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>MOH Area</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>GN Division</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Address</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Email</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Request Date</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Request Status</th>
                            <th scope="col" style={{backgroundColor: '#B3817A'}}>Proof</th>
                            <th style={{backgroundColor: '#B3817A'}}></th>
                            <th style={{backgroundColor: '#B3817A'}}></th>
                        </tr>
                    </thead>

                    <tbody>
                        {marriedCoupleInfo && marriedCoupleInfo.length > 0 ? (
                            marriedCoupleInfo.map((marriedCoupleRequest, index) => (
                                <tr key={marriedCoupleRequest.requestId || index}>
                                    <th scope="row">{marriedCoupleRequest.requestId}</th>
                                    <td>{marriedCoupleRequest.firstName}</td>
                                    <td>{marriedCoupleRequest.lastName}</td>
                                    <td>{marriedCoupleRequest.nic}</td>
                                    <td>{marriedCoupleRequest.mohArea}</td>
                                    <td>{marriedCoupleRequest.gramaNiladhariDivision}</td>
                                    <td>{marriedCoupleRequest.address}</td>
                                    <td>{marriedCoupleRequest.email}</td>
                                    <td>{marriedCoupleRequest.requestDate}</td>
                                    <td>{marriedCoupleRequest.requestStatus}</td>
                                    <td><Link to={`/MarriageProof/${marriedCoupleRequest.requestId}`}><button style={{
                                        backgroundColor: '#B03788',
                                        borderColor: '#B03788',
                                        borderRadius: '10px',
                                        color: 'white',
                                        fontWeight: 'bold'
                                    }}>View</button ></Link></td>

                                    <td><button style={{
                                        backgroundColor: '#3580F0',
                                        borderColor: '#3580F0',
                                        borderRadius: '10px',
                                        color: 'white',
                                        fontWeight: 'bold'
                                    }}>Send</button></td>

                                    {marriedCoupleRequest.requestStatus.toLowerCase() !== "registered" && (
                                        <td>
                                            <Link to={`/MarriedCoupleRegistration/${marriedCoupleRequest.requestId}`}>
                                                <button style={{
                                                    backgroundColor: '#FC8D7C',
                                                    borderColor: '#FC8D7C',
                                                    borderRadius: '10px',
                                                    color: 'white',
                                                    fontWeight: 'bold'
                                                }}>
                                                    Register
                                                </button>
                                            </Link>
                                        </td>
                                    )}
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="10" style={{ textAlign: "center" }}>
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

export default MarriedCoupleRequestList;
