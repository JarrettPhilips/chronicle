package com.company;

//import com.apple.eawt.Application;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UI2 extends JFrame{
    /*
        Variables
    */
    private Color primaryColor;
    private Color secondaryColor;
    private Color highColor;
    private Color lowColor;
    private Color medianColor;

    private Font buttonFont = new Font("Geneva", Font.PLAIN, 25);
    private Font titleFont = new Font("Futura", Font.BOLD, 40);
    private Font selectionFont = new Font("Palatino", Font.PLAIN, 14);

    private Dimension minimumSize = new Dimension(950, 500);

    private JPanel contentPanel;

    private JScrollPane selectionMenu;

    private LoadingFrame loadingFrame;

    private JButton newEntryButton;
    private JButton editEntryButton;
    private JButton refreshEntryButton;
    private JButton helpButton;

    private boolean newEntryButtonPressed = false;
    private boolean editEntryButtonPressed = false;
    private boolean refreshEntryButtonPressed = false;
    private boolean helpButtonPressed = false;

    private String currentEntryDirectory = "";

    /*
        Constructors
    */
    public UI2(Color pc, Color sc, Color hc, Color lc, Color mc){
        this.primaryColor = pc;
        this.secondaryColor = sc;
        this.highColor = hc;
        this.lowColor = lc;
        this.medianColor = mc;

        loadingFrame = new LoadingFrame(primaryColor, secondaryColor, selectionFont);

        try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            ImageIcon img = new ImageIcon("Icon.png");
            this.setIconImage(img.getImage());

            //Application application = Application.getApplication();
            Image image = Toolkit.getDefaultToolkit().getImage("ChronicleSettings/Icon64.png");
            //application.setDockIconImage(image);
        } catch(Exception e){
            System.out.println("Error setting jar icon");
        }

        loadingFrame.displayMessage("Setting up frame");
        this.setMinimumSize(minimumSize);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setBackground(secondaryColor);
        this.setTitle("Chronicle " + Main.version);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel sideMenu = createSideMenu();
        selectionMenu = createSelectionMenu();
        loadingFrame.displayMessage("Rendering");
        contentPanel = createContentPanel();

        c.gridx = 0;
        c.gridy = 0;
        this.add(sideMenu, c);
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        this.add(selectionMenu, c);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        this.add(contentPanel, c);

        //Adds a placeholder to content pane until another action is placed
        contentPanel.add(createPlaceHolderPanel(), c);
        loadingFrame.displayMessage("Done!");
        loadingFrame.destroyFrame();
        this.setVisible(true);
    }

    /*
        Functions
    */
    public void reloadSelectionMenu(){
        selectionMenu.removeAll();
        selectionMenu = createSelectionMenu();
        selectionMenu.updateUI();
        this.update(getGraphics());
    }

    public void setContentPanelToDefault(){
        contentPanel.removeAll();
        contentPanel.add(createPlaceHolderPanel());
        contentPanel.updateUI();
    }

    private void reloadEntry(){

    }

    private JScrollPane createSelectionMenu(){ //Contains lots of legacy code... good luck <3
        JPanel selectionPanelOverLay = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        //This block gathers and organizes entries
        ArrayList<LMLEntryPanel> unsortedList = new ArrayList<LMLEntryPanel>();
        for(int i = 0; i < Main.c.journalEntries.size(); i ++) {
            String entryDirectory = Main.c.journalEntries.get(i).directory;
            LMLEntryPanel e = new LMLEntryPanel(entryDirectory, primaryColor, secondaryColor, highColor, lowColor, medianColor); //This may cause a memory issue later...
            unsortedList.add(e);
        }
        final ArrayList<LMLEntryPanel> entryList = Sort.sortEntryList(unsortedList);

        //This block prepares to create the JButton panel
        ArrayList<JButton> buttonList = new ArrayList<JButton>();
        selectionPanelOverLay.setBackground(secondaryColor);
        selectionPanelOverLay.setLayout(new GridBagLayout());
        c.ipady = 20;
        c.ipadx = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c = new GridBagConstraints();
        c.gridy = 1;

        //This loop creates a JButton for each entry
        for(int i = 0; i < entryList.size(); i ++){
            loadingFrame.displayMessage("Loading " + i + " entries");
            final int index = i;

            JButton b = new JButton(entryList.get(i).getDateString());
            b.setFont(selectionFont);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setBorder(new EmptyBorder(0, 10, 0, 10));
            b.setBackground(entryList.get(index).getEntryColor());
            b.setForeground(primaryColor);
            b.setOpaque(true);

            b.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    resetSideMenu();

                    try{
                        GridBagConstraints c = new GridBagConstraints();
                        contentPanel.removeAll();
                        c.fill = GridBagConstraints.BOTH;
                        c.weightx = 1.0;
                        c.weighty = 1.0;
                        contentPanel.add(entryList.get(index), c); //If it does, just have this line create a new entry instead of using a list
                        currentEntryDirectory = entryList.get(index).getTxtDirectory();
                        contentPanel.updateUI();

                        int j = 0;
                        for(JButton bl: buttonList){
                            bl.setBackground(entryList.get(j).getEntryColor());
                            bl.setForeground(primaryColor);
                            j ++;
                        }
                        b.setBackground(primaryColor);
                        b.setForeground(secondaryColor);
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

        //The rest of this function is just formatting and aesthetic choice
        JScrollPane selectionPanel = new JScrollPane(selectionPanelOverLay);
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

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;

        return selectionPanel;
    }

    private void addButtonToSelectionMenu(){

    }

    private JPanel createSideMenu(){
        JPanel sideMenuPanel = new JPanel();
        sideMenuPanel.setBackground(primaryColor);
        sideMenuPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        JLabel titleLabel = new JLabel("Chronicle");
        titleLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        titleLabel.setForeground(secondaryColor);
        titleLabel.setFont(titleFont);
        c.gridwidth = 4;
        sideMenuPanel.add(titleLabel, c);
        c.gridy = 1;
        c.gridwidth = 1;

        newEntryButton = new JButton("+");
        newEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(newEntryButtonPressed){
                    resetSideMenu();
                    newEntryButtonPressed = false;
                    newEntryButton.setBackground(primaryColor);
                    newEntryButton.setForeground(secondaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(createPlaceHolderPanel(), c);
                    contentPanel.updateUI();
                } else {
                    resetSideMenu();
                    newEntryButtonPressed = true;
                    newEntryButton.setBackground(secondaryColor);
                    newEntryButton.setForeground(primaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(new EditingPanel(primaryColor, secondaryColor), c);
                    contentPanel.updateUI();
                }
            }
        } );
        newEntryButton.setFont(buttonFont);
        newEntryButton.setForeground(secondaryColor);
        newEntryButton.setBackground(primaryColor);
        newEntryButton.setBorder(new EmptyBorder(0, 10, 0, 5));
        newEntryButton.setOpaque(true);
        sideMenuPanel.add(newEntryButton, c);
        c.gridx = 1;

        editEntryButton = new JButton("✎");
        editEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(editEntryButtonPressed){
                    resetSideMenu();
                    editEntryButtonPressed = false;
                    editEntryButton.setBackground(primaryColor);
                    editEntryButton.setForeground(secondaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(createPlaceHolderPanel(), c);
                    contentPanel.updateUI();
                } else {
                    resetSideMenu();
                    editEntryButtonPressed = true;
                    editEntryButton.setBackground(secondaryColor);
                    editEntryButton.setForeground(primaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(new EditingPanel(primaryColor, secondaryColor, currentEntryDirectory), c);
                    contentPanel.updateUI();
                }
            }
        } );
        editEntryButton.setFont(buttonFont);
        editEntryButton.setForeground(secondaryColor);
        editEntryButton.setBackground(primaryColor);
        editEntryButton.setBorder(new EmptyBorder(0, 5, 0, 5));
        editEntryButton.setOpaque(true);
        sideMenuPanel.add(editEntryButton, c);
        c.gridx = 2;

        refreshEntryButton = new JButton("↻");
        refreshEntryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                reloadEntry();
            }
        } );
        refreshEntryButton.setFont(buttonFont);
        refreshEntryButton.setForeground(secondaryColor);
        refreshEntryButton.setBackground(primaryColor);
        refreshEntryButton.setBorder(new EmptyBorder(0, 5, 0, 5));
        refreshEntryButton.setOpaque(true);
        sideMenuPanel.add(refreshEntryButton, c);
        c.gridx = 3;

        helpButton = new JButton("H");
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints c = new GridBagConstraints();
                c.weighty = 1.0;
                c.weightx = 1.0;
                c.fill = GridBagConstraints.BOTH;

                if(helpButtonPressed){
                    resetSideMenu();
                    helpButtonPressed = false;
                    helpButton.setBackground(primaryColor);
                    helpButton.setForeground(secondaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(createPlaceHolderPanel(), c);
                    contentPanel.updateUI();
                } else {
                    resetSideMenu();
                    helpButtonPressed = true;
                    helpButton.setBackground(secondaryColor);
                    helpButton.setForeground(primaryColor);
                    contentPanel.removeAll();
                    contentPanel.add(new HelpPanel(primaryColor, secondaryColor), c);
                    contentPanel.updateUI();
                }
            }
        } );
        helpButton.setFont(buttonFont);
        helpButton.setForeground(secondaryColor);
        helpButton.setBackground(primaryColor);
        helpButton.setBorder(new EmptyBorder(0, 5, 0, 10));
        helpButton.setOpaque(true);
        sideMenuPanel.add(helpButton, c);

        return sideMenuPanel;
    }

    private void resetSideMenu(){
        newEntryButtonPressed = false;
        editEntryButtonPressed = false;
        refreshEntryButtonPressed = false;
        helpButtonPressed = false;

        newEntryButton.setBackground(primaryColor);
        newEntryButton.setForeground(secondaryColor);
        editEntryButton.setBackground(primaryColor);
        editEntryButton.setForeground(secondaryColor);
        refreshEntryButton.setBackground(primaryColor);
        refreshEntryButton.setForeground(secondaryColor);
        helpButton.setBackground(primaryColor);
        helpButton.setForeground(secondaryColor);
    }

    private JPanel createPlaceHolderPanel(){
        JPanel placeHolderPanel = new JPanel(new BorderLayout());
        placeHolderPanel.setBackground(secondaryColor);
        ImageIcon image = new ImageIcon(CustomIcon.getCustomChronicleIconBufferedImage(primaryColor, secondaryColor, 256));
        JLabel label = new JLabel("", image, JLabel.CENTER);
        label.setMaximumSize(new Dimension(64, 64));
        placeHolderPanel.add(label, BorderLayout.CENTER );
        return placeHolderPanel;
    }

    private JPanel createContentPanel(){
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        return contentPanel;
    }

}

