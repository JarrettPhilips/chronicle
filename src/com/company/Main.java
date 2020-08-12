package com.company;

public class Main {
    /*
        Variables
    */
    //Basic variables
    public static String version = "2.0.0";
    public static String journalMainDirectory;

    //Driving Components
    public static UI2 ui2;
    public static Converter c;
    public static Settings s;

    /*
        Functions
    */

    /*
        Main
    */
    public static void main(String[] args){
        s = new Settings();
        journalMainDirectory = s.journalDirectory;
        c = new Converter(journalMainDirectory);
        ui2 = new UI2(s.primaryColor, s.secondaryColor, s.highColor, s.lowColor, s.medianColor);
    }
}
