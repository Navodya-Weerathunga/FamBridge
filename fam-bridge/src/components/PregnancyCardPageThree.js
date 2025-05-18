import { useEffect, useContext } from 'react';
import BMIChart from './BMIChart';
import SFHChart from './SFHChart';
import { useNavigate,useParams } from 'react-router-dom';
import './PregnancyCardPageOne.css';
import { FormContext } from '../context/FormContext';

const PregnancyCardPageThree = () => {
  const {pcid}=useParams('');
  const { formData, updateFormData, updateBmiHistory } = useContext(FormContext);

  const handleChange = (e) => {
    const { name, value } = e.target;
    updateFormData({ [name]: value });
  };

  const navigate = useNavigate();

  useEffect(() => {
    const { poa, weight, height, bmi } = formData;

    if (weight && height) {
      const h = parseFloat(height) / 100;
      const calculatedBmi = (parseFloat(weight) / (h * h)).toFixed(2);

      if (calculatedBmi !== bmi) {
        updateFormData({ bmi: calculatedBmi });
      }
    }


    if (poa && weight) {
      const lastEntry = formData.bmiHistory?.[formData.bmiHistory.length - 1];
      const currentEntry = { x: Number(poa), y: Number(weight) };

      const isSameAsLast =
        lastEntry &&
        lastEntry.x === currentEntry.x &&
        lastEntry.y === currentEntry.y;

      if (!isSameAsLast) {
        updateBmiHistory(currentEntry);
      }
    }
  }, [formData.poa, formData.weight, formData.height]);



  return (
    <div className="form-container">
      <h1 className="form-title">Create Pregnancy Record</h1>
      <br></br>
      <div className="title-group"> {/* first title */}
        <h5>BMI Calculation</h5>
      </div>
      <form>
        <div className="form-group">
          <label>POA (weeks):</label>
          <input type="number" name="poa" className="form-control" value={formData.poa} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Height (cm):</label>
          <input
            type="number"
            name="height"
            className="form-control"
            value={formData.height}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Weight (kg):</label>
          <input
            type="number"
            name="weight"
            className="form-control"
            value={formData.weight}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>BMI:</label>
          <input
            type="text"
            name="bmi"
            className="form-control"
            value={formData.bmi}
            readOnly
          />
        </div>

        {formData.bmi && (
          <p><strong>Calculated BMI:</strong> {formData.bmi}</p>
        )}

        <BMIChart
          poa={Number(formData.poa)}
          weight={Number(formData.weight)}
          height={Number(formData.height)}
          bmi={formData.bmi}
          bmiHistory={formData.bmiHistory}
        />
        <br></br>

        <div className="title-group"> {/* first title */}
          <h5>SFH Details</h5>
        </div>

        <div className="form-group">
          <label>SFH Date:</label>
          <input type="date" name="sfhDate" className="form-control" value={formData.sfhDate} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Fundal Height:</label>
          <input type="number" name="fundalHeight" className="form-control" value={formData.fundalHeight} onChange={handleChange} />
        </div>

        <SFHChart
          poa={Number(formData.poa)}
          fundalHeight={Number(formData.fundalHeight)}

        />
        <div className="btn-container">
          <button
            type="button"
            className="btn-primary"
            onClick={() => navigate(`/pregnancy-card-page-two/${pcid}`)}
            style={{ backgroundColor: '#e19c93' }}
          >
            Back
          </button>

          <button
            type="button"
            className="btn-primary"
            onClick={() => navigate(`/pregnancy-card-page-four/${pcid}`)}
            style={{ backgroundColor: '#e19c93' }}>

            Next
          </button>

        </div>
      </form>
    </div>
  );
};

export default PregnancyCardPageThree;
