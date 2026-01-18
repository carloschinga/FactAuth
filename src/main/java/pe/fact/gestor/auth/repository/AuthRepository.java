package pe.fact.gestor.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Repository
public class AuthRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // --- LOGIN CON SOPORTE SHA-256 ---
    public String login(String username, String password) {
        // 1. Encriptamos la clave recibida para compararla con la BD
        String passwordHash = hashSHA256(password);

        // Debug para verificar
        System.out.println("Login: " + username + " | Input: " + password + " | Hash Generado: " + passwordHash);

        // 2. Consulta Directa (Buscamos usuario y clave encriptada)
        String sql = "SELECT * FROM usuario WHERE logiUsua = ? AND passUsua = ?";

        return jdbcTemplate.query(sql, new Object[]{username, passwordHash}, rs -> {
            if (rs.next()) {
                String secret = "";
                try { secret = rs.getString("secreKey"); } catch (Exception e) { secret = ""; }
                if (secret == null) secret = "";

                return "{"
                        + "\"codiUsua\":" + rs.getInt("codiUsua") + ","
                        + "\"ndniUsua\":\"" + rs.getString("ndniUsua") + "\","
                        + "\"logiUsua\":\"" + rs.getString("logiUsua") + "\","
                        + "\"niveUsua\":" + rs.getInt("niveUsua") + ","
                        + "\"secreKey\":\"" + secret + "\""
                        + "}";
            }
            return "{}"; // No encontrado
        });
    }

    public pe.fact.gestor.auth.entity.Personal buscarPorUsuario(String username) {
        String sql = "SELECT * FROM usuario WHERE logiUsua = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                pe.fact.gestor.auth.entity.Personal p = new pe.fact.gestor.auth.entity.Personal();
                p.setCodiPers(rs.getInt("codiUsua"));
                p.setUsuario(rs.getString("logiUsua"));
                p.setClave(rs.getString("passUsua"));
                p.setNombPers("Usuario");
                p.setAppaPers("Sistema");
                return p;
            });
        } catch (Exception e) {
            return null;
        }
    }

    // --- HELPER PARA ENCRIPTAR (SHA-256) ---
    private String hashSHA256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error al encriptar clave", ex);
        }
    }
}