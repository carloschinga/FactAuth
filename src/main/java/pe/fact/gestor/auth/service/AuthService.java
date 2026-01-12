package pe.fact.gestor.auth.service;



import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import pe.fact.gestor.auth.repository.AuthRepository;
import pe.fact.gestor.auth.util.JwtUtil;


@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    private JwtUtil jwtUtil;


    public String login(String username, String password) {

        // Llamada correcta al repository (MySQL + SHA)
        String response = authRepository.login(username, password);

        JSONObject result = new JSONObject(response);

        // Validación correcta: si no hay codiUsua → login inválido
        if (result.isEmpty() || !result.has("codiUsua")) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        // Generar access token
        String token = jwtUtil.generateToken(username);

        // Respuesta final
        result.put("resultado", "OK");
        result.put("token", token);

        return result.toString();
    }


}
