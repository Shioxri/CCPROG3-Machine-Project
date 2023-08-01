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
    JFrame frame;
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
    JLabel userBalanceLabel;
    JComboBox<Integer> denominations;
    JLabel systemMessage;
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
        JLabel infoLabel = new JLabel();

        regularItems = new JComboBox<>();
        denominations = new JComboBox<>(new Integer[]{1, 5, 10, 20, 50, 100});
        denominations.setBounds(10,110,120,25);


        //TODO: need method that limits page number
        AtomicInteger pageNumber = new AtomicInteger();
        //TODO: need method to reflect user balance



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
        instructionsLabel.setText("Instructions: ");
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
        infoLabel.setBounds(75,110,400,200);
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
        regularItems.setBounds(75, 75, 400, 30);
        regularItems.setFocusable(false);
        for (String i : new String[]{"Slot 1", "Slot 2", "Slot 3"}) {
            regularItems.addItem(i);
        }



        //TODO: need method to minus the slot and change of the machine
        buyButton.setBounds(75, 320, 195, 50);
        buyButton.setText("Buy");
        buyButton.setHorizontalAlignment(JButton.CENTER);


        cancelButton.setBounds(275, 320, 195, 50);
        cancelButton.setText("Cancel");
        cancelButton.setHorizontalAlignment(JButton.CENTER);


        // Buttons

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



        //TODO: need method to switch GUI to RegularVMMenu
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

    public void updateBalanceText(int userBalance){
        this.getUserBalanceLabel().setText("$"+userBalance);
        this.cashBalance.set(userBalance);
        systemMessage.setText("You Have Added $" + denominations.getSelectedItem());
    }

    public JComboBox<Integer> getDenominations() {
        return denominations;
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

    public JLabel getUserBalanceLabel() {
        return userBalanceLabel;
    }


}
