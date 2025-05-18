import React from "react";
import MarriedCoupleRequest from "../components/MarriedCouple/MarriedCoupleRequestForm";
import ClientNavBar from "../components/Navbar/ClientNavBar";

export default function RegistrationRequestMarriedCouple(){
    return(
        <div>
            <ClientNavBar></ClientNavBar>
            <MarriedCoupleRequest></MarriedCoupleRequest>
        </div>
    );
    
}