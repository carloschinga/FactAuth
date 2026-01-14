package pe.fact.gestor.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // LOGIN (No tocar, este funciona bien)
    public String login(String username, String password) {
        String sql = "CALL sp_fact_usuario_login(?, ?)";
        return jdbcTemplate.query(sql, new Object[]{username, password}, rs -> {
            if (rs.next()) {
                return "{"
                        + "\"codiUsua\":" + rs.getInt("codiUsua") + ","
                        + "\"ndniUsua\":\"" + rs.getString("ndniUsua") + "\","
                        + "\"logiUsua\":\"" + rs.getString("logiUsua") + "\","
                        + "\"niveUsua\":" + rs.getInt("niveUsua") + ","
                        + "\"secreKey\":\"" + rs.getString("secreKey") + "\""
                        + "}";
            }
            return "{}";
        });
    }

    // VALIDACIÓN DE TOKEN (Aquí estaba el error)
    public pe.fact.gestor.auth.entity.Personal buscarPorUsuario(String username) {
        // CORRECCIÓN: Usamos TU tabla 'usuario' y TUS columnas reales
        String sql = "SELECT * FROM usuario WHERE logiUsua = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username}, (rs, rowNum) -> {
                pe.fact.gestor.auth.entity.Personal p = new pe.fact.gestor.auth.entity.Personal();

                // Mapeo manual a tu Entidad Personal para que Spring no se queje
                p.setCodiPers(rs.getInt("codiUsua"));   // ID
                p.setUsuario(rs.getString("logiUsua")); // Usuario (admin)
                p.setClave(rs.getString("passUsua"));   // Contraseña Hash

                // Datos ficticios porque tu tabla usuario no tiene nombres
                p.setNombPers("Usuario");
                p.setAppaPers("Sistema");

                return p;
            });
        } catch (Exception e) {
            System.out.println("Error buscando usuario en BD: " + e.getMessage());
            return null;
        }
    }
}