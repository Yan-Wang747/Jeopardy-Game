/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamecontroller;

/**
 *
 * @author iqapp
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionReader {
    private final File questionFile;

    public QuestionReader(String fileName) throws FileNotFoundException{  
        questionFile = new File(fileName);
    }
    
    public ArrayList<String> readCategories() throws IOException{
        ArrayList<String> result = new ArrayList();
        String labelToLookFor = "/categories";
        
        BufferedReader categorySource = new BufferedReader(new FileReader(questionFile));
        String currLine = categorySource.readLine();
        boolean labelFound = false;

        while(currLine != null){
            currLine = currLine.trim();

            Scanner cateScanner = new Scanner(currLine);
            String label = "";
            if (!labelFound && cateScanner.hasNext()){
                label = cateScanner.next();

                if (label.equals(labelToLookFor))
                    labelFound = true;
            }

            if (labelFound){
                labelFound = true;

                while(cateScanner.hasNext()){
                    String category = cateScanner.next();

                    if (category.charAt(category.length() - 1) == '/'){
                        category = category.substring(0, category.length() - 1);
                        labelFound = false;
                    }
                    if(category.length() > 0)
                        result.add(category);
                }
            }

            currLine = categorySource.readLine();
        }
        
        categorySource.close();
        
        return result;
    }
    
    public ArrayList<String> readQAs(String category, char postfix) throws IOException{
        String labelToLookFor = '/' + category + postfix;
        ArrayList<String> result = new ArrayList<>();
        
        try (BufferedReader qaSource = new BufferedReader(new FileReader(questionFile))) {
            String currLine = qaSource.readLine();
            boolean labelFound = false;
            String qa = "";
            
            while(currLine != null){
                currLine = currLine.trim();
                
                Scanner qaScanner = new Scanner(currLine);
                String label = "";
                
                if (!labelFound && qaScanner.hasNext()){
                    label = qaScanner.next();
                    currLine = currLine.substring(label.length()).trim();
                    if (label.equals(labelToLookFor))
                        labelFound = true;
                }
                
                if (currLine.length() > 0 && labelFound){
                    labelFound = true;
                    
                    if(qa.isEmpty())
                        qa += currLine;
                    else
                        qa += (' ' + currLine);
                    
                    if (qa.charAt(qa.length() - 1) == '/'){
                        labelFound = false;
                        qa = qa.substring(0, qa.length() - 1);
                        result.add(qa);
                        qa = "";
                    }
                }
                
                currLine = qaSource.readLine();
            }
        }
        
        return result;
    }   
}
