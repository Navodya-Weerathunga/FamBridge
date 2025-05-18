import React from "react";
import MidwifeNavBar from "../components/Navbar/MidwifeNavbar";
import MarriedCoupleRegistrationForm from "../components/MarriedCouple/MarriedCoupleRegistrationForm";

export default function MarriedCoupleRegistration(){
    return(
        <div>
            <MidwifeNavBar></MidwifeNavBar><br></br>
            <MarriedCoupleRegistrationForm></MarriedCoupleRegistrationForm>
        </div>
    );
    
}