import axios from "axios";

class MarriedCoupleService  {

    static async request(formData) {
        try {

            const response = await axios.post("http://localhost:8080/famBridge/marriedCoupleRequest/", formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
            });

            return response.data;
        }
        catch (error) {
            console.error("Error submitting request:", error);
            throw error;
        }
    };


    static async getAllRegistrationRequestDetails(mohArea){
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/marriedCoupleRequest/requestDetails/${mohArea}`);
            return response.data;
        } catch (err) {
            throw err;
        }
    };

    static async getAllRegistrationRequestDetailsById(requestId) {
        try {
            const response = await axios.get(
                `http://localhost:8080/famBridge/marriedCoupleRequest/requestDetailsById/${requestId}`
            );
            return response.data;
        } catch (err) {
            throw err;
        }
    };


    static async registration(formData) {
        try {
            const response = await axios.post("http://localhost:8080/famBridge/marriedCouple/marriedCouple", formData)
            return response.data;
        }
        catch (error) {
            console.error("Error submitting request:", error);
            throw error;
        }
    };

    static async login(email, password) {
        try {
          const response = await axios.post("http://localhost:8080/famBridge/marriedCouple/login", { email, password });
          return response.data

        } catch (err) {
            throw err;
        }
    }

    static async updatePassword(email, password, confirmPassword) {

        try {
            const response = await axios.put("http://localhost:8080/famBridge/marriedCouple/updatePassword", { email, password, confirmPassword });
            return response.data;
    
        } catch (err) {
            throw err;
        }
    }

    static async marriageProof(requestId) {
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/marriedCoupleRequest/proof/${requestId}`,
            { responseType: 'blob'}
            );
            return response;
        
        } catch (err) {
            throw err;
        }
    }

    static async marriedCouples(workingArea) {
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/marriedCouple/marriedCouples/${workingArea}`);
                return response;
        
        } catch (err) {
            throw err;
        }
    }

    static async marriedCoupleDetailsById(marriedCoupleId) {
        try{
            const response = await axios.get(`http://localhost:8080/famBridge/marriedCouple/details/${marriedCoupleId}`);
            return response;
        }

        catch (err) {
            throw err;
        }
    }


    
    static async userProfile(email) {
        try{
            const response = await axios.get(`http://localhost:8080/famBridge/marriedCouple/userProfile/${email}`);
            return response;
        }

        catch (err) {
            throw err;
        }
    }

    static async updateProfile(email, UserData){
        try{
            const response = await axios.put(`http://localhost:8080/famBridge/marriedCouple/updateProfile/${email}`, UserData)
                return response.data;
        }

        catch(err){
            throw err;
        }
    }

    static async getAllRegistrationRequestDetailsById(requestId) {
        try {
            const response = await axios.get(
                `http://localhost:8080/famBridge/marriedCoupleRequest/requestDetailsById/${requestId}`
            );
            return response.data;
        } catch (err) {
            throw err;
        }
    };

};

export default MarriedCoupleService;
