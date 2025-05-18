import React from 'react'
import { Link } from 'react-router-dom';
import './Home.css'
import ClientNavBar from '../Navbar/ClientNavBar';
import Login from './Login';



const Home = () => {
  const login = localStorage.getItem("login")
  return (
    <div>
      
      <ClientNavBar/>
      <div className="Boxa4">
        <table>
          <tbody>
                  <h1 className="ha1">FAMBRIDGE</h1>
                  <h3 className="ha6">Precision in Planning,
                  Freedom in Care</h3>
                  
          </tbody>
        </table>
      </div>
      <br/>
      
      <h2 className='ha2'>Welcome to</h2>
      <h1 className='ha5'>FAMBRIDGE</h1>
      {login === "0" && (
      <Link to={`/Login`}><button className='btn cleintbt '>LOG IN</button></Link>)}
      <br/>
      <h3 className='ha3'>Why we need maternal care?</h3>
      <br/>
      <p className='container'>Maternal care is vital for ensuring the health and well-being of both mother and baby throughout pregnancy, childbirth, and postpartum recovery.</p>
      <p className='container'>
 Regular prenatal check-ups help monitor the baby’s growth, detect potential complications early, and provide essential medical support for a safe and healthy delivery. Proper maternal care also includes guidance on nutrition, lifestyle, and emotional well-being, ensuring mothers are well-prepared for each stage of pregnancy. Postnatal care further supports recovery and newborn health, promoting a smooth transition into motherhood.
With expert monitoring and personalized support, we are dedicated to providing the best care for you and your baby, ensuring a safe, healthy, and confident pregnancy journey.</p>

    </div>
  )
}

export default Home
