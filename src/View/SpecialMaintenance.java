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
public class SpecialMaintenance {
    JFrame frame = new JFrame();
    JLabel systemMessage = new JLabel();
    JLabel userBalanceLabel = new JLabel();
    JButton buyButton = new JButton();
    JButton cancelButton = new JButton();
    JButton addButton = new JButton();
    JButton exitButton = new JButton();
    JButton changePriceButton = new JButton();
    JButton changePriceButton2 = new JButton();
    JButton reStock = new JButton();
    JButton reStock2 = new JButton();
    JButton addItem = new JButton();
    JButton collectMoney = new JButton();
    JButton printSummary = new JButton();
    JButton instructionsButton = new JButton();
    JButton slotInfoButton = new JButton();
    JComboBox<String> slots = new JComboBox<>();
    JComboBox<String> slots2 = new JComboBox<>();
    JComboBox<Integer> addStock = new JComboBox<>();
    JComboBox<Integer> addStock2 = new JComboBox<>();
    JTextField changePrice = new JTextField();
    JTextField changePrice2 = new JTextField();
    JTextField setName = new JTextField();
    JTextField setPrice = new JTextField();
    JTextField setCalories = new JTextField();

    public SpecialMaintenance() {

    }

    public void init() {
        // Declarations

        JLabel titleLabel = new JLabel();

        JPanel titlePanel = new JPanel();
        JPanel selectionPanel = new JPanel();
        JPanel newItemPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JLabel infoLabel = new JLabel();
        JComboBox<Integer> denominations = new JComboBox<>();


        //TODO: need method to connect this to amount of $1 in backend
        AtomicInteger cash = new AtomicInteger(1);
        //TODO: need method to connect this to amount of $5 in backend
        AtomicInteger cash2 = new AtomicInteger(1);
        //TODO: need method to connect this to amount of $10 in backend
        AtomicInteger cash3 = new AtomicInteger(1);
        //TODO: need method to connect this to amount of $20 in backend
        AtomicInteger cash4 = new AtomicInteger(1);
        //TODO: need method to connect this to amount of $50 in backend
        AtomicInteger cash5 = new AtomicInteger(1);
        //TODO: need method to connect this to amount of $100 in backend
        AtomicInteger cash6 = new AtomicInteger(1);
        //TODO: need method to show the total amount of all these items
        AtomicInteger cashtotal = new AtomicInteger(186);


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

        //TODO: need method to print errors and notifications here
        systemMessage.setBounds(10, 8, 530, 110);
        systemMessage.setHorizontalAlignment(JLabel.CENTER);
        systemMessage.setVerticalAlignment(JLabel.CENTER);
        systemMessage.setBackground(new Color(0, 0, 0));
        systemMessage.setBorder(borderLinegrayl);
        systemMessage.setText("<html><p align=\"center\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Nullam ullamcorper ullamcorper risus eget elementum. Morbi ac quam in ante viverra placerat. Cras non justo purus. " +
                "In eleifend nibh lectus, a elementum purus gravida id. Praesent quis porta arcu. Integer finibus nisi id eros iaculis gravida. Cras tempor orci sit amet pharetra feugiat. " +
                "Sed at sollicitudin nisl.</p></html>"); // 368 characters max
        systemMessage.setForeground(Color.WHITE);
        systemMessage.setOpaque(true);

        //TODO: need method to show denominations here(ignore if cashes are connected)
        userBalanceLabel.setBounds(92, 50, 180, 200);
        userBalanceLabel.setText("<html>$1 Bill: " + cash.get() + "<br/>$5 Bill: " + cash2.get() + "<br/> $10 Bill: " + cash3.get() +
                "<br/>$20 Bill: " + cash4.get() + "<br/>$50 Bill: " + cash5.get() + "<br/> $100 Bill: " + cash6.get() + "<br/>Total: " + cashtotal.get() +
                "</html>");
        userBalanceLabel.setFont(new Font("Century Gothic", Font.BOLD, 14));
        userBalanceLabel.setForeground(Color.white);
        userBalanceLabel.setHorizontalAlignment(JLabel.CENTER);
        userBalanceLabel.setVerticalAlignment(JLabel.CENTER);
        userBalanceLabel.setBorder(borderLine);
        userBalanceLabel.setBackground(Color.black);
        userBalanceLabel.setOpaque(true);


        // Buttons
        //TODO: need method to connect this tobackend where it changes price based on changePrice text field
        changePriceButton.setBounds(125, 90, 50, 30);
        changePriceButton.setHorizontalAlignment(JButton.CENTER);
        changePriceButton.setText("<html>Set<br/>Price</html>");
        changePriceButton.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changePriceButton.setHorizontalTextPosition(JButton.CENTER);
        changePriceButton.addActionListener(e -> {
            System.out.println("New Money: " + Integer.parseInt(changePrice.getText()));
            changePrice.setText("");
        });

        //TODO: need method to connect this tobackend where it changes price based on changePrice2 text field
        changePriceButton2.setBounds(295, 90, 50, 30);
        changePriceButton2.setHorizontalAlignment(JButton.CENTER);
        changePriceButton2.setText("<html>Set<br/>Price</html>");
        changePriceButton2.setFont(new Font("Century Gothic", Font.BOLD, 9));
        changePriceButton2.setHorizontalTextPosition(JButton.CENTER);
        changePriceButton2.addActionListener(e -> {
            System.out.println("New Money: " + Integer.parseInt(changePrice2.getText()));
            changePrice.setText("");
        });

        //TODO: need method to connect this to backend where it restocks the stock based on the addStock dropdown
        reStock.setBounds(125, 130, 50, 30);
        reStock.setHorizontalAlignment(JButton.CENTER);
        reStock.setText("<html>Add<br/>Stock</html>");
        reStock.setFont(new Font("Century Gothic", Font.BOLD, 9));
        reStock.setHorizontalTextPosition(JButton.CENTER);
        reStock.addActionListener(e -> System.out.println("New Stock: " + addStock.getSelectedItem()));

        //TODO: need method to connect this to backend where it restocks the stock based on the addStock2 dropdown
        reStock2.setBounds(295, 130, 50, 30);
        reStock2.setHorizontalAlignment(JButton.CENTER);
        reStock2.setText("<html>Add<br/>Stock</html>");
        reStock2.setFont(new Font("Century Gothic", Font.BOLD, 9));
        reStock2.setHorizontalTextPosition(JButton.CENTER);
        reStock2.addActionListener(e -> System.out.println("New Stock: " + addStock2.getSelectedItem()));

        //TODO: need method to update denominations in the backend(if cash is connected ignore this)
        addButton.setBounds(222, 260, 50, 25);
        addButton.setText("+");
        addButton.setHorizontalAlignment(JButton.CENTER);
        addButton.addActionListener(e -> {
            switch (denominations.getSelectedIndex()) {
                case 0 -> {
                    cash.set(cash.get() + 1);
                    cashtotal.set(cashtotal.get() + 1);
                }
                case 1 -> {
                    cash2.set(cash2.get() + 1);
                    cashtotal.set(cashtotal.get() + 5);
                }
                case 2 -> {
                    cash3.set(cash3.get() + 1);
                    cashtotal.set(cashtotal.get() + 10);
                }
                case 3 -> {
                    cash4.set(cash4.get() + 1);
                    cashtotal.set(cashtotal.get() + 20);
                }
                case 4 -> {
                    cash5.set(cash5.get() + 1);
                    cashtotal.set(cashtotal.get() + 50);
                }
                case 5 -> {
                    cash6.set(cash6.get() + 1);
                    cashtotal.set(cashtotal.get() + 100);
                }
            }
            userBalanceLabel.setText("<html>$1 Bill: " + cash.get() + "<br/>$5 Bill: " + cash2.get() + "<br/> $10 Bill: " + cash3.get() +
                    "<br/>$20 Bill: " + cash4.get() + "<br/>$50 Bill: " + cash5.get() + "<br/> $100 Bill: " + cash6.get() + "<br/>Total: " + cashtotal.get() +
                    "</html>");
        });

        //TODO: need method to switch GUI to SpecialVMMenu
        exitButton.setBounds(550, 80, 190, 35);
        exitButton.setHorizontalAlignment(JButton.CENTER);
        exitButton.setText("Menu");
        exitButton.addActionListener(e -> System.exit(0));
        /* How to remove action listener: exitButton.removeActionListener(exitButton.getActionListeners()[0]); */

        //TODO: need method to show in system message the info of the 2 selected slots
        slotInfoButton.setBounds(550, 10, 190, 35);
        slotInfoButton.setHorizontalAlignment(JButton.CENTER);
        slotInfoButton.setText("Slot Info");
        slotInfoButton.addActionListener(e -> System.exit(0));

        //TODO: need method to show in system message the instructions
        instructionsButton.setBounds(550, 45, 190, 35);
        instructionsButton.setHorizontalAlignment(JButton.CENTER);
        instructionsButton.setText("Instructions");
        instructionsButton.addActionListener(e -> System.exit(0));

        //TODO: need method that adds an item based on the 3 text fields
        addItem.setBounds(75, 190, 225, 30);
        addItem.setText("Add Item");
        addItem.addActionListener(e -> slots.addItem(setName.getText()));

        //TODO: need method to mirror collecting cash(if cash is connected ignore this)
        collectMoney.setBounds(92, 295, 180, 50);
        collectMoney.setText("Collect Money");
        collectMoney.addActionListener(e -> {
            cash.set(0);
            cash2.set(0);
            cash3.set(0);
            cash4.set(0);
            cash5.set(0);
            cash6.set(0);
            systemMessage.setText("You have collected $" + cashtotal.get());
            cashtotal.set(0);
            userBalanceLabel.setText("<html>$1 Bill: " + cash.get() + "<br/>$5 Bill: " + cash2.get() + "<br/> $10 Bill: " + cash3.get() +
                    "<br/>$20 Bill: " + cash4.get() + "<br/>$50 Bill: " + cash5.get() + "<br/> $100 Bill: " + cash6.get() + "<br/>Total: " + cashtotal.get() +
                    "</html>");
        });

        //TODO: need method to print the summary in the notification with text wrapping
        printSummary.setBounds(92, 355, 180, 50);
        printSummary.setText("Print Summary");
        printSummary.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "summary");//remember to add wrapping
        });


        // Dropdows
        denominations.setBounds(92, 260, 120, 25);
        denominations.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (int i : new int[]{1, 5, 10, 20, 50, 100}) {
            denominations.addItem(i);
        }

        //TODO: need method to show slot items here
        slots.setBounds(25, 25, 150, 40);
        slots.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (String i : new String[]{"Regular Slot 1", "Slot 2", "Slot 3"}) {
            slots.addItem(i);
        }

        //TODO: need method to show slot items here
        slots2.setBounds(195, 25, 150, 40);
        slots.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (String i : new String[]{"Special Slot 1", "Slot 2", "Slot 3"}) {
            slots2.addItem(i);
        }


        addStock.setBounds(25, 130, 100, 30);
        addStock.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (int i : new int[]{1, 5, 10}) {
            addStock.addItem(i);
        }

        addStock2.setBounds(195, 130, 100, 30);
        addStock.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
        for (int i : new int[]{1, 5, 10}) {
            addStock2.addItem(i);
        }


        // Text Fields
        changePrice.setBounds(25, 90, 100, 30);
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

        changePrice2.setBounds(195, 90, 100, 30);
        changePrice2.setText("Enter New Price");
        changePrice2.setHorizontalAlignment(JTextField.CENTER);
        changePrice2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                changePrice2.setText("");
            }

            public void focusLost(FocusEvent e) {
                // nothing
            }
        });
        changePrice2.addKeyListener(new KeyAdapter() { // allows only backspace and numbers
            public void keyPressed(KeyEvent ke) {
                changePrice2.setEditable(ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9'
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
        selectionPanel.add(changePriceButton);
        selectionPanel.add(changePriceButton2);
        selectionPanel.add(reStock);
        selectionPanel.add(reStock2);
        selectionPanel.add(slots);
        selectionPanel.add(slots2);
        selectionPanel.add(changePrice);
        selectionPanel.add(changePrice2);
        selectionPanel.add(addStock);
        selectionPanel.add(addStock2);

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
        lowerPanel.add(slotInfoButton);
        lowerPanel.add(instructionsButton);
        lowerPanel.add(exitButton);

        rightPanel.setBackground(new Color(25, 25, 112, 123));
        rightPanel.setBounds(375, 150, 375, 480);
        rightPanel.setLayout(null);
        rightPanel.setOpaque(true);
        rightPanel.setBorder(borderLine);
        rightPanel.add(userBalanceLabel);
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

    public JFrame getFrame() {
        return frame;
    }

    public JButton getExitButton() {
        return exitButton;
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

    public JButton getChangePriceButton() {
        return changePriceButton;
    }

    public JButton getChangePriceButton2() {
        return changePriceButton2;
    }

    public JButton getReStock() {
        return reStock;
    }

    public JButton getReStock2() {
        return reStock2;
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

    public JButton getSlotInfoButton() {
        return slotInfoButton;
    }

    public JButton getPrintSummary() {
        return printSummary;
    }

    public JLabel getUserBalanceLabel() {
        return userBalanceLabel;
    }

    public JLabel getSystemMessage() {
        return systemMessage;
    }

    public JComboBox<String> getSlots(){return slots;}

    public JComboBox<String> getSlots2(){return slots2;}

    public JComboBox<Integer> getAddStock(){return addStock;}

    public JComboBox<Integer> getAddStock2(){return addStock2;}
    
    public JTextField getChangePrice(){return changePrice;}

    public JTextField getChangePrice2(){return changePrice2;}

    public JTextField getSetName(){return setName;}

    public JTextField getSetPrice(){return setPrice;}

    public JTextField getSetCalories(){return setCalories;}



}

