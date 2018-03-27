/*
    Settings.java

    Collects settings from an external file
*/

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Settings {
    /*
        Variables
    */
    //Settings
    public String journalDirectory;
    private Color primaryColor;
    private Color secondaryColor;
    private Color highColor;
    private Color lowColor;
    private Color medianColor;

    public HashMap<String, Color> colorPackage = new HashMap<String, Color>();

    //Class Variables
    private File file;

    /*
        Constructors
    */
    public Settings(){
        try {
            this.file = new File("ChronicleSettings/Settings");

            Scanner s = new Scanner(file);
            int r, g, b, index;
            String line;

            //Directory of Journals
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            journalDirectory = line.substring(index);
            System.out.println(journalDirectory);

            //Primary Color
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            r = Integer.parseInt(line.substring(index, index + 3));
            g = Integer.parseInt(line.substring(index + 4, index + 7));
            b = Integer.parseInt(line.substring(index + 8, index + 11));
            primaryColor = new Color(r, g, b);
            System.out.println(primaryColor);

            //Secondary Color
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            r = Integer.parseInt(line.substring(index, index + 3));
            g = Integer.parseInt(line.substring(index + 4, index + 7));
            b = Integer.parseInt(line.substring(index + 8, index + 11));
            secondaryColor = new Color(r, g, b);
            System.out.println(secondaryColor);

            //High Color
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            r = Integer.parseInt(line.substring(index, index + 3));
            g = Integer.parseInt(line.substring(index + 4, index + 7));
            b = Integer.parseInt(line.substring(index + 8, index + 11));
            highColor = new Color(r, g, b);
            System.out.println(highColor);

            //Low Color
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            r = Integer.parseInt(line.substring(index, index + 3));
            g = Integer.parseInt(line.substring(index + 4, index + 7));
            b = Integer.parseInt(line.substring(index + 8, index + 11));
            lowColor = new Color(r, g, b);
            System.out.println(lowColor);

            //Median Color
            line = s.nextLine();
            index = line.indexOf('=') + 1;
            r = Integer.parseInt(line.substring(index, index + 3));
            g = Integer.parseInt(line.substring(index + 4, index + 7));
            b = Integer.parseInt(line.substring(index + 8, index + 11));
            medianColor = new Color(r, g, b);
            System.out.println(medianColor);

        } catch(Exception e){
            System.out.println("Error thrown during settings load");
            generateSettingsFile();
        }

        //Creates the color package
        colorPackage.put("primaryColor", primaryColor);
        colorPackage.put("secondaryColor", secondaryColor);
        colorPackage.put("highColor", highColor);
        colorPackage.put("lowColor", lowColor);
        colorPackage.put("medianColor", medianColor);
    }

    /*
        Functions
    */

    private void generateSettingsFile(){

    }

    /*
        Testing Main
    */
    public static void main(String[] args){
        Settings s = new Settings();
    }
}
