package com.example.consultatabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehicle {
    Integer tipoVeiculo;
    String valor;
    String marca;
    String modelo;
    Integer anoModelo;
    String combustivel;
    String codigoFipe;
    String mesReferencia;
    String siglaCombustivel;

    public Vehicle(DataVehicle vehicle) {
        this.tipoVeiculo = vehicle.tipoVeiculo();
        this.valor = vehicle.valor();
        this.marca = vehicle.marca();
        this.modelo = vehicle.modelo();
        this.anoModelo = vehicle.anoModelo();
        this.combustivel = vehicle.combustivel();
        this.codigoFipe = vehicle.codigoFipe();
        this.mesReferencia = vehicle.mesReferencia();
        this.siglaCombustivel = vehicle.siglaCombustivel();
    }

    @Override
    public String toString() {
        return "\nMarca: " + marca +
                "\nModelo: "+ modelo +
                "\nAno: " + anoModelo +
                "\nCombustível: " + combustivel +
                "\nValor: " + valor +
                "\nMês de referência: " + mesReferencia;
    }
}
