package org.example.game;

import org.example.cities_reader.CitiesReader;
import org.example.game_exceptions.EmptyLineException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private Map<Character, List<String>> allCities;
    private Map<Character, List<String>> remainCities;
    private Map<Character, List<String>> cityRegistry;

    public Game(){
        CitiesReader cr = new CitiesReader();
        allCities = cr.getCitiesRepo();
        remainCities = cr.getCitiesRepo();
        cityRegistry = new HashMap<>();
    }

    public RepoStatus checkAnswer(String city){
        if(city.isBlank()){
            throw new EmptyLineException("Введено порожній рядок!");
        }
        city = city.toLowerCase();
        if(!allCities.containsKey(city.charAt(0)) || !(allCities.get(city.charAt(0)).contains(city))) {
            return RepoStatus.NOT_EXIST;
        }
        if(!(remainCities.get(city.charAt(0)).contains(city))) {
            return RepoStatus.ALREADY_USED;
        }
        return RepoStatus.EXIST;
    }

    public void removeCity(String city){
        city = city.trim().toLowerCase();
        cityRegistry.computeIfAbsent(city.toLowerCase().charAt(0), k -> new ArrayList<>()).add(city);
        remainCities.get(city.toLowerCase().charAt(0)).remove(city);
    }

    public char lastCharFinder(String city){
        city = city.toLowerCase();
        char lastChar = city.charAt(city.length() - 1);
        if(lastChar == 'ь' || lastChar == 'и' || lastChar == 'ї'){
            lastChar = city.charAt(city.length() - 2);
        }
        if(lastChar == 'ґ'){
            lastChar = 'г';
        }
        return lastChar;
    }

    public Map<Character, List<String>> getCityRegistry() {
        return cityRegistry;
    }

    public Map<Character, List<String>> getRemainCities() {
        return remainCities;
    }
}
