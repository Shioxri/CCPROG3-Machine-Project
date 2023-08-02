package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.Border;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/**
 * SpecialMaintenance class represents a special maintenance menu for the vending machine GUI.
 */
public class SpecialMaintenance {
    JFrame frame = new JFrame();
    JLabel systemMessage = new JLabel();
    JLabel machineBalanceLabel = new JLabel();
    JLabel regularSlotLabel = new JLabel();
    JLabel specialSlotLabel = new JLabel();
    JButton buyButton = new JButton();
    JButton cancelButton = new JButton();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    JButton changeRegularPriceButton = new JButton();
    JButton changeSpecialPriceButton = new JButton();
    JButton restockRegular = new JButton();
    JButton restockSpecial = new JButton();
    JButton addItem = new JButton();
    JButton collectMoney = new JButton();
    JButton printSummary = new JButton();
    JButton instructionsButton = new JButton();
    JButton quitButton = new JButton();
    JComboBox<String> regularSlotsDropDown = new JComboBox<>();
    JComboBox<String> specialSlotsDropDown = new JComboBox<>();
    JTextField changePriceRegular = new JTextField();
    JTextField changePriceSpecial = new JTextField();
    JTextField setName = new JTextField();
    JTextField setPrice = new JTextField();
    JTextField setCalories = new JTextField();

    JComboBox<Integer> denominations;

    private int numDenom1=0;
    private int numDenom5=0;
    private int numDenom10=0;
    private int numDenom20=0;
    private int numDenom50=0;
    private int numDenom100=0;

    // This class represents a special maintenance menu for the vending machine GUI.
    public SpecialMaintenance() {
        init();
    }
    // GUI components declarations

    /**
     * Initializes the graphical user interface (GUI) components for the Special Maintenance menu.
     * This method sets up various panels and adds components to them, sets their properties,
     * and arranges them using layout managers. The GUI is composed of background images, labels,
     * dropdowns, buttons, and panels.
     */
    public void init() {
        // Declarations

        JLabel titleLabel = new JLabel();

        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel newItemPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel infoLabel = new JLabel();
        denominations = new JComboBox<>(new Integer[]{1, 5, 10, 20, 50, 100});


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


        systemMessage.setBounds(10, 8, 530, 110);
        systemMessage.setHorizontalAlignment(JLabel.CENTER);
        systemMessage.setVerticalAlignment(JLabel.CENTER);
        systemMessage.setBackground(new Color(0, 0, 0));
        systemMessage.setBorder(borderLinegrayl);
        systemMessage.setText("<html><p align=\"center\">Hello Admin, welcome to the [Special] Maintenance Menu! </p></html>");
        systemMessage.setForeground(Color.WHITE);
        systemMessage.setOpaque(true);


        machineBalanceLabel.setBounds(92, 50, 180, 200);
        machineBalanceLabel.setText("<html>$1 Bill: <br/>$5 Bill: <br/> $10 Bill: <br/>$20 Bill: <br/>$50 Bill: <br/> $100 Bill: <br/> Total:</html>");
        machineBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        machineBalanceLabel.setForeground(Color.white);
        machineBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        machineBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        machineBalanceLabel.setBorder(borderLine);
        machineBalanceLabel.setBackground(Color.black);
        machineBalanceLabel.setOpaque(true);

        regularSlotLabel.setBounds(25,170,150, 60);
        regularSlotLabel.setText("<html>Price: <br/>Stock: ");
        regularSlotLabel.setHorizontalAlignment(JLabel.CENTER);
        regularSlotLabel.setVerticalAlignment(JLabel.CENTER);
        regularSlotLabel.setBackground(Color.BLACK);
        regularSlotLabel.setForeground(Color.WHITE);
        regularSlotLabel.setBorder(borderLine);
        regularSlotLabel.setOpaque(true);

        specialSlotLabel.setBounds(195,170,150, 60);
        specialSlotLabel.setText("<html>Price: <br/>Stock: ");
        specialSlotLabel.setHorizontalAlignment(JLabel.CENTER);
        specialSlotLabel.setVerticalAlignment(JLabel.CENTER);
        specialSlotLabel.setBackground(Color.BLACK);
        specialSlotLabel.setForeground(Color.WHITE);
        specialSlotLabel.setBorder(borderLine);
        specialSlotLabel.setOpaque(true);

        // Buttons

        changeRegularPriceButton.setBounds(125, 90, 50, 30);
        changeRegularPriceButton.setHorizontalAlignment(JButton.CENTER);
        changeRegularPriceButton.setText("<html>Set<br/>Price</html>");
        changeRegularPriceButton.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changeRegularPriceButton.setHorizontalTextPosition(JButton.CENTER);



        changeSpecialPriceButton.setBounds(295, 90, 50, 30);
        changeSpecialPriceButton.setHorizontalAlignment(JButton.CENTER);
        changeSpecialPriceButton.setText("<html>Set<br/>Price</html>");
        changeSpecialPriceButton.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changeSpecialPriceButton.setHorizontalTextPosition(JButton.CENTER);



        restockRegular.setBounds(25, 130, 150, 30);
        restockRegular.setHorizontalAlignment(JButton.CENTER);
        restockRegular.setText("<html>Restock</html>");
        restockRegular.setFont(new Font("Century Gothic", Font.BOLD, 13));
        restockRegular.setHorizontalTextPosition(JButton.CENTER);



        restockSpecial.setBounds(195, 130, 150, 30);
        restockSpecial.setHorizontalAlignment(JButton.CENTER);
        restockSpecial.setText("<html>Restock</html>");
        restockSpecial.setFont(new Font("Century Gothic", Font.BOLD, 13));
        restockSpecial.setHorizontalTextPosition(JButton.CENTER);

        addButton.setBounds(222, 260, 50, 25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);


        exitButton.setBounds(550, 80, 190, 35);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");

        quitButton.setBounds(550, 10, 190, 35);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setText("Exit");


        instructionsButton.setBounds(550, 45, 190, 35);
        instructionsButton.setHorizontalAlignment(JButton.CENTER);
        instructionsButton.setText("Instructions");


        addItem.setBounds(75, 190, 225, 30);
        addItem.setText("Add Item");


        collectMoney.setBounds(92, 295, 180, 50);
        collectMoney.setText("Collect Money");



        printSummary.setBounds(92, 355, 180, 50);
        printSummary.setText("Print Sales Summary");


        // Dropdowns
        denominations.setBounds(92, 260, 120, 25);
        denominations.setAlignmentX(JComboBox.CENTER_ALIGNMENT);



        regularSlotsDropDown.setBounds(25, 25, 150, 40);
        regularSlotsDropDown.setAlignmentX(JComboBox.CENTER_ALIGNMENT);



        specialSlotsDropDown.setBounds(195, 25, 150, 40);
        regularSlotsDropDown.setAlignmentX(JComboBox.CENTER_ALIGNMENT);



        // Text Fields
        changePriceRegular.setBounds(25, 90, 100, 30);
        changePriceRegular.setText("Enter New Price");
        changePriceRegular.setHorizontalAlignment(JTextField.CENTER);
        changePriceRegular.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                changePriceRegular.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        changePriceRegular.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                changePriceRegular.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });

        changePriceSpecial.setBounds(195, 90, 100, 30);
        changePriceSpecial.setText("Enter New Price");
        changePriceSpecial.setHorizontalAlignment(JTextField.CENTER);
        changePriceSpecial.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                changePriceSpecial.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        changePriceSpecial.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                changePriceSpecial.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
                        || ke.getKeyChar() == '\b');
            }
        });


        setName.setBounds(112, 25, 150, 40);
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

        setCalories.setBounds(75, 130, 225, 30);
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
        selectionPanel.add(changeRegularPriceButton);
        selectionPanel.add(changeSpecialPriceButton);
        selectionPanel.add(restockRegular);
        selectionPanel.add(restockSpecial);
        selectionPanel.add(regularSlotsDropDown);
        selectionPanel.add(specialSlotsDropDown);
        selectionPanel.add(changePriceRegular);
        selectionPanel.add(changePriceSpecial);
        selectionPanel.add(regularSlotLabel);
        selectionPanel.add(specialSlotLabel);

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
        lowerPanel.setBounds(0, 630, 750, 130);
        lowerPanel.setLayout(null);
        lowerPanel.setOpaque(true);
        lowerPanel.setBorder(borderLine);
        lowerPanel.add(systemMessage);
        lowerPanel.add(quitButton);
        lowerPanel.add(instructionsButton);
        lowerPanel.add(exitButton);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(375, 150, 375, 480);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);
        rightPanel.add(machineBalanceLabel);
        rightPanel.add(infoLabel);
        rightPanel.add(buyButton);
        rightPanel.add(cancelButton);
        rightPanel.add(addButton);
        rightPanel.add(denominations);
        rightPanel.add(collectMoney);
        rightPanel.add(printSummary);

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

    /**
     * Getter method to access the main JFrame of the Special Maintenance menu.
     * @return The main JFrame object representing the Special Maintenance menu.
     */
    public JFrame getFrame() {
        return frame;
    }
    /**
     * Getter method to access the "Exit" button of the Special Maintenance menu.
     * @return The "Exit" button object.
     */
    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getChangeRegularPriceButton() {
        return changeRegularPriceButton;
    }

    public JButton getChangeSpecialPriceButton() {
        return changeSpecialPriceButton;
    }

    public JButton getRestockRegular() {
        return restockRegular;
    }

    public JButton getRestockSpecial() {
        return restockSpecial;
    }

    public JButton getAddItem() {
        return addItem;
    }

    public JButton getInstructionsButton() {
        return instructionsButton;
    }

    public JButton getCollectMoney() {
        return collectMoney;
    }


    public JButton getPrintSummary() {
        return printSummary;
    }

    public JLabel getSystemMessage() {
        return systemMessage;
    }

    public JLabel getRegularSlotLabel(){return regularSlotLabel;}

    public JLabel getSpecialSlotLabel() {
        return specialSlotLabel;
    }

    public JComboBox<String> getRegularSlotsDropDown(){return regularSlotsDropDown;}

    public JComboBox<String> getSpecialSlotsDropDown(){return specialSlotsDropDown;}

    public JTextField getChangePriceRegular(){return changePriceRegular;}

    public JTextField getChangePriceSpecial(){return changePriceSpecial;}

    public JTextField getSetName(){return setName;}

    public JTextField getSetPrice(){return setPrice;}

    public JTextField getSetCalories(){return setCalories;}

    // Method to update and display the machine's balance label.
    /**
     * Method to update and display the machine's balance label.
     * It calculates the counts of different denominations in the machineStoredMoney list
     * and displays the total machine balance on the machineBalanceLabel.
     * @param machineStoredMoney An ArrayList of integers representing the stored denominations in the machine.
     * @param totalMachineMoney The total sum of money stored in the machine.
     */
    public void setMachineBalanceLabel(ArrayList<Integer> machineStoredMoney, int totalMachineMoney)
    {

        resetDenominations();

        for(int j : machineStoredMoney)
        {
            switch (j) {
                case 1 -> numDenom1++;
                case 5 -> numDenom5++;
                case 10 -> numDenom10++;
                case 20 -> numDenom20++;
                case 50 -> numDenom50++;
                case 100 -> numDenom100++;
            }
        }

        machineBalanceLabel.setText("<html>" + "Add Machine Money <br/>"+
                "<br/>Php 1 Coin: " + numDenom1 +
                "<br/>Php 5 Coin: " + numDenom5 +
                "<br/>Php 10 Coin: " + numDenom10 +
                "<br/>Php 20 Bill: " + numDenom20 +
                "<br/>Php 50 Bill: " + numDenom50 +
                "<br/>Php 100 Bill: " + numDenom100 +
                "<br/>Total: " + totalMachineMoney + "<html>");

    }
    /**
     * Method to reset the denomination counts for displaying the machine balance.
     * This method should be called before calling setMachineBalanceLabel() to ensure correct counts.
     */
    public void resetDenominations() {
        numDenom1 = 0;
        numDenom5 = 0;
        numDenom10 = 0;
        numDenom20 = 0;
        numDenom50 = 0;
        numDenom100 = 0;
    }
    /**
     * Getter method to access the denominations dropdown from other classes.
     * @return The denominations JComboBox object.
     */
    public JComboBox<Integer> getDenominations() {
        return denominations;
    }

    /**
     * Setter method for the regularSlotsDropDown JComboBox.
     * Populates the regularSlotsDropDown with the provided slotTypes.
     * @param slotTypes An ArrayList of strings representing the available slot types for regular slots.
     */
    public void setRegularSlotsDropDown(ArrayList<String> slotTypes) {
        regularSlotsDropDown.setFocusable(false);
        regularSlotsDropDown.addItem("Choose an item...");
        for (String string : slotTypes) {
            regularSlotsDropDown.addItem(string);
        }
    }
    /**
     * Setter method for the specialSlotsDropDown JComboBox.
     * Populates the specialSlotsDropDown with the provided slotTypes.
     * @param slotTypes An ArrayList of strings representing the available slot types for special slots.
     */
    public void setSpecialSlotsDropDown(ArrayList<String> slotTypes) {
        specialSlotsDropDown.setFocusable(false);
        specialSlotsDropDown.addItem("Choose an item...");
        for (String string : slotTypes) {
            specialSlotsDropDown.addItem(string);
        }
    }


}

