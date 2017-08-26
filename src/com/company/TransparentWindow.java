package com.company;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class TransparentWindow extends JFrame {

    public TransparentWindow() {
        // TODO Auto-generated constructor stub
        //JFrame jfrm=new JFrame("Transparent Window");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        getContentPane().setLayout(new FlowLayout());
        //setBackground(new Color(0,0,0,0));

        add(new JButton("Enter"));
        setOpacity(0.7f);
        setVisible(true);
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GraphicsEnvironment ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd=ge.getDefaultScreenDevice();
        if(!gd.isWindowTranslucencySupported(WindowTranslucency.TRANSLUCENT))
        {
            System.out.println("Transparency not supported");
            System.exit(0);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new Runnable(){public void run(){new TransparentWindow();}});
    }

}