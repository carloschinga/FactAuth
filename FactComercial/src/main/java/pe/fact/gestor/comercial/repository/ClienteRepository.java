package pe.fact.gestor.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.fact.gestor.comercial.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    // POST (api/clientes/guardar)
    @Modifying
    @Transactional
    @Query(value = "CALL sp_fact_cliente_insertar(:tipoDoc, :numDoc, :pat, :mat, :nom, :razon, :mail, :sexo, :cel, :pass, :usu)", nativeQuery = true)
    void registrarCliente(
            @Param("tipoDoc") String tipoDoc,
            @Param("numDoc") String numDoc,
            @Param("pat") String paterno,
            @Param("mat") String materno,
            @Param("nom") String nombres,
            @Param("razon") String razonSocial,
            @Param("mail") String correo,
            @Param("sexo") String sexo,
            @Param("cel") String celular,
            @Param("pass") String password,
            @Param("usu") Integer usuarioId
    );
    @Query(value = "CALL sp_fact_cliente_listar()", nativeQuery = true)
    java.util.List<Cliente> listarClientes();
    @Modifying
    @Transactional
    @Query(value = "CALL sp_fact_cliente_actualizar(:id, :tipoDoc, :numDoc, :pat, :mat, :nom, :razon, :mail, :sexo, :cel, :pass, :usu)", nativeQuery = true)
    void actualizarCliente(
            @Param("id") Integer id,
            @Param("tipoDoc") String tipoDoc,
            @Param("numDoc") String numDoc,
            @Param("pat") String paterno,
            @Param("mat") String materno,
            @Param("nom") String nombres,
            @Param("razon") String razonSocial,
            @Param("mail") String correo,
            @Param("sexo") String sexo,
            @Param("cel") String celular,
            @Param("pass") String password,
            @Param("usu") Integer usuarioId
    );
}