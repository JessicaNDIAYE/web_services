package com.example.discord_projet;
import POJO.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.json.*;  // Au lieu de javax.json
import JPA.UtilisateurRepository;
import java.util.Optional;

@WebServlet("/api/users/*")
public class UtilisateurServlet extends HttpServlet {

    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.utilisateurRepository = (UtilisateurRepository) context.getAttribute("utilisateurRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");
        String pathInfo = request.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // Lister tous les utilisateurs (simplifié)
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
                utilisateurRepository.findAll().forEach(user -> {
                    arrayBuilder.add(Json.createObjectBuilder()
                            .add("id", user.getId())
                            .add("nom", user.getNom())
                            .add("email", user.getEmail()));
                });
                response.getWriter().write(arrayBuilder.build().toString());
            } else {
                // Détails d'un utilisateur
                int userId = Integer.parseInt(pathInfo.substring(1));
                Optional<Utilisateur> user = utilisateurRepository.findById(userId);

                if (user.isPresent()) {
                    JsonObjectBuilder userJson = Json.createObjectBuilder()
                            .add("id", user.get().getId())
                            .add("nom", user.get().getNom())
                            .add("email", user.get().getEmail())
                            .add("dateInscription", user.get().getDateInscription().toString());

                    response.getWriter().write(userJson.build().toString());
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
            JsonObject userData = reader.readObject();

            Utilisateur newUser = new Utilisateur();
            newUser.setNom(userData.getString("nom"));
            newUser.setEmail(userData.getString("email"));
            newUser.setPassword(userData.getString("password"));
            newUser.setDateInscription(new java.util.Date());

            Utilisateur savedUser = utilisateurRepository.save(newUser);

            JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
                    .add("id", savedUser.getId())
                    .add("nom", savedUser.getNom())
                    .add("email", savedUser.getEmail());

            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write(responseBuilder.build().toString());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
