import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MidwifeService from "../../Service/Midwife_Service";

function MidwifeLogin () {

    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault(); // Prevent the default form submission
        console.log('Form submitted'); // Add this line for debugging

        try {
            const midwifeData = await MidwifeService.login(email, password);
            console.log(midwifeData);

            if(midwifeData.login && midwifeData.midwifeType == "SPH Midwife"){
                navigate("/SuperMidwifeHome"); 
                localStorage.setItem("workEmail", midwifeData.email);
                localStorage.setItem("midwifeType", midwifeData.midwifeType);
                localStorage.setItem("workingArea", midwifeData.workingArea);
                localStorage.setItem("mohArea", midwifeData.mohArea);
                
            }

            else if(midwifeData.login && midwifeData.midwifeType == "Field Midwife"){
                navigate("/MidwifeHome"); 
                localStorage.setItem("workEmail", midwifeData.email);
                localStorage.setItem("midwifeType", midwifeData.midwifeType);
                localStorage.setItem("workingArea", midwifeData.workingArea);
                localStorage.setItem("mohArea", midwifeData.mohArea);
            }
        

            
        } 
        
        catch (err) {
            console.error(err);
            setError(err.message); 
            alert(err.message); 
        }
        
    };
        

return(
    <section className="vh-100 gradient-custom">
    <div className="container py-5 h-100">
        <div className="row d-flex justify-content-center align-items-center h-100">
            <div className="col-12 col-md-8 col-lg-6 col-xl-5">
                <div className="card text-dark" style={{backgroundColor: '#FFCFC8'}}>
                    <div className="card-body p-5 text-center" >
  
                        <div className="mb-md-5 mt-md-4 pb-5">
                            <form onSubmit={handleSubmit} >
                                <h2 className="fw-bold mb-2 text-uppercase">Midwife Login</h2><br></br>
                                <p className="text-black-70 mb-5">Please enter your Email and Password!</p>
  
                                <div data-mdb-input-init className="form-outline form-white mb-4">
                                    <input type="email" id="typeEmail" className="form-control form-control-lg" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
                                </div>
  
                                <div data-mdb-input-init className="form-outline form-white mb-4">
                                    <input type="password" id="typePassword" className="form-control form-control-lg" placeholder="Password" value={password} onChange={e => setPassword(e.target.value)}/>
                                </div>
  
                                <p className="small mb-5 pb-lg-2" ><a className="text-black-50" href="MidwifeUpdatePassword">Forgot password?</a></p>
  
                                <button data-mdb-button-init data-mdb-ripple-init className="button" type="submit" style={{
                                    backgroundColor:'#FC8D7C', 
                                    fontSize: 'large', 
                                    width:'40%',
                                    borderRadius: '10px',
                                    borderColor: '#FC8D7C',
                                    padding:'1.5%',
                                    color: 'white',
                                    fontWeight: 'bold'
                                }}>Log In</button>
  
                            </form>

                        </div>
  
                    </div>
                </div>
            </div>
        </div>
    </div>
    </section>  
);
}

export default MidwifeLogin;