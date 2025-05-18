import React, { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function RegisteredMarriedCouples() {
    const [rows, setRows] = useState([]);
    const [error, setError] = useState('');

    const workingArea = localStorage.getItem("workingArea");

    useEffect(() => {
        fetchMarriedCouples();
    }, []);

    console.log("Working area: ", workingArea);

    const fetchMarriedCouples = async () => {
        try {
            const areas = workingArea.split(",").map(area => area.trim());
            let allResults = [];

            for (const area of areas) {
                const response = await MarriedCoupleService.marriedCouples(area);
                if (response?.data && Array.isArray(response.data)) {
                    allResults = [...allResults, ...response.data];
                }
            }

            setRows(allResults);
        } catch (error) {
            setError('Failed to fetch data');
            console.error('Fetch error:', error);
        }
    };

    return (
        <div className="table" style={{ margin: '0%' }}>
            {error && <p style={{ color: "red" }}>{error}</p>}
            <h1 style={{ textAlign: 'center', color: '#B3817A', marginBottom: '2%' }}>Registered Married Couples</h1>

            <div className='col-sm-8 py-2 px-5 offset-2 shadow' id='Body2' style={{ width: '100%', marginLeft: '1%', marginRight: '1%' }}>
                <table className="table">
                    <thead className="thead-dark">
                        <tr>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>ID</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>First Name</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Last Name</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>NIC</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Email</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Contact No</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Registered Date</th>
                            <th style={{ backgroundColor: '#B3817A' }}></th>
                        </tr>
                    </thead>

                    <tbody>
                        {rows && rows.length > 0 ? (
                            rows.map((marriedCouple, index) => (
                                <tr key={marriedCouple.marriedCoupleId || index}>
                                    <th scope="row">{marriedCouple.marriedCoupleId}</th>
                                    <td>{marriedCouple.firstName}</td>
                                    <td>{marriedCouple.lastName}</td>
                                    <td>{marriedCouple.nic}</td>
                                    <td>{marriedCouple.email}</td>
                                    <td>{marriedCouple.contactNo}</td>
                                    <td>{marriedCouple.registeredDate}</td>
                                    <td>
                                        <Link to={`/RegisteredMarriedCoupleDetails/${marriedCouple.marriedCoupleId}`}>
                                            <button style={{
                                                backgroundColor: '#FC8D7C',
                                                borderColor: '#FC8D7C',
                                                borderRadius: '10px',
                                                color: 'white',
                                                fontWeight: 'bold'
                                            }}>View More</button>
                                        </Link>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="8" style={{ textAlign: "center" }}>
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

export default RegisteredMarriedCouples;
