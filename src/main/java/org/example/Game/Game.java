package org.example.Game;

import org.example.CityesReader.CitiesReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Map<Character, List<String>> allCities;
    private Map<Character, List<String>> remainCities;
    private Map<Character, List<String>> cityRegistry;
    private static Game game = new Game();

    private Game(){
        CitiesReader cr = new CitiesReader();
        allCities = cr.getCitiesRepo();
        remainCities = cr.getCitiesRepo();
        cityRegistry = new HashMap<>();
    }

    public int checkAnswer(String city){
        if(!allCities.containsKey(city.charAt(0))) return - 1;
        if(!(allCities.get(city.charAt(0)).contains(city))) return -1;
        if(!(remainCities.get(city.charAt(0)).contains(city))) return 0;
        return 1;
    }

    public void removeCity(String city){
        cityRegistry.computeIfAbsent(city.charAt(0), k -> new ArrayList<>()).add(city);
        remainCities.get(city.charAt(0)).remove(city);
    }

    public static Game getInstance(){
        return game;
    }

    public Map<Character, List<String>> getCityRegistry() {
        return cityRegistry;
    }

    public Map<Character, List<String>> getRemainCities() {
        return remainCities;
    }
}
