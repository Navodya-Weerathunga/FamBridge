import React, { useContext } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './PregnancyCardPageOne.css';
import { FormContext } from '../context/FormContext';
import { submitPregnancyData } from '../services/PregnancyService';


const PregnancyCardPageOne = () => {
  const { pcid } = useParams('');
  const { formData, updateFormData } = useContext(FormContext);


  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    updateFormData({ [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault(); // Prevent the default form submit behavior
    try {
      // Ensure form data is valid
      if (!formData.pregnancyRecordNo) {
        alert("Pregnancy Record No is required");
        return;
      }

      console.log("Submitting form data:", formData); // Check form data

      // Send the form data to the backend
      const response = await submitPregnancyData(formData);

      // Handle successful submission
      if (response) {
        alert("Pregnancy data submitted successfully!");
        navigate('/pregnancy-card-page-two');
      }
    } catch (error) {
      console.error('Submit error:', error);
      alert('Failed to submit pregnancy data.');
    }
  };

  const handleNext = () => {
    navigate(`/pregnancy-card-page-two/${pcid}`);
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Create Pregnancy Record</h1>
      <form>
        <br></br>
        <div className="form-group">
          <label>Pregnancy Card ID:</label>
          <input type="number" name="pregnancyRecordNo" className="form-control" value={pcid} onChange={handleChange} required />
        </div>
        <div className="form-group">
          <label>Risk Type:</label>
          <select
            name="riskType"
            className="form-control"
            value={formData.riskType}
            onChange={handleChange}
            required
          >
            <option value="">Select Risk Type</option>
            <option value="Low">Low</option>
            <option value="Moderate">Moderate</option>
            <option value="High">High</option>
          </select>
        </div>

        <div className="form-group">
          <label>Blood Group:</label>
          <select
            name="bloodGroup" className="form-control" value={formData.bloodGroup} onChange={handleChange} required>
            <option value="">-- Select Blood Group --</option>
            <option value="A+">A+</option>
            <option value="A-">A-</option>
            <option value="B+">B+</option>
            <option value="B-">B-</option>
            <option value="AB+">AB+</option>
            <option value="AB-">AB-</option>
            <option value="O+">O+</option>
            <option value="O-">O-</option>
          </select>
        </div>

        <div className="form-group">
          <label>Allergies:</label>
          <input type="text" name="allergies" className="form-control" value={formData.allergies} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Field Clinic Name:</label>
          <input type="text" name="fieldClinicName" className="form-control" value={formData.fieldClinicName} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Hospital Clinic Name:</label>
          <input type="text" name="hospitalClinicName" className="form-control" value={formData.hospitalClinicName} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Consultant Obstetricians:</label>
          <input type="text" name="consultantObstetricians" className="form-control" value={formData.consultantObstetricians} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Antenatal Risk Conditions:</label>
          <input type="text" name="antenatalRiskConditions" className="form-control" value={formData.antenatalRiskConditions} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Consanguinity:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="consanguinity" value="true" checked={formData.consanguinity === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="consanguinity" value="false" checked={formData.consanguinity === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Rubella Immunization:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="rubellaImmunization" value="true" checked={formData.rubellaImmunization === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="rubellaImmunization" value="false" checked={formData.rubellaImmunization === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Pre Pregnancy Screening:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="prePregnancyScreening" value="true" checked={formData.prePregnancyScreening === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="prePregnancyScreening" value="false" checked={formData.prePregnancyScreening === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Preconceptional Folic Acid:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="preconceptionalFolicAcid" value="true" checked={formData.preconceptionalFolicAcid === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="preconceptionalFolicAcid" value="false" checked={formData.preconceptionalFolicAcid === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>History of Subfertility:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="historyOfSubfertility" value="true" checked={formData.historyOfSubfertility === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="historyOfSubfertility" value="false" checked={formData.historyOfSubfertility === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Planned Pregnancy:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="plannedPregnancy" value="true" checked={formData.plannedPregnancy === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="plannedPregnancy" value="false" checked={formData.plannedPregnancy === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Last Used Family Planning Methods:</label>
          <input type="text" name="lastUsedFamilyPlanningMethods" className="form-control" value={formData.lastUsedFamilyPlanningMethods} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Visiting Date:</label>
          <input type="date" name="visitingDate" className="form-control" value={formData.visitingDate} onChange={handleChange} />
        </div>

        <div className="title-group">
          <h5>Present Obstetric History</h5>
        </div>

        <div className="form-group">
          <label>Gravidity:</label>
          <input type="text" name="gravidity" className="form-control" value={formData.gravidity} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Age of the Youngest Child:</label>
          <input type="text" name="ageOfYoungestChild" className="form-control" value={formData.ageOfYoungestChild} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>LRMP:</label>
          <input type="date" name="LRMP" className="form-control" value={formData.LRMP} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>EDD:</label>
          <input type="date" name="EDD" className="form-control" value={formData.EDD} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Date of 40 Weeks Completion:</label>
          <input type="date" name="dateOf40Weeks" className="form-control" value={formData.dateOf40Weeks} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>US Corrected EDD:</label>
          <input type="date" name="usCorrectedEdd" className="form-control" value={formData.usCorrectedEdd} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>POA at Scan:</label>
          <input type="text" name="poaAtScan" className="form-control" value={formData.poaAtScan} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>POA at Registration:</label>
          <input type="text" name="poaAtRegistration" className="form-control" value={formData.poaAtRegistration} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Gestation Period:</label>
          <input type="date" name="gestationPeriod" className="form-control" value={formData.gestationPeriod} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Was a contraceptive method used before conception? If so, what:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="contraceptiveMethod" value="true" checked={formData.contraceptiveMethod === 'true'} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="contraceptiveMethod" value="false" checked={formData.contraceptiveMethod === 'false'} onChange={handleChange} />
              No
            </label>
          </div>
          {formData.contraceptiveMethod === 'true' && (
            <input type="text" name="contraceptiveDetails" className="form-control mt-2" value={formData.contraceptiveDetails} onChange={handleChange} placeholder="Specify" />
          )}
        </div>

        <div className="btn-container">
          <button type="button" className="btn-primary short-btn" onClick={handleNext} style={{ backgroundColor: '#e19c93' }}>
            Next
          </button>
        </div>
      </form>
    </div>
  );
};

export default PregnancyCardPageOne;
