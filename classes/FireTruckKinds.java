package classes;

public enum FireTruckKinds {    
    FireEngine(49),
    LadderTruck(33),
    HazmatTruck(78),
    RescueTruck(52),
    CommandVehicle(11);

    private final int vehicleType;
    FireTruckKinds(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getVehicleType() {
        return vehicleType;
    }
}
