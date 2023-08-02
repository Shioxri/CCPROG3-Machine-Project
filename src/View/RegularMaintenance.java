package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.Border;


/**
 * This class represents the graphical user interface (GUI) for regular maintenance of the vending machine.
 * It allows the admin to restock items, change prices, add new items, collect money, and print sales summary.
 */
public class RegularMaintenance {
    // Declare the GUI components
    JFrame frame = new JFrame();
    JLabel systemMessage = new JLabel();
    JLabel machineBalanceLabel = new JLabel();
    JLabel slotInfoLabel = new JLabel();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    JButton changePriceButton = new JButton();
    JButton restockButton = new JButton();
    JButton addItem = new JButton();
    JButton collectMoney = new JButton();
    JButton printSummary = new JButton();
    JButton instructionsButton = new JButton();
    JButton quitButton = new JButton();
    JComboBox<String> slotsDropdown;
    JTextField changePrice = new JTextField();
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

    /**
     * Constructor for the RegularMaintenance class.
     * Initializes the GUI by calling the init() method.
     */
    public RegularMaintenance(){
        init();
    }

    /**
     * Initializes the graphical user interface (GUI) components and sets up the regular maintenance view.
     * Creates various panels and adds components to them, sets their properties, and arranges them using layout managers.
     * The GUI is composed of background images, labels, buttons, and dropdowns.
     */
    public void init() {
        JLabel titleLabel = new JLabel();
        slotsDropdown = new JComboBox<>();
        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel newItemPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel infoLabel = new JLabel();

        denominations = new JComboBox<>(new Integer[]{1, 5, 10, 20, 50, 100});







        // Images
        ImageIcon fruitIcon = new ImageIcon("pixelatedfruit.png");
        ImageIcon titleIcon = new ImageIcon("trueregvm.png");
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

        systemMessage.setBounds(10,8,530,110);
        systemMessage.setHorizontalAlignment(JLabel.CENTER);
        systemMessage.setVerticalAlignment(JLabel.CENTER);
        systemMessage.setBackground(new Color(0,0,0));
        systemMessage.setBorder(borderLinegrayl);
        systemMessage.setText("<html><p align=\"center\">Hello Admin, welcome to the [Regular] Maintenance Menu! </p></html>");
        systemMessage.setForeground(Color.WHITE);
        systemMessage.setOpaque(true);

        //Balance
        machineBalanceLabel.setBounds(92,50,180,200);
        machineBalanceLabel.setText("<html>$1 Bill: <br/>$5 Bill: <br/> $10 Bill: <br/>$20 Bill: <br/>$50 Bill: <br/> $100 Bill: <br/> Total:</html>");
        machineBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        machineBalanceLabel.setForeground(Color.white);
        machineBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        machineBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        machineBalanceLabel.setBorder(borderLine);
        machineBalanceLabel.setBackground(Color.black);
        machineBalanceLabel.setOpaque(true);

        slotInfoLabel.setBounds(35, 70, 300, 110);
        slotInfoLabel.setBackground(Color.BLACK);
        slotInfoLabel.setText("<html>Restocking and Repricing<br/><br/>Price: <br/> Stock: </html>");
        slotInfoLabel.setHorizontalAlignment(0);
        slotInfoLabel.setVerticalAlignment(0);
        slotInfoLabel.setBorder(borderLine);
        slotInfoLabel.setForeground(Color.white);
        slotInfoLabel.setOpaque(true);

        // Buttons

        changePriceButton.setBounds(265, 185, 70, 30);
        changePriceButton.setHorizontalAlignment(JButton.CENTER);
        changePriceButton.setText("<html>Set Price</html>");
        changePriceButton.setFont(new Font("Century Gothic", Font.BOLD, 10));
        changePriceButton.setHorizontalTextPosition(JButton.CENTER);


        restockButton.setBounds(190, 25, 145, 40);
        restockButton.setHorizontalAlignment(JButton.CENTER);
        restockButton.setText("Restock");
        restockButton.setFont(new Font("Century Gothic", Font.BOLD, 14));
        restockButton.setHorizontalTextPosition(JButton.CENTER);



        addButton.setBounds(222, 260, 50,25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);



        exitButton.setBounds(550,80,190,35);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Go Back");



        quitButton.setBounds(550,10,190,35);
        quitButton.setHorizontalAlignment(JButton.CENTER);
        quitButton.setText("Exit");
        quitButton.addActionListener(e -> System.exit(0));


        instructionsButton.setBounds(550,45,190,35);
        instructionsButton.setHorizontalAlignment(JButton.CENTER);
        instructionsButton.setText("Instructions");
        instructionsButton.addActionListener(e -> System.exit(0));


        addItem.setBounds(75,190,225,30);
        addItem.setText("Add Item");



        collectMoney.setBounds(92,295, 180, 50);
        collectMoney.setText("Collect Money");


        printSummary.setBounds(92, 355,180,50);
        printSummary.setText("Print Sales Summary");



        // Dropdowns
        denominations.setBounds(92,260,120,25);
        denominations.setAlignmentX(JComboBox.CENTER_ALIGNMENT);



        slotsDropdown.setBounds(35,25,150,40);
        slotsDropdown.setAlignmentX(JComboBox.CENTER_ALIGNMENT);



        // Text Fields
        changePrice.setBounds(35, 185, 225, 30);
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
        selectionPanel.add(restockButton);
        selectionPanel.add(slotsDropdown);
        selectionPanel.add(changePrice);
        selectionPanel.add(slotInfoLabel);


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
        lowerPanel.add(quitButton);
        lowerPanel.add(instructionsButton);
        lowerPanel.add(exitButton);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(375,150,375,480);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);
        rightPanel.add(machineBalanceLabel);
        rightPanel.add(infoLabel);
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
     * Getter method to access the JFrame from other classes.
     *
     * @return The JFrame instance.
     */
    public JFrame getFrame() {
        return frame;
    }
    /**
     * Getter method to access the "Go Back" button from other classes.
     *
     * @return The "Go Back" button.
     */
    public JButton getExitButton(){
        return exitButton;
    }
    /**
     * Getter method to access the "Add" button from other classes.
     *
     * @return The "Add" button.
     */
    public JButton getAddButton(){return addButton;}
    /**
     * Getter method to access the "Set Price" button from other classes.
     *
     * @return The "Set Price" button.
     */
    public JButton getChangePriceButton(){return changePriceButton;}
    /**
     * Getter method to access the "Restock" button from other classes.
     *
     * @return The "Restock" button.
     */
    public JButton getRestockButton(){return restockButton;}
    /**
     * Getter method to access the "Add Item" button from other classes.
     *
     * @return The "Add Item" button.
     */
    public JButton getAddItem(){return addItem;}
    /**
     * Getter method to access the "Collect Money" button from other classes.
     *
     * @return The "Collect Money" button.
     */
    public JButton getCollectMoney(){return collectMoney;}
    /**
     * Getter method to access the "Instructions" button from other classes.
     *
     * @return The "Instructions" button.
     */
    public JButton getInstructionsButton(){return instructionsButton;}
    /**
     * Getter method to access the "Print Sales Summary" button from other classes.
     *
     * @return The "Print Sales Summary" button.
     */
    public JButton getPrintSummary(){return printSummary;}
    /**
     * Getter method to access the system message label from other classes.
     *
     * @return The system message label.
     */
    public JLabel getSystemMessage(){return  systemMessage;}
    /**
     * Getter method to access the slot information label from other classes.
     *
     * @return The slot information label.
     */
    public JLabel getSlotInfoLabel(){return slotInfoLabel;}
    /**
     * Getter method to access the text field for setting product calories from other classes.
     *
     * @return The text field for setting product calories.
     */
    public JTextField getSetCalories(){return setCalories;}
    /**
     * Getter method to access the text field for setting product name from other classes.
     *
     * @return The text field for setting product name.
     */
    public JTextField getSetName(){return setName;}
    /**
     * Getter method to access the text field for setting product price from other classes.
     *
     * @return The text field for setting product price.
     */
    public JTextField getSetPrice(){return setPrice;}
    /**
     * Getter method to access the text field for changing the product price from other classes.
     *
     * @return The text field for changing the product price.
     */
    public JTextField getChangePrice(){return changePrice;}
    /**
     * Getter method to access the slots dropdown (combobox) from other classes.
     *
     * @return The slots dropdown (combobox).
     */
    public JComboBox<String> getSlotsDropdown(){return slotsDropdown;}

    /**
     * Populates the slots dropdown with slot types.
     *
     * @param slotTypes An ArrayList containing the available slot types to be displayed in the dropdown.
     */
    public void setSlotsDropdown(ArrayList<String> slotTypes) {
        slotsDropdown.setFocusable(false);
        slotsDropdown.addItem("Choose an item...");
        for (String string : slotTypes) {
            slotsDropdown.addItem(string);
        }
    }

    /**
     * Sets and displays the machine's stored money and total balance.
     *
     * @param machineStoredMoney An ArrayList containing the count of each denomination of money stored in the machine.
     * @param totalMachineMoney The total balance in the machine.
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
     * method that resets denominations to zero
     *
     */
    public void resetDenominations() {
        numDenom1 = 0;
        numDenom5 = 0;
        numDenom10 = 0;
        numDenom20 = 0;
        numDenom50 = 0;
        numDenom100 = 0;
    }
    // Getter method to access the denominations dropdown (combobox) from other classes.
    public JComboBox<Integer> getDenominations() {
        return denominations;
    }
}
