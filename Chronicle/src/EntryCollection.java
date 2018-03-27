/*
    EntryCollection.java

    Collects entries
*/

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntryCollection {
    /*
        Variables
    */
    private String journalMainDirectory;
    public ArrayList<EntryData> journalEntries;

    /*
        Constructors
    */
    public EntryCollection(String journalDirectory, HashMap<String, Color> colorPackage){
        this.journalMainDirectory = journalDirectory;

        loadEntries(colorPackage);
    }

    /*
        Functions
    */
    private void loadEntries(HashMap<String, Color> colorPackage){
        journalEntries = new ArrayList<EntryData>();

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
                        journalEntries.add(new EntryData(file.getCanonicalPath().toString(), colorPackage));
                    }
                } catch(Exception e) {
                    System.out.println("An error has occured while reading files");
                }
            }
        } else {
            System.out.println("There is no Folder @ given path :" + journalMainDirectory);
        }
    }
}
