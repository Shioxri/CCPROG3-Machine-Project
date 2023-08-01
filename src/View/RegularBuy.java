package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sound.sampled.*;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class RegularBuy {
    JFrame frame;
    JButton buyButton = new JButton();

    JButton cancelButton = new JButton();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    JLabel userBalanceLabel;
    JComboBox<Integer> denominations;
    JLabel systemMessage;
    JLabel infoLabel = new JLabel();
    JComboBox<String> regularItems;

    public RegularBuy(){
        init();
    }



    public void init() {
        // Declarations
        frame = new JFrame();
        JLabel titleLabel = new JLabel();
        JLabel pageCounter = new JLabel();
        JLabel instructionsLabel = new JLabel();
        systemMessage = new JLabel();
        userBalanceLabel = new JLabel();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();


        regularItems = new JComboBox<>();
        denominations = new JComboBox<>(new Integer[]{1, 5, 10, 20, 50, 100});
        denominations.setBounds(10,110,120,25);

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

        instructionsLabel.setBounds(10, 200, 180, 400);
        instructionsLabel.setText("<html>Instructions: <br/><br/>" +
                "1. Select an Item from the dropdown<br/>" +
                "2. Add money to your balance using the dropdown and add button above<br/>" +
                "3. You may press the cancel button if you have inserted too much<br/>" +
                "4. Press the Buy button to buy your Item<br/>" +
                "Note: You can continue to buy as long as there is a balance</html>");
        instructionsLabel.setBackground(Color.BLACK);
        instructionsLabel.setBorder(borderLine);
        instructionsLabel.setForeground(Color.WHITE);
        instructionsLabel.setOpaque(true);
        instructionsLabel.setHorizontalAlignment(JLabel.CENTER);

        systemMessage.setBounds(10,8,530,110);
        systemMessage.setHorizontalAlignment(JLabel.CENTER);
        systemMessage.setVerticalAlignment(JLabel.CENTER);
        systemMessage.setBackground(new Color(0,0,0));
        systemMessage.setBorder(borderLinegrayl);
        systemMessage.setForeground(Color.WHITE);
        systemMessage.setOpaque(true);
        defaultBalanceText();
        userBalanceLabel.setBounds(10,50,180,50);

        userBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        userBalanceLabel.setForeground(Color.white);
        userBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        userBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        userBalanceLabel.setBorder(borderLine);
        userBalanceLabel.setBackground(Color.black);
        userBalanceLabel.setOpaque(true);


        infoLabel.setBounds(75,110,400,200);
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setBackground(Color.BLACK);
        infoLabel.setBorder(borderLinegrayl);
        infoLabel.setOpaque(true);
        infoLabel.setVerticalAlignment(JLabel.CENTER);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        infoLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));

        // Buttons
        regularItems.setBounds(75, 75, 400, 30);
        regularItems.setFocusable(false);

        buyButton.setBounds(75, 320, 195, 50);
        buyButton.setText("Buy");
        buyButton.setHorizontalAlignment(JButton.CENTER);


        cancelButton.setBounds(275, 320, 195, 50);
        cancelButton.setText("Cancel");
        cancelButton.setHorizontalAlignment(JButton.CENTER);




        addButton.setBounds(140, 110, 50,25);
        addButton.setText("+");



        exitButton.setBounds(10,700,180,25);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");
        /* How to remove action listener: exitButton.removeActionListener(exitButton.getActionListeners()[0]); */



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
        selectionPanel.add(infoLabel);
        selectionPanel.add(regularItems);
        selectionPanel.add(buyButton);
        selectionPanel.add(cancelButton);
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
        rightPanel.add(addButton);
        rightPanel.add(instructionsLabel);
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

    public void updateBalanceText(int userBalance){
        userBalanceLabel.setText("Balance: Php "+userBalance);
    }

    public void defaultBalanceText()
    {
        userBalanceLabel.setText("No Balance Yet");
    }


    public void showAddedMoneyText()
    {
        systemMessage.setText("You Have Added: Php " + denominations.getSelectedItem());
    }
    public void setRegularItems(ArrayList<String> slotTypes) {
        regularItems.setFocusable(false);
        regularItems.addItem("Choose an item...");
        for (String string : slotTypes) {
            regularItems.addItem(string);
        }
    }

    public JComboBox<Integer> getDenominations() {
        return denominations;
    }

    public JComboBox<String> getRegularItems(){return regularItems;}

    public JLabel getInfoLabel(){return infoLabel;}

    public JFrame getFrame() {
        return frame;
    }

    public JButton getExitButton(){
        return exitButton;
    }

    public JButton getAddButton() {
        return addButton;
    }


    public JButton getCancelButton(){return cancelButton;}

    public JButton getBuyButton() {return buyButton;}

    public void setTextAfterBuy(int errorType)
    {
        switch (errorType) {
            case 1 -> systemMessage.setText("Please select an item at the dropdown menu");
            case 2 -> systemMessage.setText("Chosen item is not available due to being out of stock.");
            case 3 -> systemMessage.setText("Chosen item is not available due to insufficient balance.");
            case 4 -> systemMessage.setText("Insufficient change in the machine. Transaction canceled.");
            default -> systemMessage.setText("Unknown Error");

        }
    }

    public JLabel getSystemMessage() {
        return systemMessage;
    }

}
