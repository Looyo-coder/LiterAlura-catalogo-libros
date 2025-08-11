package com.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDTO {
    private String title;
    private List<AutorDTO> authors;
    private List<String> languages;
    private int numeroDeDescargas;

    public String getTitle() {
        return title;
    }

    public List<AutorDTO> getAuthors() {
        return authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
}