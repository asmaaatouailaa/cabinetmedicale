package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.AppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

@WebServlet("/AppointmentServlet")
public class AppointmentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String patientName = request.getParameter("patient_name");
        String dateStr = request.getParameter("appointment_date");
        Date appointmentDate = null;

        // Vérifier et convertir la date si elle est fournie
        if (dateStr != null && !dateStr.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                appointmentDate = sdf.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        AppointmentDAO appointmentDAO = new AppointmentDAO();
        List<Appointment> appointments;

        // Si au moins un champ est rempli, effectuer la recherche
        if ((patientName != null && !patientName.trim().isEmpty()) || appointmentDate != null) {
            appointments = appointmentDAO.searchAppointments(patientName, appointmentDate);
        } else {
            appointments = appointmentDAO.getAllAppointments();
        }

        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("rendezVous.jsp").forward(request, response);
    }
}

