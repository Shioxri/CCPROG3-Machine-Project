package View;


import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/**
 * This class represents the graphical user interface (GUI) for the main menu of the vending machine.
 */
public class MainMenuGUI {

    private JButton regularVMButton;
    private JButton specialVMButton;
    private JButton exitButton;
    private JFrame frame;

    /**
     * Constructor for the MainMenuGUI class.
     * Initializes the GUI by calling the init() method.
     */
    public MainMenuGUI() {
        init();
    }

    /**
     * Initializes the graphical user interface (GUI) components and sets up the main menu.
     */
    private void init()
    {
        regularVMButton = new JButton();
        specialVMButton = new JButton();
        exitButton = new JButton();
        // Declarations
        frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();


        String musicFilePath = "assets/music.wav"; // Make sure the music.wav file is in the same directory as the source file
        playBackgroundMusic(musicFilePath);

        // Images
        ImageIcon fruitIcon = new ImageIcon("assets/pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("assets/logo.png");
        ImageIcon sampleBG = new ImageIcon("assets/VM.gif");

        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panels
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Buttons

        regularVMButton.setBounds(100, 75, 300, 50);
        regularVMButton.setHorizontalAlignment(JButton.CENTER);
        regularVMButton.setText("Create Regular Vending Machine");
        regularVMButton.setVisible(true);



        specialVMButton.setBounds(100, 175, 300, 50);
        specialVMButton.setHorizontalAlignment(JButton.CENTER);
        specialVMButton.setText("Create Special Vending Machine");
        specialVMButton.setVisible(true);



        exitButton.setBounds(100, 275, 300, 50);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Exit");

        // Label
        titleLabel.setText("Vending Machine Main Menu");
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.BOTTOM);
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
        selectionPanel.add(regularVMButton);
        selectionPanel.add(specialVMButton);
        selectionPanel.add(exitButton);

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

    public JFrame getFrame() {
        return frame;
    }

    public JButton getSpecialVMButton() {
        return specialVMButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getRegularVMButton()
    {
        return regularVMButton;
    }



    /**
     * Plays background music for the main menu.
     * The music should be a WAV file and located in the same directory as the source file.
     *
     * @param musicFilePath The file path of the background music (WAV format).
     */
    public void playBackgroundMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = -5.0f; // Adjust this value to set the desired volume level
                gainControl.setValue(volume);
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Play the music on a loop
                clip.start();

            } else {
                System.out.println("Music file not found: " + musicFilePath);
            }
        } catch (Exception e) {
            System.out.println("Error while playing background music: " + e.getMessage());
        }
    }



}