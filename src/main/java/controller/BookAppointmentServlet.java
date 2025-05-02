package controller;

import dao.AppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;

import java.io.IOException;
import java.sql.Date;

@WebServlet("/bookAppointment")
public class BookAppointmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String patientName = request.getParameter("patientName");
        String date = request.getParameter("date");
        AppointmentDAO dao = new AppointmentDAO();

     // Create the Appointment object using the form data
     Appointment appointment = new Appointment(0, patientName, Date.valueOf(date));

     // Now call the addAppointment method with the Appointment object
     dao.addAppointment(appointment);


        response.sendRedirect("confirmation.jsp");
    }
}
