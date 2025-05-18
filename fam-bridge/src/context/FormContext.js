import { createContext, useState } from 'react';

export const FormContext = createContext();

export function FormProvider({ children }) {
  const [formData, setFormData] = useState({
    pcid: '',
    riskType: '',
    bloodGroup: '',
    allergies: '',
    fieldClinicName: '',
    hospitalClinicName: '',
    consultantObstetricians: '',
    antenatalRiskConditions: '',
    consanguinity: '',
    rubellaImmunization: '',
    prePregnancyScreening: '',
    preconceptionalFolicAcid: '',
    historyOfSubfertility: '',
    plannedPregnancy: '',
    lastUsedFamilyPlanningMethods: '',
    visitingDate: '',
    ageOfYoungestChild: '',
    LRMP: '',
    EDD: '',
    dateOf40Weeks: null,
    usCorrectedEdd: null,
    poaAtScan: '',
    poaAtRegistration: '',
    gestationPeriod: '',
    gravidity: null,
    contraceptiveMethod: '',
    contraceptiveDetails: '',
    wifeAge: '',
    wifeHighestEducation: '',
    wifeOccupation: '',
    husbandAge: '',
    husbandHighestEducation: '',
    husbandOccupation: '',
    diabetesMellitus: '',
    hypertension: '',
    haematologicalDiseases: '',
    twinOrMultiplePregnancies: '',
    others: '',
    diabetes: '',
    hypertensionMedHis: '',
    cardiacDiseases: '',
    renalDiseases: '',
    hepaticDiseases: '',
    psychiatricIllnesses: '',
    epilepsy: '',
    malignancies: '',
    haematologicalDiseasesMedHis: '',
    tuberculosis: '',
    thyroidDiseases: '',
    bronchialAsthma: '',
    pregnancy: '',
    antenatalComplications: '',
    placeOfDelivery: '',
    modeOfDelivery: '',
    outcome: '',
    brithWeight: '',
    postnatalComplications: '',
    sexOfChild: '',
    ageOfChild: '',
    poa: '',
    weight: '',
    height: '',
    bmi: '',
    intendedHospital: '',
    modeOfTransport: '',
    averageCost: '',
    distanceFromHome: '',
    timeTakenToReach: '',
    intendedHospitalEmergency: '',
    modeOfTransportEmergency: '',
    averageCostEmergency: '',
    distanceFromHomeEmergency: '',
    timeTakenToReachEmergency: '',
    bmiHistory: [],
  });

const updateFormData = (data) => {
  setFormData((prev) => {
    const updated = { ...prev };

    for (const key in data) {
      if (Object.prototype.hasOwnProperty.call(data, key)) {
        updated[key] = data[key] !== undefined ? data[key] : prev[key];
      }
    }

    return updated;
  });
};

  const updateBmiHistory = (newEntry) => {
    setFormData((prev) => ({
      ...prev,
      bmiHistory: [...prev.bmiHistory, newEntry],
    }));
  };

  return (
    <FormContext.Provider value={{ formData, updateFormData, updateBmiHistory }}>
      {children}
    </FormContext.Provider>
  );
}
