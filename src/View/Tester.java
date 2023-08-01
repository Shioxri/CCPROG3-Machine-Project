package View;

import Controller.MainMenuController;

public class Tester {
    public static void main(String[] args) {
        // Create and show the main menu GUI
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.getFrame().setVisible(true);
        MainMenuController mainMenuController = new MainMenuController(mainMenuGUI);

}}
