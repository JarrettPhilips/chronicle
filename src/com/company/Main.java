package com.company;

public class Main {
    /*
        Variables
    */
    //Basic variables
    public static String version = "Indev 1.0.0";
    public static String journalMainDirectory = "/Users/jarrettphilips/dropbox/chronicle/journals";

    //Driving Components
    public static UI ui;
    public static Converter c;

    /*
        Functions
    */

    /*
        Main
    */
    public static void main(String[] args) {
        c = new Converter(journalMainDirectory);

        ui = new UI();
    }
}
