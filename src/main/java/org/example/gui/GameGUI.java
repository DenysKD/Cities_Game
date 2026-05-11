package org.example.gui;

import org.example.bot.GameBot;
import org.example.game.Game;
import org.example.game_exceptions.*;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private User user;
    private GameBot bot;
    private String lastCity = null;
    public final Game game;

    public GameGUI() {
        game = new Game();
        user = new User(game);
        bot = new GameBot(game);


        super.setTitle("Міста");
        this.setSize(400, 150);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField cityField = new JTextField(10);
        JButton moveButton = new JButton("Зробити хід");
        JLabel statusLabel = new JLabel("Введіть назву міста");
        JLabel computerLabel = new JLabel("Комп'ютер: ");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(cityField);
        mainPanel.add(statusLabel);
        mainPanel.add(moveButton);
        mainPanel.add(computerLabel);

        this.setLayout(new BorderLayout());
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);

        moveButton.addActionListener(e -> {
            String userCity = cityField.getText().trim();

            if (userCity.isEmpty()) {
                statusLabel.setText("Введіть назву міста!");
                return;
            }

            try {
                user.userMove(lastCity, userCity);
                user.winCheck(userCity);
            } catch (CityDoesNotExistException ex) {
                showWarningDialog("Невідоме місто", ex.getMessage());
                cityField.setText("");
                return;
            } catch (DejaVuException ex) {
                showWarningDialog("Місто вже було", ex.getMessage());
                cityField.setText("");
                return;
            } catch (UserLoseGameException ex) {
                showUserLoseDialog(user.getAnswersCount());
                return;
            } catch (WrongCharacterCityException ex) {
                showWarningDialog("Неправильна буква", ex.getMessage());
                cityField.setText("");
                return;
            } catch (BotLoseGameException ex) {
                showVictoryDialog(user.getAnswersCount());
                return;
            }

            lastCity = userCity;
            cityField.setText("");

            try {
                String botCity = bot.botMove(lastCity);
                lastCity = botCity;
                computerLabel.setText("Комп'ютер: " + botCity.substring(0,1).toUpperCase() + botCity.substring(1));
                statusLabel.setText("Місто на літеру: " +
                        Character.toUpperCase(game.lastCharFinder(botCity)));
                bot.winCheck(botCity);
            } catch (UserLoseGameException ex) {
                showUserLoseDialog(user.getAnswersCount());
            }
        });
    }

    private JDialog createDialog(String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout(10, 10));
        return dialog;
    }

    private void showWarningDialog(String title, String message) {
        JDialog dialog = createDialog(title);

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        JButton closeButton = new JButton("Закрити");
        closeButton.addActionListener(e -> dialog.dispose());

        dialog.add(messageLabel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showVictoryDialog(int score) {
        JDialog dialog = createDialog("Перемога!");

        JLabel messageLabel = new JLabel(
                "<html><center>Вітаю з перемогою!<br>Ваш рахунок - " + score + " балів</center></html>",
                SwingConstants.CENTER
        );
        JButton closeButton = new JButton("Закрити");
        closeButton.addActionListener(e -> {
            dialog.dispose();
            this.dispose();
        });

        dialog.add(messageLabel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    private void showUserLoseDialog(int score) {
        JDialog dialog = createDialog("Поразка");

        JLabel messageLabel = new JLabel(
                "<html><center>На жаль, ви програли!<br>Ваш рахунок - " + score + " балів</center></html>",
                SwingConstants.CENTER
        );
        JButton closeButton = new JButton("Закрити");
        closeButton.addActionListener(e -> {
            dialog.dispose();
            this.dispose();
        });

        dialog.add(messageLabel, BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}