package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RegularBuy {
    public static void main(String[] args) {
        // Declarations
        JFrame frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JButton Item1Button = new JButton();
        JButton Item2Button = new JButton();
        JButton Item3Button = new JButton();
        JButton Item4Button = new JButton();
        JButton Item5Button = new JButton();
        JButton Item6Button = new JButton();
        JButton Item7Button = new JButton();
        JButton Item8Button = new JButton();
        JButton Item9Button = new JButton();


        String musicFilePath = "music.wav"; // Make sure the music.wav file is in the same directory as the source file
        playBackgroundMusic(musicFilePath);

        // Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("trueregvm.png");
        ImageIcon sampleIcon = new ImageIcon("Maintenance.png");
        ImageIcon sampleBG = new ImageIcon("VM.gif");
        ImageIcon cola = new ImageIcon("cola.png");
        Border borderLine = BorderFactory.createLineBorder(Color.white, 2);


        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panels
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Buttons
        Item1Button.setBounds(25, 75, 150, 50);
        Item1Button.setHorizontalAlignment(JButton.CENTER);
        Item1Button.setIcon(cola);
        Item1Button.setText("Item 1");
        Item1Button.setHorizontalTextPosition(JButton.CENTER);
        Item1Button.addActionListener(e -> System.out.println("show regular VM"));

        Item2Button.setBounds(200, 75, 150, 50);
        Item2Button.setHorizontalAlignment(JButton.CENTER);
        Item2Button.setText("Item 2");
        Item2Button.setHorizontalTextPosition(JButton.CENTER);
        Item2Button.setIcon(sampleIcon);
        Item2Button.addActionListener(e -> System.out.println("show special VM"));

        Item3Button.setBounds(375, 75, 150, 50);
        Item3Button.setHorizontalAlignment(JButton.CENTER);
        Item3Button.setText("Item 3");
        Item3Button.addActionListener(e -> System.exit(0));

        Item4Button.setBounds(25, 175, 150, 50);
        Item4Button.setHorizontalAlignment(JButton.CENTER);
        Item4Button.setText("Item 4");

        Item5Button.setBounds(200, 175, 150, 50);
        Item5Button.setHorizontalAlignment(JButton.CENTER);
        Item5Button.setText("Item 5");

        Item6Button.setBounds(375, 175, 150, 50);
        Item6Button.setHorizontalAlignment(JButton.CENTER);
        Item6Button.setText("Item 6");

        Item7Button.setBounds(25, 275, 150, 50);
        Item7Button.setHorizontalAlignment(JButton.CENTER);
        Item7Button.setText("Item 7");

        Item8Button.setBounds(200, 275, 150, 50);
        Item8Button.setHorizontalAlignment(JButton.CENTER);
        Item8Button.setText("Item 8");

        Item9Button.setBounds(375, 275, 150, 50);
        Item9Button.setHorizontalAlignment(JButton.CENTER);
        Item9Button.setText("Not Available");

        // Label

        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));

        // Panels
        titlePanel.setBackground(new Color(25, 25, 112, 123));
        titlePanel.setBounds(0, 0, 550, 150);
        titlePanel.setOpaque(true);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(borderLine);

        selectionPanel.setBackground(new Color(25, 25, 112, 123));
        selectionPanel.setBounds(0, 150, 550, 480);
        selectionPanel.setLayout(null);
        selectionPanel.setOpaque(true);
        selectionPanel.add(Item1Button);
        selectionPanel.add(Item2Button);
        selectionPanel.add(Item3Button);
        selectionPanel.add(Item4Button);
        selectionPanel.add(Item5Button);
        selectionPanel.add(Item6Button);
        selectionPanel.add(Item7Button);
        selectionPanel.add(Item8Button);
        selectionPanel.add(Item9Button);
        selectionPanel.setBorder(borderLine);

        lowerPanel.setBackground(new Color(25, 25, 112, 123));
        lowerPanel.setBounds(0,630,550,130);
        lowerPanel.setLayout(null);
        lowerPanel.setOpaque(true);
        lowerPanel.setBorder(borderLine);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(550,0,200,751);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);

        // Layered Pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(750, 750));
        layeredPane.add(bgpanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(1));
        layeredPane.add(selectionPanel, Integer.valueOf(1));
        layeredPane.add(lowerPanel, Integer.valueOf(1));
        layeredPane.add(rightPanel, Integer.valueOf(1));

        // Frame
        frame.setTitle("Vending Machine");
        frame.setIconImage(fruitIcon.getImage());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setContentPane(layeredPane);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void playBackgroundMusic(String musicFilePath) {
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
}
