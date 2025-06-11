package com.example.discord_projet;

import POJO.Channel;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.json.*;
import JPA.ChannelRepository;
import java.util.List;
import java.util.Optional;

@WebServlet("/api/channels/*")
public class ChannelServlet extends HttpServlet {

    private ChannelRepository channelRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.channelRepository = (ChannelRepository) context.getAttribute("channelRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Lister tous les canaux
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                channelRepository.findAll().forEach(channel -> {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", channel.getIdChannel())
                            .add("nom", channel.getTopic()));
                            //.add("description", channel.getDescription()));
                });
                response.getWriter().write(arrayBuilder.build().toString());
            } else {
                // Détails d'un canal
                int channelId = Integer.parseInt(pathInfo.substring(1));
                Optional<Channel> channel = channelRepository.findById(channelId);

                if (channel.isPresent()) {
                    JsonObjectBuilder channelJson = Json.createObjectBuilder()
                            .add("id", channel.get().getIdChannel())
                            .add("nom", channel.get().getTopic());
                            //.add("description", channel.get().getDescription())
                            //.add("dateCreation", channel.get().getDateCreation().toString());

                    response.getWriter().write(channelJson.build().toString());
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        try (JsonReader reader = Json.createReader(request.getReader())) {
            JsonObject channelData = reader.readObject();

            Channel newChannel = new Channel();
            newChannel.setTopic(channelData.getString("nom"));
            //newChannel.setDescription(channelData.getString("description"));
            //newChannel.setDateCreation(new java.util.Date());

            Channel savedChannel = channelRepository.save(newChannel);

            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
                    .add("id", savedChannel.getIdChannel())
                    .add("nom", savedChannel.getTopic());
                    //.add("description", savedChannel.getDescription());

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(responseBuilder.build().toString());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
