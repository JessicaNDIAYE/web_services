<%--
  Created by IntelliJ IDEA.
  User: hp
  Date: 11/06/2025
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Discord - Dashboard</title>
    <style>
        :root {
            --primary: #5865f2;
            --dark-primary: #4752c4;
            --dark-bg: #36393f;
            --darker-bg: #2f3136;
            --darkest-bg: #202225;
            --text: #dcddde;
            --text-muted: #72767d;
        }
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: var(--dark-bg);
            color: var(--text);
            display: grid;
            grid-template-columns: 240px auto 240px;
            height: 100vh;
        }
        /* Sidebar styles */
        .sidebar {
            background-color: var(--darker-bg);
            padding: 1rem;
            overflow-y: auto;
        }
        .server-name {
            font-weight: bold;
            padding: 1rem 0;
            border-bottom: 1px solid var(--darkest-bg);
            margin-bottom: 1rem;
        }
        .channel-list {
            list-style: none;
            padding: 0;
        }
        .channel-list li {
            padding: 0.5rem;
            border-radius: 3px;
            cursor: pointer;
        }
        .channel-list li:hover {
            background-color: var(--darkest-bg);
        }
        .channel-list li.active {
            background-color: var(--darkest-bg);
        }
        /* Main content styles */
        .main-content {
            display: grid;
            grid-template-rows: auto 1fr auto;
        }
        .channel-header {
            padding: 1rem;
            border-bottom: 1px solid var(--darkest-bg);
        }
        .messages {
            padding: 1rem;
            overflow-y: auto;
        }
        .message {
            margin-bottom: 1rem;
        }
        .message-user {
            font-weight: bold;
            color: var(--primary);
        }
        .message-time {
            color: var(--text-muted);
            font-size: 0.8rem;
        }
        .message-content {
            margin-top: 0.25rem;
        }
        .message-input {
            padding: 1rem;
            background-color: var(--darker-bg);
        }
        .message-input textarea {
            width: 100%;
            background-color: var(--darkest-bg);
            border: none;
            border-radius: 5px;
            padding: 1rem;
            color: var(--text);
            resize: none;
        }
        /* Members sidebar */
        .members {
            background-color: var(--darker-bg);
            padding: 1rem;
            overflow-y: auto;
        }
        .member {
            display: flex;
            align-items: center;
            padding: 0.5rem 0;
        }
        .member-avatar {
            width: 32px;
            height: 32px;
            border-radius: 50%;
            background-color: var(--primary);
            margin-right: 0.5rem;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
        }
    </style>
</head>
<body>
<!-- Left sidebar - Channels -->
<div class="sidebar">
    <div class="server-name">Serveur Discord</div>
    <ul class="channel-list">
        <c:forEach items="${channels}" var="channel">
            <li class="${channel.id == currentChannel.id ? 'active' : ''}">
                # ${channel.nom}
            </li>
        </c:forEach>
    </ul>
</div>

<!-- Main content area -->
<div class="main-content">
    <div class="channel-header">
        <h2># ${currentChannel.nom}</h2>
        <p>${currentChannel.description}</p>
    </div>

    <div class="messages">
        <c:forEach items="${messages}" var="message">
            <div class="message">
                <div>
                    <span class="message-user">${message.utilisateur.nom}</span>
                    <span class="message-time">${message.dateEtHeure}</span>
                </div>
                <div class="message-content">${message.contenu}</div>
            </div>
        </c:forEach>
    </div>

    <div class="message-input">
        <form action="messages" method="POST">
            <input type="hidden" name="channelId" value="${currentChannel.id}">
            <textarea name="content" placeholder="Écrivez un message..." rows="3"></textarea>
            <button type="submit">Envoyer</button>
        </form>
    </div>
</div>

<!-- Right sidebar - Members -->
<div class="members">
    <h3>MEMBRES</h3>
    <c:forEach items="${users}" var="user">
        <div class="member">
            <div class="member-avatar">${user.nom.charAt(0)}</div>
            <div>${user.nom}</div>
        </div>
    </c:forEach>
</div>
</body>
</html>
