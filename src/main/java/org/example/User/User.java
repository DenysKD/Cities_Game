package org.example.User;

import org.example.Game.Game;
import org.example.GameExceptions.BotLoseGameException;
import org.example.GameExceptions.CityDoesNotExistException;
import org.example.GameExceptions.DejaVuException;
import org.example.GameExceptions.UserLoseGameException;

import java.util.List;
import java.util.Map;

public class User {
    private Game game;
    private int answersCount = 0;

    public User(){
        game = Game.getInstance();
    }

    public void userMove(String botAnswer, String city) throws CityDoesNotExistException, DejaVuException, UserLoseGameException{
        if(city.equals("Здаюсь") || city.equals("здаюсь")) throw new UserLoseGameException("Нажаль ви здались :(");
        if (botAnswer != null) {
            Map<Character, List<String>> remainCities = game.getRemainCities();
            char botsLasrChar = Character.toUpperCase(botAnswer.charAt(botAnswer.length() - 1));
            if (remainCities.get(botsLasrChar).isEmpty()) throw new UserLoseGameException("Гравець програв!");
        }

        int checkResult = game.checkAnswer(city);
        if(checkResult < 0) throw new CityDoesNotExistException("Я не знаю такого міста!");
        if(checkResult == 0) throw new DejaVuException("Це ж було вже!");

        game.removeCity(city);
        answersCount++;
    }

    public int getAnswersCount() {
        return answersCount;
    }
}
