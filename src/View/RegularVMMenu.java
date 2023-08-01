package View;

import Controller.MainMenuController;
import Controller.RegVMMenuController;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RegularVMMenu {
    JFrame frame;
    JButton UseVMButton;
    JButton maintenanceButton;
    JButton backButton;

    public RegularVMMenu() {
        init();
    }
    private void init()
    {
        UseVMButton = new JButton();
        maintenanceButton = new JButton();
        backButton = new JButton();
        // Declarations
        frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();


        String musicFilePath = "music.wav"; // Make sure the music.wav file is in the same directory as the source file
        playBackgroundMusic(musicFilePath);

        // Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("trueregvm.png");
        ImageIcon sampleIcon = new ImageIcon("Maintenance.png");
        ImageIcon sampleBG = new ImageIcon("VM.gif");
        ImageIcon cola = new ImageIcon("cola.png");

        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panels
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Buttons
        //TODO: need method to switch GUI to RegularBuy
        UseVMButton.setBounds(100, 75, 300, 50);
        UseVMButton.setHorizontalAlignment(JButton.CENTER);
        UseVMButton.setIcon(cola);
        UseVMButton.setText("Use Vending Machine");
        UseVMButton.setHorizontalTextPosition(JButton.CENTER);

        //TODO: need method to switch GUI to RegularMaintenance
        maintenanceButton.setBounds(100, 175, 300, 50);
        maintenanceButton.setHorizontalAlignment(JButton.CENTER);
        maintenanceButton.setText("Enter Maintenance Mode");
        maintenanceButton.setHorizontalTextPosition(JButton.CENTER);
        maintenanceButton.setIcon(sampleIcon);

        //TODO: need method to switch GUI to MainMenuGUI
        backButton.setBounds(100, 275, 300, 50);
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.setText("Back");

        // Label
        titleLabel.setText("\u200E");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));

        // Panels
        titlePanel.setBackground(new Color(25, 25, 112, 123));
        titlePanel.setBounds(125, 100, 500, 150);
        titlePanel.setOpaque(true);
        titlePanel.add(titleLabel);

        selectionPanel.setBackground(new Color(25, 25, 112, 123));
        selectionPanel.setBounds(125, 250, 500, 400);
        selectionPanel.setLayout(null);
        selectionPanel.setOpaque(true);
        selectionPanel.add(UseVMButton);
        selectionPanel.add(maintenanceButton);
        selectionPanel.add(backButton);

        // Layered Pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(750, 750));
        layeredPane.add(bgpanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(1));
        layeredPane.add(selectionPanel, Integer.valueOf(1));

        // Frame
        frame.setTitle("Vending Machine");
        frame.setIconImage(fruitIcon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(layeredPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void playBackgroundMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Play the music on a loop
                clip.start();
            } else {
                System.out.println("Music file not found: " + musicFilePath);
            }
        } catch (Exception e) {
            System.out.println("Error while playing background music: " + e.getMessage());
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getUseVMButton() {
        return UseVMButton;
    }

    public JButton getMaintenanceButton() {
        return maintenanceButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}