<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 11/06/2025
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Erreur</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #36393f;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            text-align: center;
        }
        .error-container {
            background-color: #2f3136;
            padding: 2rem;
            border-radius: 8px;
            width: 500px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }
        .error-title {
            color: #f04747;
            font-size: 2rem;
            margin-bottom: 1rem;
        }
        .error-message {
            margin-bottom: 1.5rem;
        }
        .home-link {
            display: inline-block;
            padding: 10px 20px;
            background-color: #5865f2;
            color: white;
            text-decoration: none;
            border-radius: 3px;
        }
        .home-link:hover {
            background-color: #4752c4;
        }
    </style>
</head>
<body>
<div class="error-container">
    <div class="error-title">Une erreur est survenue</div>
    <div class="error-message">
        ${not empty errorMessage ? errorMessage : 'Une erreur inattendue s\'est produite.'}
    </div>
    <a href="dashboard.jsp" class="home-link">Retour à l'accueil</a>
</div>
</body>
</html>
