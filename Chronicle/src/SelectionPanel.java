/*
    SelectionPanel.java

    Content panel that displays a list of entries. Alows the user to browse and select them
*/

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectionPanel extends JScrollPane {
    /*
        Variables
    */
    private HashMap<String, Color> colorPackage;
    private ArrayList<EntryButton> entryButtons = new ArrayList<EntryButton>();

    /*
        Constructors
    */
    public SelectionPanel(HashMap<String, Color> cp, EntryCollection entryCollection){
        this.colorPackage = cp;
        this.setPreferredSize(new Dimension(300, 400));
        JPanel overlayPanel = new JPanel();
        overlayPanel.setLayout(new GridBagLayout());
        overlayPanel.setBackground(new Color(0, 255, 255));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        for(int i = 0; i < entryCollection.journalEntries.size(); i ++){
            EntryButton entryButton = new EntryButton(colorPackage, entryCollection.journalEntries.get(i));
            c.gridy ++;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            overlayPanel.add(entryButton, c);
            entryButtons.add(entryButton);
        }

        //JScrollPane selectionPanel = new JScrollPane(overlayPanel);
        this.setViewportView(overlayPanel);
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.setBorder(new EmptyBorder(0, 0, 0, 0));
        overlayPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.validate();
        overlayPanel.validate();
    }

    /*
        Functions
    */
}
