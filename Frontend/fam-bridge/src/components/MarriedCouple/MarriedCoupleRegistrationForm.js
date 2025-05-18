import { useState, useEffect } from "react";
import React from "react";
import { useNavigate, useParams } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";
import MidwifeService from "../../Service/Midwife_Service";
import './FormCSS.css';

function MarriedCoupleRegistrationForm() {
    const { requestId } = useParams();
    console.log("requestId: ", requestId)

    const navigate = useNavigate();
    const [splitValues, setSplitValues] = useState ({});
    const [basicData,setBasicData]=useState([])
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [userNic, setUserNic] = useState("");
    const [moh, setMoh] = useState("");
    const [division, setDivision] = useState("");
    const [address, setAddress] = useState("");
    const [date, setDate] = useState("");
    const [assignMidwife, setAssignMidwife] = useState([]);

    const workingArea = localStorage.getItem("workingArea")

    const [formData, setFormData] = useState({
        nic: userNic,
        dob: '',
        phmArea: workingArea,
        occupation: '',
        assignMidwife: '',
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

    useEffect(() => {
        setFormData((prevFormData) => ({
            ...prevFormData,
            nic: userNic,  // Ensure the correct NIC is set in formData
        }));
    }, [userNic]);

    useEffect(() => {
        fetchMarriedCoupleRequests();
    }, []);

    useEffect(() => {
        if (formData.phmArea && formData.phmArea.trim() !== "") {
            fetchMidwivesByArea();
        }
    }, [formData.phmArea]);

    useEffect(() => {
        console.log("Updated assignMidwife:", assignMidwife);
    }, [assignMidwife]);

    const fetchMarriedCoupleRequests = async () => {
        try {
            const response = await MarriedCoupleService.getAllRegistrationRequestDetailsById(requestId);
            console.log("Raw API Response:", response);
    
            if (!response || typeof response !== 'object') {
                console.error("Invalid API response:", response);
                return;
            }
    
            const objectValues = Object.values(response);
            console.log("Converted Array:", objectValues);

            const splitValues = objectValues.map(value => 
                typeof value === 'string' ? value.split(",").map(v => v.trim()) : value
            );

            setSplitValues(splitValues);
            console.log("Split Values",splitValues)

           
            
        } catch (err) {
            console.error("Error fetching data:", err);
        }
    };

    const fetchMidwivesByArea = async () => {
        try {
            const response = await MidwifeService.getMidwifeByArea(formData.phmArea);
            setAssignMidwife(response);
            console.log("Fetched midwives:", response);
        } catch (err) {
            console.error("Error fetching midwives:", err);
        }
    };
    

    useEffect(() => {
        if (splitValues.length > 0) {
            setFirstName(splitValues[0][0]);
            setLastName(splitValues[0][1]);
            setEmail(splitValues[0][2]);
            setUserNic(splitValues[0][3]);
            setMoh(splitValues[3]);
            setDivision(splitValues[4]);
            setAddress(splitValues[0][6]);
            setDate(splitValues[6]);
        }
    }, [splitValues]);

    const handleFetchChange = (event) => {
        const { name, value } = event.target;
        setBasicData((prevData) => {
            const newData = [...prevData];
            switch (name) {
                case "firstName":
                    newData[0] = value; 
                    break;
                case "lastName":
                    newData[1] = value;
                    break;
                case "userNic":
                    newData[3] = value;
                    break;
                default:
                    break;
            }
    
            return newData;
        });
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        console.log("Submitting Form:", formData);

        try {
            const response = await MarriedCoupleService.registration(formData);
            setFormData({
                nic: '',
                dob: '',
                phmArea: '',
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
            console.log("NIC in formData:", formData.nic);

            alert(response.message);
            navigate('/MarriedCoupleRequests')

        } catch (error) {
            console.error('Error Registering Married Couple:', error);
            alert("An Error Occurred While Registering Married Couple");
        }
    };

    return(

      <div className='Body1'>
      <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
          <h1 className="heading" style={{textAlign: 'center', color: '#B3817A', marginBottom: '2%'}}>Married Couple Registration Form</h1><br></br>
          <form onSubmit={handleSubmit} >
      <div>
      
      <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Married Woman Details</h4></div><br></br>

      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="firstName" className="input-group-text">First Name</label>
              <input type="text" className="form-control col-sm-6" id="firstName" name="firstName" value={firstName} onChange={handleFetchChange} readOnly/>
          </div>
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="lastName" className="input-group-text">Last Name</label>
              <input type="text" className="form-control col-sm-6" id="lastName" name="lastName" value={lastName} onChange={handleInputChange} readOnly/>
          </div>
          </div>

      </div>

      <div class="row">
          <div className="input-group mb-5">
              <label htmlFor="nic" className="input-group-text">NIC</label>
              <input type="text" className="form-control col-sm-6" id="userNic" name="userNic" value={userNic} onChange={handleInputChange} readOnly/>
          </div>   
      </div>

      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="email" className="input-group-text">Email</label>
              <input type="text" className="form-control col-sm-6" id="email" name="email" value={email} onChange={handleInputChange}readOnly/>
          </div>
          </div>
      </div>

      <div class="row">

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="dob" className="input-group-text">DOB</label>
              <input type="date" className="form-control col-sm-6" id="dob" name="dob" value={formData.dob} onChange={handleInputChange}/>
          </div>
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="phmArea" className="input-group-text">PHM Area</label>
              <input type="text" className="form-control col-sm-6" id="phmArea" name="phmArea" value={formData.phmArea} readOnly onBlur={fetchMidwivesByArea}/>
          </div>
          </div>
      </div>

      <div className="row">
            <div className="input-group mb-5">
            <label htmlFor="assignMidwife" className="input-group-text">Assign Midwife</label>
                <select id="assignMidwife" name="assignMidwife" className="form-control col-sm-6" value={formData.assignMidwife} onChange={handleInputChange}>
                    <option value="">Select a Midwife</option>
                        {assignMidwife.map((midwifeName, index) => (
                        <option key={index} value={midwifeName}>
                        {midwifeName}
                    </option>
                    ))}
                </select>
            </div>
      </div>


      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="gnDivision" className="input-group-text">Occupation</label>
              <input type="text" className="form-control col-sm-6" id="occupation" name="occupation" value={formData.occupation} onChange={handleInputChange}/>
          </div>
          </div>

          <div class="col">
            <div className="input-group mb-5">
                <select id="educationLevel" className="form-select" name="educationLevel" value={formData.educationLevel} onChange={handleInputChange}>
                    <option value="" disabled>Education Level</option>
                    <option value="O/L">O/L</option>
                    <option value="A/L">A/L</option>
                    <option value="Degree">Degree</option>
                </select>
            </div>
          </div>
      </div>

    <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Spouse's Details</h4></div><br></br>

      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="husbandFirstName" className="input-group-text">First Name</label>
              <input type="text" className="form-control col-sm-6" id="husbandFirstName" name="husbandFirstName" value={formData.husbandFirstName} onChange={handleInputChange}/>
          </div>
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="husbandLastName" className="input-group-text">Last Name</label>
              <input type="text" className="form-control col-sm-6" id="husbanLastName" name="husbandLastName" value={formData.husbandLastName} onChange={handleInputChange}/>
          </div>
          </div>

      </div>

      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="husbandNic" className="input-group-text">NIC</label>
              <input type="text" className="form-control col-sm-6" id="husbandNic" name="husbandNic" value={formData.husbandNic} onChange={handleInputChange}/>
          </div>   
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="husbandDob" className="input-group-text">DOB</label>
              <input type="date" className="form-control col-sm-6" id="husbandDob" name="husbandDob" value={formData.husbandDob} onChange={handleInputChange}/>
          </div>
          </div>
      </div>

      <div class="row">
          <div class="col">
            <div className="input-group mb-5">
                <select id="husbandEducationLevel" className="form-select" name="husbandEducationLevel" value={formData.husbandEducationLevel} onChange={handleInputChange}>
                    <option value="" disabled>Education Level</option>
                    <option value="O/L">O/L</option>
                    <option value="A/L">A/L</option>
                    <option value="Degree">Degree</option>
                </select>
            </div>
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="husbandOccupation" className="input-group-text">Occupation</label>
              <input type="text" className="form-control col-sm-6" id="husbandOccupation" name="husbandOccupation" value={formData.husbandOccupation} onChange={handleInputChange}/>
          </div>
          </div>
      </div>

      <div class="row">
          <div className="input-group mb-5">
              <label htmlFor="husbandContactNo" className="input-group-text">Contact Number</label>
              <input type="tel" className="form-control col-sm-6" id="husbandContactNo" name="husbandContactNo" value={formData.husbandContactNo} onChange={handleInputChange}/>
          </div>
      </div>

      <div class="row" style={{backgroundColor: '#B3817A'}}><h4 style={{textAlign:'center'}}>Marriage Details</h4></div><br></br>

      <div class="row">
          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="marriedDate" className="input-group-text">Married Date</label>
              <input type="date" className="form-control col-sm-6" id="marriedDate" name="marriedDate" value={formData.marriedDate} onChange={handleInputChange}/>
          </div>
          </div>

          <div class="col">
          <div className="input-group mb-5">
              <label htmlFor="marriageCertificateNo" className="input-group-text">Marriage Certificate Number</label>
              <input type="text" className="form-control col-sm-6" id="marriageCertificateNo" name="marriageCertificateNo" value={formData.marriageCertificateNo} onChange={handleInputChange}/>
          </div>
          </div>
      </div>

      <div class="row">
          <div className="input-group mb-5">
              <label htmlFor="marriagePlace" className="input-group-text">Married Place</label>
              <input type="text" className="form-control col-sm-6" id="marriagePlace" name="marriagePlace" value={formData.marriagePlace} onChange={handleInputChange}/>
          </div>
      </div>
     
          <div className="input-group mb-5" style={{ display: "flex", justifyContent: "flex-end" }}>
              <button type="submit" className="button1" style={{
                      backgroundColor:'#FC8D7C', 
                      borderColor: '#FC8D7C',
                      fontSize: 'large', 
                      width:'20%',
                      padding:'1%',
                      borderRadius: '10px',
                      color: 'white',
                      fontWeight: 'bold'}}>Register</button>
          </div>
          </div>
          </form>

          </div>
          </div>

  );
}

export default MarriedCoupleRegistrationForm;
