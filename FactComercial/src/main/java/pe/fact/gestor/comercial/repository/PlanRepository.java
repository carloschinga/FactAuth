package pe.fact.gestor.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.fact.gestor.comercial.entity.Plan;
import java.math.BigDecimal;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    @Modifying
    @Transactional
    @Query(value = "CALL sp_fact_plan_insertar(:nombre, :monto, :tipo, :usuario)", nativeQuery = true)
    void registrarPlan(
            @Param("nombre") String nombre,
            @Param("monto") BigDecimal monto,
            @Param("tipo") Integer tipo,
            @Param("usuario") Integer usuarioId
    );

    @Query(value = "CALL sp_fact_plan_listar()", nativeQuery = true)
    java.util.List<Plan> listarPlanes();

}