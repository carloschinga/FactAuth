package pe.fact.gestor.comercial.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "plan")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codiPlan")
    private Integer codiPlan;

    @Column(name = "nombPlan")
    private String nombPlan;

    @Column(name = "montPlan")
    private BigDecimal montPlan; // BigDecimal es mejor para dinero que Double

    @Column(name = "tipoPlan")
    private Integer tipoPlan;

    // Este campo no está en la tabla, es temporal para enviar el ID del usuario que registra
    @Transient
    private Integer usuarioRegistroId;
}