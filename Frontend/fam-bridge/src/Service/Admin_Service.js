import axios from "axios";

class AdminService{

    static async register(formData) {
        try {
            console.log("Sending Data:", formData); // Log the form data

            const response = await axios.post(`http://localhost:8080/famBridge/midwife/`, formData, {
                headers: {
                    "Content-Type": "application/json",
                },
                withCredentials: true, 
            });

            return response.data;
        } catch (err) {
            console.error("Error Registering Midwife:", err.response?.data || err.message);
            throw err;
        }
    }

    static async getAllMidwives(){
        try {
            const response = await axios.get(`http://localhost:8080/famBridge/midwife/midwives`);
            return response.data;
        } catch (err) {
            throw err;
        }
    };

}

export default AdminService;