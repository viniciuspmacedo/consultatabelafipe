package com.example.consultatabelafipe.main;

import com.example.consultatabelafipe.model.Data;
import com.example.consultatabelafipe.model.DataVehicle;
import com.example.consultatabelafipe.model.Models;
import com.example.consultatabelafipe.model.Vehicle;
import com.example.consultatabelafipe.service.ApiConnection;
import com.example.consultatabelafipe.service.Converter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final Converter converter = new Converter();
    private final ApiConnection api = new ApiConnection();

    public void showMenu() {
        Scanner read = new Scanner(System.in);
        System.out.println("Qual o tipo de veículo de que você procura:");
        final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";
        String url = BASE_URL;
        boolean exit = false;
        do {
            System.out.println("Digite 1 para carros;\nDigite 2 para motos;\nDigite 3 para caminhões;");
            String vehicle = read.nextLine();

            switch (vehicle) {
                case "1" -> {
                    url += "carros/marcas";
                    exit = true;
                }
                case "2" -> {
                    url += "motos/marcas";
                    exit = true;
                }
                case "3" -> {
                    url += "caminhoes/marcas";
                    exit = true;
                }
                default -> System.out.println("Opção inválida");
            }
        } while (!exit);

        var json = api.getData(url);

        var brands = converter.getList(json, Data.class);

        System.out.println("-------- Marcas --------");
        brands.stream().sorted(Comparator.comparing(Data::codigo)).forEach(System.out::println);
        System.out.println("------------------------");
        System.out.println("Digite o código da marca:");

        String brandCode = read.nextLine();
        url += "/" + brandCode + "/modelos";
        json = api.getData(url);

        var models = converter.getData(json, Models.class);

        System.out.println("\n--------- Modelos ---------");
        models.modelos().stream().sorted(Comparator.comparing(Data::codigo)).forEach(System.out::println);
        System.out.println("---------------------------");

        System.out.println("Qual modelo deseja consultar?");
        String model = read.nextLine();
        List<Data> modelList = models.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(model.toLowerCase()))
                .collect(Collectors.toList());

        modelList.forEach(System.out::println);

        System.out.println("\nDigite o código do modelo:");
        var modelCode = read.nextLine();
        url += "/" + modelCode + "/anos";

        json = api.getData(url);
        var years = converter.getList(json, Data.class);

        List<Vehicle> vehicles = new ArrayList<>();
        for (Data year : years) {
            String yearsUrl = url + "/" + year.codigo();
            String yearJson = api.getData(yearsUrl);
            Vehicle vehicle = new Vehicle(converter.getData(yearJson, DataVehicle.class));
            vehicles.add(vehicle);
        }

        vehicles.forEach(System.out::println);
    }
}
