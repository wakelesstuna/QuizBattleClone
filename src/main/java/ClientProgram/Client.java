package ClientProgram;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Vilma Couturier Kaijser
 * Date: 2020-11-13
 * Project: BestQuizBattleEver
 * Copyright: MIT
 */
public class Client {

    public Client() throws UnknownHostException {
        InetAddress iAdr = InetAddress.getLocalHost();
        int port = 12345;

        try (Socket clientSocket = new Socket(iAdr, port);
             ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
             BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
        ){
            Object fromServer;
            String fromUser;

            while((fromServer = in.readObject()) != null){
                // kolla vad servern skickar för objekt
                // göra olika saker beroende på det
                if(fromServer instanceof String){
                    System.out.println(fromServer);
                } /*else if (fromServer instanceof Question){
                    System.out.println(fromServer.getQuestion());
                    System.out.println(fromServer.getShuffledAnswers());
                } */


                fromUser = userIn.readLine();
                if(fromUser != null){
                    // skicka int för att användaren valt ett svar
                    // men kanske bättre att omvandla till int på servern,
                    // om användaren kan välja annat i andra situationer
                    out.writeObject(Integer.parseInt(fromUser));
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        Client c = new Client();
    }

}
