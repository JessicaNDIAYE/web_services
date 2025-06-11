package com.example.discord_projet;

import JPA.ChannelRepository;
import JPA.MessageRepository;
import JPA.UtilisateurRepository;
import POJO.Channel;
import POJO.Message;
import POJO.Publier;
import POJO.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import com.google.gson.*;


@WebServlet("/api/messages/*")
public class MessageServlet extends HttpServlet {

    private MessageRepository messageRepository;
    private UtilisateurRepository utilisateurRepository;
    private ChannelRepository channelRepository;
    private Gson gson = new Gson();

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.messageRepository = (MessageRepository) context.getAttribute("messageRepository");
        this.utilisateurRepository = (UtilisateurRepository) context.getAttribute("utilisateurRepository");
        this.channelRepository = (ChannelRepository) context.getAttribute("channelRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String pathInfo = request.getPathInfo();

            if (pathInfo == null || pathInfo.equals("/")) {
                // Récupérer tous les messages
                List<Message> messages = messageRepository.findAll();
                out.print(gson.toJson(messages));
            } else {
                String[] splits = pathInfo.split("/");

                if (splits.length == 2) {
                    // Récupérer un message par ID
                    int messageId = Integer.parseInt(splits[1]);
                    Optional<Message> message = messageRepository.findById(messageId);

                    if (message.isPresent()) {
                        out.print(gson.toJson(message.get()));
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else if (splits.length == 3 && splits[1].equals("channel")) {
                    // Récupérer les messages d'un canal
                    int channelId = Integer.parseInt(splits[2]);
                    List<Message> messages = messageRepository.findByChannelId(channelId);
                    out.print(gson.toJson(messages));
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (BufferedReader reader = request.getReader()) {
            JsonObject json = gson.fromJson(reader, JsonObject.class);

            Message message = new Message();
            message.setContenu(json.get("contenu").getAsString());
            message.setDateEtHeure(new Date());

            int utilisateurId = json.get("id_utilisateur").getAsInt();
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(utilisateurId);

            if (utilisateur.isPresent()) {
                message.setUtilisateur(utilisateur.get());

                // Vérifier si c'est un message de canal ou direct
                if (json.has("id_channel")) {
                    int channelId = json.get("id_channel").getAsInt();
                    Optional<Channel> channel = channelRepository.findById(channelId);

                    if (channel.isPresent()) {
                        Message savedMessage = messageRepository.save(message);
                        Publier publier = new Publier(savedMessage, channel.get());
                        // Sauvegarder la relation
                        // (implémentez cette partie selon votre modèle)

                        response.setStatus(HttpServletResponse.SC_CREATED);
                        out.print(gson.toJson(savedMessage));
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Channel non trouvé");
                    }
                } else if (json.has("id_destinataire")) {
                    // Message direct (implémentation similaire)
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Utilisateur non trouvé");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (BufferedReader reader = request.getReader()) {
            String pathInfo = request.getPathInfo();
            String[] splits = pathInfo.split("/");

            if (splits.length == 2) {
                int messageId = Integer.parseInt(splits[1]);
                Optional<Message> existingMessage = messageRepository.findById(messageId);

                if (existingMessage.isPresent()) {
                    Message message = existingMessage.get();
                    JsonObject json = gson.fromJson(reader, JsonObject.class);

                    if (json.has("contenu")) {
                        message.setContenu(json.get("contenu").getAsString());
                        message.setDateEtHeure(new LocalDate());

                        Message updatedMessage = messageRepository.save(message);
                        out.print(gson.toJson(updatedMessage));
                    } else {
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Contenu manquant");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "URL invalide");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } finally {
            out.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String pathInfo = request.getPathInfo();
            String[] splits = pathInfo.split("/");

            if (splits.length == 2) {
                int messageId = Integer.parseInt(splits[1]);

                if (messageRepository.existsById(messageId)) {
                    messageRepository.deleteById(messageId);
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "URL invalide");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
