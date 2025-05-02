package dao;

import model.Appointment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getString("patient_name"),
                    rs.getDate("appointment_date")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
        }
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, appointment.getPatientName());
            pstmt.setDate(2, new java.sql.Date(appointment.getAppointmentDate().getTime()));

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding appointment: " + e.getMessage());
        }
    }

    public List<Appointment> searchAppointments(String patientName, java.util.Date appointmentDate) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";
        
        // Vérification des valeurs pour ajuster la requête
        if ((patientName != null && !patientName.trim().isEmpty()) && appointmentDate != null) {
            sql += " WHERE patient_name LIKE ? AND appointment_date = ?";
        } else if (patientName != null && !patientName.trim().isEmpty()) {
            sql += " WHERE patient_name LIKE ?";
        } else if (appointmentDate != null) {
            sql += " WHERE appointment_date = ?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Affectation des paramètres en fonction du cas
            if ((patientName != null && !patientName.trim().isEmpty()) && appointmentDate != null) {
                pstmt.setString(1, "%" + patientName + "%");
                pstmt.setDate(2, new java.sql.Date(appointmentDate.getTime()));
            } else if (patientName != null && !patientName.trim().isEmpty()) {
                pstmt.setString(1, "%" + patientName + "%");
            } else if (appointmentDate != null) {
                pstmt.setDate(1, new java.sql.Date(appointmentDate.getTime()));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                appointments.add(new Appointment(
                    rs.getInt("id"),
                    rs.getString("patient_name"),
                    rs.getDate("appointment_date")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des rendez-vous: " + e.getMessage());
        }
        return appointments;
    }
    public int countAppointmentsByDate(String appointmentDate) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM appointments WHERE date = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, appointmentDate);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return count;
    }
}



