package org.example.user;

import org.example.game.Game;
import org.example.game.RepoStatus;
import org.example.game_exceptions.*;

import java.util.List;
import java.util.Map;

public class User {
    private final Game game;
    private int answersCount = 0;

    public User(Game game){
        this.game = game;
    }

    public void userMove(String botAnswer, String city) throws CityDoesNotExistException, DejaVuException,
            UserLoseGameException, WrongCharacterCityException {

        if(city.equalsIgnoreCase("здаюсь")) {
            throw new UserLoseGameException("Нажаль ви здались :(");
        }
        city = city.toLowerCase();
        if (botAnswer != null) {
            botAnswer = botAnswer.toLowerCase();

            char botsLastChar = game.lastCharFinder(botAnswer);
            if(!city.startsWith(String.valueOf(botsLastChar))) {
                throw new WrongCharacterCityException("Ваше місто починається на неправильну букву!");
            }
            //Map<Character, List<String>> remainCities = game.getRemainCities();

            /*if (remainCities.get(botsLastChar).isEmpty()) {
                throw new UserLoseGameException("Гравець програв!");
            }*/
        }

        RepoStatus checkResult = game.checkAnswer(city);
        if(checkResult == RepoStatus.NotExist) {
            throw new CityDoesNotExistException("Я не знаю такого міста!");
        }
        if(checkResult == RepoStatus.AlreadyUsed) {
            throw new DejaVuException("Це ж було вже!");
        }

        game.removeCity(city);
        answersCount++;
    }

    public void winCheck(String city) throws BotLoseGameException {
        //city = city.toLowerCase();
        Map<Character, List<String>> remainCities = game.getRemainCities();
        char lastChar = game.lastCharFinder(city);
        if(!remainCities.containsKey(lastChar) || remainCities.get(lastChar).isEmpty()) {
            throw new BotLoseGameException("Бот програв гру!");
        }
    }

    public int getAnswersCount() {
        return answersCount;
    }
}
