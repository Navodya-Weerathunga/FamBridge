import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MarriedCoupleService from "../../Service/Married_Couple_Service";

function Login () {

    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault(); // Prevent the default form submission
        console.log('Form submitted'); // Add this line for debugging

        try {
            const loginData = await MarriedCoupleService.login(email, password)
            console.log(loginData);
      
            if (loginData.login) {

                alert(loginData.message);

                if(loginData.loginCount > 1){
                    localStorage.setItem("email", loginData.email);
                    localStorage.setItem("marriedCoupleId", loginData.marriedCoupleId);
                    localStorage.setItem("login", loginData.login);
                    navigate("/Home");
                }

                else{
                    navigate("/UpdatePassword");
                }
            }
            else{
                alert(loginData.message)
            }
                

        } catch (error) {
            alert(error.message);  // Currently showing: "You have to update your OTP to new Password"
        }
        
    };
        

    return (
        <section className="vh-100">
        <div className="container py-4 h-70">
            <div className="row d-flex justify-content-center align-items-center h-80">
                <div className="col col-xl-8">
                    <div className="card" style={{ borderRadius: '1rem' }}>
                        <div className="row g-0">
                            <div className="col-md-6 col-lg-5 d-none d-md-block">
                            <img src='/UserLoginImg.jpg' className="img-fluid" style={{ borderRadius: '1rem 0rem 0rem 1rem', borderColor: 'black' }} />
                            </div>

                        <div className="col-md-6 col-lg-7 d-flex align-items-center" style={{ backgroundColor: "#C2DCF6", borderRadius: '0rem 1rem 1rem 0rem' }}>
                        <div className="card-body p-4 p-lg-5 text-black">

                        <form onSubmit={handleSubmit}>

                        <div>
                            <h1 style={{textAlign: 'center', marginBottom: '8%', color: '#56697F', fontWeight: 'bold' }}>FamBridge</h1>
                        </div>

                        <h5 className="fw-normal mb-3 pb-3" style={{ letterSpacing: '1px'}}>User Login</h5>

                        <div data-mdb-input-init className="form-outline mb-4">
                            <input type="email" id="form2Example17" className="form-control form-control-lg" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)}/>
                        </div>

                        <div data-mdb-input-init className="form-outline mb-4">
                            <input type="password" id="form2Example27" className="form-control form-control-lg" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)}/>
                        </div>

                        <div className="update" style={{ textAlign: 'right'}}>
                        <a className="text-black" href="UpdatePassword" style={{color: 'black'}}>Forgot Password?</a>
                        </div><br></br>

                        <div className="pt-1 mb-4" style={{marginTop:'7%', display: "flex", justifyContent: "center" }}>    
                            <button type="submit" className="button1" style={{
                                backgroundColor:'#729CA7', 
                                fontSize: 'large', 
                                width:'40%',
                                padding:'1%',
                                borderRadius: '10px',
                                color: 'black',
                                fontWeight: 'bold',
                                borderColor: '#729CA7'}}
                            >Log In</button>      
                        </div>

                        </form>

                        </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </section>
);
}

export default Login;