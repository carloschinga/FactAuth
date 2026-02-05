package pe.fact.gestor.auth.controller;

import java.util.Collections;
import java.util.Map;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;
import pe.fact.gestor.auth.service.AuthService;

@RestController
@RequestMapping("/") // La raíz del contexto (/auth)
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ==========================================
    // 1. DIAGNÓSTICO EN LA PÁGINA PRINCIPAL
    // ==========================================
    // Se ejecuta al entrar a: http://IP:8080/auth/
    @GetMapping // <--- SIN RUTA, responde a la raíz "/"
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("ESTADO: OPERATIVO - El módulo AUTH responde correctamente.");
    }

    // ==========================================
    // 2. ENDPOINT DE LOGIN
    // ==========================================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String username = loginData.get("username");
            String password = loginData.get("password");

            if (username == null || password == null) {
                return ResponseEntity.badRequest()
                        .body(Collections.singletonMap("error", "Username y password son obligatorios"));
            }

            String response = authService.login(username, password);
            JSONObject json = new JSONObject(response);
            return ResponseEntity.ok(json.toMap());

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Credenciales inválidas"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "Error interno: " + e.getMessage()));
        }
    }
}