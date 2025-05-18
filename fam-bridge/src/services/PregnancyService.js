const API_BASE = 'http://localhost:8080/famBridge/pregnancyCard';



export const submitPregnancyData = async (data) => {
  console.log("Data being sent to backend:", data);  // Log the data being sent
  try {
    const response = await fetch(API_BASE, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });

    if (!response.ok) {
      throw new Error('Failed to submit pregnancy data');
    }

    return await response.json();
  } catch (error) {
    console.error('Error submitting pregnancy data:', error);
    throw error;
  }
};



export const getPregnancyData = async (pregnancyRecordNo) => {
  try {
    const response = await fetch(`${API_BASE}/by-pregnancyRecordNo/${pregnancyRecordNo}`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    });

    if (!response.ok) {
      throw new Error(`Failed to fetch pregnancy data for pregnancyRecordNo: ${pregnancyRecordNo}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Error fetching pregnancy data:', error);
    throw error;
  }
};


export const updatePregnancyData = async (pregnancyRecordNo, updatedData) => {
  try {
    const response = await fetch(`${API_BASE}/by-pregnancyRecordNo/${pregnancyRecordNo}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(updatedData),
    });

    if (!response.ok) {
      throw new Error(`Failed to update pregnancy data for pregnancyRecordNo: ${pregnancyRecordNo}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Error updating pregnancy data:', error);
    throw error;
  }
};
