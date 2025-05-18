import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function UpdateProfile(){ 
    const navigate = useNavigate();
    const { email: emailFromParams } = useParams();
    const email = emailFromParams || localStorage.getItem('email');
    console.log("Email from useParams:", email);

    const[UserData, setUserData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        contactNo: '',
        occupation: '',
        husbandFirstName: '',
        husbandLastName: '',
        husbandContactNo: '',
        husbandOccupation: '',
    });

    const[error, setError] = useState('');

    useEffect(() => {
        fetchUserDataByEmail(email);
    },[email]);

    const fetchUserDataByEmail = async (email) => {
        try{
            const response = await MarriedCoupleService.userProfile(email)
            console.log('API Response:', response);
            if (response && response.data) {
                setUserData(response.data);
            } else {
                setError('Empty or invalid response');
            }
        }

        catch(error){
            console.error('Error fetching user data: ', error);
        }
    };

    const handleInputChange = (event) => {
        const {name, value} = event.target;
        setUserData ((previousUserData) => ({
            ...previousUserData,
            [name]: value
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        try{
            const response = await MarriedCoupleService.updateProfile(email,UserData);
            alert("You have successfully updated your details")
            navigate(`/UserProfile/${email}`)
        }
        catch(error){
            console.error('Error updating user: ', error);
        }
    };

    return(

        <div className='Body1'>
        <div className='col-sm-6 py-2 px-5 offset-3 shadow' id='Body2'>
            <h1 className="heading" style={{textAlign: 'center', color: '#729CA7', marginBottom: '2%', fontWeight:'bolder'}}>Update Profile</h1><br></br>
            <form onSubmit={handleSubmit}>
        <div>
        
        <div class="row" style={{backgroundColor: '#729CA7'}}><h4 style={{textAlign:'center'}}>Married Woman Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="firstName" className="input-group-text">First Name</label>
                <input type="text" className="form-control col-sm-6" id="firstName" name="firstName" value={UserData.firstName}  onChange={handleInputChange}/>
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="lastName" className="input-group-text">Last Name</label>
                <input type="text" className="form-control col-sm-6" id="lastName" name="lastName" value={UserData.lastName}  onChange={handleInputChange}/>
            </div>
            </div>
  
        </div>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="email" className="input-group-text">Email</label>
                <input type="text" className="form-control col-sm-6" id="email" name="email" value={UserData.email} onChange={handleInputChange}/>
            </div>
            </div>

            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="contactNo" className="input-group-text">Contact Number</label>
                <input type="tel" className="form-control col-sm-6" id="contactNo" name="contactNo" value={UserData.contactNo} onChange={handleInputChange}/>
            </div>
            </div>
        </div>
  
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="gnDivision" className="input-group-text">Occupation</label>
                <input type="text" className="form-control col-sm-6" id="occupation" name="occupation" value={UserData.occupation} onChange={handleInputChange} />
            </div>
        </div>
  
      <div class="row" style={{backgroundColor: '#729CA7'}}><h4 style={{textAlign:'center'}}>Spouse's Details</h4></div><br></br><br></br>
  
        <div class="row">
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandFirstName" className="input-group-text">First Name</label>
                <input type="text" className="form-control col-sm-6" id="husbandFirstName" name="husbandFirstName" value={UserData.husbandFirstName} onChange={handleInputChange}/>
            </div>
            </div>
  
            <div class="col">
            <div className="input-group mb-5">
                <label htmlFor="husbandLastName" className="input-group-text">Last Name</label>
                <input type="text" className="form-control col-sm-6" id="husbanLastName" name="husbandLastName" value={UserData.husbandLastName} onChange={handleInputChange}/>
            </div>
            </div>
  
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="husbandOccupation" className="input-group-text">Occupation</label>
                <input type="text" className="form-control col-sm-6" id="husbandOccupation" name="husbandOccupation" value={UserData.husbandOccupation} onChange={handleInputChange}/>
            </div>
        </div>
  
        <div class="row">
            <div className="input-group mb-5">
                <label htmlFor="husbandContactNo" className="input-group-text">Contact Number</label>
                <input type="tel" className="form-control col-sm-6" id="husbandContactNo" name="husbandContactNo" value={UserData.husbandContactNo} onChange={handleInputChange}/>
            </div>
        </div>

        <div className="input-group mb-5" style={{ display: "flex", justifyContent: "flex-end" }}>
                <button type="submit" className="button1" style={{
                        backgroundColor:'#729CA7', 
                        fontSize: 'large', 
                        width:'25%',
                        padding:'1%',
                        borderRadius: '10px',
                        color: 'black',
                        borderColor:'#729CA7',
                        fontWeight: 'bold'}}>Update</button>
        </div>
       
        </div>
        </form>
  
        </div>
        </div>
  
    );
}

export default UpdateProfile;