package org.example.bot;

import org.example.game.Game;
import org.example.game_exceptions.UserLoseGameException;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameBot {

    Game game;

    public GameBot(Game game){
        this.game = game;
    }

    public String botMove(String city) /*throws BotLoseGameException*/ {
        city = city.toLowerCase();
        //Map<Character, List<String>> remainCities = game.getRemainCities();

        char lastChar = game.lastCharFinder(city);
        //System.out.println(lastChar);
        /*if(!remainCities.containsKey(lastChar) || remainCities.get(lastChar).isEmpty()) {
            throw new BotLoseGameException("Бот програв гру!");
        }*/

        List<String> citiesList = game.getRemainCities().get(lastChar);
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

    public void winCheck(String city) throws UserLoseGameException {
        char lastChar = game.lastCharFinder(city);
        Map<Character, List<String>> remainCities = game.getRemainCities();
        if (remainCities.get(lastChar).isEmpty()) {
            throw new UserLoseGameException("Гравець програв!");
        }
    }


}
