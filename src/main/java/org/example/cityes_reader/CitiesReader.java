package org.example.cityes_reader;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesReader {

    private final String root = "Cities.txt";
    private final Map<Character, List<String>> citiesRepo = new HashMap<>();

    public CitiesReader(){

        try(InputStream is = getClass().getClassLoader().getResourceAsStream(root)){
            if (is == null) {
                throw new FileNotFoundException("Файл не знайдено в classpath: file.txt");
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))){
                String line;
                while ((line = br.readLine()) != null){

                    line = line.toLowerCase();
                    String[] tmpLine = line.split(",+");
                    for (int i = 0; i < tmpLine.length; i++) {
                        String city = tmpLine[i].trim();
                        System.out.println("city - " + city + ":::: char - " + city.charAt(0));
                        citiesRepo.computeIfAbsent(city.charAt(0), k -> new ArrayList<>()).add(city);
                    }
                }
            }
        }catch (FileNotFoundException e) {
            System.err.println("Помилка: " + e.getMessage());
        }  catch (IOException e) {
            throw new RuntimeException("Помилка читання файлу:", e);
        }
        /*try (BufferedReader br = Files.newBufferedReader(Path.of(url), StandardCharsets.UTF_8)){
            String line;
            while ((line = br.readLine()) != null){

                line = line.toLowerCase();
                String[] tmpLine = line.split(",+");
                for (int i = 0; i < tmpLine.length; i++) {
                    String city = tmpLine[i].trim();
                    citiesRepo.computeIfAbsent(city.charAt(0), k -> new ArrayList<>()).add(city);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }*/
    }

    public Map<Character, List<String>> getCitiesRepo() {
        Map<Character, List<String>> copy = new HashMap<>();
        for (Map.Entry<Character, List<String>> entry : citiesRepo.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }
}
