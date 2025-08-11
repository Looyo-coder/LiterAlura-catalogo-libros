package com.literalura.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer anoNacimiento;
    private Integer anoFallecimiento;

    @ManyToMany(mappedBy = "autores")
    private List<Libro> libros;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAnoNacimiento() { return anoNacimiento; }
    public void setAnoNacimiento(Integer anoNacimiento) { this.anoNacimiento = anoNacimiento; }

    public Integer getAnoFallecimiento() { return anoFallecimiento; }
    public void setAnoFallecimiento(Integer anoFallecimiento) { this.anoFallecimiento = anoFallecimiento; }
}