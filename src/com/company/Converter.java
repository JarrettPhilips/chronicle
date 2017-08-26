package com.company;

import java.io.File;
import java.util.ArrayList;

public class Converter{
    /*
        Variables
    */
    private String journalMainDirectory;

    public ArrayList<Entry> journalEntries;

    /*
        Constructors
    */
    public Converter(String directory){
        this.journalMainDirectory = directory;

        createList();
        sortList();
    }

    /*
        Functions
    */
    private void createList(){
        journalEntries = new ArrayList<Entry>();

        File folder = new File(journalMainDirectory);

        if (folder.isDirectory()) {
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles.length < 1)
                System.out.println("There is no File inside Folder");
            else
                System.out.println("List of Files & Folders");

            for (File file : listOfFiles) {
                try {
                    if(!file.getName().equals(".DS_Store")){
                        System.out.println(file.getCanonicalPath().toString());
                        journalEntries.add(new Entry(file.getCanonicalPath().toString()));
                    }
                } catch(Exception e) {
                    System.out.println("An error has occured while reading files");
                }
            }
        } else {
            System.out.println("There is no Folder @ given path :" + journalMainDirectory);
        }
    }

    private void sortList(){

    }

    /*
        Testing Main
    */
    public static void main(String args[]){
        System.out.println("Initiating testing main for converter");
        String d = "/Users/jarrettphilips/desktop/Journals";

        Converter c = new Converter(d);
    }
}
