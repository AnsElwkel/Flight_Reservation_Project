package com.egyptFlightReservation.Model.FileHandling;

import java.io.File;
import java.util.ArrayList;

public class FolderCleaner {

    public static ArrayList<String> getFolderNames(String dirPath) {
        ArrayList<String> folderNames = new ArrayList<>();
        File directory = new File(dirPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null)
                for (File file : files)
                    if (file.isDirectory())
                        folderNames.add(file.getName());
        }

        return folderNames;
    }

    public static void deleteFolder(File folder) { // rec
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files)
                if (file.isDirectory())
                    deleteFolder(file);
                else
                    file.delete();
        }
        folder.delete();
    }

    ///  to delete any folder that removed from database when saving
    public static void cleanFolder(String dirPath , ArrayList<String> nonDeletedFolderNames) {
        ArrayList<String> folderNames = getFolderNames(dirPath);
        for (String folderName : folderNames) {
            if(nonDeletedFolderNames.contains(folderName)) continue;
            File file = new File(dirPath + '/'+ folderName);
            if (file.exists()){
                deleteFolder(file);
//                System.out.println("Deleted "+folderName);
            }
//            else
//                System.out.println("Folder " + folderName + " does not exist");
        }
    }
}
