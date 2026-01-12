package pe.fact.gestor.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.fact.gestor.comercial.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    //POST (api/productos/guardar)
    @Modifying // Indica que esto va a alterar datos, no solo leer
    @Transactional // Manejo de transacción
    @Query(value = "CALL sp_fact_producto_insertar(:nombre)", nativeQuery = true)
    void registrarProducto(@Param("nombre") String nombre);
}