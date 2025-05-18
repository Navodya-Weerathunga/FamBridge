import React from 'react'
import { Link, useParams, useNavigate } from 'react-router-dom';
import profileIcon from '../Pictures/profile.png';

const MidwifeNavbar = () => {
    
    const workEmail = localStorage.getItem("email")
    const workingArea = localStorage.getItem("workingArea")
    const navigate = useNavigate();

    const handleLogout = () =>{
        
        alert("Logged out successfully");
        localStorage.removeItem("email");
        navigate(`/MidwifeLogin`)
        
    }
    
  return (
   
    <nav className="navbar navbar-expand-lg nav1">
        <div className="container-fluid">
            <a className="navbar-brand">
                <Link to={`/nhome/${workEmail}`} style={{textDecoration:'none', color:'black'}}>FAMBRIDGE</Link>
            </a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                        <Link className="nav-link active" aria-current="page" to ={"/"}></Link>
                    </li>
                    <li className="nav-item dropdown">
                        <a className='nav-link dropdown-toggle'
                           id='navbarDropdown'
                           role='button'
                           data-bs-toggle='dropdown'
                           aria-expanded='false'>USERS</a>
                           <ul className='dropdown-menu'>
                               <li><Link className='dropdown-item' to={'/RegisteredPregnantWomen'}>PREGNANT WOMEN</Link></li>
                               <li><Link className='dropdown-item' to={'/RegisteredMarriedCouples'}>COUPLE</Link></li>
                           </ul>
                    </li>
                    <li className="nav-item dropdown">
                        <a className='nav-link dropdown-toggle'
                           id='requestDropdown'
                           role='button'
                           data-bs-toggle='dropdown'
                           aria-expanded='false'>REGISTRATION REQUEST</a>
                           <ul className='dropdown-menu'>
                               <li><Link className='dropdown-item' id="pregnantRequest" to={`/PregnantWomenRequests/${workingArea}`}>PREGNANT WOMEN</Link></li>
                               <li><Link className='dropdown-item' id='marriedRequest' to={'/MarriedCoupleRequests'}>COUPLE</Link></li>
                           </ul>
                    </li>

                    <li className="nav-item dropdown">
                        <a className='nav-link dropdown-toggle'
                           id='navbarDropdown'
                           role='button'
                           data-bs-toggle='dropdown'
                           aria-expanded='false'>VIRTUAL MEETING</a>
                           <ul className='dropdown-menu'>
                               <li><Link className='dropdown-item' to={'/AdminChannelingManage'}>CREATE</Link></li>
                               <li><Link className='dropdown-item' to={'/CancelationTable'}>CANCEL</Link></li>
                           </ul>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link active" to ={"/ReminderTable"}>REMINDERS</Link>
                    </li>
                   
                    <li className="nav-item">
                        <Link className="nav-link active" to ={""}>ADVICE</Link>
                    </li>


                    <li className="nav-item">
                        <Link className="nav-link active" to ={`/appointments`}>SCHEDULE</Link>
                    </li>
                    
                </ul>

                <form className="d-flex" role="search">
                    <Link className="nav-link" to={``}>
                          
                    </Link>
                </form> 

                <form className="d-flex" role="search">
                    <Link className="nav-link" to={`/MidwifeProfile`}>
                        <img src={profileIcon} className="profile-icon mx-sm-2" />
                    </Link>
                </form>
                
                <form className="d-flex">
                    <button className='btn text-light btn-danger ' type="submit" onClick={handleLogout}>LOGOUT</button>
                </form>
                
                   
                
            </div>
            
        </div>
    </nav> 
    
  )
}

export default MidwifeNavbar;
