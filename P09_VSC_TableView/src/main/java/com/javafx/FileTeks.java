package com.javafx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileTeks {
    private String file_name;
    
    FileTeks(String file){
        this.file_name = file;
    }

    public void write(String text) throws IOException{
        try {
            FileWriter fileOutput = new FileWriter(file_name);
            fileOutput.write(text);
            fileOutput.close();
        } catch (IOException e) {
            System.err.println("An error occured. " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String[] read() throws IOException{
        String result = "";
        try {
            File file = new File(file_name);
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                result += fileReader.nextLine() + "\n";
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error occured. " + e.getMessage());
            e.printStackTrace();
        }
        return result.split("\n");
    }

    public String getFileName(){
        return file_name;
    }
    public void setFileName(String newFileName){
        this.file_name = newFileName;
    }
}
