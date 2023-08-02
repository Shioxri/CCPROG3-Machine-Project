
import Controller.MainMenuController;
import View.MainMenuGUI;

public class Driver {
    public static void main(String[] args) {
        // Create and show the main menu GUI
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.getFrame().setVisible(true);
        MainMenuController mainMenuController = new MainMenuController(mainMenuGUI);
    }}