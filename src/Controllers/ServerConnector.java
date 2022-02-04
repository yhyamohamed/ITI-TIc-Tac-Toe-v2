package Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ui_modules.GameBoard;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Optional;

public class ServerConnector {
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static ArrayList<javafx.scene.control.Button> buttons;
    private static ArrayList<Player> onlinePlayersFromServer =new ArrayList<>();
    private static StreamReader reader;
    private static Stage primaryStage;
static
    {
       /* try {
            socket= new Socket(InetAddress.getLocalHost(),5001);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
         reader=new StreamReader();
        getOnlinePlayersInfo();*/
    }

    public ServerConnector()
    {
    }
    public static void setPrimaryStage(Stage currentPrimaryStage)
    {
        primaryStage=currentPrimaryStage;
    }
    public static String signIn(String userName,String passWord)
    {
        if(socket==null) {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        reader = new StreamReader();
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
            e.printStackTrace();
        }
        getOnlinePlayersInfo();
        reader.start();
        return PlayerInfo.login;
    }

    public  static boolean signUp(String username,String password)
    {
        if(socket==null) {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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


}
private static void getOnlinePlayersInfo()
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","getonlineplayers");

    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static class Player
{
    private int id;
    private String username;
    private int score;
}
public static void instantiateNewOnlineGameboard()
{

            boolean playAgainstPC=false;
            System.out.println("newgameboard");
            GameBoard root = new GameBoard(primaryStage, playAgainstPC);
            Scene scene = new Scene(root);
            primaryStage.setTitle("GameBoard screen ");
            primaryStage.setScene(scene);
            primaryStage.show();



}
public static void sendInvetation(int id)
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","sendInvitation");
    requestObject.addProperty("senderplayerid",PlayerInfo.id);
    requestObject.addProperty("sendtoid",id);
    PlayerInfo.opponentId= String.valueOf(id);
    try {
        dataOutputStream.writeUTF(requestObject.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}
public static void acceptInvetation()
{
    JsonObject requestObject=new JsonObject();
    requestObject.addProperty("type","acceptinvetation");
    requestObject.addProperty("accepter",PlayerInfo.id);
    requestObject.addProperty("accepted",PlayerInfo.opponentId);

    try {
        dataOutputStream.writeUTF(requestObject.toString());
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
        public StreamReader()
        {

        }

        @Override
        public void  run()
        {
            System.out.println("readeron");
            while (true)
            {
                try {
                    String lineSent = dataInputStream.readUTF();
                    if (lineSent == null) throw new IOException();
                    JsonObject requestObject = JsonParser.parseString(lineSent).getAsJsonObject();
                    String type = requestObject.get("type").getAsString();
                    System.out.println(type);
                    switch (type)
                    {
                        case "oponnetmove" :
                            int position=requestObject.get("position").getAsInt();
                            opponentsMove(position);

                            break;
                        case "loginresponse":
                            System.out.println("responsethroughthread");

                            break;
                        case "onlineplayers":
                            JsonArray onlinePlayers=requestObject.getAsJsonArray();
                            for(JsonElement rplayerobject : onlinePlayers) {
                                JsonObject playerObject=rplayerobject.getAsJsonObject();
                                Player player= new  Player();
                                player.id=playerObject.get("id").getAsInt();
                                player.username=playerObject.get("username").getAsString();
                                player.score=playerObject.get("score").getAsInt();
                                onlinePlayersFromServer.add(player);
                            }
                            break;
                        case "yourinvetationaccepted":
                            int accepterID=requestObject.get("whoaccepted").getAsInt();
                            PlayerInfo.opponentId= String.valueOf(accepterID);
                            PlayerInfo.playerTurn=false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    boolean playAgainstPC=false;
                                    System.out.println("newgameboard");
                                    GameBoard root = new GameBoard(primaryStage, playAgainstPC);
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("GameBoard screen ");
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                }
                            });


                            break;
                        case "invitationreceived":
                            int opponentID=requestObject.get("sender").getAsInt();
                            PlayerInfo.opponentId= String.valueOf(opponentID);
                            System.out.println(opponentID+"chalenges you");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "waiting for response...", ButtonType.NO, ButtonType.YES);
                                    alert2.setTitle("invitation");
                                    alert2.setHeaderText("Do you want to play with " + PlayerInfo.opponentId + " ?");
                                    alert2.setResizable(false);


                                    Optional<ButtonType> result = alert2.showAndWait();
                                    ButtonType button = result.orElse(ButtonType.NO);

                                    if (button == ButtonType.YES) {
                                        // if condition yes && no : call isma3el methods
                                        System.out.println("yes"); //accept play
                                        PlayerInfo.playerTurn=true;
                                        PlayerInfo.allowFire=true;
                                        acceptInvetation();
                                        boolean playAgainstPC=false;
                                        System.out.println("newgameboard");
                                        GameBoard root = new GameBoard(primaryStage, playAgainstPC);
                                        Scene scene = new Scene(root);
                                        primaryStage.setTitle("GameBoard screen ");
                                        primaryStage.setScene(scene);
                                        primaryStage.show();

                                    } else {
                                        System.out.println("noo"); // reject play
                                    }
                                }
                            });
                            break;
                    }
                }catch (IOException e){}
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
