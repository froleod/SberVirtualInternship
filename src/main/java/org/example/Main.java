package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File("src/main/java/org/example/cities.csv");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                System.out.println(Arrays.toString(parts));
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

        for (City city : cities) {
            System.out.println(city);
        }
    }
}