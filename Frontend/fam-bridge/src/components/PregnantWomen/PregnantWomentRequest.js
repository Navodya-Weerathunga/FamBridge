import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";
import PregnantWomenService from "../../Service/Pregnant_Women_Service";

function PregnantWomenRequest() {
    const email = localStorage.getItem("email");
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const [formData, setFormData] = useState({
        pregnancyProof: null,
        appointmentDate: '',
        email,  
    });

    const [details, setDetails] = useState({
        firstName: '',
        contactNo: '',
        nic: '',
        phmArea: '',
        assignMidwife: '',
    });

    useEffect(() => {
        fetchMarriedCoupleDetails();
    }, [email]);

    const fetchMarriedCoupleDetails = async () => {
        try {
            const response = await MarriedCoupleService.userProfile(email);
            if (response && response.data) {
                setDetails(response.data);
            } else {
                setError('Empty or invalid response');
            }
        } catch (error) {
            setError('Failed to fetch data');
            console.error('Fetch error:', error);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleFileChange = (e) => {
        const file = e.target.files[0] || null;
        setFormData({
            ...formData,
            pregnancyProof: file
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Creating a new FormData instance
        const data = new FormData();

        // Adding form data values to FormData object
        Object.keys(formData).forEach(key => {
            if (formData[key] !== null && formData[key] !== '') {
                data.append(key, formData[key]);
            }
        });

        try {
            const response = await PregnantWomenService.request(data); // Passing FormData directly
            console.log('Response:', response);
            alert(response.message || 'Request submitted successfully!');
            navigate('/Home'); // Navigate to home page after success
        } catch (error) {
            console.error("Error:", error);
            alert("There was an error submitting your request.");
        }
    };

    return (
        <div className='Body1' style={{ marginTop: '2%' }}>
            <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
                <h2 className="heading" style={{ textAlign: 'center', color: '#3F5A61' }}>Pregnant Women Registration Request</h2><br />
                <form onSubmit={handleSubmit}>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="firstName" className="input-group-text">First Name</label>
                            <input type="text" className="form-control col-sm-6" id="firstName" name="firstName" defaultValue={details.firstName} readOnly />
                        </div>
                    </div>

                    <div className="row">
                        <div className="col">
                            <div className="input-group mb-5">
                                <label htmlFor="nic" className="input-group-text">NIC</label>
                                <input type="text" className="form-control col-sm-6" id="nic" name="nic" defaultValue={details.nic} readOnly />
                            </div>
                        </div>

                        <div className="col">
                            <div className="input-group mb-5">
                                <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                                <input type="tel" className="form-control col-sm-6" id="contactNumber" name="contactNumber" defaultValue={details.contactNo} readOnly />
                            </div>
                        </div>
                    </div>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="email" className="input-group-text">Email</label>
                            <input type="text" className="form-control col-sm-6" id="email" name="email" defaultValue={email} readOnly />
                        </div>
                    </div>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="phmArea" className="input-group-text">PHM Area</label>
                            <input type="text" className="form-control col-sm-6" id="phmArea" name="phmArea" defaultValue={details.phmArea} readOnly />
                        </div>
                    </div>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="appointmentDate" className="input-group-text">Appointment Date</label>
                            <input type="date" className="form-control" id="appointmentDate" name="appointmentDate" value={formData.appointmentDate} onChange={handleInputChange} required />
                        </div>
                    </div>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="pregnancyProof" className="input-group-text">Pregnancy Proof</label>
                            <input type="file" className="form-control" id="pregnancyProof" name="pregnancyProof" onChange={handleFileChange} accept=".pdf,.jpg,.jpeg,.png" required />
                        </div>
                    </div>

                    <div className="input-group mb-5" style={{ display: "flex", justifyContent: "flex-end" }}>
                        <button type="submit" className="button1" style={{
                            backgroundColor: '#729CA7',
                            fontSize: 'large',
                            width: '15%',
                            padding: '1%',
                            borderRadius: '10px',
                            color: 'black',
                            fontWeight: 'bold'
                        }}>Submit</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default PregnantWomenRequest;
