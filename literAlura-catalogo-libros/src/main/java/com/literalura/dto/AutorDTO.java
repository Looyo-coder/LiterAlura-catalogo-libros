package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {

    @JsonProperty("name")
    private String nombre;

    @JsonProperty("birth_year")
    private Integer anoNacimiento;

    @JsonProperty("death_year")
    private Integer anoFallecimiento;

    public String getNombre() {
        return nombre;
    }

    public Integer getAnoNacimiento() {
        return anoNacimiento;
    }

    public Integer getAnoFallecimiento() {
        return anoFallecimiento;
    }
}