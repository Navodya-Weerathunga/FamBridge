import axios from "axios";

class PregnantWomenService {
    static async request(formData) {
        try {
            const response = await axios.post(
                `http://localhost:8080/famBridge/pregnantWomenRequest/`,
                formData,
                {
                    headers: {
                        "Content-Type": "multipart/form-data"
                    }
                }
            );

            return response.data;
        } catch (error) {
            console.error("Error submitting request:", error);
            throw error;
        }
    }

    static async getAllRegistrationRequestDetails(workinArea){
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/pregnantWomenRequest/requestDetails/${workinArea}`);
            return response.data;
        } catch (err) {
            throw err;
        }
    };

    static async pregnancyProof(requestId) {
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/pregnantWomenRequest/proof/${requestId}`,
            { responseType: 'blob'}
            );
            return response;
        
        } catch (err) {
            throw err;
        }
    }

    static async register(requestId) {
            const response = await axios.post(`http://localhost:8080/famBridge/pregnantWomen/${requestId}`, {})
            .then(response => {
              console.log("Registration success:", response.data);
            })
            .catch(error => {
              console.error("Error during registration:", error);
            });
        
    }

    static async pregnantWomen(workingArea) {
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/pregnantWomen/${workingArea}`);
                return response;
        
        } catch (err) {
            throw err;
        }
    }

    static async pregnancyRecordNo(email) {
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/pregnantWomen/pregnancyCards/${email}`);
                return response;
        
        } catch (err) {
            throw err;
        }
    }
}

export default PregnantWomenService;
