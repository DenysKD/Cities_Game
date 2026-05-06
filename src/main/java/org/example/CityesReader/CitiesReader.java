package org.example.CityesReader;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitiesReader {

    private final String url = "src/main/resources/Cities.txt";
    private final Map<Character, List<String>> citiesRepo = new HashMap<>();

    public CitiesReader(){
        try (BufferedReader br = Files.newBufferedReader(Path.of(url), /*Charset.forName("CP1251")*/StandardCharsets.UTF_8)){
            String line;
            while ((line = br.readLine()) != null){

                String[] tmpLine = line.split(",+");
                for (int i = 0; i < tmpLine.length; i++) {
                    String city = tmpLine[i].trim();
                    citiesRepo.computeIfAbsent(city.charAt(0), k -> new ArrayList<>()).add(city);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Map<Character, List<String>> getCitiesRepo() {
        Map<Character, List<String>> copy = new HashMap<>();
        for (Map.Entry<Character, List<String>> entry : citiesRepo.entrySet()) {
            copy.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return copy;
    }
}
