package pe.fact.gestor.comercial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.fact.gestor.comercial.entity.Cliente;
import pe.fact.gestor.comercial.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public void crear(Cliente c) {
        if(c.getUsuarioRegistroId() == null) c.setUsuarioRegistroId(1);

        // Manejo de nulos para que el SP no falle
        if(c.getRaznSociClie() == null) c.setRaznSociClie("-");

        repository.registrarCliente(
                c.getCodiTipoDocu(),
                c.getNumeDocu(),
                c.getAppaClie(),
                c.getApmaClie(),
                c.getNombClie(),
                c.getRaznSociClie(),
                c.getMailClie(),
                c.getSexoClie(),
                c.getCeluClie(),
                c.getPassClie(),
                c.getUsuarioRegistroId()
        );
    }
    public java.util.List<Cliente> listarTodos() {
        return repository.listarClientes();
    }
    public void actualizar(Integer id, Cliente c) {
        if(id == null) {
            throw new RuntimeException("El ID del cliente es obligatorio para actualizar");
        }

        // Asumimos usuario 1 si no viene (auditoría)
        if(c.getUsuarioRegistroId() == null) c.setUsuarioRegistroId(1);
        if(c.getRaznSociClie() == null) c.setRaznSociClie("-");

        repository.actualizarCliente(
                id,
                c.getCodiTipoDocu(),
                c.getNumeDocu(),
                c.getAppaClie(),
                c.getApmaClie(),
                c.getNombClie(),
                c.getRaznSociClie(),
                c.getMailClie(),
                c.getSexoClie(),
                c.getCeluClie(),
                c.getPassClie(),
                c.getUsuarioRegistroId()
        );
    }
}