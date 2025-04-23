package classes;

public class EmergencyDispatchCenter {
    private List<FireStation> fireStations; // List for fire stations
    private List<Emergency> emergencies; // List for emergencies

    // Constructor
    public EmergencyDispatchCenter() {
        this.fireStations = new List<>(); // Initialize list of fire stations
        this.emergencies = new List<>();  // Initialize list of emergencies
    }

    // Get the list of fire stations
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    // Set the list of fire stations
    public void setFireStations(List<FireStation> fireStations) {
        this.fireStations = fireStations;
    }

    // Get the list of emergencies
    public List<Emergency> getEmergencies() {
        return emergencies;
    }

    // Set the list of emergencies
    public void setEmergencies(List<Emergency> emergencies) {
        this.emergencies = emergencies;
    }

    // Add a fire station to the dispatch center
    public boolean addFireStation(FireStation station) {
        Node<FireStation> newNode = new Node<>(station);
        return fireStations.append(newNode); // Append fire station to the list
    }

    // Add an ambulance to the given station
    public boolean addVehicle(int station, boolean hasDoctor) {
        // Find the fire station by its number
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation fireStation = fireStations.get(i).getContent();
            if (fireStation.getNumber() == station) {
                return fireStation.addVehicle(hasDoctor); // Add the ambulance to the station
            }
        }
        return false; // If fire station not found
    }

    // Add a fire truck of a given kind to the given station
    public boolean addVehicle(int station, FireTruckKinds kind) {
        // Find the fire station by its number
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation fireStation = fireStations.get(i).getContent();
            if (fireStation.getNumber() == station) {
                return fireStation.addVehicle(kind); // Add the fire truck to the station
            }
        }
        return false; // If fire station not found
    }

    // Remove a vehicle by its ID from the fire stations
    public boolean removeVehicle(String ID) {
        // Iterate through each fire station
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation fireStation = fireStations.get(i).getContent();
            if (fireStation.removeVehicle(ID)) {
                return true; // If the vehicle is removed from a station
            }
        }
        return false; // If vehicle with the given ID not found
    }

    // Create a new emergency call
    public Emergency newCall(String location, EmergencyKinds kind, int patients, int patientsNeedDoctor) {
        return new Emergency(location, kind, patients, patientsNeedDoctor); // Create and return a new emergency call
    }

    // Add an emergency call to the list of emergencies
    public boolean addCalltoList(Emergency call) {
        Node<Emergency> newNode = new Node<>(call);
        return emergencies.append(newNode); // Add emergency to the list
    }

    // Made with help from ChatGPT
    public void sortCalls() {
        // Get the total number of emergencies in the list
        int totalEmergencies = emergencies.getSize();
    
        // Loop through the list to compare and sort emergencies
        for (int i = 0; i < totalEmergencies - 1; i++) {
            // Inner loop to compare adjacent emergencies
            for (int j = 0; j < totalEmergencies - 1 - i; j++) {
                // Get the current emergency node
                Node<Emergency> currentEmergencyNode = emergencies.get(j);
                // Get the next emergency node
                Node<Emergency> nextEmergencyNode = emergencies.get(j + 1);
                // Get the kind of the current emergency
                EmergencyKinds currentEmergencyKind = currentEmergencyNode.getContent().getKind();
                // Get the kind of the next emergency
                EmergencyKinds nextEmergencyKind = nextEmergencyNode.getContent().getKind();
                // Compare the severity of the emergencies using their ordinal values
                // Higher ordinal value means lower severity
                if (currentEmergencyKind.ordinal() > nextEmergencyKind.ordinal()) {
                    // Swap the positions of the two emergencies in the list
                    emergencies.swap(j, j + 1);
                }
            }
        }
    }
    
    // Sort the vehicles in ascending order by their ID
    public void sortVehicles() {
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation station = fireStations.get(i).getContent();
            station.sortVehicles(); // Sort vehicles in each station
        }
    }

    // ChatGPT was used for the logic
    public boolean dispatchVehicles() {
        for (int i = 0; i < emergencies.getSize(); i++) {
            Emergency emergency = emergencies.get(i).getContent();
            int casualties = emergency.getCasualties();
            int casualtiesNeedDoctor = emergency.getCasualtiesNeedsDoctor();
            EmergencyKinds kind = emergency.getKind();

            int fireEngines = 0, ladderTrucks = 0, rescueTrucks = 0, hazmatTrucks = 0, commandVehicles = 0;

            // Determine required fire trucks using switch-case
            switch (kind) {
                case FireSmall:
                    fireEngines = 1;
                    break;
                case TechnicalEmergencySmall:
                    fireEngines = 1;
                    ladderTrucks = 1;
                    break;
                case HazmatEmergencySmall:
                    fireEngines = 2;
                    break;
                case FireMiddle:
                    fireEngines = 3;
                    ladderTrucks = 1;
                    commandVehicles = 1;
                    break;
                case TechnicalEmergencyMiddle:
                    fireEngines = 2;
                    rescueTrucks = 1;
                    commandVehicles = 1;
                    break;
                case HazmatEmergencyMiddle:
                    fireEngines = 2;
                    rescueTrucks = 1;
                    hazmatTrucks = 1;
                    commandVehicles = 1;
                    break;
                case FireLarge:
                    fireEngines = 5;
                    ladderTrucks = 2;
                    rescueTrucks = 1;
                    commandVehicles = 1;
                    break;
                case TechnicalEmergencyLarge:
                    fireEngines = 4;
                    ladderTrucks = 1;
                    rescueTrucks = 2;
                    commandVehicles = 1;
                    break;
                case HazmatEmergencyLarge:
                    fireEngines = 4;
                    ladderTrucks = 1;
                    rescueTrucks = 1;
                    hazmatTrucks = 2;
                    commandVehicles = 1;
                    break;
                case MedicalEmergency:
                    // No fire trucks needed
                    break;
            }

            // Dispatch fire trucks if required
            boolean fireTruckDispatched = true;
            if (kind != EmergencyKinds.MedicalEmergency) {
                fireTruckDispatched = dispatchFireTrucks(fireEngines, ladderTrucks, rescueTrucks, hazmatTrucks, commandVehicles);
            }

            // Dispatch ambulances based on casualty levels
            boolean ambulanceDispatched = dispatchAmbulances(casualties, casualtiesNeedDoctor);

            if (!(fireTruckDispatched && ambulanceDispatched)) {
                return false; // If either dispatch fails, return false
            }
        }
        return true; // All emergencies handled successfully
    }

    private boolean dispatchFireTrucks(int fireEngines, int ladderTrucks, int rescueTrucks, int hazmatTrucks, int commandVehicles) {
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation station = fireStations.get(i).getContent();

            int availableFireEngines = 0, availableLadderTrucks = 0, availableRescueTrucks = 0, availableHazmatTrucks = 0, availableCommandVehicles = 0;

            // Count available vehicles
            for (int j = 0; j < station.getFireTrucks().getSize(); j++) {
                FireTruck truck = station.getFireTrucks().get(j).getContent();
                switch (truck.getKind()) {
                    case FireEngine:
                        availableFireEngines++;
                        break;
                    case LadderTruck:
                        availableLadderTrucks++;
                        break;
                    case RescueTruck:
                        availableRescueTrucks++;
                        break;
                    case HazmatTruck:
                        availableHazmatTrucks++;
                        break;
                    case CommandVehicle:
                        availableCommandVehicles++;
                        break;
                }
            }

            // Check if this station has enough of each vehicle type
            if (availableFireEngines >= fireEngines && availableLadderTrucks >= ladderTrucks &&
                availableRescueTrucks >= rescueTrucks && availableHazmatTrucks >= hazmatTrucks &&
                availableCommandVehicles >= commandVehicles) {
                return true; // Vehicles are available to dispatch
            }
        }
        return false; // Not enough fire trucks available
    }

    private boolean dispatchAmbulances(int casualties, int casualtiesNeedDoctor) {
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation station = fireStations.get(i).getContent();

            int availableAmbulances = 0, availableDoctorAmbulances = 0;

            for (int j = 0; j < station.getAmbulances().getSize(); j++) {
                Ambulance ambulance = station.getAmbulances().get(j).getContent();
                if (ambulance.getHasDoctor()) {
                    availableDoctorAmbulances++;
                } else {
                    availableAmbulances++;
                }
            }

            // Check if enough ambulances are available
            if (availableAmbulances >= (casualties - casualtiesNeedDoctor) && availableDoctorAmbulances >= casualtiesNeedDoctor) {
                return true; // Enough ambulances available to dispatch
            }
        }
        return false; // Not enough ambulances available
    }


    public void printRespondingVehicles(Emergency call) {
        // Print the "is responding" message once
        System.out.print("To " + call.getKind() + " in " + call.getLocation() + " is responding:\n");
    
        // Iterate through fire stations
        for (int i = 0; i < fireStations.getSize(); i++) {
            FireStation station = fireStations.get(i).getContent();
    
            // Iterate over fire trucks in the station
            for (int j = 0; j < station.getFireTrucks().getSize(); j++) {
                FireTruck truck = station.getFireTrucks().get(j).getContent();
                // Print details of the fire truck
                System.out.print(truck.getID() + " - " + truck.getKind());
            }
    
            // Iterate over ambulances in the station
            for (int k = 0; k < station.getAmbulances().getSize(); k++) {
                Ambulance ambulance = station.getAmbulances().get(k).getContent();
                // Print details of the ambulance
                if (ambulance.getHasDoctor()) {
                    System.out.print(ambulance.getID() + " - Ambulance(Doctor)");
                } else {
                    System.out.print(ambulance.getID() + " - Ambulance");
                }
            }
        }
    }
    
        
}
