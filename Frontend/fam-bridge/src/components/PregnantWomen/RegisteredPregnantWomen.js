import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import PregnantWomenService from "../../Service/Pregnant_Women_Service";

function RegisteredpregnantWomen() {
    const [rows, setRows] = useState([]);
    const [error, setError] = useState('');
    
    const workingArea = localStorage.getItem("workingArea");

    useEffect(() => {
        fetchPregnantWomen();
    }, []);

    console.log("Working area: ", workingArea);

    const fetchPregnantWomen = async () => {
        try {
            const areas = workingArea.split(",").map(area => area.trim());
            let allResults = [];

            for (const area of areas) {
                const response = await PregnantWomenService.pregnantWomen(area);
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
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Pregnancy Record No</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>First Name</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>NIC</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Email</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Contact No</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}>Registered Date</th>
                            <th scope="col" style={{ backgroundColor: '#B3817A' }}></th>
                        </tr>
                    </thead>

                    <tbody>
                        {rows && rows.length > 0 ? (
                            rows.map((pregnantWomen, index) => (
                                <tr key={pregnantWomen.pregnancyRecordNo || index}>
                                    <th scope="row">{pregnantWomen.pregnancyRecordNo}</th>
                                    <td>{pregnantWomen.firstName}</td>
                                    <td>{pregnantWomen.nic}</td>
                                    <td>{pregnantWomen.email}</td>
                                    <td>{pregnantWomen.contactNo}</td>
                                    <td>{pregnantWomen.registeredDate}</td>
                                    <td>
                                        <Link to={``}>
                                            <button style={{
                                                backgroundColor: '#FC8D7C',
                                                borderColor: '#FC8D7C',
                                                borderRadius: '10px',
                                                color: 'white',
                                                fontWeight: 'bold'
                                            }}>View Card</button>
                                        </Link>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="6" style={{ textAlign: "center" }}>
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

export default RegisteredpregnantWomen;
