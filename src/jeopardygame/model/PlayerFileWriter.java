/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeopardygame.model;

import java.io.*;
import jeopardygame.constant.JeopardyGameConstants;

/**
 *
 * @author iqapp
 */
public class PlayerFileWriter {
    public static void write(PlayerManager thePlayerManager) throws IOException{
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(JeopardyGameConstants.ALL_PLAYERS_FILE_PATH))) {
            outputStream.writeObject(thePlayerManager);
            outputStream.close();
        }
    }
}
