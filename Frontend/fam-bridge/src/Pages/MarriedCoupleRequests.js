import React from "react";
import MidwifeNavBar from "../components/Navbar/MidwifeNavbar";
import MarriedCoupleRequestList from "../components/MarriedCouple/MarriedCoupleRequestList";

export default function MarriedCoupleRequests(){
    return(
        <div>
            <MidwifeNavBar></MidwifeNavBar><br></br>
            <MarriedCoupleRequestList></MarriedCoupleRequestList>
        </div>
    );
    
}