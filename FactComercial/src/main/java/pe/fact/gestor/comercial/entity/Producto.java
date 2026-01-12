package pe.fact.gestor.comercial.entity;

import jakarta.persistence.*;
// Si usas Java antiguo (Java EE), cambia jakarta por javax
import lombok.Data; // Si agregaste Lombok. Si no, genera Getters/Setters manual.

@Data
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codiProd") // Nombre exacto en BD
    private Integer codiProd;

    @Column(name = "nombProd")
    private String nombProd;

    // Ojo: En tu tabla 'producto' vi que solo tenías estas columnas principales
    // Si agregaste precio/stock, añádelos aquí también.
}