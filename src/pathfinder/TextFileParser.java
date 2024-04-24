/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/*

P.A.A.O Perera
20220670

*/
package w1998779;

/**
 *
 * @author DELL
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;
public class TextFileParser {

    private int rows;
    private int columns;
    private int startY;
    private int startX;
    private int endY;
    private int endX;
    private String[][] block;

    public int getStartY() {
        return startY;
    }

    public int getStartX() {
        return startX;
    }

    public int getEndY() {
        return endY;
    }

    public int getEndX() {
        return endX;
    }

    public String[][] getblock(){
        return block;
    }

    // The method to retrieve the number of rows and columns of the data structure and store the data in a 2D array.
    public void fileReader(){
        try {
            String filePath = "C:\\Users\\DELL\\OneDrive\\Documents\\NetBeansProjects\\w1998779_P.A.A.O Perera (20220670)\\src\\w1998779\\Input_Files\\benchmark_series\\benchmark_series\\puzzle_10.txt";
            rows = Files.readAllLines(Paths.get(filePath)).size();
            columns = Files.readAllLines(Paths.get(filePath)).get(0).length();
        }
        catch (IOException ignored){
            System.out.println("File not found");
        }

        block = new String[rows][columns];
        try {

            String filePath = "C:\\Users\\DELL\\OneDrive\\Documents\\NetBeansProjects\\w1998779_P.A.A.O Perera (20220670)\\src\\w1998779\\Input_Files\\benchmark_series\\benchmark_series\\puzzle_10.txt";
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int x =-1;
            while ((line = bufferedReader.readLine()) != null) {
                x+=1;
                
                for (int y = 0; y < columns; y++){
                    block[x][y] = String.valueOf(line.charAt(y));

                    if (String.valueOf(line.charAt(y)).equals("S")){  
                        startY = y;
                        startX = x;
                    }
                    if (String.valueOf(line.charAt(y)).equals("F")){      
                        endY = y;
                        endX = x;
                    }
                }

            }
        }

        catch (IOException e)
        {
        }
    }

    public static void main(String[] args) {

        TextFileParser parser = new TextFileParser();
        parser.fileReader();

        Algorithm IceSlider = new Algorithm();
        IceSlider.findPath(parser.getblock(),parser.getStartY(), parser.getStartX(), parser.getEndY(), parser.getEndX());
        
        System.out.println("    ");
        
    }
}
