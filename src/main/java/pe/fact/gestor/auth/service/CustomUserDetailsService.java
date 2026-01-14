package pe.fact.gestor.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.fact.gestor.auth.repository.AuthRepository;
import pe.fact.gestor.auth.entity.Personal;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository repository; // Tu repositorio que busca en la BD

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscamos el usuario en TU base de datos
        // Nota: Asegúrate de que AuthRepository tenga un metodo findByUsuario(username)
        // O usa el query nativo que ya tenías, adaptado.

        // Aquí asumo que repository.findByUsuario devuelve tu entidad Personal
        // Si no tienes ese metodo, tendrás que agregarlo en AuthRepository.
        Personal personal = repository.buscarPorUsuario(username);

        if (personal == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // 2. Convertimos tu Personal a un User de Spring Security
        // "new ArrayList<>()" es para los roles (Authorities), por ahora vacío funciona.
        return new User(personal.getUsuario(), personal.getClave(), new ArrayList<>());
    }
}