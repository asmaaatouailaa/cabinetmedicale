<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Ajouter un Rendez-vous</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 20px;
        }

        h1 {
            color: #333;
        }

        form {
            background: white;
            max-width: 400px;
            margin: auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #28a745;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #218838;
        }

        button {
            background-color: #007bff;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: 0.3s;
            margin-top: 10px;
        }

        button:hover {
            background-color: #0056b3;
        }

        p {
            color: red;
            font-weight: bold;
        }
    </style>
    <title>Ajouter un Rendez-vous</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
    <h1>Ajouter un Rendez-vous</h1>
    
    <!-- Display error message if there is one -->
    <% String error = (String) request.getAttribute("error"); %>
    <% if (error != null) { %>
        <p style="color: red;"><%= error %></p>
    <% } %>

    <form action="AppointmentConfirmationServlet" method="post">
        Nom du Patient: <input type="text" name="patient_name" required><br>
      
        Date: <input type="date" name="appointment_date" required><br>
        <input type="submit" value="Enregistrer">
    </form>
    <br>
   <a href="appointments"><button>Retour</button></a>

</body>
</html>