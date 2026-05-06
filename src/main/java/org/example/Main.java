package org.example;

import org.example.cityes_reader.CitiesReader;
import org.example.gui.StartGameGUI;

public class Main {
    static void main() {

        StartGameGUI startWindow = new StartGameGUI();
        CitiesReader cr = new CitiesReader();
        System.out.println(cr.getCitiesRepo().toString());

    }
}
