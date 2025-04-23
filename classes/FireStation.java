package classes;

import interfaces.I_FireStation;

public class FireStation implements I_FireStation {

    private int number;
    private String district;
    private List<FireTruck> fireTrucks;  // List for FireTrucks
    private List<Ambulance> ambulances;  // List for Ambulances

    // Constructor
    public FireStation(int number, String district) {
        this.number = number;
        this.district = district;
        this.fireTrucks = new List<>();  // Initialize List for fire truck
        this.ambulances = new List<>();  // Initialize List for ambulance
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public List<FireTruck> getFireTrucks() {
        return this.fireTrucks;  // Return the list of fire truck
    }

    public void setFireTrucks(List<FireTruck> fireTrucks) {
        this.fireTrucks = fireTrucks;
    }

    public List<Ambulance> getAmbulances() {
        return this.ambulances;  // Return the list of ambulance
    }

    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulances = ambulances;
    }

    public boolean addVehicle(FireTruck truck) {
        Node<FireTruck> newNode = new Node<>(truck);
        return fireTrucks.append(newNode);  // Use List's append method
    }

    public boolean addVehicle(FireTruckKinds kind) {
        FireTruck truck = new FireTruck(this.number, fireTrucks.getSize() + 1, kind);
        Node<FireTruck> newNode = new Node<>(truck);
        return fireTrucks.append(newNode);  // Use List's append method
    }

    public boolean addVehicle(Ambulance ambulance) {
        Node<Ambulance> newNode = new Node<>(ambulance);
        return ambulances.append(newNode);  // Use List's append method
    }

    public boolean addVehicle(boolean hasDoctor) {
        Ambulance ambulance = new Ambulance(this.number, ambulances.getSize() + 1, hasDoctor);
        Node<Ambulance> newNode = new Node<>(ambulance);
        return ambulances.append(newNode);  // Use List's append method
    }

    public boolean removeVehicle(FireTruck truck) {
        return fireTrucks.remove(truck);  // Use List's remove method
    }

    public boolean removeVehicle(Ambulance ambulance) {
        return ambulances.remove(ambulance);  // Use List's remove method
    }

    public boolean removeVehicle(String ID) {
        // Iterate over the fire trucks to find the matching ID
        for (int i = 0; i < fireTrucks.getSize(); i++) {
            Node<FireTruck> node = fireTrucks.get(i);
            if (node.getContent().getID().equals(ID)) {
                return fireTrucks.remove(i);  // Use List's remove method
            }
        }

        // Iterate over the ambulances to find the matching ID
        for (int i = 0; i < ambulances.getSize(); i++) {
            Node<Ambulance> node = ambulances.get(i);
            if (node.getContent().getID().equals(ID)) {
                return ambulances.remove(i);  // Use List's remove method
            }
        }
        return false;
    }

    public void printVehicles() {
        // Print all fire trucks
        for (int i = 0; i < this.fireTrucks.getSize(); i++) {
            FireTruck truck = this.fireTrucks.get(i).getContent();
            if (truck != null) {
                System.out.println(truck.toString());  // Print FireTruck's string representation
            }
        }
    
        // Print all ambulances
        for (int i = 0; i < this.ambulances.getSize(); i++) {
            Ambulance ambulance = this.ambulances.get(i).getContent();
            if (ambulance != null) {
                System.out.println(ambulance.toString());  // Print Ambulance's string representation
            }
        }
    }

    public void sortVehicles() {
        // Sort fire trucks
        for (int i = 0; i < fireTrucks.getSize() - 1; i++) {
            for (int j = i + 1; j < fireTrucks.getSize(); j++) {
                Node<FireTruck> nodeOne = fireTrucks.get(i);
                Node<FireTruck> nodeTwo = fireTrucks.get(j);
                if (nodeOne.getContent().getID().compareTo(nodeTwo.getContent().getID()) > 0) {
                    fireTrucks.swap(i, j);  // Swap fire trucks using the custom swap method
                }
            }
        }

        // Sort ambulances
        for (int i = 0; i < ambulances.getSize() - 1; i++) {
            for (int j = i + 1; j < ambulances.getSize(); j++) {
                Node<Ambulance> nodeOne = ambulances.get(i);
                Node<Ambulance> nodeTwo = ambulances.get(j);
                if (nodeOne.getContent().getID().compareTo(nodeTwo.getContent().getID()) > 0) {
                    ambulances.swap(i, j);  // Swap ambulances using the custom swap method
                }
            }
        }
    }        
}
