import React from 'react'
import { Link,useParams } from 'react-router-dom';
import profileIcon from '../Pictures/profile.png';
import "./ClientNavBar.css"

const ClientNavBar = () => {
    const {nic} = useParams();
    const email = localStorage.getItem("email")
    const login = localStorage.getItem("login")

    const handleLogout = () =>{
        
        alert("Logged out successfully");
        localStorage.removeItem("email");
        localStorage.setItem("login", "0");
        localStorage.removeItem("marriedCoupleId")
        
    }

    console.log("login", login)
  return (
    <nav className="navbar navbar-expand-lg nav2">
        <div className="container-fluid">
            <a className="navbar-brand">
                FAMBRIDGE
            </a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                    <li className="nav-item">
                        <Link className="nav-link active" aria-current="page" to ={`/home/${nic}`}>HOME</Link>
                    </li>

                    <li className="nav-item dropdown">
                        <a className='nav-link active'
                           id='navbarDropdown'
                           role='button'
                           data-bs-toggle='dropdown'
                           aria-expanded='false'>REGISTRATION REQUEST</a>
                           <ul className='dropdown-menu'>
                                {login === "0" && (
                                    <li><Link className='dropdown-item' to={'/MarriedCoupleRequest'}>MARRIED COUPLE</Link></li>
                                )}
                                   
                                {login === "1" && (
                                    <li><Link className='dropdown-item' to={`/PregnantWomenRequest/${email}`}>PREGNANT WOMEN</Link></li>
                                )}
                           </ul>
                    </li>

                    <li className="nav-item">
                        <Link className="nav-link active" to={``}>ABOUT</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link active" to ={``}>CONTACT</Link>
                    </li>
                    <li className="nav-item">
                        <Link className="nav-link active" to ={``}>ADVICE</Link>
                    </li>

                    {login === "1" && (
                    <li className="nav-item">
                        <Link className="nav-link active" to ={`/PlaceVaccineChanneling/${nic}`}>MEETING</Link>
                    </li>)}
                   
                    {login === "1" && (
                    <li className="nav-item">
                        <Link className="nav-link active" to ={`/CancelationForm/${nic}`}>CANCELATION</Link>
                    </li>)}

                    {login === "1" && (
                    <li className="nav-item">
                        <Link className="nav-link active" to ={``}>FEEDBACK</Link>
                    </li>)}
                    
                </ul>
                
                {login === "1" && (
                <form className="d-flex" role="search">
                    <Link className="nav-link" to={`/UserProfile/${email}`}>
                          <img src={profileIcon} className="profile-icon mx-sm-2" />
                    </Link>
                </form>)}

                {login === "1" && (
                <form className="d-flex">
                    <button className='btn text-light btn-danger ' type="submit" onClick={handleLogout}>LOGOUT</button>
                </form>)}
                    
                
            </div>
            
        </div>
    </nav> 
    
  )
}

export default ClientNavBar;
