import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PregnancyCardPageOne from './components/PregnancyCardPageOne'
import PregnancyCardPageTwo  from './components/PregnancyCardPageTwo'
import PregnancyCardPageThree from './components/PregnancyCardPageThree';
import PregnancyCardPageFour from './components/PregnancyCardPageFour'
import PregnancyCardView from './components/PregnancyCardView'
import CardUpdate from './components/CardUpdate'
import { FormProvider } from './context/FormContext';
import PregnancyCardViewMidwife from './components/PregnancyCardViewMidwife';



function App() {
  return (
    <FormProvider>
      <Router>
        <div className="App">
          <Routes>
             <Route path="/pregnancy-card-page-one/:pcid" element={<PregnancyCardPageOne />} />
            <Route path="/pregnancy-card-page-two/:pcid" element={<PregnancyCardPageTwo />} />
            <Route path="/pregnancy-card-page-three/:pcid" element={<PregnancyCardPageThree />} />
            <Route path="/pregnancy-card-page-four/:pcid" element={<PregnancyCardPageFour />} />
            <Route path="/pregnancy-card-view/:pcid" element={<PregnancyCardView />} />
            <Route path="/pregnancy-card-update/:pcid" element={<CardUpdate />} />
            <Route path="/pregnancy-card-view-midwife/:pcid" element={<PregnancyCardViewMidwife />} /> 
          </Routes>
        </div>
      </Router>
    </FormProvider>
  );
}

export default App;
