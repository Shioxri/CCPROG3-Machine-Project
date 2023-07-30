package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sound.sampled.*;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RegularMaintenance {
    public static void main(String[] args) {
        // Declarations
        JFrame frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JLabel pageCounter = new JLabel();
        JLabel systemMessage = new JLabel();
        JLabel userBalanceLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel newItemPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel infoLabel = new JLabel();
        JButton buyButton = new JButton();
        JButton cancelButton = new JButton();
        JButton addButton = new JButton();
        JButton exitButton = new JButton();
        JButton changePriceButton = new JButton();
        JButton changeCaloriesButton = new JButton();
        JButton reStock = new JButton();
        JButton addItem = new JButton();
        JComboBox<Integer> denominations = new JComboBox<>();
        JComboBox<String> slots = new JComboBox<>();
        JComboBox<Integer> addStock = new JComboBox<>();
        JTextField changePrice = new JTextField();
        JTextField changeCalories = new JTextField();
        JTextField setName = new JTextField();
        JTextField setPrice = new JTextField();
        JTextField setCalories = new JTextField();



        AtomicInteger pageNumber = new AtomicInteger(1);
        AtomicInteger cash = new AtomicInteger(9999);


        String musicFilePath = "music.wav"; // Make sure the music.wav file is in the same directory as the source file
        playBackgroundMusic(musicFilePath);

        // Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("trueregvm.png");
        ImageIcon sampleIcon = new ImageIcon("Maintenance.png");
        ImageIcon sampleBG = new ImageIcon("VM.gif");
        ImageIcon cola = new ImageIcon("cola.png");
        Border borderLine = BorderFactory.createLineBorder(Color.white, 2);
        Border borderLinegrayl = BorderFactory.createLineBorder(Color.lightGray, 2);


        // Components for Background
        JLabel backgroundLabel = new JLabel(sampleBG);
        backgroundLabel.setBounds(0, 0, 750, 750);

        // Panels
        JPanel bgpanel = new JPanel(null);
        bgpanel.setBounds(0, 0, 10000, 10000);
        bgpanel.add(backgroundLabel);

        // Label

        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalTextPosition(JLabel.TOP);
        titleLabel.setIcon(titleIcon);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 30));

        pageCounter.setBounds(225, 400, 100, 50);
        pageCounter.setHorizontalAlignment(JLabel.CENTER);
        pageCounter.setText(String.valueOf(pageNumber));
        pageCounter.setBorder(borderLine);
        pageCounter.setForeground(Color.WHITE);

        systemMessage.setBounds(10,8,530,110);
        systemMessage.setHorizontalAlignment(JLabel.CENTER);
        systemMessage.setVerticalAlignment(JLabel.CENTER);
        systemMessage.setBackground(new Color(0,0,0));
        systemMessage.setBorder(borderLinegrayl);
        systemMessage.setText("<html><p align=\"center\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nullam ullamcorper ullamcorper risus eget elementum. Morbi ac quam in ante viverra placerat. Cras non justo purus. " +
                "In eleifend nibh lectus, a elementum purus gravida id. Praesent quis porta arcu. Integer finibus nisi id eros iaculis gravida. Cras tempor orci sit amet pharetra feugiat. " +
                "Sed at sollicitudin nisl.</p></html>"); // 368 characters max
        systemMessage.setForeground(Color.WHITE);
        systemMessage.setOpaque(true);

        userBalanceLabel.setBounds(10,50,180,50);
        userBalanceLabel.setText("$" + cash);
        userBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        userBalanceLabel.setForeground(Color.white);
        userBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        userBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        userBalanceLabel.setBorder(borderLine);
        userBalanceLabel.setBackground(Color.black);
        userBalanceLabel.setOpaque(true);

        infoLabel.setBounds(10,200,180,200);
        infoLabel.setText("<html>Price: $999<br/>Calories: 999 kCal <br/> Stock: 10</html>");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setBorder(borderLinegrayl);
        infoLabel.setOpaque(true);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));

        // Buttons
        changePriceButton.setBounds(265, 90, 70, 30);
        changePriceButton.setHorizontalAlignment(JButton.CENTER);
        changePriceButton.setText("<html>Set<br/>Price</html>");
        changePriceButton.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changePriceButton.setHorizontalTextPosition(JButton.CENTER);
        changePriceButton.addActionListener(e -> {
            System.out.println("New Money: " + Integer.parseInt(changePrice.getText()));
            changePrice.setText("");
        });

        changeCaloriesButton.setBounds(265, 130, 70, 30);
        changeCaloriesButton.setHorizontalAlignment(JButton.CENTER);
        changeCaloriesButton.setText("<html>Set<br/>Calories</html>");
        changeCaloriesButton.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changeCaloriesButton.setHorizontalTextPosition(JButton.CENTER);
        changeCaloriesButton.addActionListener(e -> {
            System.out.println("New Calories: " + Integer.parseInt(changeCalories.getText()));
            changeCalories.setText("");
        });

        reStock.setBounds(265, 170, 70, 30);
        reStock.setHorizontalAlignment(JButton.CENTER);
        reStock.setText("Restock");
        reStock.setFont(new Font("Century Gothic", Font.BOLD, 9));
        reStock.setHorizontalTextPosition(JButton.CENTER);
        reStock.addActionListener(e -> {
            System.out.println("New Stock: " + addStock.getSelectedItem());
        });


        addButton.setBounds(140, 110, 50,25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);
        addButton.addActionListener(e -> {
            cash.set(magicAdd(userBalanceLabel, cash.get(), (Integer) denominations.getSelectedItem()));
            systemMessage.setText("You Have Added $" + denominations.getSelectedItem());
        });


        exitButton.setBounds(10,700,180,25);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");
        exitButton.addActionListener(e -> System.exit(0));
        /* How to remove action listener: exitButton.removeActionListener(exitButton.getActionListeners()[0]); */

        addItem.setBounds(75,190,225,30);
        addItem.setText("Add Item");


        // Dropdows
        denominations.setBounds(10,110,120,25);
        denominations.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (int i : new int[]{1, 5, 10, 20, 50, 100}) {
            denominations.addItem(i);
        }

        slots.setBounds(112,25,150,40);
        slots.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (String i : new String[]{"Slot 1", " Slot 2", "Slot 3"}) {
            slots.addItem(i);
        }

        addStock.setBounds(35,170,225,30);
        addStock.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (int i : new int[]{1, 5, 10}) {
            addStock.addItem(i);
        }


        // Text Fields
        changePrice.setBounds(35, 90, 225, 30);
        changePrice.setText("Enter New Price");
        changePrice.setHorizontalAlignment(JTextField.CENTER);
        changePrice.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                changePrice.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        changePrice.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                changePrice.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });

        changeCalories.setBounds(35, 130, 225, 30);
        changeCalories.setText("Enter New Calorie Count");
        changeCalories.setHorizontalAlignment(JTextField.CENTER);
        changeCalories.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                changeCalories.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        changeCalories.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                changeCalories.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });

        setName.setBounds(112,25,150,40);
        setName.setText("Enter Product Name");
        setName.setHorizontalAlignment(JTextField.CENTER);
        setName.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setName.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });

        setPrice.setBounds(75, 90, 225, 30);
        setPrice.setText("Set Product Price");
        setPrice.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setPrice.setText("");
            }
            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        setPrice.setHorizontalAlignment(JTextField.CENTER);
        setPrice.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                setPrice.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });

        setCalories.setBounds(75,130,225,30);
        setCalories.setText("Enter Product Calories");
        setCalories.setHorizontalAlignment(JTextField.CENTER);
        setCalories.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                setCalories.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        setCalories.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                setCalories.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });





        // Panels
        titlePanel.setBackground(new Color(25, 25, 112, 123));
        titlePanel.setBounds(0, 0, 750, 150);
        titlePanel.setOpaque(true);
        titlePanel.add(titleLabel);
        titlePanel.setBorder(borderLine);

        selectionPanel.setBackground(new Color(25, 25, 112, 123));
        selectionPanel.setBounds(0, 150, 375, 240);
        selectionPanel.setLayout(null);
        selectionPanel.setOpaque(true);
        selectionPanel.setBorder(borderLine);
        selectionPanel.add(changePriceButton);
        selectionPanel.add(changeCaloriesButton);
        selectionPanel.add(reStock);
        selectionPanel.add(slots);
        selectionPanel.add(changePrice);
        selectionPanel.add(changeCalories);
        selectionPanel.add(addStock);

        newItemPanel.setBackground(new Color(25, 25, 112, 123));
        newItemPanel.setBounds(0, 390, 375, 240);
        newItemPanel.setLayout(null);
        newItemPanel.setOpaque(true);
        newItemPanel.add(setName);
        newItemPanel.add(setCalories);
        newItemPanel.add(setPrice);
        newItemPanel.add(addItem);
        newItemPanel.setBorder(borderLine);

        lowerPanel.setBackground(new Color(25, 25, 112, 250));
        lowerPanel.setBounds(0,630,750,130);
        lowerPanel.setLayout(null);
        lowerPanel.setOpaque(true);
        lowerPanel.setBorder(borderLine);
        lowerPanel.add(systemMessage);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(375,150,375,480);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);
        rightPanel.add(userBalanceLabel);
        rightPanel.add(infoLabel);
        rightPanel.add(buyButton);
        rightPanel.add(cancelButton);
        rightPanel.add(addButton);
        rightPanel.add(denominations);
        rightPanel.add(exitButton);

        // Layered Pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(750, 750));
        layeredPane.add(bgpanel, Integer.valueOf(0));
        layeredPane.add(titlePanel, Integer.valueOf(1));
        layeredPane.add(selectionPanel, Integer.valueOf(1));
        layeredPane.add(newItemPanel, Integer.valueOf(1));
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
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            if (musicFile.exists()) {
                clip.open(audioStream);
                clip.loop(0); // Play the music on a loop
                clip.start();
            } else {
                System.out.println("Music file not found: " + musicFilePath);
            }
        } catch (Exception e) {
            System.out.println("Error while playing background music: " + e.getMessage());
        }
    }

    public static int magicAdd(JLabel userBalance, int cash, int addition){
        cash += addition;
        userBalance.setText("$"+cash);
        return cash;
    }
}
