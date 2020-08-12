package com.company;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class CustomIcon{ //Creates a Chronicle Icon using the custom colors the user sets
    /*
        Variables
    */
    static boolean[][] booleanIcon = {//There is certainly a way better way to do this, but it's super late....
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, false, false, false, true, true, true, true, false, false, true, false, false, false},
            {false, false, false, false, true, true, true, true, true, true, true, true, true, false, false, false},
            {false, false, false, true, true, true, false, false, false, false, true, true, true, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, true, true, true, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, true, true, true, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, true, true, true, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, true, true, true, false, false, true, true, false, false, false, false, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false},
            {false, false, false, true, true, true, false, false, false, false, true, true, true, false, false, false},
            {false, false, false, false, true, true, true, true, true, true, true, true, true, false, false, false},
            {false, false, false, false, false, false, true, true, true, true, false, false, true, false, false, false},
            {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}
    };

    /*
        Functions
    */
    public static BufferedImage getCustomChronicleIconBufferedImage(Color primaryColor, Color secondaryColor, int resolution){
        //Creates custom buffered image
        BufferedImage bi = new BufferedImage(resolution, resolution, BufferedImage.TYPE_INT_RGB);
        int divisor = resolution / 16;

        for(int i = 0; i < resolution; i ++){
            for(int j = 0; j < resolution; j ++){
                if(booleanIcon[i / divisor][j / divisor]){
                    bi.setRGB(j, i, secondaryColor.getRGB());
                } else {
                    bi.setRGB(j, i, primaryColor.getRGB());
                }
            }
        }

        return bi;
    }
}
