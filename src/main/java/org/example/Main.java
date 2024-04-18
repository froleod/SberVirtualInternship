package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File("src/main/java/org/example/cities.csv");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                String name = parts[1];
                String region = parts[2];
                String district = parts[3];
                int population = Integer.parseInt(parts[4]);
                String foundation;
                if (parts.length < 6) {
                    foundation = "";
                } else {
                    foundation = parts[5];
                }
                City city = new City(name, region, district, population, foundation);
                cities.add(city);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }

        // 1 сортировка списка городов по наименованию в алфавитном порядке по убыванию без учета регистра
        Comparator<City> nameComparator = Comparator.comparing(city -> city.getName().toLowerCase());
        cities.sort(nameComparator.reversed());
        // на сайте ошибка в примере - вывод идет по возрастанию

//        cities.forEach(System.out::println);


        // 2 сортировка списка городов по федеральному округу и наименованию города внутри каждого федерального округа
        // в алфавитном порядке по убыванию с учетом регистра
        Comparator<City> districtComparator = Comparator.comparing(City::getDistrict).thenComparing(City::getName);
        cities.sort(districtComparator.reversed());
        // на сайте ошибка в примере - вывод идет по возрастанию

        cities.forEach(System.out::println);
    }
}