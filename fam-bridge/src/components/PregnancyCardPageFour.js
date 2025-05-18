import React, { useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { FormContext } from '../context/FormContext';
import { submitPregnancyData } from '../services/PregnancyService';
import './PregnancyCardPageOne.css';

const PregnancyCardPageFour = () => {
  const { formData, updateFormData } = useContext(FormContext);
  const navigate = useNavigate();
  const { pcid } = useParams();

  const handleChange = (e) => {
     
    const { name, value } = e.target;
    updateFormData({ [name]: value });
  };

  const handleSubmit = async () => {
    const dataToSubmit = {
      ...formData,
      pregnancyRecordNo: pcid, // <-- Fix is here
    };

    console.log("Final payload to be submitted:", dataToSubmit);

    try {
      await submitPregnancyData(dataToSubmit);
      alert('Data saved successfully!');
      navigate('/RegisteredPregnantWomen');
    } catch (err) {
      console.error('Submit error:', err);
      alert('Something went wrong. Try again.');
    }
  };




  return (
    <div className="form-container">
      <h1 className="form-title">Create Pregnancy Record</h1>
      <br></br>
      <div className="title-group">
        <h5>Birth and Emergency Preparedness Plan: Delivery</h5>
      </div>

      <form>
        <div className="form-group">
          <label>Intended Hospital:</label>
          <input type="text" name="intendedHospital" className="form-control" value={formData.intendedHospital} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Mode of Transport:</label>
          <input type="text" name="modeOfTransport" className="form-control" value={formData.modeOfTransport} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Average Cost:</label>
          <input type="number" name="averageCost" className="form-control" value={formData.averageCost} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Distance From Home:</label>
          <input type="number" name="distanceFromHome" className="form-control" value={formData.distanceFromHome} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Time Taken to Reach:</label>
          <input type="text" name="timeTakenToReach" className="form-control" value={formData.timeTakenToReach} onChange={handleChange} />
        </div>

        <div className="title-group">
          <h5>Birth and Emergency Preparedness Plan: In an Emergency</h5>
        </div>

        <div className="form-group">
          <label>Intended Hospital:</label>
          <input type="text" name="intendedHospitalEmergency" className="form-control" value={formData.intendedHospitalEmergency} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Mode of Transport:</label>
          <input type="text" name="modeOfTransportEmergency" className="form-control" value={formData.modeOfTransportEmergency} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Average Cost:</label>
          <input type="number" name="averageCostEmergency" className="form-control" value={formData.averageCostEmergency} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Distance From Home:</label>
          <input type="number" name="distanceFromHomeEmergency" className="form-control" value={formData.distanceFromHomeEmergency} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Time Taken to Reach:</label>
          <input type="text" name="timeTakenToReachEmergency" className="form-control" value={formData.timeTakenToReachEmergency} onChange={handleChange} />
        </div>

        <div className="btn-container">
          <button type="button" className="btn-primary" onClick={() => navigate(`/pregnancy-card-page-three/${pcid}`)} style={{ backgroundColor: '#e19c93' }}>
            Back
          </button>

          <button type="button" className="btn-primary" onClick={handleSubmit} style={{ backgroundColor: '#e19c93' }}>
            Submit
          </button>
        </div>
      </form>
    </div>
  );
};

export default PregnancyCardPageFour;
