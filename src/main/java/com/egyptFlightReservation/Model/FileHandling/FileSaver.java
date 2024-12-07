package com.egyptFlightReservation.Model.FileHandling;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

// Handle Singleton DP
public class FileSaver {
    private static FileSaver saver;

    private FileSaver() { }

    public static FileSaver getSaver(){
        if(saver == null)
            saver = new FileSaver();

        return saver;
    }

    public static void save(String filePath , String[] content) {
        try(FileWriter fout = new FileWriter(filePath)){
            for(int i = 0; i < content.length; i++)
                fout.write(content[i] + "\n");
        }catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }catch(IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("File saved !!"); // To trace only
    }

}
