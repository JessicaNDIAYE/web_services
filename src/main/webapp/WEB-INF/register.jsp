<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 11/06/2025
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Discord - Inscription</title>
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
        }
        .register-container {
            background-color: #2f3136;
            padding: 2rem;
            border-radius: 8px;
            width: 300px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }
        .register-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #b9bbbe;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: none;
            border-radius: 3px;
            background-color: #40444b;
            color: white;
        }
        .form-group input:focus {
            outline: none;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #5865f2;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
            font-weight: bold;
        }
        button:hover {
            background-color: #4752c4;
        }
        .login-link {
            margin-top: 1rem;
            text-align: center;
        }
        .login-link a {
            color: #00aff4;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>Créer un compte</h2>
    <form action="users" method="POST">
        <div class="form-group">
            <label for="username">Nom d'utilisateur</label>
            <input type="text" id="username" name="nom" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit">S'inscrire</button>
    </form>
    <div class="login-link">
        <a href="login.jsp">Déjà un compte ? Se connecter</a>
    </div>
</div>
</body>
</html>
