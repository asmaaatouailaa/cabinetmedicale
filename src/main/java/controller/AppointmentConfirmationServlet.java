package controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import dao.AppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

@WebServlet("/AppointmentConfirmationServlet")
public class AppointmentConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String patientName = request.getParameter("patient_name");
        String dateStr = request.getParameter("appointment_date");

        try {
            Date appointmentDate = Date.valueOf(dateStr);
            AppointmentDAO appointmentDAO = new AppointmentDAO();

            // Vérifier si la date est déjà prise
            List<Appointment> existingAppointments = appointmentDAO.searchAppointments("", appointmentDate);
            if (!existingAppointments.isEmpty()) {
                request.setAttribute("error", "Un rendez-vous est déjà pris pour cette date !");
                request.getRequestDispatcher("addAppointment.jsp").forward(request, response);
                return;
            }

            // Ajouter le rendez-vous
            Appointment appointment = new Appointment(0, patientName, appointmentDate);
            appointmentDAO.addAppointment(appointment);

            request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Format de date invalide !");
            request.getRequestDispatcher("addAppointment.jsp").forward(request, response);
        }
    }

}

