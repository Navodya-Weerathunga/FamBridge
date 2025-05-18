import { useState } from "react";
import React from "react";
import { useNavigate } from "react-router-dom";
import AdminService from "../../Service/Admin_Service";

function MidwifeRegistration() {
    const navigate = useNavigate();
    const [formData, setFormData] = useState({
        fullName: '',
        nic: '',
        dob: '',
        province: '',
        district: '',
        address: '',
        contactNumber: '',
        email: '',
        medicalCouncilNumber: '',
        password: '',
        registeredDate: new Date().toISOString().split("T")[0],
        mohArea: '',
        workingArea: '',
        midwifeType: '',
        qualifications: '',
    });

    const provinceCityMap = {
        Central: ["Kandy", "Matale", "Nuwara Eliya"],
        Eastern: ["Ampara", "Batticaloa", "Trincomalee"],
        "North Central": ["Anuradhapura", "Polonnaruwa"],
        Northern: ["Jaffna", "Kilinochchi", "Mannar", "Mullaitivu", "Vavuniya"],
        "North Western": ["Kurunegala", "Puttalam"],
        Sabaragamuwa: ["Kegalle", "Ratnapura"],
        Southern: ["Galle", "Hambantota", "Matara"],
        Uva: ["Badulla", "Monaragala"],
        Western: ["Colombo", "Gampaha", "Kalutara"],
    };

    const cityMohAreaMap = {
        Colombo: [
            "Battaramulla", "Boralesgamuwa", "Dehiwala", "Egoda Uyana", "Gothatuwa", "Hanwella",
            "Homagama", "Kaduwela", "Kahathuduwa", "Kesbewa", "Kolonnawa", "Maharagama", 
            "Moratuwa", "Nugegoda", "Padukka", "Piliyandala", "Pitakotte", "Rathmalana"
        ]
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const updatedFormData = {
            ...formData,
            workingArea: formData.workingArea
            .split(',')
            .map(area => area.trim())
            .filter(area => area !== ''),
        };

        console.log(updatedFormData);

        try {
            await AdminService.register(updatedFormData);

            setFormData({
                fullName: '',
                nic: '',
                dob: '',
                province: '',
                district: '',
                address: '',
                contactNumber: '',
                email: '',
                medicalCouncilNumber: '',
                password: '',
                registeredDate: new Date().toISOString().split("T")[0],
                mohArea: '',
                workingArea: '',
                midwifeType: '',
                qualifications: '',
            });

            alert("Midwife Registered Successfully");
            navigate('/MidwivesList');
        } catch (error) {
            console.error('Error Registering Midwife: ', error);
            alert("An Error Occurred While Registering Midwife");
        }
    };

return (
    <div className='Body1'>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h1 className="heading" style={{ textAlign: 'center', color: '#B3817A' }}>Midwife Registration Form</h1><br />
            <form onSubmit={handleSubmit}>
                <div>
            
                    <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Personal Details</h4></div><br></br>

                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="fullName" className="input-group-text">Full Name</label>
                            <input type="text" className="form-control" id="fullName" name="fullName" value={formData.fullName} onChange={handleInputChange} />
                        </div>
                    </div>

            
                    <div className="row">
                        <div className="col">
                            <div className="input-group mb-5">
                                <label htmlFor="nic" className="input-group-text">NIC</label>
                                <input type="text" className="form-control" id="nic" name="nic" value={formData.nic} onChange={handleInputChange} />
                            </div>
                        </div>

                        <div className="col">
                            <div className="input-group mb-5">
                                <label htmlFor="medicalCouncilNumber" className="input-group-text">Medical Council Number</label>
                                <input type="text" className="form-control" id="medicalCouncilNumber" name="medicalCouncilNumber" value={formData.medicalCouncilNumber} onChange={handleInputChange} />
                            </div>
                        </div>
                    </div>

            
                    <div className="row">
                        <div className="input-group mb-5">
                            <label htmlFor="dob" className="input-group-text">Date of Birth</label>
                            <input type="date" className="form-control" id="dob" name="dob" value={formData.dob} onChange={handleInputChange} />
                        </div>
                    </div>

                <div className="row">
                    <div className="input-group mb-5">
                        <label htmlFor="address" className="input-group-text">Address</label>
                        <input type="text" className="form-control" id="address" name="address" value={formData.address} onChange={handleInputChange} />
                    </div>
                </div>

                <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Working Area Details</h4></div><br></br>

            <div className="row">
                <div className="col">
                    <div className="input-group mb-5">
                    <select id="province" className="form-select" name="province" value={formData.province} onChange={(e) => {
                        setFormData(prevData => ({ ...prevData, province: e.target.value, district: "" }));
                    }}>
                        <option value="" disabled>Select Province</option>
                        {Object.keys(provinceCityMap).map(prov => (
                        <option key={prov} value={prov}>{prov}</option>
                        ))}
                    </select>
                    </div>
                </div>

                <div className="col">
                    <div className="input-group mb-5">
                    <select id="district" className="form-select" name="district" value={formData.district} onChange={handleInputChange} disabled={!formData.province}>
                        <option value="" disabled>Select District</option>
                            {provinceCityMap[formData.province]?.map(district => (
                        <option key={district} value={district}>{district}</option>
                        ))}
                    </select>
                    </div>
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <div className="input-group mb-5">
                    <select id="mohArea" className="form-select" name="mohArea" value={formData.mohArea} onChange={handleInputChange} disabled={!formData.district}>
                        <option value="" disabled>Select MOH</option>
                            {cityMohAreaMap[formData.district]?.map(moh => (
                        <option key={moh} value={moh}>{moh}</option>
                        ))}
                    </select>
                    </div>
                </div>

                <div className="col">
                    <div className="input-group mb-5">
                        <label htmlFor="workingArea" className="input-group-text">Working Area(s)</label>
                        <input
                            type="text"
                            className="form-control"
                            id="workingArea"
                            name="workingArea"
                            placeholder="e.g. Area1, Area2"
                            value={formData.workingArea}
                            onChange={handleInputChange}
                        />
                    </div>
                </div>
            </div>

           
            <div className="row">
                <div className="col">
                    <div className="input-group mb-5">
                        <label htmlFor="email" className="input-group-text">Email</label>
                        <input type="email" className="form-control" id="email" name="email" value={formData.email} onChange={handleInputChange} />
                    </div>
                </div>

                <div className="col">
                    <div className="input-group mb-5">
                        <label htmlFor="password" className="input-group-text">Password</label>
                        <input type="password" className="form-control" id="password" name="password" value={formData.password} onChange={handleInputChange}
                            pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                            title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                        />
                    </div>
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <div className="input-group mb-5">
                        <label htmlFor="contactNumber" className="input-group-text">Contact Number</label>
                        <input type="tel" className="form-control" id="contactNumber" name="contactNumber" value={formData.contactNumber} onChange={handleInputChange} />
                    </div>
                </div>

                <div className="col">
                    <div className="input-group mb-5">
                        <label htmlFor="registeredDate" className="input-group-text">Register Date</label>
                        <input type="date" className="form-control" id="registeredDate" name="registeredDate" value={formData.registeredDate} onChange={handleInputChange} />
                    </div>
                </div>
            </div>

            <div className="row">
                <div className="col">
                    <div className="input-group mb-5">
                        <select id="qualifications" className="form-select" name="qualifications" value={formData.qualifications} onChange={handleInputChange}>
                            <option value="" disabled>Qualification</option>
                            <option value="A/L">A/L</option>
                            <option value="Diploma">Diploma</option>
                        </select>
                    </div>
                </div>

                <div className="col">
                    <div className="input-group mb-5">
                        <select id="midwifeType" className="form-select" name="midwifeType" value={formData.midwifeType} onChange={handleInputChange}>
                            <option value="" disabled>Type</option>
                            <option value="SPH Midwife">SPH Midwife</option>
                            <option value="Field Midwife">Field Midwife</option>
                        </select>
                    </div>
                </div>
            </div>

            <div className="input-group mb-5" style={{ display: "flex", justifyContent: "flex-end" }}>
                <button type="submit" className="button1"
                    style={{
                        backgroundColor: '#FC8D7C',
                        borderColor: '#FC8D7C',
                        fontSize: 'large',
                        width: '15%',
                        padding: '1%',
                        borderRadius: '10px',
                        color: 'white',
                        fontWeight: 'bold'
                    }}>
                Register
                </button>
            </div>

            </div>
        </form>
    </div>
    </div>
  );
}

export default MidwifeRegistration;
