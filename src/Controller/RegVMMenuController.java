package Controller;

import Model.VendingMachine;
import View.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The RegVMMenuController class handles the actions and navigation for the regular vending machine menu.
 */
public class RegVMMenuController implements ActionListener {
    private RegularVMMenu regularVMMenu;

    /**
     * Constructs the RegVMMenuController with the specified RegularVMMenu, MainMenuController, and VendingMachine.
     *
     * @param regularVMMenu       The RegularVMMenu to associate with the controller.
     * @param mainMenuController  The MainMenuController to handle main menu navigation.
     * @param vendingMachine      The VendingMachine representing the regular vending machine.
     */
    public RegVMMenuController(RegularVMMenu regularVMMenu, MainMenuController mainMenuController, VendingMachine vendingMachine) {
        this.regularVMMenu = regularVMMenu;

        // ActionListener for the "Use Vending Machine" button
        regularVMMenu.getUseVMButton().addActionListener(e -> {
            playButtonClickSound();
            regularVMMenu.getFrame().setVisible(false);
            RegularBuy regularBuyMenu = new RegularBuy();
            RegularBuyController regularBuyController =
                    new RegularBuyController(regularBuyMenu, this, vendingMachine);
            regularBuyMenu.getFrame().setVisible(true);
        });

        // ActionListener for the "Maintenance" button
        regularVMMenu.getMaintenanceButton().addActionListener(e -> {
            playButtonClickSound();
            regularVMMenu.getFrame().setVisible(false);
            RegularMaintenance regularMaintenanceMenu = new RegularMaintenance();
            RegMaintenanceController regMaintenanceController =
                    new RegMaintenanceController(regularMaintenanceMenu, this, vendingMachine);
            regularMaintenanceMenu.getFrame().setVisible(true);
        });


        // ActionListener for the "Back" button
        regularVMMenu.getBackButton().addActionListener(e -> {
            playButtonClickSound();
            regularVMMenu.getFrame().setVisible(false);
            mainMenuController.getMainMenuGUI().getFrame().setVisible(true);
        });
    }

    /**
     * Returns the RegularVMMenu associated with this controller.
     *
     * @return The RegularVMMenu.
     */
    public RegularVMMenu getRegularVMMenu() {
        return regularVMMenu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No action required for the regular vending machine menu
    }

    /**

     Plays a button click sound when the button is clicked.
     The sound is played from the "assets/sfx.wav" file.
     If an error occurs while playing the sound, the exception is printed to the standard error stream.

     */
    private void playButtonClickSound() {
        try {
            File soundFile = new File("assets/sfx.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
