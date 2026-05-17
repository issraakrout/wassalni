// ============================================================
// Fichier : src/main/java/com/wassalni/controller/AuthController.java
// ============================================================
package com.wassalni.controller;

import com.wassalni.model.Personnel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wassalni.repository.PersonnelRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")  // Permet à Android d'appeler l'API
public class AuthController {

    @Autowired
    private PersonnelRepository personnelRepository;

    // -------------------------------------------------------
    // POST /api/login
    // Body JSON : { "login": "k.bensalah", "motP": "1234" }
    // -------------------------------------------------------
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> body) {

        String login = body.get("login");
        String motP  = body.get("motP");

        Optional<Personnel> result = personnelRepository.findByLoginAndMotP(login, motP);

        Map<String, Object> response = new HashMap<>();

        if (result.isPresent()) {
            Personnel p = result.get();
            response.put("success", true);
            response.put("idpers",     p.getIdpers());
            response.put("nom",        p.getNompers());
            response.put("prenom",     p.getPrenompers());
            response.put("role",       p.getCodeposte()); // "CTRL" ou "LIV"
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Login ou mot de passe incorrect");
            return ResponseEntity.status(401).body(response);
        }
    }
}
