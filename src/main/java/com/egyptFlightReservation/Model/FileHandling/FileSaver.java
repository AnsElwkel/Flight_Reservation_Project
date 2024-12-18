package com.egyptFlightReservation.Model.FileHandling;

import java.io.*;
import java.util.ArrayList;

// Handle Singleton DP
public class FileSaver {
    public static void save(String filePath, ArrayList<String> lines) {
        if(lines == null || lines.size() == 0)
            return;
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            if (!file.exists()) {
                file.getParentFile().mkdirs();///testing
                file.createNewFile();
//                System.out.println("new file created " + filePath); ///testing
            }

            for (int i = 0; i < lines.size(); i++) {
                if (i != 0) writer.newLine();
                writer.write(lines.get(i));
            }
//            System.out.println("WRITE IS DONE"); ///testing
        } catch (IOException e) {
//            System.err.println("FILE SAVER ERROR file: " + e.getMessage() + "AT SAVE");
        }
    }

    public static void testDir(String dirPath) { /// make dir if not found
        File dir = new File(dirPath);

        if (dir.exists()) {
            if (dir.isDirectory())
                System.out.println("Directory already exists: " + dirPath);
            else
                System.out.println("this is a file Path not dir path" + dirPath); /// testing
//
        } else {
            if (dir.mkdirs()) {
                System.out.println("Directory did not exist. It has been created at: " + dirPath);
            } else {
                System.out.println("Failed to create the dir at: " + dirPath);
            }
        }
    }
}
//  /home/anaselwkel/IdeaProjects/Flight_Reservation_Project/src/Files/Airline

