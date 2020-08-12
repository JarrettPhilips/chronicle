package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EditingPanel extends JPanel{
    /*
        Variables
    */
    private Font textFont = new Font("Palatino", Font.PLAIN, 16);
    private Font labelFont = new Font("Geneva", Font.BOLD, 16);

    private JTextField dateTextField;
    private JTextField titleTextField;
    private JComboBox ratingBox;
    private JTextArea textArea;

    private boolean newEntry = true;

    private String directory;

    /*
        Constructors
    */
    public EditingPanel(Color primaryColor, Color secondaryColor){//Creates a new entry
        setUpPanel(primaryColor, secondaryColor);
    }

    public EditingPanel(Color primaryColor, Color secondaryColor, String directory){//Edits an existing entry
        newEntry = false;
        this.directory = directory;
        setUpPanel(primaryColor, secondaryColor);
        loadEntry(directory);
    }

    /*
        Functions
    */
    private void saveEntry(){ //Contains a good bit of legacy code...
        boolean success = true;

        try {
            System.out.println("Creating entry...");

            if (titleTextField.getText().equals("Title") || dateTextField.getText().equals("YYYYMMDD") || ratingBox.getSelectedItem().toString().equals("N/A")) {
                Error r = new Error("Please ensure all fields are filled out");
            } else {
                File theDir = new File(Main.journalMainDirectory + "/" + dateTextField.getText());

                if (!theDir.exists()) {
                    System.out.println("Creating directory: " + theDir.getName());

                    try {
                        theDir.mkdir();
                    } catch (Exception e) {
                        System.out.println("Error creating directory");
                        success = false;
                    }

                    //Replaces spaces with underscores for filename
                    String filename = titleTextField.getText().replace(' ','_');

                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(Main.journalMainDirectory + "/" + dateTextField.getText() + "/" + filename + ".txt"))) {
                        textArea.append("^^" + ratingBox.getSelectedItem() + "^^ ");
                        textArea.write(fileOut);
                    } catch (Exception e) {
                        System.out.println("Error writing file");
                        success = false;
                    }
                } else {
                    Error r = new Error("Directory already exists");
                }
            }
        } catch(Exception e){
            System.out.println("Program failed to properly create entry");
        }

        Main.ui2.setContentPanelToDefault();
        Main.ui2.reloadSelectionMenu();
    }

    private void saveChangesToExistingEntry(){
        File file = new File(directory);
        String entryText = textArea.getText();
        FileWriter fw;

        try {
            fw = new FileWriter(file,false);
            fw.write(entryText);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving changes to file at:" + directory);
            e.printStackTrace();
        }

        Main.ui2.setContentPanelToDefault();
        Main.ui2.reloadSelectionMenu();
    }

    private void loadEntry(String directory){
        try(FileReader reader = new FileReader(directory)){
            textArea.read(reader, null);
        } catch (Exception e){
            System.out.println("Error loading entry files:");
            System.out.println("Attempted to reach directory:" + directory);
        }
    }

    private void setUpPanel(Color primaryColor, Color secondaryColor){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel header;

        if(newEntry) {
            header = createNewEntryHeader(primaryColor, secondaryColor);
        } else {
            header = createEditedEntryHeader(primaryColor, secondaryColor);
        }

        header.setBackground(primaryColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(header, c);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(secondaryColor);
        textArea.setFont(textFont);
        textArea.setBorder(new EmptyBorder(40, 40, 40, 40));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(new EmptyBorder(0,0,0,0));
        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(scrollPane, c);

        this.setBackground(secondaryColor);
    }

    private JPanel createNewEntryHeader(Color primaryColor, Color secondaryColor){
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        header.setBorder(new EmptyBorder(5,30,5,30));
        GridBagConstraints c = new GridBagConstraints();

        titleTextField = new JTextField(20);
        titleTextField.setText("Title");
        titleTextField.setBackground(secondaryColor);
        titleTextField.setFont(textFont);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.VERTICAL;
        header.add(titleTextField, c);

        dateTextField = new JTextField(14);
        dateTextField.setText("YYYYMMDD");
        dateTextField.setBackground(secondaryColor);
        dateTextField.setFont(textFont);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        header.add(dateTextField, c);

        ratingBox = new JComboBox();
        ratingBox.setBackground(secondaryColor);
        ratingBox.setFont(labelFont);
        ratingBox.addItem("N/A");
        for (int i = 10; i >= 1; i--) {
            ratingBox.addItem(new Integer(i));
        }
        c.gridx = 1;
        c.gridy = 1;
        header.add(ratingBox, c);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitButton.setBackground(secondaryColor);
                submitButton.setForeground(primaryColor);
                saveEntry();
            }
        });
        submitButton.setBorder(new EmptyBorder(20,20,20,20));
        submitButton.setOpaque(true);
        submitButton.setBackground(primaryColor);
        submitButton.setForeground(secondaryColor);
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
        c.fill = GridBagConstraints.VERTICAL;
        header.add(submitButton, c);

        JPanel fillerPanel = new JPanel();
        fillerPanel.setBackground(primaryColor);
        c.gridx = 3;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.BOTH;
        header.add(fillerPanel, c);

        return header;
    }

    private JPanel createEditedEntryHeader(Color primaryColor, Color secondaryColor){
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel editingLabel = new JLabel("If you wish to edit title or date, please do so manually in the journal files.");
        editingLabel.setForeground(secondaryColor);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        header.add(editingLabel, c);

        JLabel editingLabel2 = new JLabel("This is an effort to prevent accidental deletion and corruption of files.");
        editingLabel2.setForeground(secondaryColor);
        c.gridx = 0;
        c.gridy = 1;
        header.add(editingLabel2, c);

        JButton editSubmitButton = new JButton("Save Changes");
        editSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChangesToExistingEntry();
            }
        });
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 2;
        header.add(editSubmitButton, c);

        return header;
    }

    /*
        Testing main
    */
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(500,500);
        frame.setMinimumSize(new Dimension(850, 200));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new EditingPanel(new Color(44,62,79), new Color(255,255,252)));
    }
}
