import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import MidwifeService from "../../Service/Midwife_Service";

function MidwifeUpdatePassword () {

    const[email, setEmail] = useState('');
    const[password, setPassword] = useState('');
    const[confirmPassword, setConfirmPassword] = useState('');
    const[error, setError] = useState('');

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault(); 
        console.log('Form submitted');

        if(!password && !confirmPassword){
            alert("Password and Confirm Password Cannot be Null")
            return
        }

        else{
            try {
                const midwifeData = await MidwifeService.updatePassword(email, password, confirmPassword);
                console.log(midwifeData);
                const statusCode = midwifeData.statusCode;
    
                if (statusCode == '200'){
                    if(password == confirmPassword){
                        alert(midwifeData.message);
                        navigate('/MidwifeLogin')
                    }
                    else{
                        alert(midwifeData.message);
                    }
                }
    
                else{
                    alert(midwifeData.message)
                }
    
            } catch (err) {
                console.error(err);
                setError(err.message); 
                alert(err.message); 
            }
            
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
                                <h2 className="fw-bold mb-2 text-uppercase">Update Password</h2><br></br>
  
                                <div data-mdb-input-init className="form-outline form-white mb-4">
                                    <input type="email" id="typeEmail" className="form-control form-control-lg" placeholder="Email" value={email} onChange={e => setEmail(e.target.value)} />
                                </div>
  
                                <div data-mdb-input-init className="form-outline form-white mb-4">
                                    <input type="password" id="password" className="form-control form-control-lg" placeholder="New Password" value={password} onChange={e => setPassword(e.target.value)}
                                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                    title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"/>
                                </div>

                                <div data-mdb-input-init className="form-outline form-white mb-4">
                                    <input type="password" id="confirmPassword" className="form-control form-control-lg" placeholder="Confirm Password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)}
                                    pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                    title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"/>
                                </div> <br></br>
  
                                <button data-mdb-button-init data-mdb-ripple-init className="button" type="submit" style={{
                                    backgroundColor:'#FC8D7C', 
                                    fontSize: 'large', 
                                    width:'40%',
                                    borderRadius: '10px',
                                    borderColor: '#FC8D7C',
                                    padding:'1.5%',
                                    color: 'white',
                                    fontWeight: 'bold'
                                }}>Update</button>
  
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

export default MidwifeUpdatePassword;