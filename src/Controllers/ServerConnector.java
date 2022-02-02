package Controllers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ui_modules.GameBoard;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.net.SocketException;
import java.util.ArrayList;

public class ServerConnector {
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static ArrayList<javafx.scene.control.Button> buttons;
static
    {
        try {
            socket= new Socket(InetAddress.getLocalHost(),5001);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public ServerConnector()
    {

    }
    public static String signIn(String userName,String passWord)
    {


        JsonObject jasoSign=new JsonObject();
        jasoSign.addProperty("type","login");
        jasoSign.addProperty("username",userName);
        jasoSign.addProperty("password",passWord);
        try {
            dataOutputStream.writeUTF(jasoSign.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String resMsg= dataInputStream.readUTF();
            JsonObject response =JsonParser.parseString(resMsg).getAsJsonObject();
            System.out.println(response);
            PlayerInfo.login=response.get("successful").getAsString();
            if(PlayerInfo.login.equals("true")) {
                PlayerInfo.id = response.get("id").getAsString();
                PlayerInfo.username = response.get("username").getAsString();
                PlayerInfo.score = response.get("score").getAsString();
                PlayerInfo.wins = response.get("wins").getAsString();
                PlayerInfo.losses = response.get("losses").getAsString();
            }else{}
        } catch (IOException e) {
            e.printStackTrace();
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
            dataOutputStream.writeUTF(jsonsignup.toString());
        } catch (IOException e) {
            e.printStackTrace();
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
public static void assignGameBoardButtons()
{
    buttons= GameBoard.gameBoardController.getBtns();
}
public static void play(int position,int sign)
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","play");
    requestObject.addProperty("opponet",PlayerInfo.opponentId);
    requestObject.addProperty("position",position);
    requestObject.addProperty("sign",sign);
    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }


}

public static void opponentsMove()
{
    String resMsg= null;
    try {
        resMsg = dataInputStream.readUTF();
    } catch (IOException e) {
        e.printStackTrace();
    }
    JsonObject gameresponse =JsonParser.parseString(resMsg).getAsJsonObject();
    int position=gameresponse.get("positon").getAsInt();
    buttons.get(position).fire();

}
static public void getopponentId()
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","getOpponentId");
    requestObject.addProperty("playerid",PlayerInfo.id);
    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        String resMsg= dataInputStream.readUTF();
        JsonObject responseOpject=new JsonObject();
        responseOpject=JsonParser.parseString(resMsg).getAsJsonObject();
        PlayerInfo.opponentId=responseOpject.get("opponentid").getAsString();
    } catch (IOException e) {
        e.printStackTrace();
    }


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
}
