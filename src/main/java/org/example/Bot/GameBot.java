package org.example.Bot;

import org.example.Game.Game;
import org.example.GameExceptions.BotLoseGameException;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameBot {

    Game game;

    public GameBot(){
        game = Game.getInstance();
    }

    public String botMove(String city) throws BotLoseGameException {
        Map<Character, List<String>> remainCities = game.getRemainCities();
        //System.out.println(remainCities.toString());

        char lastChar = Character.toUpperCase(city.charAt(city.length() - 1));
        System.out.println(lastChar);
        if(!remainCities.containsKey(lastChar) || remainCities.get(lastChar).isEmpty())
            throw new BotLoseGameException("Бот програв гру!");

        List<String> citiesList = remainCities.get(lastChar);
        String chosenCity;
        if(citiesList.size() > 1){
            Random random = new Random();
            chosenCity = citiesList.get(random.nextInt(citiesList.size()));
        } else {
            chosenCity = citiesList.getFirst();
        }
        game.removeCity(chosenCity);

        return chosenCity;
    }


}
