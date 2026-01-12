package pe.fact.gestor.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String login(String username, String password) {

        String sql = "CALL sp_fact_usuario_login(?, ?)";

        return jdbcTemplate.query(sql, new Object[]{username, password}, rs -> {
            if (rs.next()) {
                // Ejemplo de JSON manual
                return "{"
                        + "\"codiUsua\":" + rs.getInt("codiUsua") + ","
                        + "\"ndniUsua\":\"" + rs.getString("ndniUsua") + "\","
                        + "\"logiUsua\":\"" + rs.getString("logiUsua") + "\","
                        + "\"niveUsua\":" + rs.getInt("niveUsua") + ","
                        + "\"secreKey\":\"" + rs.getString("secreKey") + "\""
                        + "}";
            }
            return "{}"; // login incorrecto
        });
    }


}
