package Controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.scene.control.Button;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class ServerConnector {
    private static Socket socket;
    static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;
    private static ArrayList<javafx.scene.control.Button> buttons;
    private static StreamReader reader;
static
    {
        try {
            socket= new Socket(InetAddress.getLocalHost(),5001);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
               
        }

         reader=new StreamReader();
    }

    public ServerConnector()
    {

    }
    
    public static String signIn(String userName,String passWord)
    {
     
        String Empty ="";
        JsonObject jasoSign=new JsonObject();
        jasoSign.addProperty("type","login");
        jasoSign.addProperty("username",userName);
        jasoSign.addProperty("password",passWord);
        try {
            if(dataOutputStream==null)throw new IOException();
            dataOutputStream.writeUTF(jasoSign.toString());
        } catch (IOException e) {
          
           return Empty;
         
        }
        try {
            String resMsg= dataInputStream.readUTF();
            JsonObject response =JsonParser.parseString(resMsg).getAsJsonObject();
            System.out.println(response);
            String type=response.get("type").getAsString();
            
            if(type.equals("loginresponse")){
            PlayerInfo.login=response.get("successful").getAsString();
            
            if(PlayerInfo.login.equals("true")) {
                PlayerInfo.id = response.get("id").getAsString();
                PlayerInfo.username = response.get("username").getAsString();
                PlayerInfo.score = response.get("score").getAsString();
                PlayerInfo.wins = response.get("wins").getAsString();
                PlayerInfo.losses = response.get("losses").getAsString();
            }
            }else{}
        } catch (IOException e) {
      
        }

        return PlayerInfo.login;
    }
    public  static boolean signUp(String username,String password)
    {
   
        Boolean validuser;
        JsonObject jsonsignup=new JsonObject();
        jsonsignup.addProperty("type","signup");
        jsonsignup.addProperty("username",username);
        jsonsignup.addProperty("password",password);
      
        try {
             if(dataOutputStream==null)throw new IOException();
            dataOutputStream.writeUTF(jsonsignup.toString());
        } catch (IOException e) {  
     
        }
        try {
            String resMsg= dataInputStream.readUTF();
            JsonObject response =JsonParser.parseString(resMsg).getAsJsonObject();
            String serverResponse=response.get("successful").getAsString();
            validuser=(serverResponse.equals("true"))? true:false;
        } catch (IOException e) {
            e.printStackTrace();
            validuser=false;
            
        }

        return validuser;
    }
public static void assignGameBoardButtons(ArrayList<Button> btns)
{
  buttons= btns;
}
public static void play(int position,int sign)
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","play");
    requestObject.addProperty("opponet",PlayerInfo.opponentId);
    requestObject.addProperty("position",position);
    requestObject.addProperty("sign",sign);
    System.out.println("ayy 7aga");
    System.out.println(position);
    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
    //opponentsMove();

}

public static void opponentsMove(int position)
{
    System.out.println("opponent"+position);
    Platform.runLater(new Runnable() {
        @Override
        public void run() {
            PlayerInfo.allowFire=true;

            buttons.get(position).fire();
            PlayerInfo.playerTurn=true;
        }
    });

}
static public void getopponentId()
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","getOpponentId");
    System.out.println("getOpidclient");
    requestObject.addProperty("playerid",PlayerInfo.id);
    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        String resMsg= dataInputStream.readUTF();
        System.out.println(resMsg);
        JsonObject responseOpject=new JsonObject();
        responseOpject=JsonParser.parseString(resMsg).getAsJsonObject();
        PlayerInfo.opponentId=responseOpject.get("opponentid").getAsString();
        PlayerInfo.playerTurn=responseOpject.get("turn").getAsBoolean();
        PlayerInfo.allowFire=PlayerInfo.playerTurn;
    } catch (IOException e) {
        e.printStackTrace();
    }

    reader.start();
}

    public static void close(JsonObject closingObj) {
        try {
            dataOutputStream.writeUTF(closingObj.toString());
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
            StreamReader.running=false;
        } catch (IOException e) {

        }
}

    

    /*private static ArrayList<Player> getPlayersInfo()
    {

    }*/
public class Player
{
    private int id;
    private String username;
    private String hashedPassword;
    private int wins;
    private int losses;
    private int score;
    private boolean online;
}
    public static class PlayerInfo
    {
        static String username;
        static String score;
        static String wins;
        static String losses;
        static String id;
        static String login;
        static String opponentId;
        static boolean playerTurn;
        static boolean allowFire;


        public String getLogin() {
            return login;
        }

        public String getId() {
            return id;
        }

        public static String getLosses() {
            return losses;
        }

        public static String getScore() {
            return score;
        }

        public static String getUsername() {
            return username;
        }

        public static String getWins() {
            return wins;
        }
    }
    private static class StreamReader extends Thread
    {
        static boolean running=true;

        public StreamReader()
        {

        }

        @Override
        public void  run()
        {
            while (running)
            {
                try {
                    String lineSent = dataInputStream.readUTF();
                    if (lineSent == null) throw new IOException();
                    JsonObject requestObject = JsonParser.parseString(lineSent).getAsJsonObject();
                    String type = requestObject.get("type").getAsString();
                    switch (type)
                    {
                        case "oponnetmove" :
                            int position=requestObject.get("position").getAsInt();
                            System.out.println(position+"bbbbbb");
                            opponentsMove(position);


                            break;
                        case "loginresponse":
                            System.out.println("responsethroughthread");

                            break;
                        case "opponent_disconnect":
                            ServerConnector.dataOutputStream.close();
                            ServerConnector.dataInputStream.close();
                            ServerConnector.socket.close();
                            running=false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                //render pop up
                                }
                            });
                    }
                }catch (IOException e){
                    running=false;
                    return;
                }if(running){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                }

            }
        }
    }

   

    
}