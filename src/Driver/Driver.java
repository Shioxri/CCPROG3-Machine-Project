package Driver;/*
    By: Gerard Vito J. Belardo - 12205982
        Karl Andrei L. Ordinario - 12209732
        S18A | Aug. 3, 2023

        To run and compile:
        javac Model\Initializer.java View\MainMenuGUI.java Controller\MainMenuController.java Driver\Driver.java
        java Driver\Driver.java

 */
import Controller.MainMenuController;
import View.MainMenuGUI;

public class Driver {

    public static void main(String[] args) {
        // Create and show the main menu GUI
        MainMenuGUI mainMenuGUI = new MainMenuGUI();
        mainMenuGUI.getFrame().setVisible(true);
        MainMenuController mainMenuController = new MainMenuController(mainMenuGUI);
    }
}
