import React, { useState, useEffect } from "react";
import { Link, useNavigate} from "react-router-dom";
import MidwifeService from "../../Service/Midwife_Service";

function MidwifeUpdateProfile() {
    const [details, setDetails] = useState({
        workEmail: '',
        contactNumber: '',
        nic: '',
        dob: '',
        address: '',
        medicalCouncilNumber: '' 
    
    });
    const navigate = useNavigate();
    const [error, setError] = useState('');

    const workEmail = localStorage.getItem("workEmail")


    useEffect(() => {
        fetchMidwifeDetails();
    }, [workEmail]);

    console.log("Midwife workEmail from local storage: ", workEmail)

    const fetchMidwifeDetails = async () => {
        try {
            const response = await MidwifeService.profile(workEmail)
            console.log("API Response:", response);
    
            if (response && response.data) {
                setDetails(response.data.midwife);
            } else {
                setError('Empty or invalid response');
            }
        } catch (error) {
            setError('Failed to fetch data');
            console.error('Fetch error:', error);
        }

    };

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setDetails ((previousUserData) => ({
            ...previousUserData,
            [name]: value
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try{
            const response = await MidwifeService.updateProfile(workEmail, details);
            alert("You have successfully updated your details")
            navigate(`/MidwifeProfile`)
        }
        catch(error){
            console.error('Error updating user: ', error);
        }
    };


    return(

        <div className='Body1'>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h1 className="heading" style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%', fontWeight:'bolder'}}>Update Profile</h1><br></br>
            <form onSubmit={handleSubmit}>
        <div>
        
        <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Personal Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="fullName" className="input-group-text">Full Name</label>
                <input type="text" className="form-control col-sm-6" id="fullName" name="fullName" defaultValue={details.fullName}  readOnly/>
            </div>
        </div>
  
        <div class="row">
            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="nic" className="input-group-text">NIC</label>
                    <input type="text" className="form-control col-sm-6" id="nic" name="nic" value={details.nic}  onChange={handleInputChange}/>
                </div>  
            </div>

            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="dob" className="input-group-text">DOB</label>
                    <input type="text" className="form-control col-sm-6" id="dob" name="dob" value={details.dob}  onChange={handleInputChange}/>
                </div> 
            </div>  
        </div>

        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="address" className="input-group-text">Address</label>
                <input type="text" className="form-control col-sm-6" id="address" name="address" value={details.address}  onChange={handleInputChange}/>
            </div>   
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="workEmail" className="input-group-text">Email</label>
                <input type="text" className="form-control col-sm-6" id="workEmail" name="workEmail" value={workEmail} readOnly/>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                    <input type="tel" className="form-control col-sm-6" id="contactNumber" name="contactNumber" value={details.contactNumber} onChange={handleInputChange}/>
                </div>
            </div>

            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="mCNo" className="input-group-text">Medical Council Number</label>
                    <input type="text" className="form-control col-sm-6" id="medicalCouncilNo" name="medicalCouncilNo" value={details.medicalCouncilNumber} onChange={handleInputChange}/>
                </div>
            </div>
        </div>


        <div className="input-group mb-10" style={{ display: "flex", justifyContent: "flex-end" }}>
                <button type="submit" className="button1" style={{
                        backgroundColor:'#FC8D7C', 
                        fontSize: 'large', 
                        padding:'1%',
                        borderRadius: '10px',
                        color: 'black',
                        borderColor:'#FC8D7C',
                        fontWeight: 'bold'}}>Update</button>
        </div>
       
        </div>
        </form>
  
        </div>
        </div>
  
    );
  }


export default MidwifeUpdateProfile;
