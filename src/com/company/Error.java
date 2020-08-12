package com.company;

import javax.swing.*;

public class Error extends JFrame {
    public Error(String errorMessage){
        this.setSize(400, 100);
        this.setResizable(false);
        this.add(new JLabel(errorMessage));
        this.setVisible(true);
    }
}
