/*
    Main.java

    Loads primary objects
*/

import java.awt.*;

public class Main {
    /*
        Variables
    */
    public static String version = "3.0.0";

    //Components
    public static Settings s;
    public static EntryCollection c;
    public static Assembly a;

    /*
        Main
    */
    public static void main(String[] args){
        System.out.println("Loading Settings");
        s = new Settings();
        System.out.println("Loading Entries");
        c = new EntryCollection(s.journalDirectory, s.colorPackage);
        System.out.println("Building UI Assembly");
        a = new Assembly(new Color(42,52,54), new Color(245,246,250));
    }
}
