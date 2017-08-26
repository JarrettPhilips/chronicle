package com.company;

import javax.swing.*;
import java.awt.*;

/**
 Created by jarrettphilips on 2/3/17.
 */
public class CustomFrame extends JFrame{
    public CustomFrame(){
        /*try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch(Exception e){

        }*/
        this.setUndecorated(true);
        this.setBackground(new Color(255, 255, 255, 180));
        this.setVisible(true);
        this.setSize(400, 400);
    }

    public static void main(String args[]){
        CustomFrame cf = new CustomFrame();
    }
}
