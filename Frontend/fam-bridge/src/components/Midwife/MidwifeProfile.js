import React, { useState, useEffect } from "react";
import { Link, useParams } from "react-router-dom";
import MidwifeService from "../../Service/Midwife_Service";

function MidwifeProfile() {
    const [details, setDetails] = useState({
        fullName: '',
        workEmail: '',
        contactNumber: '',
        nic: '',
        dob: '',
        workingArea: '',
        medicalCouncilNumber: '',
        province: '',
        district: '',
        address: '', 
        mohArea: '',     
    });
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


    return(

        <div className='Body1'>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h1 className="heading" style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%', fontWeight:'bolder'}}>Profile</h1><br></br>
            <form >
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
                    <input type="text" className="form-control col-sm-6" id="nic" name="nic" defaultValue={details.nic}  readOnly/>
                </div>  
            </div>

            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="dob" className="input-group-text">DOB</label>
                    <input type="text" className="form-control col-sm-6" id="dob" name="dob" defaultValue={details.dob}  readOnly/>
                </div> 
            </div>  
        </div>

        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="address" className="input-group-text">Address</label>
                <input type="text" className="form-control col-sm-6" id="address" name="address" defaultValue={details.address}  readOnly/>
            </div>   
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="workEmail" className="input-group-text">Email</label>
                <input type="text" className="form-control col-sm-6" id="workEmail" name="workEmail" defaultValue={workEmail} readOnly/>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                    <input type="tel" className="form-control col-sm-6" id="contactNo" name="contactNo" defaultValue={details.contactNumber} readOnly/>
                </div>
            </div>

            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="mCNo" className="input-group-text">Medical Council Number</label>
                    <input type="text" className="form-control col-sm-6" id="medicalCouncilNo" name="medicalCouncilNo" defaultValue={details.medicalCouncilNumber} readOnly/>
                </div>
            </div>
        </div>

        <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Working Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="address" className="input-group-text">Province</label>
                <input type="text" className="form-control col-sm-6" id="province" name="province" defaultValue={details.province}  readOnly/>
            </div> 
            </div>  

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="district" className="input-group-text">District</label>
                <input type="text" className="form-control col-sm-6" id="district" name="district" defaultValue={details.district}  readOnly/>
            </div> 
            </div> 
        </div>

        <div class="row">
            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="moh" className="input-group-text">MOH Area</label>
                    <input type="text" className="form-control col-sm-6" id="mohArea" name="mohArea" defaultValue={details.mohArea} readOnly />
                </div>
            </div>

            <div class="col">
                <div className="input-group mb-5">
                    <label htmlFor="area" className="input-group-text">Working Area(s)</label>
                    <input type="text" className="form-control col-sm-6" id="workingArea" name="workingArea" defaultValue={details.workingArea} readOnly />
                </div>
            </div>

        </div>


        <div className="input-group mb-10" style={{ display: "flex", justifyContent: "flex-end" }}>
                <Link to={`/MidwifeUpdateProfile/${workEmail}`}><button type="submit" className="button1" style={{
                        backgroundColor:'#FC8D7C', 
                        fontSize: 'large', 
                        padding:'1%',
                        borderRadius: '10px',
                        color: 'black',
                        borderColor:'#FC8D7C',
                        fontWeight: 'bold'}}>Update Profile</button></Link>
        </div>
       
        </div>
        </form>
  
        </div>
        </div>
  
    );
  }


export default MidwifeProfile;
