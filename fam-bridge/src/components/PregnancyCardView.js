import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getPregnancyData } from '../services/PregnancyService';
import BMIChart from './BMIChart';
import './PregnancyCardPageOne.css';
import SFHChart from './SFHChart';

const PregnancyCardView = () => {
  const { pcid } = useParams();
  const navigate = useNavigate();
  const [pregnancyData, setPregnancyData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);

  useEffect(() => {
    const fetchPregnancyData = async () => {
      try {
        const data = await getPregnancyData(pcid);
        setPregnancyData(data || []);
      } catch (err) {
        console.error('Error fetching pregnancy data:', err);
      }
    };

    fetchPregnancyData();
  }, [pcid]);

  const handleNextPage = () => {
    setCurrentPage((prev) => prev + 1);
  };

  const handlePreviousPage = () => {
    setCurrentPage((prev) => prev - 1);
  };

  const renderRadioButtons = (value) => (
    <div>
      <label>
        <input type="radio" disabled checked={value === true} /> Yes
      </label>
      <label>
        <input type="radio" disabled checked={value === false} /> No
      </label>
    </div>
  );

  if (!pregnancyData.length) {
    return <div>Loading...</div>;
  }

  const record = pregnancyData[0];
  console.log("p", pregnancyData)
  return (
    <div className="form-container">
      <h1 className="form-title" style={{ color: 'rgb(63,90,97)' }}>Pregnancy Record - Read-Only View</h1>


      {currentPage === 1 && (
        <div>
          <br></br>
          <div className="title-group">
            <h5>Basic Information</h5>
          </div>
          <form>
            <div className="form-group">
              <label>PCID:</label>
              <input type="text" name="pcid" className="form-control" value={pcid} readOnly />
            </div>
            <div className="form-group">
              <label>Risk Type:</label>
              <input type="text" name="riskType" className="form-control" value={record.riskType} readOnly />
            </div>
            <div className="form-group">
              <label>Blood Group:</label>
              <input type="text" name="bloodGroup" className="form-control" value={record.bloodGroup} readOnly />
            </div>
            <div className="form-group">
              <label>Allergies:</label>
              <input type="text" name="allergies" className="form-control" value={record.allergies} readOnly />
            </div>
            <div className="form-group">
              <label>Field Clinic Name:</label>
              <input type="text" name="fieldClinicName" className="form-control" value={record.fieldClinicName} readOnly />
            </div>
            <div className="form-group">
              <label>Hospital Clinic Name:</label>
              <input type="text" name="hospitalClinicName" className="form-control" value={record.hospitalClinicName} readOnly />
            </div>
            <div className="form-group">
              <label>Consultant Obstetricians:</label>
              <input type="text" name="consultantObstetricians" className="form-control" value={record.consultantObstetricians} readOnly />
            </div>
          </form>
          <div className="btn-container">
            <button type="button" className="btn-primary" style={{ backgroundColor: 'rgb(114,156,167)' }} onClick={handleNextPage}>Next</button>
          </div>
        </div>
      )}


      {currentPage === 2 && (
        <div>
          <br></br>
          <div className="title-group">
            <h5>Visiting Information</h5>
          </div>
          {pregnancyData.length > 0 ? (
            <table className="table table-bordered table-hover shadow container">
              <thead>
                <tr className="text-center">

                  <th style={{ textAlign: 'center' }}>Visit Date</th>

                </tr>
              </thead>
              <tbody className="text-center">
                {pregnancyData.map((entry, index) => (
                  <tr key={index}>

                    <td style={{ textAlign: 'center' }}>{entry.visitingDate}</td>

                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No Visit history found.</p>
          )}
          <br></br>
          <div className="title-group">
            <h5>Antenatal Information</h5>
          </div>
          <form>
            <div className="form-group">
              <label>Antenatal Risk Conditions:</label>
              <input type="text" name="antenatalRiskConditions" className="form-control" value={record.antenatalRiskConditions} readOnly />
            </div>
            <div className="form-group">
              <label>Consanguinity:</label>
              {renderRadioButtons(record.consanguinity === true || record.consanguinity === 'true')}
            </div>
            <div className="form-group">
              <label>Rubella Immunization:</label>
              {renderRadioButtons(record.rubellaImmunization  === true || record.rubellaImmunization=== 'true')}
            </div>
            <div className="form-group">
              <label>Pre-Pregnancy Screening:</label>
              {renderRadioButtons(record.prePregnancyScreening === true || record.prePregnancyScreening === 'true')}
            </div>

          </form>
          <div className="btn-container">
            <button type="button" className="btn-primary" style={{ backgroundColor: 'rgb(114,156,167)' }} onClick={handlePreviousPage}>Back</button>
            <button type="button" className="btn-primary" style={{ backgroundColor: 'rgb(114,156,167)' }} onClick={handleNextPage}>Next</button>
          </div>
        </div>
      )}


      {currentPage === 3 && (
        <div>
          <br></br>
          <div className="title-group">
            <h5>BMI & Physical Information</h5>
          </div>

          {pregnancyData.length > 0 ? (
            <table className="table table-bordered table-hover shadow container">
              <thead>
                <tr className="text-center">
                  <th style={{ textAlign: 'center' }}>POA</th>
                  <th style={{ textAlign: 'center' }}>Height</th>
                  <th style={{ textAlign: 'center' }}>Weight</th>
                  <th style={{ textAlign: 'center' }}>BMI</th>
                </tr>
              </thead>
              <tbody className="text-center">
                {pregnancyData.map((entry, index) => (
                  <tr key={index}>
                    <td style={{ textAlign: 'center' }}>{entry.poa}</td>
                    <td style={{ textAlign: 'center' }}>{entry.height}</td>
                    <td style={{ textAlign: 'center' }}>{entry.weight}</td>
                    <td style={{ textAlign: 'center' }}>{entry.bmi}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No BMI history found.</p>
          )}

          <BMIChart
            bmiHistory={pregnancyData.map(entry => ({
              x: Number(entry.poa),
              y: Number(entry.weight),
            }))}
          />
          <br></br>
          <div className="title-group">
            <h5>SFH Information</h5>
          </div>


          {pregnancyData.length > 0 ? (
            <table className="table table-bordered table-hover shadow container mt-4">
              <thead>
                <tr className="text-center">
                  <th style={{ textAlign: 'center' }}>POA</th>
                  <th style={{ textAlign: 'center' }}>Fundal Height</th>
                  <th style={{ textAlign: 'center' }}>Date</th>
                </tr>
              </thead>
              <tbody className="text-center">
                {pregnancyData.map((entry, index) => (
                  <tr key={index}>
                    <td style={{ textAlign: 'center' }}>{entry.poa}</td>
                    <td style={{ textAlign: 'center' }}>{entry.fundalHeight}</td>
                    <td style={{ textAlign: 'center' }}>{entry.sfhDate}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          ) : (
            <p>No SFH history found.</p>
          )}
          <SFHChart
            data={pregnancyData.map(entry => ({
              x: Number(entry.poa),
              y: Number(entry.fundalHeight),
            }))}
          />

          <div className="btn-container">
            <button type="button" style={{ backgroundColor: 'rgb(114,156,167)' }} className="btn-primary" onClick={handlePreviousPage}>
              Back
            </button>
            <button type="button" style={{ backgroundColor: 'rgb(114,156,167)' }} className="btn-primary" onClick={handleNextPage}>
              Next
            </button>
          </div>
        </div>
      )}



      {currentPage === 4 && (
        <div>
          <br></br>
          <div className="title-group">
            <h5>Delivery and Emergency Preparedness Plan</h5>
          </div>
          <form>
            <div className="form-group">
              <label>Intended Hospital:</label>
              <input type="text" name="intendedHospital" className="form-control" value={record.intendedHospital} readOnly />
            </div>
            <div className="form-group">
              <label>Mode of Transport:</label>
              <input type="text" name="modeOfTransport" className="form-control" value={record.modeOfTransport} readOnly />
            </div>
            <div className="form-group">
              <label>Average Cost:</label>
              <input type="number" name="averageCost" className="form-control" value={record.averageCost} readOnly />
            </div>
            <div className="form-group">
              <label>Distance From Home:</label>
              <input type="number" name="distanceFromHome" className="form-control" value={record.distanceFromHome} readOnly />
            </div>
            <div className="form-group">
              <label>Time Taken to Reach:</label>
              <input type="text" name="timeTakenToReach" className="form-control" value={record.timeTakenToReach} readOnly />
            </div>
          </form>
          <br></br>
          <div className="title-group">
            <h5>Emergency Preparedness Plan</h5>
          </div>
          <form>
            <div className="form-group">
              <label>Intended Hospital (Emergency):</label>
              <input type="text" name="intendedHospitalEmergency" className="form-control" value={record.intendedHospitalEmergency} readOnly />
            </div>
            <div className="form-group">
              <label>Mode of Transport (Emergency):</label>
              <input type="text" name="modeOfTransportEmergency" className="form-control" value={record.modeOfTransportEmergency} readOnly />
            </div>
            <div className="form-group">
              <label>Average Cost (Emergency):</label>
              <input type="number" name="averageCostEmergency" className="form-control" value={record.averageCostEmergency} readOnly />
            </div>
            <div className="form-group">
              <label>Distance From Home (Emergency):</label>
              <input type="number" name="distanceFromHomeEmergency" className="form-control" value={record.distanceFromHomeEmergency} readOnly />
            </div>
            <div className="form-group">
              <label>Time Taken to Reach (Emergency):</label>
              <input type="text" name="timeTakenToReachEmergency" className="form-control" value={record.timeTakenToReachEmergency} readOnly />
            </div>
          </form>

          <div className="btn-container">
            <button type="button" style={{ backgroundColor: 'rgb(114,156,167)' }} className="btn-primary" onClick={handlePreviousPage}>Back</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default PregnancyCardView;
