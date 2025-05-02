package model;

import java.util.Date;

public class Appointment {
    private int id;
    private String patientName;
  
    private Date appointmentDate;

    public Appointment(int id, String patientName,  Date appointmentDate) {
        this.id = id;
        this.patientName = patientName;
     
        this.appointmentDate = appointmentDate;
    }

    public int getId() { return id; }
    public String getPatientName() { return patientName; }
   
    public Date getAppointmentDate() { return appointmentDate; }
}
