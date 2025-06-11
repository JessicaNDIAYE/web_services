<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 11/06/2025
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profil de ${user.nom}</title>
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
        .profile-container {
            background-color: #2f3136;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        }
        .profile-header {
            display: flex;
            align-items: center;
            margin-bottom: 1.5rem;
        }
        .profile-avatar {
            width: 80px;
            height: 80px;
            border-radius: 50%;
            background-color: #5865f2;
            margin-right: 1rem;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 2rem;
            font-weight: bold;
        }
        .profile-info {
            flex-grow: 1;
        }
        .profile-username {
            font-size: 1.5rem;
            margin: 0;
        }
        .profile-status {
            color: #72767d;
        }
        .profile-details {
            margin-top: 1.5rem;
        }
        .detail-item {
            margin-bottom: 1rem;
        }
        .detail-label {
            color: #b9bbbe;
            font-size: 0.9rem;
        }
        .detail-value {
            margin-top: 0.25rem;
        }
        .back-link {
            display: inline-block;
            margin-top: 1.5rem;
            color: #00aff4;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="profile-container">
    <div class="profile-header">
        <div class="profile-avatar">${user.nom.charAt(0)}</div>
        <div class="profile-info">
            <h2 class="profile-username">${user.nom}</h2>
            <div class="profile-status">En ligne</div>
        </div>
    </div>

    <div class="profile-details">
        <div class="detail-item">
            <div class="detail-label">EMAIL</div>
            <div class="detail-value">${user.email}</div>
        </div>
        <div class="detail-item">
            <div class="detail-label">DATE D'INSCRIPTION</div>
            <div class="detail-value">${user.dateInscription}</div>
        </div>
    </div>

    <a href="dashboard.jsp" class="back-link">← Retour au serveur</a>
</div>
</body>
</html>
