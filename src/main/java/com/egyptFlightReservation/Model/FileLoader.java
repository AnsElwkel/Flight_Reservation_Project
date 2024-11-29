package com.egyptFlightReservation.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileLoader {
    private static FileLoader loader;

    private FileLoader() {}

    public static FileLoader getLoader() {
        if(loader == null)
            loader = new FileLoader();

        return loader;
    }

    public static ArrayList<String> load(String filePath){
        ArrayList<String> fileContent = new ArrayList<String>();
        try(Scanner fin = new Scanner(new File(filePath))){
            while(fin.hasNextLine()){
                String line = fin.nextLine();
                fileContent.add(line);
            }
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return fileContent;
    }
}
