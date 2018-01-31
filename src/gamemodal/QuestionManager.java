/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamemodal;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
/**
 *
 * @author student
 */
public class QuestionManager{
    private ArrayList<String> categories;
    private final ArrayList<ArrayList<String>> questions;
    private final ArrayList<ArrayList<Integer>> weights;
    private final ArrayList<ArrayList<String>> answers;
    
    public QuestionManager(){
        categories = new ArrayList();
        weights = new ArrayList();
        questions = new ArrayList();
        answers = new ArrayList();
    }

    public void start(String filename) throws FileNotFoundException, IOException{
        QuestionReader gameReader = new QuestionReader(filename);
        categories = gameReader.readCategories();
        
        for(int i = 0; i < categories.size(); i++){
            questions.add(gameReader.readQAs(categories.get(i), 'q'));
            answers.add(gameReader.readQAs(categories.get(i), 'a'));
            
            weights.add(new ArrayList());
            
            int j = 0;

            for(; j < questions.get(i).size(); j++){
                Scanner weightScanner = new Scanner(questions.get(i).get(j));
                weights.get(i).add(weightScanner.nextInt());
                questions.get(i).set(j, weightScanner.nextLine()); 
            }
        } 
    }
    
    public void end(){
        categories.clear();
        questions.clear();
        weights.clear();
        answers.clear();
    }
    
    public String getCategory(int index){
        return categories.get(index);
    }
    
    public int getWeight(int categoryIndex, int questionIndex){
        return weights.get(categoryIndex).get(questionIndex);
    }
    
    public String getQuestion(int categoryIndex, int questionIndex){
        return questions.get(categoryIndex).get(questionIndex);
    }
    
    public String getAnswer(int categoryIndex, int questionIndex){
        return answers.get(categoryIndex).get(questionIndex);
    }
    
    public int getNumberOfCategories(){
        return categories.size();
    }
    
    public int getNumberOfQuestions(int categoryIndex){
        return this.questions.get(categoryIndex).size();
    }
}
