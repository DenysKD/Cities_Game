package org.example.bot;

import org.example.game.Game;
import org.example.game_exceptions.BotLoseGameException;
import org.example.game_exceptions.UserLoseGameException;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameBot {

    private final Game game;
    private final Random random;

    public GameBot(Game game){
        this.game = game;
        this.random = new Random();
    }

    public String botMove(String city) throws BotLoseGameException {
        city = city.toLowerCase();

        char lastChar = game.lastCharFinder(city);

        List<String> citiesList = game.getRemainCities().get(lastChar);
        if(citiesList == null || citiesList.isEmpty()){
            throw new BotLoseGameException("Список міст пустий!");
        }
        String chosenCity;
        if(citiesList.size() > 1){
            chosenCity = citiesList.get(random.nextInt(citiesList.size()));
        } else {
            chosenCity = citiesList.getFirst();
        }
        game.removeCity(chosenCity);

        return chosenCity;
    }

    public void winCheck(String city) throws UserLoseGameException {
        char lastChar = game.lastCharFinder(city);
        Map<Character, List<String>> remainCities = game.getRemainCities();
        if (!remainCities.containsKey(lastChar) || remainCities.get(lastChar).isEmpty()) {
            throw new UserLoseGameException("Гравець програв!");
        }
    }


}
