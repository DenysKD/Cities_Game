package org.example.GUI;

import javax.swing.*;
import java.awt.*;

public class StartGameGUI extends JFrame {

    public StartGameGUI(){
        super("Вітаємо!");

        this.setSize(500,120);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Вітаємо вас у грі дитинства і всіх розумників!");
        label.setFont(new Font("Arial", Font.BOLD, 15));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton okButton = new JButton("OK");
        okButton.setHorizontalAlignment(SwingConstants.HORIZONTAL);

        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(okButton);

        panel.add(buttonPanel, BorderLayout.EAST);

        this.add(panel);

        okButton.addActionListener(e -> {
            new GameGUI();
            this.dispose();
        });

        this.setVisible(true);
    }
}