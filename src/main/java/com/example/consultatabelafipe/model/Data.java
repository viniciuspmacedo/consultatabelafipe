package com.example.consultatabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(
        @JsonAlias("codigo") String codigo,
        @JsonAlias("nome") String nome
) {
    @Override
    public String toString() {
        return "cod: " + codigo + ", nome: " + nome;
    }
}
