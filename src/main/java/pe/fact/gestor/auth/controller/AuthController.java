package pe.fact.gestor.auth.controller;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import pe.fact.gestor.auth.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /* El API login es la funcionalidad de validar inicio de aplicacion*/
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            // Validación básica de entrada
            String username = loginData.get("username");
            String password = loginData.get("password");

            if (username == null || password == null) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Username y password son obligatorios"));
            }

            // Llamada al servicio (SHA validado en MySQL)
            String response = authService.login(username, password);

            // Convertir JSON string a Map para la respuesta
            JSONObject json = new JSONObject(response);
            return ResponseEntity.ok(json.toMap());

        } catch (BadCredentialsException e) {
            // 401 - credenciales inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Credenciales inválidas"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error interno del servidor"));
        }
    }






}
