package com.example.discord_projet;

import POJO.Message;
import POJO.Reaction;
import POJO.ReactionId;
import POJO.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.json.*;
import JPA.ReactionRepository;
import JPA.MessageRepository;
import JPA.UtilisateurRepository;

import java.util.Optional;

@WebServlet("/api/reactions/*")
public class ReactionServlet extends HttpServlet {

    private ReactionRepository reactionRepository;
    private MessageRepository messageRepository;
    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.reactionRepository = (ReactionRepository) context.getAttribute("reactionRepository");
        this.messageRepository = (MessageRepository) context.getAttribute("messageRepository");
        this.utilisateurRepository = (UtilisateurRepository) context.getAttribute("utilisateurRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        try (JsonReader reader = Json.createReader(request.getReader())) {
            JsonObject reactionData = reader.readObject();

            int messageId = reactionData.getInt("messageId");
            int userId = reactionData.getInt("userId");
            String emoji = reactionData.getString("emoji");

            Optional<Message> message = messageRepository.findById(messageId);
            Optional<Utilisateur> user = utilisateurRepository.findById(userId);

            if (message.isPresent() && user.isPresent()) {
                Reaction reaction = new Reaction();
                reaction.setMessage(message.get());
                reaction.setUtilisateur(user.get());
                reaction.setEmoji(emoji);
                reaction.setDate(new java.util.Date());

                Reaction savedReaction = reactionRepository.save(reaction);

                JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
                        .add("userId", savedReaction.getUtilisateur().getIdUtilisateur())
                        .add("messageId", savedReaction.getMessage().getIdMessage())
                        .add("emoji", savedReaction.getEmoji())
                        .add("date", savedReaction.getDate().toString());

                response.setStatus(HttpServletResponse.SC_CREATED);
                response.getWriter().write(responseBuilder.build().toString());
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Message ou utilisateur non trouvé");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo(); // format attendu : /userId/messageId
        if (pathInfo == null || pathInfo.equals("/") || pathInfo.split("/").length != 3) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "URL attendue : /{userId}/{messageId}");
            return;
        }

        try {
            String[] parts = pathInfo.split("/");
            int userId = Integer.parseInt(parts[1]);
            int messageId = Integer.parseInt(parts[2]);

            ReactionId id = new ReactionId(userId, messageId);
            if (reactionRepository.existsById(id)) {
                reactionRepository.deleteById(id);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Réaction introuvable");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
