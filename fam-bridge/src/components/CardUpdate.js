import { useEffect, useContext, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { FormContext } from '../../context/FormContext';
import { submitPregnancyData, getPregnancyData } from '../../Service/PregnancyService';
import BMIChart from './BMIChart';
import SFHChart from './SFHChart';
import './PregnancyCardPageOne.css';

const CardUpdate = () => {
  const { pcid } = useParams();
  const navigate = useNavigate();
  const { formData, updateFormData, updateBmiHistory } = useContext(FormContext);
  const [pregnancyData, setPregnancyData] = useState(null);

  useEffect(() => {
    const fetchPregnancyData = async () => {
      try {
        const data = await getPregnancyData(pcid);
        setPregnancyData(data);

        if (data && data.length > 0) {
          const record = data[0];
          Object.entries(record).forEach(([key, value]) => {
            updateFormData({ [key]: value });
          });
        }
      } catch (err) {
        console.error('Error fetching pregnancy data:', err);
      }
    };

    fetchPregnancyData();
  }, [pcid]);


  const handleChange = (e) => {
    const { name, value } = e.target;
    updateFormData({ [name]: value });
  };

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
      const isSameAsLast = lastEntry && lastEntry.x === currentEntry.x && lastEntry.y === currentEntry.y;
      if (!isSameAsLast) {
        updateBmiHistory(currentEntry);
      }
    }
  }, [formData.poa, formData.weight, formData.height, formData.bmi]);

  const handleSubmit = async () => {
    // Remove cid from the data before submitting
    const { cid, ...dataToSubmit } = formData;

    console.log("Submitting data:", dataToSubmit);

    try {
      await submitPregnancyData(dataToSubmit);
      alert('Data saved successfully!');
      navigate('/RegisteredPregnantWomen');
    } catch (err) {
      console.error('Submit error:', err);
      alert('Something went wrong. Try again.');
    }
  };
  const sfhData = pregnancyData?.map((item) => ({
    x: item.poa,
    y: item.fundalHeight
  }));
  if (!pregnancyData) {
    return <p>Loading pregnancy data...</p>;
  }


  return (
    <div className="form-container">

      <h1 className="form-title">Update Card Information</h1>

      <div className="form-group">
        <label>Visiting Date:</label>
        <input type="date" name="visitingDate" className="form-control" value={formData.visitingDate} onChange={handleChange} />
      </div>
      <br></br>
      <br></br>
      <h1 className="form-title">Update BMI Information</h1>
      <div className="title-group">
        <h5>BMI Calculation</h5>
      </div>

      <form>
        <div className="form-group">
          <label>POA (weeks):</label>
          <input
            type="number"
            name="poa"
            className="form-control"
            value={formData.poa || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Height (cm):</label>
          <input
            type="number"
            name="height"
            className="form-control"
            value={formData.height || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Weight (kg):</label>
          <input
            type="number"
            name="weight"
            className="form-control"
            value={formData.weight || ''}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>BMI:</label>
          <input
            type="text"
            name="bmi"
            className="form-control"
            value={formData.bmi || ''}

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
        <br></br>
        <h1 className="form-title">Update SFH Information</h1>

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
          data={sfhData}

        />

        <div className="btn-container">
          <button
            type="button"
            className="btn-primary"
            onClick={() => navigate('/pregnancy-card-page-two')}
            style={{ backgroundColor: '#e19c93' }}
          >
            Back
          </button>

          <button
            type="button"
            className="btn-primary"
            onClick={handleSubmit}
            style={{ backgroundColor: '#e19c93' }}
          >
            Save & Continue
          </button>
        </div>
      </form>
    </div>
  );
};

export default CardUpdate;
