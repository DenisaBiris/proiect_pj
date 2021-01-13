package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static com.company.CoffeeType.*;
import static com.company.RoastedBeans.*;


public class UserInterface {
    private static final int LABEL_WIDTH = 300;
    private static final int INPUT_WIDTH = 165;
    private static final int BUTTON_ALIGNMENT = 140;
    private static final int BUTTON_ALIGNMENT_SAVE = 20;
    private static final int INPUT_ALIGNMENT = 120;
    private static final int FIELD_HEIGHT = 30;
    private static final int BUTTON_WIDTH = 200;
    private static final int MENU_BUTTON_WIDTH = 300;
    private static final int MENU_FIELD_HEIGHT = 40;
    private static final int LABEL_ALIGNMENT = 20;
    private static final int LIST_BUTTONS_ALLIGNMENT = 380;
    private static final int LIST_BUTTON_WIDTH = 150 ;

    private static final int ROW_1 = 20;
    private static final int ROW_2 = 60;
    private static final int ROW_3 = 100;
    private static final int ROW_4 = 140;
    private static final int ROW_5= 180;
    private static final int ROW_6= 220;
    private static final int ROW_7= 260;


    private static JTextField companyNameInput;
    private static JTextField unitsInput;
    private static JButton saveButton;
    private static JButton buttonListCoffee;
    private static JButton buttonAddCoffee;
    private static JButton buttonExit;
    private static JButton modifybutton;
    private static JButton deletebutton;
    private static JButton backtomenubutton;
    private static JRadioButton arabicaRadio;
    private static JRadioButton robustaRadio;
    private static JRadioButton lightRoasted;
    private static JRadioButton mediumRoasted;
    private static JRadioButton darkRoasted;
    private static ButtonGroup radioGroup1;
    private static ButtonGroup radioGroup2;
    private static JComboBox<Integer> acididyList;
    private static JComboBox<String> flavourList;

    private static List<Coffee> coffeeList = new ArrayList<>();
    private static DefaultListModel<String> coffeeListModel;
    private static JList<String> jList;


    public static JPanel buildPanel() {
        coffeeList = FileUtility.readFromFile();
        JFrame frame = new JFrame("Coffee Shop Inventory");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.add(panel);
        showMenu(panel);
        frame.setVisible(true);
        return panel;
    }



    private static void showAddPage(JPanel panel) {
        radioGroup1 = new ButtonGroup();
        radioGroup2 = new ButtonGroup();
        JLabel companyNameLabel = new JLabel("Company:");
        companyNameLabel.setBounds(LABEL_ALIGNMENT,ROW_1, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(companyNameLabel);

        companyNameInput = new JTextField(20);
        companyNameInput.setBounds(INPUT_ALIGNMENT, ROW_1, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(companyNameInput);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(LABEL_ALIGNMENT,ROW_2, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(typeLabel);

        arabicaRadio = new JRadioButton();
        arabicaRadio.setText("Arabica");
        arabicaRadio.setBounds(INPUT_ALIGNMENT, ROW_2, 80, FIELD_HEIGHT);

        robustaRadio = new JRadioButton();
        robustaRadio.setText("Robusta");
        robustaRadio.setBounds(INPUT_ALIGNMENT+100, ROW_2, 80, FIELD_HEIGHT);


        radioGroup1.add(arabicaRadio);
        radioGroup1.add(robustaRadio);
        panel.add(arabicaRadio);
        panel.add(robustaRadio);

        JLabel acidityLabel = new JLabel("Acidity:");
        acidityLabel.setBounds(LABEL_ALIGNMENT,ROW_3, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(acidityLabel);

        acididyList = new JComboBox<>();
        for(int i = 0; i < 6; i++)
        acididyList.addItem(i);
        acididyList.setBounds(INPUT_ALIGNMENT,ROW_3,100, FIELD_HEIGHT);
        panel.add(acididyList);

        JLabel flavourLabel = new JLabel("Flavour: ");
        flavourLabel.setBounds(LABEL_ALIGNMENT,ROW_4, LABEL_WIDTH,FIELD_HEIGHT);
        panel.add(flavourLabel);

        flavourList = new JComboBox<>();
        flavourList.addItem("--Select--");
        flavourList.addItem("fruity");
        flavourList.addItem("chocolatey");
        flavourList.addItem("syrups");
        flavourList.addItem("winey");
        flavourList.addItem("nutty");
        flavourList.setBounds(INPUT_ALIGNMENT,ROW_4,100, FIELD_HEIGHT);
        panel.add(flavourList);

        JLabel roastedLabel = new JLabel("Roasted Beans:");
        roastedLabel.setBounds(LABEL_ALIGNMENT,ROW_5, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(roastedLabel);

        lightRoasted = new JRadioButton();
        lightRoasted.setText("Light");
        lightRoasted.setBounds(INPUT_ALIGNMENT, ROW_5, 80, FIELD_HEIGHT);

        mediumRoasted = new JRadioButton();
        mediumRoasted.setText("Medium");
        mediumRoasted.setBounds(INPUT_ALIGNMENT+100, ROW_5, 80, FIELD_HEIGHT);

        darkRoasted = new JRadioButton();
        darkRoasted.setText("Dark");
        darkRoasted.setBounds(INPUT_ALIGNMENT+200, ROW_5, 80, FIELD_HEIGHT);


        radioGroup2.add(lightRoasted);
        radioGroup2.add(mediumRoasted);
        radioGroup2.add(darkRoasted);
        panel.add(lightRoasted);
        panel.add(mediumRoasted);
        panel.add(darkRoasted);

        JLabel unitsLabel = new JLabel("Units:");
        unitsLabel.setBounds(LABEL_ALIGNMENT,ROW_6, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(unitsLabel);

        unitsInput = new JTextField(20);
        unitsInput.setBounds(INPUT_ALIGNMENT, ROW_6, INPUT_WIDTH, FIELD_HEIGHT);
        panel.add(unitsInput);

        saveButton = new JButton("Save");
        saveButton.setBounds(BUTTON_ALIGNMENT_SAVE, ROW_7, BUTTON_WIDTH,FIELD_HEIGHT);
        saveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (companyNameInput.getText().trim().length() == 0 || acididyList.getSelectedIndex() == -1 || flavourList.getSelectedIndex() == -1 || unitsInput.getText().trim().length() == 0 || radioGroup1.getSelection() == null || radioGroup2.getSelection() == null)
                            throw new NoFieldIsFilled("Fill in all the fields!");
                        else if (!isNumeric(unitsInput.getText()))
                            throw new FieldNeedsToBeNumeric("Fill 'Units' field with numeric!");
                        else if (acididyList.getSelectedIndex() == 0)
                            throw new SelectAValue("Select a value for 'Acidity' field!");
                        else if ( flavourList.getSelectedIndex() == 0)
                            throw new SelectAValue("Select a value for 'Flavour' field!");

                        else {
                            Coffee coffee = new Coffee();
                            coffee.setCompany(companyNameInput.getText());
                            coffee.setAcidity((Integer) acididyList.getSelectedItem());
                            coffee.setFlavour((String) flavourList.getSelectedItem());
                            coffee.setUnits(unitsInput.getText());

                            if (arabicaRadio.isSelected()) {
                                coffee.setType(ARABICA);
                            } else {
                                coffee.setType(ROBUSTA);
                            }

                            if (lightRoasted.isSelected()) {
                                coffee.setRoasted(LIGHT);
                            } else if (mediumRoasted.isSelected())
                                coffee.setRoasted(MEDIUM);

                            else coffee.setRoasted(DARK);

                            coffeeList.add(coffee);
                            cleanPanel(panel);
                            showMenu(panel);

                        }

                    }


                });
        saveButton.setBackground(Color.yellow);
        panel.add(saveButton);

        backtomenubutton = new JButton("Back to menu");
        backtomenubutton.setBounds(BUTTON_ALIGNMENT_SAVE+220, ROW_7, BUTTON_WIDTH,FIELD_HEIGHT);
        backtomenubutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanPanel(panel);
                        showMenu(panel);
                    }
                }
        );
        backtomenubutton.setBackground(Color.yellow);
        panel.add(backtomenubutton);

    }

    private static void showModifiedPage(JPanel panel, Coffee coffee) {
        radioGroup1 = new ButtonGroup();
        radioGroup2 = new ButtonGroup();

        JLabel companyNameLabel = new JLabel("Company:");
        companyNameLabel.setBounds(LABEL_ALIGNMENT,ROW_1, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(companyNameLabel);

        companyNameInput = new JTextField(20);
        companyNameInput.setBounds(INPUT_ALIGNMENT, ROW_1, INPUT_WIDTH, FIELD_HEIGHT);
        companyNameInput.setText(coffee.getCompany());
        panel.add(companyNameInput);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setBounds(LABEL_ALIGNMENT,ROW_2, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(typeLabel);

        arabicaRadio = new JRadioButton();
        arabicaRadio.setText("Arabica");
        arabicaRadio.setBounds(INPUT_ALIGNMENT, ROW_2, 80, FIELD_HEIGHT);

        if (coffee.getType().equals(ARABICA)){
            arabicaRadio.setSelected(true);
        }

        robustaRadio = new JRadioButton();
        robustaRadio.setText("Robusta");
        robustaRadio.setBounds(INPUT_ALIGNMENT+100, ROW_2, 80, FIELD_HEIGHT);

        if (coffee.getType().equals(ROBUSTA)){
            robustaRadio.setSelected(true);
        }

        radioGroup1.add(arabicaRadio);
        radioGroup1.add(robustaRadio);
        panel.add(arabicaRadio);
        panel.add(robustaRadio);

        JLabel acidityLabel = new JLabel("Acidity:");
        acidityLabel.setBounds(LABEL_ALIGNMENT,ROW_3, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(acidityLabel);

        acididyList = new JComboBox<>();

        for(int i = 0; i < 6; i++)
            acididyList.addItem(i);

        acididyList.setBounds(INPUT_ALIGNMENT,ROW_3,100, FIELD_HEIGHT);

        for(int i = 0; i < 6; i++){
            if (coffee.getAcidity() == i)
                acididyList.setSelectedIndex(i);
        }

        panel.add(acididyList);

        JLabel flavourLabel = new JLabel("Flavour: ");
        flavourLabel.setBounds(LABEL_ALIGNMENT,ROW_4, LABEL_WIDTH,FIELD_HEIGHT);
        panel.add(flavourLabel);

        flavourList = new JComboBox<>();
        flavourList.addItem("--Select--");
        flavourList.addItem("fruity");
        flavourList.addItem("chocolatey");
        flavourList.addItem("syrups");
        flavourList.addItem("winey");
        flavourList.addItem("nutty");
        flavourList.setBounds(INPUT_ALIGNMENT,ROW_4,100, FIELD_HEIGHT);

        if (coffee.getFlavour().equals("fruity"))
            flavourList.setSelectedIndex(1);
        if (coffee.getFlavour().equals("chocolatey"))
            flavourList.setSelectedIndex(2);
        if (coffee.getFlavour().equals("syrups"))
            flavourList.setSelectedIndex(3);
        if (coffee.getFlavour().equals("winey"))
            flavourList.setSelectedIndex(4);
        if (coffee.getFlavour().equals("nutty"))
            flavourList.setSelectedIndex(5);

        panel.add(flavourList);

        JLabel roastedLabel = new JLabel("Roasted Beans:");
        roastedLabel.setBounds(LABEL_ALIGNMENT,ROW_5, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(roastedLabel);

        lightRoasted = new JRadioButton();
        lightRoasted.setText("Light");
        lightRoasted.setBounds(INPUT_ALIGNMENT, ROW_5, 80, FIELD_HEIGHT);

        if (coffee.getRoasted().equals(LIGHT)){
            lightRoasted.setSelected(true);
        }


        mediumRoasted = new JRadioButton();
        mediumRoasted.setText("Medium");
        mediumRoasted.setBounds(INPUT_ALIGNMENT+100, ROW_5, 80, FIELD_HEIGHT);

        if (coffee.getRoasted().equals(MEDIUM)){
            mediumRoasted.setSelected(true);
        }

        darkRoasted = new JRadioButton();
        darkRoasted.setText("Dark");
        darkRoasted.setBounds(INPUT_ALIGNMENT+200, ROW_5, 80, FIELD_HEIGHT);

        if (coffee.getRoasted().equals(DARK)){
            darkRoasted.setSelected(true);
        }


        radioGroup2.add(lightRoasted);
        radioGroup2.add(mediumRoasted);
        radioGroup2.add(darkRoasted);
        panel.add(lightRoasted);
        panel.add(mediumRoasted);
        panel.add(darkRoasted);

        JLabel unitsLabel = new JLabel("Units:");
        unitsLabel.setBounds(LABEL_ALIGNMENT,ROW_6, LABEL_WIDTH, FIELD_HEIGHT);
        panel.add(unitsLabel);

        unitsInput = new JTextField(20);
        unitsInput.setBounds(INPUT_ALIGNMENT, ROW_6, INPUT_WIDTH, FIELD_HEIGHT);
        unitsInput.setText(coffee.getUnits());
        panel.add(unitsInput);

        saveButton = new JButton("Save");
        saveButton.setBounds(BUTTON_ALIGNMENT_SAVE, ROW_7, BUTTON_WIDTH,FIELD_HEIGHT);
        saveButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (companyNameInput.getText().trim().length() == 0 || acididyList.getSelectedIndex() == -1 || flavourList.getSelectedIndex() == -1 || unitsInput.getText().trim().length() == 0 || radioGroup1.getSelection() == null || radioGroup2.getSelection() == null)
                            throw new NoFieldIsFilled("Fill in all the fields!");
                        else if (!isNumeric(unitsInput.getText()))
                            throw new FieldNeedsToBeNumeric("Fill 'Units' field with numeric!");
                        else if (acididyList.getSelectedIndex() == 0)
                            throw new SelectAValue("Select a value for 'Acidity' field!");
                        else if ( flavourList.getSelectedIndex() == 0)
                            throw new SelectAValue("Select a value for 'Flavour' field!");

                        else {
                        coffee.setCompany(companyNameInput.getText());
                        coffee.setAcidity((Integer) acididyList.getSelectedItem());
                        coffee.setFlavour((String) flavourList.getSelectedItem());
                        coffee.setUnits(unitsInput.getText());

                        if (arabicaRadio.isSelected()) {
                            coffee.setType(ARABICA);
                        }
                        else {
                            coffee.setType(ROBUSTA);
                        }

                        if (lightRoasted.isSelected()){
                            coffee.setRoasted(LIGHT);
                        }
                        else if (mediumRoasted.isSelected())
                            coffee.setRoasted(MEDIUM);
                        else coffee.setRoasted(DARK);


                        cleanPanel(panel);
                        showListPage(panel);
                    }}



                });
        saveButton.setBackground(Color.yellow);
        panel.add(saveButton);

        backtomenubutton = new JButton("Back to menu");
        backtomenubutton.setBounds(BUTTON_ALIGNMENT_SAVE+220, ROW_7, BUTTON_WIDTH,FIELD_HEIGHT);
        backtomenubutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanPanel(panel);
                        showMenu(panel);
                    }
                }
        );
        backtomenubutton.setBackground(Color.yellow);

        panel.add(backtomenubutton);


    }



    private static void showMenu(JPanel panel) {
        panel.setLayout(null);

        buttonAddCoffee = new JButton("Add Coffee Drink");
        buttonAddCoffee.setBounds(BUTTON_ALIGNMENT, 150, MENU_BUTTON_WIDTH,MENU_FIELD_HEIGHT);
        buttonAddCoffee.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        buttonAddCoffee.setBackground(Color.yellow);
        buttonAddCoffee.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanPanel(panel);
                        showAddPage(panel);
                    }
                }
        );
        panel.add(buttonAddCoffee);

        buttonListCoffee = new JButton("List of Coffee Drinks");
        buttonListCoffee.setBounds(BUTTON_ALIGNMENT, 200, MENU_BUTTON_WIDTH,MENU_FIELD_HEIGHT);
        buttonListCoffee.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        buttonListCoffee.setBackground(Color.yellow);
        buttonListCoffee.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanPanel(panel);
                        showListPage(panel);
                    }
                }
        );

        panel.add(buttonListCoffee);

        buttonExit = new JButton("Exit Program");
        buttonExit.setBounds(BUTTON_ALIGNMENT, 250, MENU_BUTTON_WIDTH,MENU_FIELD_HEIGHT);
        buttonExit.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        buttonExit.setBackground(Color.yellow);
        buttonExit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        FileUtility.writeToFile(coffeeList);
                        System.exit(0);
                    }
                }
        );
        panel.add(buttonExit);


    }



    private static void showListPage(JPanel panel) {
        coffeeListModel = new DefaultListModel<>();
        addCoffeeToJList();


        jList = new JList<>(coffeeListModel);
        jList.setBounds(LABEL_ALIGNMENT, ROW_1, 300 ,500);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(jList);
        jList.getSelectedIndex();

        modifybutton = new JButton("Modify");
        modifybutton.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_1, LIST_BUTTON_WIDTH,FIELD_HEIGHT);
        modifybutton.setBackground(Color.yellow);
        modifybutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = jList.getSelectedIndex();
                        if (selectedIndex < 0){
                            throw new NoItemSelectedException("Please select an item!");}
                        else {
                           cleanPanel(panel);
                            showModifiedPage(panel, coffeeList.get(selectedIndex));
                        }
                    }
                }
        );
        panel.add(modifybutton);

        deletebutton = new JButton("Delete");
        deletebutton.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_2, LIST_BUTTON_WIDTH,FIELD_HEIGHT);
        deletebutton.setBackground(Color.yellow);
        deletebutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedIndex = jList.getSelectedIndex();
                        if (selectedIndex < 0){
                            throw new NoItemSelectedException("Please select an item!");}
                        else {
                            coffeeList.remove(selectedIndex);
                            coffeeListModel.clear();
                            addCoffeeToJList();
                        }
                    }
                }
        );
        panel.add(deletebutton);

        backtomenubutton = new JButton("Back to menu");
        backtomenubutton.setBounds(LIST_BUTTONS_ALLIGNMENT, ROW_3, LIST_BUTTON_WIDTH,FIELD_HEIGHT);
        backtomenubutton.setBackground(Color.yellow);
        backtomenubutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cleanPanel(panel);
                        showMenu(panel);
                    }
                }
        );
        panel.add(backtomenubutton);

    }
    public static boolean isNumeric(String string)
    {
        for (char c : string.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    private static void addCoffeeToJList() {
        coffeeList.forEach(coffee -> coffeeListModel.addElement(coffee.getCompany()+" "+coffee.getType()));
    }

    private static void cleanPanel(JPanel panel) {
        panel.removeAll();
        panel.updateUI();
    }


}
