package classes;

import interfaces.I_Ambulance;

public class Ambulance implements I_Ambulance {
    private String ID; // Unique ID for the ambulance
    private boolean hasDoctor; // Tells if the ambulance has a doctor
   
    public String getID() {
        return this.ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean getHasDoctor() {
        return this.hasDoctor;
    }
  
    public Ambulance(int stationNum, int vehicleNum, boolean hasDoctor) {
        int vehicleType;
        if (hasDoctor == true) { 
            vehicleType = 81;
        } else {
            vehicleType = 83;
        }
        this.ID = stationNum + "/" + vehicleType + "/" + vehicleNum;
        this.hasDoctor = hasDoctor;
    }


    public void setHasDoctor(boolean hasDoctor) {
        this.hasDoctor = hasDoctor;
        // Update the vehicle type in the ID based on doctor presence
        String[] parts = this.ID.split("/");
        int vehicleType;
        if (hasDoctor == true) { 
            vehicleType = 81;
        } else {
            vehicleType = 83;
        }
        this.ID = parts[0] + "/" + vehicleType + "/" + parts[2];
    }

    // Made with help from ChatGPT
    public String toString() {
        String vehicleType;
        if (hasDoctor == true) {
            vehicleType = "81";
        } else {
            vehicleType = "83";
        }
        String vehicleNum = ID.split("/")[2]; // Extract vehicle number from ID
        String stationNum = ID.split("/")[0]; // Extract station number from ID
        return stationNum + "/" + vehicleType + "/" + vehicleNum + " - Ambulance";
    }


}
