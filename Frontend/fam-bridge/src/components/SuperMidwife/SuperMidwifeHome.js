import React from 'react'
import { Link } from 'react-router-dom';
import '../Midwife/MidwifeHome.css'
import SuperMidwifeNavbar from '../Navbar/SuperMidwifeNavBar';


const SuperMidwifeHome = () => {
  console.log(localStorage.getItem("workEmail"))
  console.log(localStorage.getItem("mohArea"))
  return (
    <div>
      <SuperMidwifeNavbar></SuperMidwifeNavbar>
      <br/>
      
      <h2 className='ha5'>Welcome to FamBridge</h2>
     
     <div className="row" style={{padding:'3% 10% 3% 10%'}}>
     <div className="col">
     <img src='/midwifeHomeImg.jpg' className="img-fluid"/>
     </div>

     <div className="col" style={{margin: '3% 0% 3% 0%'}}>
     <p className='container'>
        Bringing new life into the world is a journey of love, strength, and care and midwives are at the heart 
        of it all. With gentle hands and expert guidance, we walk alongside mothers from the first heartbeat to the first embrace, 
        ensuring a safe, empowering, and nurturing experience.
      </p><br></br>


      <p className='container'>
        Our midwives blend tradition with modern expertise, offering personalized care that honours every mother's unique journey. 
        Whether it’s a reassuring voice during labour or compassionate support beyond birth, we are here to make every moment special, 
        because every birth is a beautiful beginning.
      </p>
     </div>
     </div>
      
    </div>
  )
}

export default SuperMidwifeHome;
