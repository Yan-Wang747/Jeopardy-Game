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
import exception.*;
import java.io.*;
import java.util.*;

class QuestionManager{
    private ArrayList<String> categories;
    private final ArrayList<ArrayList<String>> questions;
    private final ArrayList<ArrayList<Integer>> weights;
    private final ArrayList<ArrayList<String>> answers;
    private final ArrayList<Integer> doubleCategoryIndex;
    private final ArrayList<Integer> doubleQuestionIndex;
    
    public QuestionManager(){
        categories = new ArrayList();
        weights = new ArrayList();
        questions = new ArrayList();
        answers = new ArrayList();
        doubleCategoryIndex = new ArrayList();
        doubleQuestionIndex = new ArrayList();
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
        
        Random rnd = new Random();
        doubleCategoryIndex.add(rnd.nextInt(categories.size()));
        doubleCategoryIndex.add(rnd.nextInt(categories.size()));
        doubleQuestionIndex.add(rnd.nextInt(questions.get(doubleCategoryIndex.get(0)).size()));
        doubleQuestionIndex.add(rnd.nextInt(questions.get(doubleCategoryIndex.get(1)).size()));
    }
    
    public void end(){
        categories.clear();
        questions.clear();
        weights.clear();
        answers.clear();
        doubleCategoryIndex.clear();
        doubleQuestionIndex.clear();
    }
    
    public String getCategory(int index){
        return categories.get(index);
    }
    
    public int getWeight(int categoryIndex, int questionIndex){
        return weights.get(categoryIndex).get(questionIndex);
    }
    
    public void setWeight(int categoryIndex, int questionIndex, int newWeight){
        this.weights.get(categoryIndex).set(questionIndex, newWeight);
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
    
    public boolean isDoubleJeopardy(int categoryIndex, int questionIndex){
        return doubleCategoryIndex.contains(categoryIndex) && doubleQuestionIndex.contains(questionIndex);
    }
}

class PlayerManager extends Observable{
    private final ArrayList<Player> allPlayers;
    private final ArrayList<Player> players;
    private int answeringPlayerIndex;
    private final ArrayList<Character> forbiddenKeys;
    
    public PlayerManager(){
        allPlayers = new ArrayList();
        players = new ArrayList();
        forbiddenKeys = new ArrayList<>();
    }
    
    public void start() throws NotEnoughPlayersException{
        if(players.size() <= 1){
            throw new NotEnoughPlayersException();
        }
        
        Random rnd = new Random();
        this.answeringPlayerIndex = rnd.nextInt(players.size());
    }
    
    public void end() {
        players.forEach((player) -> {
            int loc = this.containsName(this.allPlayers, player.getName());
            if(loc != JeopardyConstants.NOT_FOUND){
                if(this.allPlayers.get(loc).getCredits() < player.getCredits())
                    this.allPlayers.get(loc).setCredits(player.getCredits());
            }else
                this.allPlayers.add(player);
                
        });
        
        players.clear();
        this.deleteObservers();
    }
    
    public boolean setAnsweringPlayer(char key){
        boolean found = false;
        for(int i = 0; i < players.size() && !found; i++){
            if(players.get(i).getKey() == key){
                this.answeringPlayerIndex = i;
                found = true;
            }
        }
        
        return found;
    }
    
    public int getAnsweringPlayerIndex(){
        return this.answeringPlayerIndex;
    }
    
    private void validatePlayer(String name, char key) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        
        if(name.length() == 0)
            throw new EmptyPlayerNameException();
        
        if(key == 0)
            throw new EmptyPlayerKeyException();
            
        if(containsName(this.players, name) != JeopardyConstants.NOT_FOUND)
            throw new DuplicateNameException();

        
        if(containsKey(this.players, key) != JeopardyConstants.NOT_FOUND)
            throw new DuplicateKeyException();
        
    }
    
    private int containsName(ArrayList<Player> playerList, String name){
        int res = JeopardyConstants.NOT_FOUND;
        for(int i = 0; i < playerList.size() && res == JeopardyConstants.NOT_FOUND; i++)
            if(playerList.get(i).getName().toLowerCase().equals(name.toLowerCase()))
                res = i;
 
        
        return res;
    }
    
    private int containsKey(ArrayList<Player> playerList, char key){
        int res = JeopardyConstants.NOT_FOUND;
        for(int i = 0; i < playerList.size() && res == JeopardyConstants.NOT_FOUND; i++)
            if(playerList.get(i).getKey() == key)
                res = i;
 
        
        return res;
    }
    
    public void addNewPlayer(String name, char key) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        validatePlayer(name, key);
        
        Player newPlayer = new Player(name, key, JeopardyConstants.INITIAL_CREDITS);
        players.add(newPlayer);
        
        this.setChanged();
        this.notifyObservers(this.getNumOfCurrentPlayers());
    }
    
    public int getNumOfCurrentPlayers(){
        return players.size();
    }
    
    public void modifyPlayer(int playerIndex, String newName, char newKey) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        Player editingPlayer = this.getPlayer(playerIndex);
        String oldName = editingPlayer.getName();
        char oldKey = editingPlayer.getKey();
        
        try{            
            editingPlayer.setName("");
        
            editingPlayer.setKey((char)0);

            validatePlayer(newName, newKey);

            editingPlayer.setName(newName);
            editingPlayer.setKey(newKey);
        }
        catch(DuplicateNameException | DuplicateKeyException | EmptyPlayerNameException | EmptyPlayerKeyException e){
            editingPlayer.setName(oldName);
            editingPlayer.setKey(oldKey);
            
            throw e;
        }
        
    }
    
    public Player getPlayer(int playerIndex){
        return players.get(playerIndex);
    }
    
    public ArrayList<Player> getOrderedPlayers(boolean forAll){
        ArrayList<Player> res;
        if(forAll)
            res = this.allPlayers;
        else
            res = (ArrayList<Player>)this.players.clone();
         
        res.sort(null);
        
        return res;
    }
    
    public void changeCredit(int offset){
        if(offset < 0){
            char key = players.get(this.answeringPlayerIndex).getKey();
            forbiddenKeys.add(key);
        }
        
        int newCredits = players.get(this.answeringPlayerIndex).getCredits() + offset;
        players.get(this.answeringPlayerIndex).setCredits(newCredits);
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public void clearForbiddenPlayers(){
        this.forbiddenKeys.clear();
    }
    
    public int numberOfAllowablePlayers(){
        return players.size() - forbiddenKeys.size();
    }
}


public class JeopardyGame extends Observable {
    private final QuestionManager theQuestionManager;
    private final PlayerManager thePlayerManager;
    private boolean isStarted;

    public JeopardyGame(){
        theQuestionManager = new QuestionManager();
        thePlayerManager = new PlayerManager();
        this.isStarted = false;
    }
    
    public void start(String filename) throws NotEnoughPlayersException, FileNotFoundException, IOException{
        try{
            theQuestionManager.start(filename);
            thePlayerManager.start();
            this.isStarted = true;
        }
        catch(NotEnoughPlayersException e){
            theQuestionManager.end();
            throw e;
        }
    }
    
    public void end(){
        theQuestionManager.end();
        thePlayerManager.end();
        this.isStarted = false;
    }
    
    public boolean setAnsweringPlayer(char key){
        return this.thePlayerManager.setAnsweringPlayer(key);
    }
    
    public int getAnsweringPlayerIndex(){
        return this.thePlayerManager.getAnsweringPlayerIndex();
    }
    
    public boolean isStarted(){
        return isStarted;
    }
    
    @Override
    public void addObserver(Observer o){
        this.thePlayerManager.addObserver(o);
    }
    
    @Override
    public void deleteObservers(){
        this.thePlayerManager.deleteObservers();
    }
    
    public int getNumOfCurrentPlayers(){
        return this.thePlayerManager.getNumOfCurrentPlayers();
    }
    
    public void addNewPlayer(String name, char key) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        this.thePlayerManager.addNewPlayer(name, key);
    }
    
    public void modifyPlayer(int playerIndex, String newName, char newKey) throws DuplicateNameException, DuplicateKeyException, EmptyPlayerNameException, EmptyPlayerKeyException{
        this.thePlayerManager.modifyPlayer(playerIndex, newName, newKey);
    }
    
    public Player getPlayer(int playerIndex){
        return this.thePlayerManager.getPlayer(playerIndex);
    }
    
    public ArrayList<Player> getOrderedPlayers(boolean forAll){
        return this.thePlayerManager.getOrderedPlayers(forAll);
    }
    
    public boolean isDoubleJeopardy(int categoryIndex, int questionIndex){
        return this.theQuestionManager.isDoubleJeopardy(categoryIndex, questionIndex);
    }
    
    public String getCategory(int index){
        return this.theQuestionManager.getCategory(index);
    }
    
    public int getWeight(int categoryIndex, int questionIndex){
        return this.theQuestionManager.getWeight(categoryIndex, questionIndex);
    }
    
    public void setWeight(int categoryIndex, int questionIndex, int newWeight){
        this.theQuestionManager.setWeight(categoryIndex, questionIndex, newWeight);
    }
    
    public void clearForbiddenPlayers(){
        this.thePlayerManager.clearForbiddenPlayers();
    }
    
    public int numberOfAllowablePlayers(){
        return this.thePlayerManager.numberOfAllowablePlayers();
    }
    
    public void changeCredit(int offset){
        this.thePlayerManager.changeCredit(offset);
    }
    
}
