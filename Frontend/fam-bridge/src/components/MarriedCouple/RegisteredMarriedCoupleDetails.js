import React, { useState, useEffect } from "react";
import {useParams } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function RegisteredMarriedCoupleDetails() {
    const [details, setDetails] = useState({
        firstName: '',
        lastName: '',
        email: '',
        contactNo: '',
        nic: '',
        dob: '',
        phmArea: '',
        assignMidwife: '',
        occupation: '',
        educationLevel: '',
        husbandFirstName: '',
        husbandLastName: '',
        husbandNic: '',
        husbandContactNo: '',
        husbandDob: '',
        husbandOccupation: '',
        husbandEducationLevel: '',
        marriedDate: '',
        marriageCertificateNo: '',
        marriagePlace: '',       
    });
    const [error, setError] = useState('');
    const { marriedCoupleId } = useParams();


    useEffect(() => {
        fetchMarriedCoupleDetails();
    }, [marriedCoupleId]);

    console.log("Married couple ID: ", marriedCoupleId)

    const fetchMarriedCoupleDetails = async () => {
        try {
            const response = await MarriedCoupleService.marriedCoupleDetailsById(marriedCoupleId);
            console.log("API Response:", response);
    
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

    return(

        <div className='Body1'>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h1 className="heading" style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%'}}>Married Couple Details</h1><br></br>
            <form >
        <div>
        
        <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Married Woman Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="firstName" className="input-group-text">First Name</label>
                <input type="text" className="form-control col-sm-6" id="firstName" name="firstName" defaultValue={details.firstName}  readOnly/>
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="lastName" className="input-group-text">Last Name</label>
                <input type="text" className="form-control col-sm-6" id="lastName" name="lastName" defaultValue={details.lastName}  readOnly/>
            </div>
            </div>
  
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="nic" className="input-group-text">NIC</label>
                <input type="text" className="form-control col-sm-6" id="nic" name="nic" defaultValue={details.nic}  readOnly/>
            </div>   
        </div>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="email" className="input-group-text">Email</label>
                <input type="text" className="form-control col-sm-6" id="email" name="email" defaultValue={details.email} readOnly/>
            </div>
            </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                <input type="tel" className="form-control col-sm-6" id="contactNo" name="contactNo" defaultValue={details.contactNo} readOnly/>
            </div>
            </div>
        </div>
  
        <div class="row">
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="dob" className="input-group-text">DOB</label>
                <input type="date" className="form-control col-sm-6" id="dob" name="dob" defaultValue={details.dob} readOnly />
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="phmArea" className="input-group-text">PHM Area</label>
                <input type="text" className="form-control col-sm-6" id="phmArea" name="phmArea" defaultValue={details.phmArea} readOnly/>
            </div>
            </div>
        </div>
  
        <div className="row">
            <div className="input-group mb-5">
                <label htmlFor="assignMidwife" className="input-group-text">Assign Midwife</label>
                <input type="text" className="form-control col-sm-6" id="assignMidwife" name="assignMidwife" defaultValue={details.assignMidwife} readOnly/>     
            </div>
        </div>
  
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="gnDivision" className="input-group-text">Occupation</label>
                <input type="text" className="form-control col-sm-6" id="occupation" name="occupation" defaultValue={details.occupation} readOnly />
            </div>
            </div>
  
            <div class="col">
              <div className="input-group mb-5">
                <label htmlFor="gnDivision" className="input-group-text">Education Level</label>
                <input type="text" className="form-control col-sm-6" id="educationLevel" name="educationLevel" defaultValue={details.educationLevel} readOnly/>
              </div>
            </div>
        </div>
  
      <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Spouse's Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandFirstName" className="input-group-text">First Name</label>
                <input type="text" className="form-control col-sm-6" id="husbandFirstName" name="husbandFirstName" defaultValue={details.husbandFirstName} readOnly/>
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandLastName" className="input-group-text">Last Name</label>
                <input type="text" className="form-control col-sm-6" id="husbanLastName" name="husbandLastName" defaultValue={details.husbandLastName} readOnly/>
            </div>
            </div>
  
        </div>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandNic" className="input-group-text">NIC</label>
                <input type="text" className="form-control col-sm-6" id="husbandNic" name="husbandNic" defaultValue={details.husbandNic} readOnly/>
            </div>   
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandDob" className="input-group-text">DOB</label>
                <input type="date" className="form-control col-sm-6" id="husbandDob" name="husbandDob" defaultValue={details.husbandDob} readOnly/>
            </div>
            </div>
        </div>
  
        <div class="row">
            <div class="col">
              <div className="input-group mb-5">
                <label htmlFor="gnDivision" className="input-group-text">Education Level</label>
                <input type="text" className="form-control col-sm-6" id="husbandEducationLevel" name="husbandEducationLevel" defaultValue={details.husbandEducationLevel} readOnly/>
              </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandOccupation" className="input-group-text">Occupation</label>
                <input type="text" className="form-control col-sm-6" id="husbandOccupation" name="husbandOccupation" defaultValue={details.husbandOccupation} readOnly/>
            </div>
            </div>
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="husbandContactNo" className="input-group-text">Contact Number</label>
                <input type="tel" className="form-control col-sm-6" id="husbandContactNo" name="husbandContactNo" defaultValue={details.husbandContactNo} readOnly/>
            </div>
        </div>
  
        <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Marriage Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="marriedDate" className="input-group-text">Married Date</label>
                <input type="date" className="form-control col-sm-6" id="marriedDate" name="marriedDate" defaultValue={details.marriedDate} readOnly/>
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="marriageCertificateNo" className="input-group-text">Marriage Certificate No</label>
                <input type="text" className="form-control col-sm-6" id="marriageCertificateNo" name="marriageCertificateNo" defaultValue={details.marriageCertificateNo} readOnly/>
            </div>
            </div>
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="marriagePlace" className="input-group-text">Married Place</label>
                <input type="text" className="form-control col-sm-6" id="marriagePlace" name="marriagePlace" defaultValue={details.marriagePlace} readOnly/>
            </div>
        </div>
       
            </div>
            </form>
  
            </div>
            </div>
  
    );
  }


export default RegisteredMarriedCoupleDetails;
