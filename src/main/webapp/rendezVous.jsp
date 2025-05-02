
<%@ page import="java.util.List, model.Appointment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Liste des Rendez-vous</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
   <h1>Liste des Rendez-vous</h1>

<!-- Formulaire de recherche -->
<form action="AppointmentServlet" method="get">
    Nom du Patient: <input type="text" name="patient_name">
    Date: <input type="date" name="appointment_date">
    <input type="submit" value="Rechercher">
</form>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Nom du Patient</th>
        <th>Date</th>
    </tr>
    <% 
        List<Appointment> appointments = (List<Appointment>) request.getAttribute("appointments");
        if (appointments != null) {
            for (Appointment appt : appointments) { 
    %>
    <tr>
        <td><%= appt.getId() %></td>
        <td><%= appt.getPatientName() %></td>
        <td><%= appt.getAppointmentDate() %></td>
    </tr>
    <% 
            }
        } 
    %>
</table>

<a href="addAppointment.jsp"><button>Ajouter un Rendez-vous</button></a>

</body>
</html>

