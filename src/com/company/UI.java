package com.company;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.util.ArrayList;

public class UI implements ActionListener{
    /*
        Variables
    */
    //Dimensions
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    //Colors
    Color buttonUnpressed = new Color(255, 255, 255);
    Color buttonUnpressedForeground = new Color(0, 0, 0);
    Color buttonPressed = new Color(255, 122, 14);
    Color textBackground = new Color(255, 255, 255);
    Color selectionBackground = new Color(240, 240, 240);
    Color splitBackground = new Color(50, 50, 50);

    //Fonts
    Font titleFont = new Font("Lucida Sans Unicode", Font.BOLD, 24);

    //Frames
    JFrame window;

    //Panels
    JSplitPane masterPanel;
    JPanel textPanel;
    JScrollPane selectionPanel;
    JPanel selectionPanelOverLay = new JPanel();
    JScrollPane textScrollPane;
    JPanel titlePanel;

    //Text
    JTextArea textArea;

    //Buttons
    ArrayList<JButton> buttonList;

    //Labels
    JLabel selectionLabel;
    JLabel titleLabel;
    JLabel dateLabel;

    //Constraints
    private GridBagConstraints c = new GridBagConstraints();

    /*
        Constructors
    */
    public UI(){
        setUp();
    }

    /*
        Functions
    */
    private void setUp(){

        setUpSelectionPanel();
        setUpTextPanel();
        setUpWindow();

        window.validate();
    }

    private void setUpWindow(){
        window = new JFrame("Chronicle " + Main.version);
        window.setVisible(true);
        window.setSize(800, 600);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocation(screenSize.width/2-window.getSize().width/2, screenSize.height/2-window.getSize().height/2);

        masterPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, selectionPanel, textPanel);
        masterPanel.setOneTouchExpandable(true);
        masterPanel.setDividerLocation(150);
        //masterPanel.setLayout(new GridBagLayout());
        masterPanel.setBackground(splitBackground);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        masterPanel.setBorder(emptyBorder);

        window.add(masterPanel);
    }

    private void setUpSelectionPanel(){
        resetc();
        buttonList = new ArrayList<JButton>();
        selectionPanelOverLay.setBackground(selectionBackground);
        selectionPanelOverLay.setLayout(new GridBagLayout());
        selectionLabel = new JLabel("Entries", SwingConstants.CENTER);
        selectionLabel.setBackground(splitBackground);
        selectionLabel.setForeground(buttonUnpressed);
        selectionLabel.setOpaque(true);
        c.ipady = 20;
        c.ipadx = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        selectionPanelOverLay.add(selectionLabel, c);
        resetc();
        c.gridy = 1;

        for(int i = 0; i < Main.c.journalEntries.size(); i ++){
            JButton b = new JButton(Main.c.journalEntries.get(i).dateString);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setBorder(new EmptyBorder(0, 10, 0, 10));
            b.setBackground(buttonUnpressed);
            b.setOpaque(true);
            String txtDirectory = Main.c.journalEntries.get(i).txtDirectory;
            String title = Main.c.journalEntries.get(i).title;
            String dateString = Main.c.journalEntries.get(i).dateString;

            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    try (FileReader reader = new FileReader(txtDirectory)){
                        textArea.read(reader, null);
                        titleLabel.setText(title);
                        dateLabel.setText(dateString);

                        for (JButton bl: buttonList) {
                            bl.setBackground(buttonUnpressed);
                            bl.setForeground(buttonUnpressedForeground);
                        }
                        b.setBackground(buttonPressed);
                        b.setForeground(buttonUnpressed);
                    } catch(Exception r){
                        System.out.println("Error in loading file contents");
                    }
                }
            });
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1;
            c.ipadx = 10;
            c.ipady = 20;
            c.gridy ++;
            buttonList.add(b);
            selectionPanelOverLay.add(b, c);
        }

        selectionPanel = new JScrollPane(selectionPanelOverLay);
        selectionPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        selectionPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        selectionPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        selectionPanelOverLay.setBorder(new EmptyBorder(0, 0, 0, 0));
        selectionPanel.validate();
        selectionPanelOverLay.validate();

        //Adds a blank label to push buttons into correct position
        JLabel l = new JLabel();
        c.gridy ++;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        selectionPanelOverLay.add(l, c);

        resetc();
        c.fill = GridBagConstraints.VERTICAL;
        //masterPanel.add(selectionPanel, c);
    }

    private void setUpTextPanel(){
        resetc();
        textPanel = new JPanel();
        textPanel.setBackground(buttonUnpressed);
        textPanel.setLayout(new GridBagLayout());
        textPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        setUpTitlePanel();
        resetc();
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText("Testing Text");
        textArea.setBackground(textBackground);
        textScrollPane = new JScrollPane();
        resetc();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setVisible(true);
        textScrollPane = new JScrollPane(textArea);
        textScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textScrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        Border emptyBorder = new EmptyBorder(30, 30, 30, 30);
        textArea.setBorder(emptyBorder);
        c.gridy = 1;
        textPanel.add(textScrollPane, c);

        resetc();
        c.gridx = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        //masterPanel.add(textPanel, c);
    }

    private void setUpTitlePanel(){
        resetc();
        titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        titlePanel.setBorder(new MatteBorder(0, 0, 2, 0, splitBackground));
        titleLabel = new JLabel("Test Title");
        titleLabel.setBorder(new EmptyBorder(10, 10, 0, 10));
        titleLabel.setOpaque(true);
        titleLabel.setFont(titleFont);
        titleLabel.setBackground(buttonUnpressed);
        dateLabel = new JLabel("Test date");
        dateLabel.setBorder(new EmptyBorder(2, 10, 10, 10));
        dateLabel.setOpaque(true);
        dateLabel.setBackground(buttonUnpressed);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        titlePanel.add(titleLabel, c);
        c.gridy ++;
        titlePanel.add(dateLabel, c);
        c.gridy ++;

        resetc();
        textPanel.add(titlePanel, c);
    }

    private void resetc(){
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 0;
        c.insets = new Insets(0, 0, 0, 0);
        c.anchor = GridBagConstraints.LINE_START;
    }

    public void actionPerformed(ActionEvent e) {

    }
}
