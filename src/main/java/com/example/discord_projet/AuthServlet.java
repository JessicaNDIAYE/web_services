package com.example.discord_projet;

import POJO.Utilisateur;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.Optional;

import jakarta.json.*;
import JPA.UtilisateurRepository;

@WebServlet("/api/auth/*")
public class AuthServlet extends HttpServlet {

    private UtilisateurRepository utilisateurRepository;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        this.utilisateurRepository = (UtilisateurRepository) context.getAttribute("utilisateurRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json;charset=UTF-8");

        try (JsonReader reader = Json.createReader(request.getReader())) {
            JsonObject credentials = reader.readObject();
            String email = credentials.getString("email");
            String password = credentials.getString("password");

            Optional<Utilisateur> user = utilisateurRepository.findByEmailAndPassword(email, password);

            if (user.isPresent()) {
                // Créer la session
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.get().getIdUtilisateur());
                session.setAttribute("username", user.get().getNom());

                // Réponse JSON
                JsonObjectBuilder responseBuilder = Json.createObjectBuilder()
                        .add("id", user.get().getId())
                        .add("nom", user.get().getNom())
                        .add("email", user.get().getEmail());

                response.getWriter().write(responseBuilder.build().toString());
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Identifiants invalides");
            }
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}