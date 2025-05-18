
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import MidwifeRegistration from './components/Admin/MidwifeRegistration';
import MidwifeLogin from './components/Midwife/MidwifeLogin';
import MidwifeList from './components/Admin/MidwifeList';
import Login from './components/MarriedCouple/Login';
import RegistrationRequestMarriedCouple from './Pages/RegistrationRequestMarriedCouple';
import MarriedCoupleRegistration from './Pages/MarriedCoupleRegistration';
import MarriedCoupleRequests from './Pages/MarriedCoupleRequests';
import MarriageProof from './components/MarriedCouple/MarriageProof';
import RegisteredMarriedCouples from './components/MarriedCouple/RegisteredMarriedCouples';
import MidwifeHome from './components/Midwife/MidwifeHomePage';
import RegisteredMarriedCoupleDetails from './components/MarriedCouple/RegisteredMarriedCoupleDetails';
import Home from './components/MarriedCouple/Home';
import UserProfile from './components/MarriedCouple/UserProfile';
import PregnantWomenRequest from './components/PregnantWomen/PregnantWomentRequest';
import UpdateProfile from './components/MarriedCouple/UpdateProfile';
import PregnantWomenRequestList from './components/PregnantWomen/PregnantWomenRequestList';
import PregnancyProof from './components/PregnantWomen/PregnancyProof';
import RegisteredpregnantWomen from './components/PregnantWomen/RegisteredPregnantWomen';
import MidwifeUpdatePassword from './components/Midwife/UpdatePassword';
import UpdatePassword from './components/MarriedCouple/UpdatePassword';
import MidwifeProfile from './components/Midwife/MidwifeProfile';
import MidwifeUpdateProfile from './components/Midwife/UpdateProfile';
import SuperMidwifeHome from './components/SuperMidwife/SuperMidwifeHome';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path='Home' element={<Home/>}/>
        <Route path='MidwifeHome' element={<MidwifeHome/>}/>
        <Route path='MidwifeRegistration' element={<MidwifeRegistration/>}></Route>
        <Route path='MidwifeLogin' element={<MidwifeLogin/>}></Route>
        <Route path='MidwifeUpdatePassword' element={<MidwifeUpdatePassword/>}/>
        <Route path='MarriedCoupleRequest' element={<RegistrationRequestMarriedCouple/>}></Route>
        <Route path='MidwivesList' element={<MidwifeList/>}></Route>
        <Route path='MarriedCoupleRequests' element={<MarriedCoupleRequests/>}></Route>
        <Route path='/MarriedCoupleRegistration/:requestId' element={<MarriedCoupleRegistration/>} />
        <Route path='Login' element={<Login/>}></Route>
        <Route path='UpdatePassword' element={<UpdatePassword/>}/>
        <Route path='/MarriageProof/:requestId' element={<MarriageProof/>}/>
        <Route path='RegisteredMarriedCouples' element={<RegisteredMarriedCouples/>}/>
        <Route path='/RegisteredMarriedCoupleDetails/:marriedCoupleId' element={<RegisteredMarriedCoupleDetails/>}/>
        <Route path='UserProfile/:email' element={<UserProfile/>}/>
        <Route path='PregnantWomenRequest/:email' element={<PregnantWomenRequest/>}/>
        <Route path='UpdateProfile/:email' element={<UpdateProfile/>}/>
        <Route path='PregnantWomenRequests/:workingArea' element={<PregnantWomenRequestList/>}/>
        <Route path='/PregnancyProof/:requestId' element={<PregnancyProof/>}/>
        <Route path='RegisteredPregnantWomen' element={<RegisteredpregnantWomen/>}/>
        <Route path='MidwifeProfile' element={<MidwifeProfile/>}/>
        <Route path='MidwifeUpdateProfile/:workEmail' element={<MidwifeUpdateProfile/>}/>
        <Route path='SuperMidwifeHome' element={<SuperMidwifeHome/>}/>

      </Routes>
      
    </BrowserRouter>
  );
}

export default App;
