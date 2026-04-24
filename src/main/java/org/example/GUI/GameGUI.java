package org.example.GUI;

import org.example.Bot.GameBot;
import org.example.GameExceptions.BotLoseGameException;
import org.example.GameExceptions.CityDoesNotExistException;
import org.example.GameExceptions.DejaVuException;
import org.example.GameExceptions.UserLoseGameException;
import org.example.User.User;

import javax.swing.*;
import java.awt.*;

public class GameGUI extends JFrame {
    private User user;
    private GameBot bot;
    private String lastCity = null;

    public GameGUI() {
        user = new User();
        bot = new GameBot();

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
            } catch (CityDoesNotExistException ex) {
                showWarningDialog("Невідоме місто", ex.getMessage());
                cityField.setText("");
                return;
            } catch (DejaVuException ex) {
                showWarningDialog("Місто вже було", ex.getMessage());
                cityField.setText("");
                return;
            } catch (UserLoseGameException ex) {
                showUserLoseDialog(ex.getMessage());
                return;
            }

            lastCity = userCity;
            cityField.setText("");

            try {
                String botCity = bot.botMove(lastCity);
                lastCity = botCity;
                computerLabel.setText("Комп'ютер: " + botCity);
                statusLabel.setText("Місто на літеру: " +
                        Character.toUpperCase(botCity.charAt(botCity.length() - 1)));
            } catch (BotLoseGameException ex) {
                showVictoryDialog(user.getAnswersCount());
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

    private void showUserLoseDialog(String cause) {
        JDialog dialog = createDialog("Поразка");

        JLabel messageLabel = new JLabel(
                "<html><center>Ви програли по причині - \"" + cause + "\"<br>Ваш рахунок = 0<br>Просто через те, що автору так схотілось :)</center></html>",
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