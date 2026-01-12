package pe.fact.gestor.comercial.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codiClie")
    private Integer codiClie;

    @Column(name = "codiTipoDocu")
    private String codiTipoDocu; // CHAR(2)

    @Column(name = "numeDocu")
    private String numeDocu;

    @Column(name = "appaClie")
    private String appaClie;

    @Column(name = "apmaClie")
    private String apmaClie;

    @Column(name = "nombClie")
    private String nombClie;

    @Column(name = "raznSociClie")
    private String raznSociClie;

    @Column(name = "mailClie")
    private String mailClie;

    @Column(name = "sexoClie")
    private String sexoClie; // CHAR(1)

    @Column(name = "celuClie")
    private String celuClie;

    @Column(name = "passClie")
    private String passClie;

    @Transient
    private Integer usuarioRegistroId;
}