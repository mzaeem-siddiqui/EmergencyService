package classes;

import interfaces.I_FireTruck;

public class FireTruck implements I_FireTruck {
    private String ID; // The unique ID for the fire truck
    private FireTruckKinds kind; // The type of fire truck

    public FireTruck(int stationNum, int vehicleNum, FireTruckKinds kind) {
        // Create the ID using the given station number, vehicle type, and vehicle number
        this.ID = stationNum + "/" + kind.getVehicleType() + "/" + vehicleNum;
        this.kind = kind;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public FireTruckKinds getKind() {
        return kind;
    }

    public void setKind(FireTruckKinds kind) {
        this.kind = kind;
    }
}
