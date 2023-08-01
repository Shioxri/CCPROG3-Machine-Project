package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sound.sampled.*;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SpecialBuy {
    JFrame frame = new JFrame();
    JButton buyButton = new JButton();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    //TODO: need method to either connect this to userbalance in backend or pass its value here
    AtomicInteger cash = new AtomicInteger();

    public void setCash(AtomicInteger cash){
        this.cash.set(cash.get());
    }

    public SpecialBuy() {
        init();
    }

    private void init() {
        // Declarations
        JLabel titleLabel = new JLabel();
        JLabel pageCounter = new JLabel();
        JLabel systemMessage = new JLabel();
        JLabel userBalanceLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel infoLabel = new JLabel();
        JComboBox<Integer> denominations = new JComboBox<>();
        JComboBox<String> fruit1 = new JComboBox<>();
        JComboBox<String> fruit2 = new JComboBox<>();
        JComboBox<String> waterType = new JComboBox<>();
        JComboBox<String> milkType = new JComboBox<>();
        JComboBox<String> iceType = new JComboBox<>();
        JComboBox<String> toppingsType = new JComboBox<>();



        //TODO: need method that limits page numbers and sets a page by 9 items
        AtomicInteger pageNumber = new AtomicInteger(1);


        String musicFilePath = "music.wav"; // Make sure the music.wav file is in the same directory as the source file
        playBackgroundMusic(musicFilePath);

        // Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("SPECIALVM1.png");
        ImageIcon sampleBG = new ImageIcon("VM.gif");
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

        //TODO: need method that limits page numbers and automatically changes item names based on page number
        pageCounter.setBounds(225, 400, 100, 50);
        pageCounter.setHorizontalAlignment(JLabel.CENTER);
        pageCounter.setText(String.valueOf(pageNumber));
        pageCounter.setBorder(borderLine);
        pageCounter.setForeground(Color.WHITE);

        //TODO: need method that prints error messages or normal notifications
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

        //TODO: need method to put the price and calories of the shake/item here
        infoLabel.setBounds(10,200,180,200);
        infoLabel.setText("<html>Price: $999 " +
                "<br/>Calories: 999 kCal" +
                "<br/>First Fruit: " + fruit1.getSelectedItem() +
                "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                "<br/> Water Type: " + waterType.getSelectedItem() +
                "<br/> Milk Type " + milkType.getSelectedItem() +
                "<br/> Ice Type:" + iceType.getSelectedItem() +
                " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setBorder(borderLinegrayl);
        infoLabel.setOpaque(true);
        infoLabel.setFont(new Font("Century Gothic", Font.BOLD, 13));

        // Buttons

        //TODO: need method that simulates buy(reduce change reduce stocks)
        buyButton.setToolTipText("Buy Item");
        buyButton.setBounds(10, 410, 180, 50);
        buyButton.setText("Buy");
        buyButton.setHorizontalAlignment(JButton.CENTER);

        //TODO: need method to connect this to userbalance in backend or make "cash" connected to the backend
        addButton.setBounds(140, 110, 50,25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);
        addButton.addActionListener(e -> {
            cash.set(magicAdd(userBalanceLabel, cash.get(), (Integer) denominations.getSelectedItem()));
            systemMessage.setText("You Have Added $" + denominations.getSelectedItem());
        });

        //TODO: need method to switch GUI to SpecialVMMenu
        exitButton.setBounds(10,700,180,25);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");
        exitButton.addActionListener(e -> System.exit(0));
        /* How to remove action listener: exitButton.removeActionListener(exitButton.getActionListeners()[0]); */

        // Dropdowns
        denominations.setFocusable(false);
        denominations.setToolTipText("Choose Denomination");
        denominations.setBounds(10,110,120,25);
        for (int i : new int[]{1, 5, 10, 20, 50, 100}) {
            denominations.addItem(i);
        }

        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        fruit1.setFocusable(false);
        fruit1.setToolTipText("First Fruit");
        fruit1.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        fruit1.setBounds(25,75,240,50);
        for (String i : new String[]{"No Fruit","test", "test2"}) {
            fruit1.addItem(i);
        }
        fruit1.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));


        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        fruit2.setFocusable(false);
        fruit2.setToolTipText("Second Fruit");
        fruit2.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        fruit2.setBounds(285,75,240,50);
        for (String i : new String[]{"No Fruit","test", "test2"}) {
            fruit2.addItem(i);
        }
        fruit2.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));


        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        waterType.setFocusable(false);
        waterType.setToolTipText("Water type");
        waterType.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        waterType.setBounds(25,140,500,50);
        for (String i : new String[]{"No Water","test", "test2"}) {
            waterType.addItem(i);
        }
        waterType.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));


        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        milkType.setFocusable(false);
        milkType.setToolTipText("Milk type");
        milkType.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        milkType.setBounds(25,205,500,50);
        for (String i : new String[]{"No Milk","test", "test2"}) {
            milkType.addItem(i);
        }
        milkType.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));


        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        iceType.setFocusable(false);
        iceType.setFocusable(false);
        iceType.setToolTipText("Ice type");
        iceType.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        iceType.setBounds(25,270,500,50);
        for (String i : new String[]{"No Ice","test", "test2"}) {
            iceType.addItem(i);
        }
        iceType.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));


        //TODO: need method to add calories and price to the info when clicked(use action listener)
        //TODO: need method/String[] to input items in the dropdown(for loop)
        toppingsType.setFocusable(false);
        toppingsType.setToolTipText("Toppings");
        toppingsType.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        toppingsType.setBounds(25,335,500,50);
        for (String i : new String[]{"No Toppings","test", "test2"}) {
            toppingsType.addItem(i);
        }
        toppingsType.addActionListener(e ->
                infoLabel.setText("<html>Price: $999 " +
                        "<br/>Calories: 999 kCal" +
                        "<br/>First Fruit: " + fruit1.getSelectedItem() +
                        "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                        "<br/> Water Type: " + waterType.getSelectedItem() +
                        "<br/> Milk Type " + milkType.getSelectedItem() +
                        "<br/> Ice Type:" + iceType.getSelectedItem() +
                        " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>"));

        infoLabel.setText("<html>Price: $999 " +
                "<br/>Calories: 999 kCal" +
                "<br/>First Fruit: " + fruit1.getSelectedItem() +
                "<br/> Second Fruit: " + fruit2.getSelectedItem() +
                "<br/> Water Type: " + waterType.getSelectedItem() +
                "<br/> Milk Type " + milkType.getSelectedItem() +
                "<br/> Ice Type:" + iceType.getSelectedItem() +
                " <br/> Toppings: " +toppingsType.getSelectedItem()+"</html>");

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
        selectionPanel.setBorder(borderLine);
        selectionPanel.add(fruit1);
        selectionPanel.add(fruit2);
        selectionPanel.add(waterType);
        selectionPanel.add(milkType);
        selectionPanel.add(toppingsType);
        selectionPanel.add(iceType);

        lowerPanel.setBackground(new Color(25, 25, 112, 250));
        lowerPanel.setBounds(0,630,550,130);
        lowerPanel.setLayout(null);
        lowerPanel.setOpaque(true);
        lowerPanel.setBorder(borderLine);
        lowerPanel.add(systemMessage);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(550,0,200,751);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);
        rightPanel.add(userBalanceLabel);
        rightPanel.add(infoLabel);
        rightPanel.add(buyButton);
        rightPanel.add(addButton);
        rightPanel.add(denominations);
        rightPanel.add(exitButton);

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

    public JFrame getFrame() {
        return frame;
    }

    public JButton getExitButton(){
        return exitButton;
    }

}
