import axios from "axios";

class MidwifeService {

  static async login(email, password) {

    try {
        const response = await axios.post("http://localhost:8080/famBridge/midwife/login", { email, password });

        if (response.data.login) {
            return response.data;
        } else {
            throw new Error(response.data.message || "Invalid credentials"); 
        }
    } catch (err) {
        throw err;
    }
  }

  static async updatePassword(email, password, confirmPassword) {

    try {
        const response = await axios.put("http://localhost:8080/famBridge/midwife/updatePassword", { email, password, confirmPassword });
        return response.data;

    } catch (err) {
        throw err;
    }
  }

  static async getMidwifeByArea(workingArea) {
    try{
      const response = await axios.get(`http://localhost:8080/famBridge/midwife/workingArea/${workingArea}`);
      return response.data;
    }

    catch (err){
      throw err;
    }
  }

  static async profile(email) {
    try{
      const response = await axios.get(`http://localhost:8080/famBridge/midwife/${email}`);
      return response;
    }

    catch (err){
      throw err;
    }
  }

  static async updateProfile(workEmail, details) {
    try{
      const response = await axios.put(`http://localhost:8080/famBridge/midwife/updateProfile/${workEmail}`, details);
      return response;
    }

    catch (err){
      throw err;
    }
  }
}

export default MidwifeService;
