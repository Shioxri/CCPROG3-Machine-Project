package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sound.sampled.*;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RegularBuy {
    JFrame frame = new JFrame();
    JButton Item1Button = new JButton();
    JButton Item2Button = new JButton();
    JButton Item3Button = new JButton();
    JButton Item4Button = new JButton();
    JButton Item5Button = new JButton();
    JButton Item6Button = new JButton();
    JButton Item7Button = new JButton();
    JButton Item8Button = new JButton();
    JButton Item9Button = new JButton();
    JButton backButton = new JButton();
    JButton nextButton = new JButton();
    JButton buyButton = new JButton();
    JButton cancelButton = new JButton();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    AtomicInteger cashBalance = new AtomicInteger();



    public RegularBuy(){
        init();
    }
    public void init() {
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

        //TODO: need method that limits page number
        AtomicInteger pageNumber = new AtomicInteger();
        //TODO: need method to reflect user balance




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

        //TODO: need method that limits the page number and changes the name of the items per page
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
        userBalanceLabel.setText("$" + cashBalance);
        userBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        userBalanceLabel.setForeground(Color.white);
        userBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        userBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        userBalanceLabel.setBorder(borderLine);
        userBalanceLabel.setBackground(Color.black);
        userBalanceLabel.setOpaque(true);

        //TODO: need method to display item information(may be seperated into variables to be easier like userBalance)
        infoLabel.setBounds(10,200,180,200);
        infoLabel.setText("<html>Price: <br/>Calories: <br/> Stock: </html>");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setBorder(borderLinegrayl);
        infoLabel.setOpaque(true);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));

        // Buttons
        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item1Button.setBounds(25, 75, 150, 50);
        Item1Button.setHorizontalAlignment(JButton.CENTER);
        Item1Button.setIcon(cola);
        Item1Button.setText("Item 1");
        Item1Button.setHorizontalTextPosition(JButton.CENTER);
        Item1Button.addActionListener(e -> infoLabel.setText("<html>Price: itemprice<br/>Calories: itemcalories kCal " +
                "<br/> Stock: itemstock</html>"));

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item2Button.setBounds(200, 75, 150, 50);
        Item2Button.setHorizontalAlignment(JButton.CENTER);
        Item2Button.setText("Item 2");
        Item2Button.setHorizontalTextPosition(JButton.CENTER);
        Item2Button.setIcon(sampleIcon);
        Item2Button.addActionListener(e -> System.out.println("show special VM"));

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item3Button.setBounds(375, 75, 150, 50);
        Item3Button.setHorizontalAlignment(JButton.CENTER);
        Item3Button.setText("Item 3");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item4Button.setBounds(25, 175, 150, 50);
        Item4Button.setHorizontalAlignment(JButton.CENTER);
        Item4Button.setText("Item 4");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item5Button.setBounds(200, 175, 150, 50);
        Item5Button.setHorizontalAlignment(JButton.CENTER);
        Item5Button.setText("Item 5");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item6Button.setBounds(375, 175, 150, 50);
        Item6Button.setHorizontalAlignment(JButton.CENTER);
        Item6Button.setText("Item 6");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item7Button.setBounds(25, 275, 150, 50);
        Item7Button.setHorizontalAlignment(JButton.CENTER);
        Item7Button.setText("Item 7");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item8Button.setBounds(200, 275, 150, 50);
        Item8Button.setHorizontalAlignment(JButton.CENTER);
        Item8Button.setText("Item 8");

        //TODO: need method to show infoLabel the information of this item when clicked(use the actionlistener)
        Item9Button.setBounds(375, 275, 150, 50);
        Item9Button.setHorizontalAlignment(JButton.CENTER);
        Item9Button.setText("Not Available");

        backButton.setBounds(125, 400, 100, 50);
        backButton.setHorizontalAlignment(JButton.CENTER);
        backButton.setText("<--");
        backButton.addActionListener(e -> {
            if(pageNumber.get()>1) {
                pageNumber.set(RegularBuy.magicDecrement(pageCounter, pageNumber.get()));
            }
        });

        nextButton.setBounds(325, 400, 100, 50);
        nextButton.setHorizontalAlignment(JButton.CENTER);
        nextButton.setText("-->");
        nextButton.addActionListener(e -> pageNumber.set(RegularBuy.magicIncrement(pageCounter, pageNumber.get())));

        //TODO: need method to minus the slot and change of the machine
        buyButton.setBounds(10, 410, 80, 50);
        buyButton.setText("Buy");
        backButton.setHorizontalAlignment(JButton.CENTER);


        cancelButton.setBounds(110, 410, 80, 50);
        cancelButton.setText("Cancel");
        cancelButton.setHorizontalAlignment(JButton.CENTER);

        //TODO: need method to connect this to userbalance or make "cash" automatically update the balance in the back end
        addButton.setBounds(140, 110, 50,25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);
            addButton.addActionListener(e -> {
                cashBalance.set(magicAdd(userBalanceLabel, cashBalance.get(), (Integer) denominations.getSelectedItem()));
                systemMessage.setText("You Have Added $" + denominations.getSelectedItem());
            });


        //TODO: need method to switch GUI to RegularVMMenu
        exitButton.setBounds(10,700,180,25);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");
        exitButton.addActionListener(e -> System.exit(0));
        /* How to remove action listener: exitButton.removeActionListener(exitButton.getActionListeners()[0]); */

        denominations.setBounds(10,110,120,25);
        for (int i : new int[]{1, 5, 10, 20, 50, 100}) {
            denominations.addItem(i);
        }


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
        selectionPanel.add(backButton);
        selectionPanel.add(nextButton);
        selectionPanel.add(pageCounter);
        selectionPanel.setBorder(borderLine);

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

    public static int magicIncrement(JLabel pageCounter, int pageNumber){
        pageNumber++;
        pageCounter.setText(String.valueOf(pageNumber));
        return pageNumber;
    }

    public static int magicDecrement(JLabel pageCounter, int pageNumber){
        pageNumber--;
        pageCounter.setText(String.valueOf(pageNumber));
        return pageNumber;
    }

    //TODO: either update the userbalance in the backend itself or make cash connected to userbalance
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

    public void setCashBalance(int cash)
    {
        this.cashBalance.set(cash);
    }


    public JButton getItem1Button() {
        return Item1Button;
    }

    public JButton getItem2Button() {
        return Item2Button;
    }

    public JButton getItem3Button() {
        return Item3Button;
    }

    public JButton getItem4Button() {
        return Item4Button;
    }

    public JButton getItem5Button() {
        return Item5Button;
    }

    public JButton getItem6Button() {
        return Item6Button;
    }

    public JButton getItem7Button() {
        return Item7Button;
    }

    public JButton getItem8Button() {
        return Item8Button;
    }

    public JButton getItem9Button() {
        return Item9Button;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public JButton getBuyButton() {
        return buyButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getAddButton() {
        return addButton;
    }
}
