package classes;

import interfaces.I_Emergency;

public class Emergency implements I_Emergency {
    private String location; // Location of emergency
    private EmergencyKinds kind; // Type of emergency
    private int casualties; // Number of casualties
    private int casualtiesNeedsDoctor; // Number of casualties that need a doctor

    public Emergency(String location, EmergencyKinds kind, int casualties, int casualtiesNeedsDoctor) {
        this.location = location;
        this.kind = kind;
        this.casualties = casualties;
        this.casualtiesNeedsDoctor = casualtiesNeedsDoctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EmergencyKinds getKind() {
        return kind;
    }

    public void setKind(EmergencyKinds kind) {
        this.kind = kind;
    }

    public int getCasualties() {
        return casualties;
    }

    public void setCasualties(int casualties) {
        this.casualties = casualties;
    }

    public int getCasualtiesNeedsDoctor() {
        return casualtiesNeedsDoctor;
    }

    
    public void setCasualtiesNeedsDoctor(int casualtiesNeedsDoctor) {
        this.casualtiesNeedsDoctor = casualtiesNeedsDoctor;
    }
}
