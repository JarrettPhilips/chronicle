/*
    HelpPanel.java

    Content panel to be loaded into the Assembly frame that displays information to provide guidance to users. Reads in this information from external files.
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.HashMap;

public class HelpPanel extends JPanel{
    /*
        Variables
    */
    Color primaryColor;
    Color secondaryColor;

    JTextArea contentTextArea;

    JButton generalButton;
    JButton markdownButton;
    JButton keybindsButton;
    JButton aboutButton;

    boolean generalButtonPressed = true;
    boolean markdownButtonPressed = false;
    boolean keybindsButtonPressed = false;
    boolean aboutButtonPressed = false;

    /*
        Constructors
    */
    public HelpPanel(HashMap<String, Color> cp){
        primaryColor = cp.get("primaryColor");
        secondaryColor = cp.get("secondaryColor");

        this.setLayout(new GridBagLayout());
        this.setBackground(secondaryColor);
        GridBagConstraints c = new GridBagConstraints();
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        c.gridx= 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        this.add(createHeader(primaryColor, secondaryColor), c);

        contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        contentTextArea.setLineWrap(true);
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setBorder(new EmptyBorder(40, 40, 40, 40));
        contentTextArea.setBackground(secondaryColor);
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        this.add(contentTextArea, c);
        loadTxtToContentPanel("GeneralHelp");
    }

    /*
        Functions
    */
    private JPanel createHeader(Color primaryColor, Color secondaryColor){
        JPanel header = new JPanel();
        header.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        generalButton = new JButton("General");
        generalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                generalButton.setBackground(secondaryColor);
                generalButton.setForeground(primaryColor);
                loadTxtToContentPanel("GeneralHelp");
            }
        });
        generalButton.setBorder(new EmptyBorder(20,20,20,20));
        generalButton.setOpaque(true);
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        header.add(generalButton, c);

        markdownButton = new JButton("Markdown");
        markdownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                markdownButton.setBackground(secondaryColor);
                markdownButton.setForeground(primaryColor);
                loadTxtToContentPanel("MarkdownHelp");
            }
        });
        markdownButton.setBorder(new EmptyBorder(20,20,20,20));
        markdownButton.setOpaque(true);
        c.gridx = 1;
        header.add(markdownButton, c);

        keybindsButton = new JButton("Keybinds");
        keybindsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                keybindsButton.setBackground(secondaryColor);
                keybindsButton.setForeground(primaryColor);
                loadTxtToContentPanel("KeybindsHelp");
            }
        });
        keybindsButton.setBorder(new EmptyBorder(20,20,20,20));
        keybindsButton.setOpaque(true);
        c.gridx = 2;
        header.add(keybindsButton, c);

        aboutButton = new JButton("About");
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButtons();
                aboutButton.setBackground(secondaryColor);
                aboutButton.setForeground(primaryColor);
                loadTxtToContentPanel("About");
            }
        });
        aboutButton.setBorder(new EmptyBorder(20,20,20,20));
        aboutButton.setOpaque(true);
        c.gridx = 3;
        header.add(aboutButton, c);

        JPanel fillerPanel = new JPanel();
        fillerPanel.setBackground(primaryColor);
        c.gridx = 4;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        header.add(fillerPanel, c);

        resetButtons();
        generalButton.setBackground(secondaryColor);
        generalButton.setForeground(primaryColor);
        return header;
    }

    private void resetButtons(){
        generalButtonPressed = false;
        markdownButtonPressed = false;
        keybindsButtonPressed = false;
        aboutButtonPressed = false;

        generalButton.setBackground(primaryColor);
        generalButton.setForeground(secondaryColor);
        markdownButton.setBackground(primaryColor);
        markdownButton.setForeground(secondaryColor);
        keybindsButton.setBackground(primaryColor);
        keybindsButton.setForeground(secondaryColor);
        aboutButton.setBackground(primaryColor);
        aboutButton.setForeground(secondaryColor);
    }

    private void loadTxtToContentPanel(String txtDirectory){
        try(FileReader reader = new FileReader("ChronicleSettings/" + txtDirectory)){
            contentTextArea.read(reader, null);
        } catch (Exception e){
            System.out.println("Error loading help files:");
        }
    }
}
