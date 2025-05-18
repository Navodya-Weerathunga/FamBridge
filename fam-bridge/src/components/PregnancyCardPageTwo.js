import React, { useContext } from 'react';
import { useNavigate,useParams } from 'react-router-dom';
import './PregnancyCardPageOne.css';
import { FormContext } from '../context/FormContext';

const PregnancyCardPageTwo = () => {
  const {pcid}=useParams('');
  const { formData, updateFormData } = useContext(FormContext);

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type } = e.target;

    const parsedValue =
      type === "radio" && (value === "true" || value === "false")
        ? value === "true"
        : value;

    updateFormData({ [name]: parsedValue });
  };


  const handleNext = () => {
    navigate(`/pregnancy-card-page-three/${pcid}`);
  };

  return (
    <div className="form-container">
      <h1 className="form-title">Create Pregnancy Record</h1>
      <br></br>
      <div className="title-group">
        <h5>Personal Information</h5>
      </div>
      <form>
        <div className="form-group">
          <label>Wife's Age:</label>
          <input type="text" name="wifeAge" className="form-control" value={formData.wifeAge} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Wife's Highest Education:</label>
          <input type="text" name="wifeHighestEducation" className="form-control" value={formData.wifeHighestEducation} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Wife's Occupation:</label>
          <input type="text" name="wifeOccupation" className="form-control" value={formData.wifeOccupation} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Husband's Age:</label>
          <input type="text" name="husbandAge" className="form-control" value={formData.husbandAge} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Husband's Highest Education:</label>
          <input type="text" name="husbandHighestEducation" className="form-control" value={formData.husbandHighestEducation} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Husband's Occupation:</label>
          <input type="text" name="husbandOccupation" className="form-control" value={formData.husbandOccupation} onChange={handleChange} />
        </div>

        <div className="title-group">
          <h5>Family History</h5>{/* second title */}
        </div>

        <div className="form-group">
          <label>diabetes Mellitus:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="diabetesMellitus" value={true} checked={formData.diabetesMellitus === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="diabetesMellitus" value={false} checked={formData.diabetesMellitus === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Hypertension:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="hypertension" value={true} checked={formData.hypertension === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="hypertension" value={false} checked={formData.hypertension === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Haematological Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="haematologicalDiseases" value={true} checked={formData.haematologicalDiseases === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="haematologicalDiseases" value={false} checked={formData.haematologicalDiseases === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Twin/ Multiple Pregnancies:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="twinOrMultiplePregnancies" value={true} checked={formData.twinOrMultiplePregnancies === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="twinOrMultiplePregnancies" value={false} checked={formData.twinOrMultiplePregnancies === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>others:</label>
          <input type="text" name="others" className="form-control" value={formData.others} onChange={handleChange} />
        </div>

        <div className="title-group">
          <h5>Medical/ Surgical History</h5> {/*third title*/}
        </div>

        <div className="form-group">
          <label>Diabetes:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="diabetes" value={true} checked={formData.diabetes === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="diabetes" value={false} checked={formData.diabetes === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Hypertension:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="hypertensionMedHis" value={true} checked={formData.hypertensionMedHis === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="hypertensionMedHis" value={false} checked={formData.hypertensionMedHis === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Cardiac Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="cardiacDiseases" value={true} checked={formData.cardiacDiseases === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="cardiacDiseases" value={false} checked={formData.cardiacDiseases === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Renal Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="renalDiseases" value={true} checked={formData.renalDiseases === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="renalDiseases" value={false} checked={formData.renalDiseases === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Hepatic Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="hepaticDiseases" value={true} checked={formData.hepaticDiseases === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="hepaticDiseases" value={false} checked={formData.hepaticDiseases === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Psychiatric Illnesses:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="psychiatricIllnesses" value={true} checked={formData.psychiatricIllnesses === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="psychiatricIllnesses" value={false} checked={formData.psychiatricIllnesses === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Epilepsy:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="epilepsy" value={true} checked={formData.epilepsy === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="epilepsy" value={false} checked={formData.epilepsy === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Malignancies:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="malignancies" value={true} checked={formData.malignancies === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="malignancies" value={false} checked={formData.malignancies === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Haematological Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="haematologicalDiseasesMedHis" value={true} checked={formData.haematologicalDiseasesMedHis === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="haematologicalDiseasesMedHis" value={false} checked={formData.haematologicalDiseasesMedHis === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Tuberculosis:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="tuberculosis" value={true} checked={formData.tuberculosis === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="tuberculosis" value={false} checked={formData.tuberculosis === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Thyroid Diseases:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="thyroidDiseases" value={true} checked={formData.thyroidDiseases === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="thyroidDiseases" value={false} checked={formData.thyroidDiseases === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="form-group">
          <label>Bronchial Asthma:</label>
          <div className="radio-group">
            <label>
              <input type="radio" name="bronchialAsthma" value={true} checked={formData.bronchialAsthma === true} onChange={handleChange} />
              Yes
            </label>
            <label>
              <input type="radio" name="bronchialAsthma" value={false} checked={formData.bronchialAsthma === false} onChange={handleChange} />
              No
            </label>
          </div>
        </div>

        <div className="title-group">
          <h5>Past Obstetric History</h5> {/*fourth title*/}
        </div>

        <div className="form-group">
          <label>Pregnancy:</label>
          <input type="text" name="pregnancy" className="form-control" value={formData.pregnancy} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Antenatal Complications:</label>
          <input type="text" name="antenatalComplications" className="form-control" value={formData.antenatalComplications} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Place of Delivery:</label>
          <input type="text" name="placeOfDelivery" className="form-control" value={formData.placeOfDelivery} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Mode of Delivery:</label>
          <input type="text" name="modeOfDelivery" className="form-control" value={formData.modeOfDelivery} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Outcome:</label>
          <input type="text" name="outcome" className="form-control" value={formData.outcome} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Birth Weight:</label>
          <input type="number" name="brithWeight" className="form-control" value={formData.brithWeight} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Postnatal Complications:</label>
          <input type="text" name="postnatalComplications" className="form-control" value={formData.postnatalComplications} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Sex of Child:</label>
          <input type="text" name="sexOfChild" className="form-control" value={formData.sexOfChild} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Age of Child:</label>
          <input type="number" name="ageOfChild" className="form-control" value={formData.ageOfChild} onChange={handleChange} required />
        </div>

        <div className="btn-container">
          <button type="button" className="btn-primary" style={{ backgroundColor: '#e19c93' }} onClick={() => navigate(`/pregnancy-card-page-one/${pcid}`)}>
            Back
          </button>
          <button type="button" className="btn-primary" style={{ backgroundColor: '#e19c93' }} onClick={handleNext}>
            Next
          </button>
        </div>

      </form>
    </div>
  );
};

export default PregnancyCardPageTwo;
