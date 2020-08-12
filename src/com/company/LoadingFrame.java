package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class LoadingFrame extends JFrame{
    /*
        Variables
    */
    Dimension size = new Dimension(300, 100);

    JLabel label;

    /*
        Constructors
    */
    public LoadingFrame(Color primaryColor, Color secondaryColor, Font font){
        this.setTitle("Loading Chronicle");
        this.setSize(size);
        this.setResizable(false);
        //this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        JPanel panel = new JPanel();
        panel.setBackground(primaryColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        label = new JLabel();
        label.setBackground(primaryColor);
        label.setForeground(secondaryColor);
        label.setFont(font);
        c.fill = GridBagConstraints.BOTH;
        panel.add(label, c);
        this.add(panel);

        this.setVisible(true);
    }

    /*
        Functions
    */
    public void displayMessage(String message){
        label.setText(message);
        label.updateUI();
    }

    public void destroyFrame(){
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
}
