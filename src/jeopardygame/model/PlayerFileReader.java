/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import jeopardygame.constant.JeopardyGameConstants;
import jeopardygame.sharedmodel.QuestionManager;

/**
 *
 * @author iqapp
 */
public class PlayerFileReader {
    public static PlayerManager read() throws ClassNotFoundException, IOException{
       PlayerManager res;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(JeopardyGameConstants.ALL_PLAYERS_FILE_PATH))) {
            res = (PlayerManager)inputStream.readObject();
            inputStream.close();
        }

        return res;
    }
}
