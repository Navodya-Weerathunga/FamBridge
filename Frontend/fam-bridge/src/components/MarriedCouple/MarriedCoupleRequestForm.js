import { useState } from "react";
import React from "react";
import { useNavigate } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function MarriedCoupleRequest(){

        const navigate = useNavigate();
        const[formData, setFormData] = useState({
            firstName: '',
            lastName: '',
            nic: '',
            province: '',
            district: '',
            address: '',
            city: '',
            contactNumber: '',
            email: '',
            mohArea: '',
            gramaNiladhariDivision: '',
            marriageProof: null,
            appointmentDate: '',
        }) 

        const provinceCityMap = {
            Central: ["Kandy", "Matale", "Nuwara Eliya"],
            Eastern: ["Ampara", "Batticaloa", "Trincomalee"],
            "North Central": ["Anuradhapura", "Polonnaruwa"],
            Northern: ["Jaffna", "Kilinochchi", "Mannar", "Mullaitivu", "Vavuniya"],
            "North Western": ["Kurunegala", "Puttalam"],
            Sabaragamuwa: ["Kegalle", "Ratnapura"],
            Southern: ["Galle", "Hambantota", "Matara"],
            Uva: ["Badulla", "Monaragala"],
            Western: ["Colombo", "Gampaha", "Kalutara"],
        };

        const cityMohAreaMap = {
          Colombo: [
              "Battaramulla", "Boralesgamuwa", "Dehiwala", "Egoda Uyana", "Gothatuwa", "Hanwella",
              "Homagama", "Kaduwela", "Kahathuduwa", "Kesbewa", "Kolonnawa", "Maharagama", 
              "Moratuwa", "Nugegoda", "Padukka", "Piliyandala", "Pitakotte", "Rathmalana"
          ]
        };
    
        const handleInputChange = (e) => {
            const { name, value } = e.target;
            setFormData({
              ...formData,
              [name]: value
            });
          };
        
          const handleFileChange = (e) => {
            const file = e.target.files[0] || null;
            setFormData({
              ...formData,
              marriageProof: file
            });
          };
        
          const handleSubmit = async (e) => {
            e.preventDefault();
            const data = new FormData();
            
            // Manually appending form data (already controlled via state)
            Object.keys(formData).forEach(key => {
              if (formData[key] !== null && formData[key] !== '') {
                data.append(key, formData[key]);
              }
            });
        
            try {
              const response = await MarriedCoupleService.request(formData);
              console.log('Response:', response);
              alert(response.message);
              navigate('/Home')
            } catch (error) {
              console.error("Error:", error);
              alert("There was an error submitting your request.");
            }
          };
        

    return(

        <div className='Body1' style={{marginTop: '2%'}}>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h2 className="heading" style={{textAlign:'center', color:'#3F5A61'}}>Married Couple Registration Request</h2><br></br>
            <form onSubmit={handleSubmit} >
        <div>
        

        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="firstName" className="input-group-text">First Name</label>
                <input type="text" className="form-control col-sm-6" id="firstName" name="firstName" value={formData.firstName} onChange={handleInputChange}/>
            </div>
            </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="lastName" className="input-group-text">Last Name</label>
                <input type="text" className="form-control col-sm-6" id="lastName" name="lastName" value={formData.lastName} onChange={handleInputChange}/>
            </div>
            </div>

        </div>

        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="nic" className="input-group-text">NIC</label>
                <input type="text" className="form-control col-sm-6" id="nic" name="nic" value={formData.nic} onChange={handleInputChange}/>
            </div>   
        </div>


        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="s_mobileNumber" className="input-group-text">Address</label>
                <input type="text" className="form-control col-sm-6" id="address" name="address" value={formData.address} onChange={handleInputChange}/>
            </div>
        </div>

        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <select id="province" className="form-select" name="province" value={formData.province} onChange={(e) => {
                      setFormData((prevData) => ({
                        ...prevData,
                        province: e.target.value,
                        district: "", 
                      }));
                    }}
                  >
                <option value="" disabled>
                      Select Province
                    </option>
                    {Object.keys(provinceCityMap).map((prov) => (
                      <option key={prov} value={prov}>
                        {prov}
                      </option>
                    ))}
                </select>
            </div>
            </div>
            
            <div class="col">
            <div className="input-group mb-5">
                <select id="district" className="form-select" name="district" value={formData.district} onChange={handleInputChange} disabled={!formData.province}>
                    <option value="" disabled>
                      Select District
                    </option>
                        {provinceCityMap[formData.province]?.map((district) => (
                    <option key={district} value={district}>
                        {district}
                      </option>
                    ))}
                </select>
            </div>
            </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="city" className="input-group-text">City</label>
                <input type="text" className="form-control col-sm-6" id="city" name="city" value={formData.city} onChange={handleInputChange}/>
            </div>
            </div>

        </div>

        <div class="row">
        <div className="col">
            <div className="input-group mb-5">
            <select id="mohArea" className="form-select" name="mohArea" value={formData.mohArea} onChange={handleInputChange} disabled={!formData.district}>
                <option value="" disabled>Select MOH</option>
                    {cityMohAreaMap[formData.district]?.map(moh => (
                <option key={moh} value={moh}>{moh}</option>
                ))}
            </select>
            </div>
        </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="gnDivision" className="input-group-text">Grama Niladhari Division</label>
                <input type="text" className="form-control col-sm-6" id="gramaNiladhariDivision" name="gramaNiladhariDivision" value={formData.gramaNiladhariDivision} onChange={handleInputChange}/>
            </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="email" className="input-group-text">Email</label>
                <input type="text" className="form-control col-sm-6" id="email" name="email" value={formData.email} onChange={handleInputChange}/>
            </div>
            </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                <input type="tel" className="form-control col-sm-6" id="contactNumber" name="contactNumber" value={formData.contactNumber} onChange={handleInputChange}/>
            </div>
            </div>
        </div>


        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="appointmentDate" className="input-group-text">Appointment Date</label>
                <input type="date" className="form-control" id="appointmentDate" name="appointmentDate" value={formData.appointmentDate} onChange={handleInputChange} required />
            </div>
        </div>

        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="marriageProof" className="input-group-text">Marriage Proof</label>
                <input type="file" className="form-control" id="marriageProof" name="marriageProof" onChange={handleFileChange} accept=".pdf,.jpg,.jpeg,.png" required />
            </div>
        </div>
       
            <div className="input-group mb-5" style={{ display: "flex", justifyContent: "flex-end" }}>
                <button type="submit" className="button1" style={{
                        backgroundColor:'#729CA7', 
                        borderColor:'#729CA7', 
                        fontSize: 'large', 
                        width:'15%',
                        padding:'1%',
                        borderRadius: '10px',
                        color: 'black',
                        fontWeight: 'bold'}}>Submit</button>
            </div>
            </div>
            </form>

            </div>
            </div>

    );
}

export default MarriedCoupleRequest;