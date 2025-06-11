package com.example.discord_projet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.json.*;
import JPA.MessageRepository;
import JPA.ChannelRepository;
import JPA.UtilisateurRepository;

@WebServlet("/api/home")
public class HomeServlet extends HttpServlet {

    private MessageRepository messageRepository;
    private ChannelRepository channelRepository;
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.messageRepository = (MessageRepository) context.getAttribute("messageRepository");
        this.channelRepository = (ChannelRepository) context.getAttribute("channelRepository");
        this.utilisateurRepository = (UtilisateurRepository) context.getAttribute("utilisateurRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        try {
            // Statistiques de base
            long userCount = utilisateurRepository.count();
            long channelCount = channelRepository.count();
            long messageCount = messageRepository.count();

            // Derniers messages
            JsonArrayBuilder lastMessages = Json.createArrayBuilder();
            messageRepository.findTop5ByOrderByDateEtHeureDesc().forEach(msg -> {
                lastMessages.add(Json.createObjectBuilder()
                        .add("id", msg.getIdMessage())
                        .add("contenu", msg.getContenu())
                        .add("auteur", msg.getUtilisateur().getNom())
                        .add("date", msg.getDateEtHeure().toString()));
            });

            JsonObjectBuilder stats = Json.createObjectBuilder()
                    .add("userCount", userCount)
                    .add("channelCount", channelCount)
                    .add("messageCount", messageCount)
                    .add("lastMessages", lastMessages);

            response.getWriter().write(stats.build().toString());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
