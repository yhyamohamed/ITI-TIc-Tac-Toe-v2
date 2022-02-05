package Controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui_modules.GameBoard;
import ui_modules.Home;
import ui_modules.playonlinescreen;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class ServerConnector {
    private static Socket socket;
    private static DataInputStream dataInputStream;
    static DataOutputStream dataOutputStream;
    private static ArrayList<javafx.scene.control.Button> buttons;
    private static StreamReader reader;
    private static playonlinescreen playonlinescreen;
    private static Stage primaryStage;
    private static ArrayList<Player> onlinePlayersFromServer =new ArrayList<>();
    private static ArrayList<Player> offlinePlayersFromServer =new ArrayList<>();

static
    {

    }

    public ServerConnector()
    {

    }
    public static void setPrimaryStage(Stage currentPrimaryStage)
    {
        primaryStage=currentPrimaryStage;
    }
    public static void setPlayonlinescreen(playonlinescreen currentplayOnlinescreen){playonlinescreen=currentplayOnlinescreen;}
    public static String signIn(String userName,String passWord)
    {
        if(socket==null || socket.isClosed())
        {
            try {

                socket= new Socket(InetAddress.getLocalHost(),5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            }catch (IOException e){
                e.printStackTrace();
            }
        }

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
                reader=new StreamReader();
                reader.running=true;
                reader.start();
               queryOfflinePlayersFromServer();
               queryOnlinePlayersFromServer();


            }
            }else{}
        } catch (IOException e) {
            e.printStackTrace();
        }

        return PlayerInfo.login;
    }
    public  static boolean signUp(String username,String password)
    {
        if(socket==null)
        {
            try {
                socket= new Socket(InetAddress.getLocalHost(),5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            }catch (IOException e){
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
    public static void logOut()
    {
        JsonObject requestObject=new JsonObject();
        requestObject.addProperty("type","logout");
        requestObject.addProperty("username",PlayerInfo.username);
        try {
            dataOutputStream.writeUTF(requestObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    requestObject.addProperty("game_id",PlayerInfo.gameId);
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

    private static void renderRecordedGame(String recordsArray) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                GameBoard root = new GameBoard(primaryStage, false,true,false);
                Scene scene = new Scene(root);
                primaryStage.setTitle("record screen ");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        String[] string = recordsArray.replaceAll("\\[", "")
                .replaceAll("]", "")
                .split(",");
        for (int i = 0; i < string.length; i++) {
            String[] st = string[i].trim().split("-");
            System.out.println(Arrays.toString(st));


            int pos = Integer.parseInt(st[0]);
            int sign = Integer.parseInt(st[1]);
            int player_id = Integer.parseInt(st[2]);


            double tim = i + 0.5;

            PauseTransition pause = new PauseTransition(Duration.seconds(i));
            pause.setOnFinished(event -> {
                Button btn = buttons.get(pos);
                btn.setFont(new Font("System Bold Italic", 200));
                btn.setStyle("-fx-font-size:40");
                String si = (sign == 8) ? "X" : "O";
                btn.setText(si);
            });
            pause.playFromStart();
        }
    }
    public static void sendReplayreq(JsonObject showRecObj)
    {
        try {
            dataOutputStream.writeUTF(showRecObj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendFinishingObj(JsonObject gameFinish)
    {
        try {
            dataOutputStream.writeUTF(gameFinish.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static ArrayList<Player> getOnlinePlayersFromServer()
{
    return onlinePlayersFromServer;
}
    public static ArrayList<Player> getOfflinePlayersFromServer()
    {
        return offlinePlayersFromServer;
    }
    public static void close(JsonObject closingObj) {
        try {
            dataOutputStream.writeUTF(closingObj.toString());
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
            reader.running=false;
        } catch (IOException e) {

        }
}


    public static void sendInvetation(int id)
    {
        JsonObject requestObject=new JsonObject();
        requestObject.addProperty("type","sendInvitation");
        requestObject.addProperty("senderplayerid",PlayerInfo.id);
        requestObject.addProperty("senderusername",PlayerInfo.username);
        requestObject.addProperty("senderscore",PlayerInfo.score);
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
        requestObject.addProperty("game_id",PlayerInfo.gameId);
        requestObject.addProperty("accepter",PlayerInfo.id);
        requestObject.addProperty("accepted",PlayerInfo.opponentId);

        try {
            dataOutputStream.writeUTF(requestObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void queryOnlinePlayersFromServer()
    {
        JsonObject requestObject=new JsonObject();
        requestObject.addProperty("type","getonlineplayers");
        try {
            dataOutputStream.writeUTF(requestObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void queryOfflinePlayersFromServer()
    {
        JsonObject requestObject=new JsonObject();
        requestObject.addProperty("type","getofflineplayers");
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
    private int wins;
    private int losses;
    private int score;
    private boolean online;

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }
    public int getWins()
    {
        return wins;
    }

    public int getScore() {
        return score;
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
        static String mySign;
        static int gameId;
        static int opponentScore;
        static String opponentUsername;


       static public String getOpponentUsername()
        {
            return opponentUsername;
        }
        static    public int getOpponentScore()
        {
            return opponentScore;
        }
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
         boolean running=true;

        public StreamReader()
        {

        }

        @Override
        public void  run()
        {
            System.out.println("readeron");
            System.out.println(running);
            while (running)
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
                        case "yourinvetationaccepted":
                            int accepterID=requestObject.get("whoaccepted").getAsInt();
                            PlayerInfo.opponentId= String.valueOf(accepterID);
                            PlayerInfo.gameId=requestObject.get("game_id").getAsInt();
                            PlayerInfo.playerTurn=false;
                            PlayerInfo.mySign="O";
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    boolean playAgainstPC=false;
                                    playonlinescreen.getinvitationAlert().hide();
                                    System.out.println("newgameboard");
                                    GameBoard root = new GameBoard(primaryStage,  playAgainstPC,false,false);
                                    Scene scene = new Scene(root);
                                    primaryStage.setTitle("GameBoard screen ");
                                    primaryStage.setScene(scene);
                                    primaryStage.show();
                                }
                            });
                            break;
                        case "invitationreceived":
                            int opponentID=requestObject.get("sender").getAsInt();
                            PlayerInfo.gameId=requestObject.get("game_id").getAsInt();
                            PlayerInfo.opponentId= String.valueOf(opponentID);
                            PlayerInfo.opponentUsername=requestObject.get("opponentusername").getAsString();
                            PlayerInfo.opponentScore=requestObject.get("opponentsscore").getAsInt();

                            System.out.println(opponentID+"chalenges you");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "waiting for response...", ButtonType.NO, ButtonType.YES);
                                    alert2.setTitle("invitation");
                                    alert2.setHeaderText("Do you want to play with " + PlayerInfo.opponentUsername + " ?");
                                    alert2.setResizable(false);
                                    alert2.initOwner(primaryStage);
                                    Optional<ButtonType> result = alert2.showAndWait();
                                    ButtonType button = result.orElse(ButtonType.NO);

                                    if (button == ButtonType.YES) {
                                        // if condition yes && no : call isma3el methods
                                        System.out.println("yes"); //accept play
                                        PlayerInfo.playerTurn=true;
                                        PlayerInfo.allowFire=true;
                                        PlayerInfo.mySign="X";
                                        acceptInvetation();
                                        boolean playAgainstPC=false;
                                        System.out.println("newgameboard");
                                        GameBoard root = new GameBoard(primaryStage,  playAgainstPC,false,false);
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
                        case "game_record":
                            System.out.println(requestObject);
                           String moves= requestObject.get("moves").getAsString();
                            renderRecordedGame(moves);
                            break;


                        case "offlineplayers":
                            if(offlinePlayersFromServer != null) offlinePlayersFromServer.clear();
                            JsonArray offlinePlayers=requestObject.getAsJsonArray("offlineplayers");
                            System.out.println(offlinePlayers);
                            for(JsonElement rplayerobject : offlinePlayers) {
                                JsonObject playerObject=rplayerobject.getAsJsonObject();
                                Player player= new  Player();
                                player.id=playerObject.get("id").getAsInt();
                               // System.out.println(player.id);
                                player.username=playerObject.get("username").getAsString();
                                player.score=playerObject.get("score").getAsInt();
                                offlinePlayersFromServer.add(player);
                            }
                            for(Player player:offlinePlayersFromServer){
                                System.out.println(player.username);
                            }
                            break;

                        case "onlineplayers":

                            JsonArray onlinePlayers=requestObject.getAsJsonArray("onlineplayers");
                            if(onlinePlayersFromServer != null) onlinePlayersFromServer.clear();
                            System.out.println(onlinePlayers);
                            for(JsonElement rplayerobject : onlinePlayers) {
                                JsonObject playerObject=rplayerobject.getAsJsonObject();
                                Player player= new  Player();
                                player.id=playerObject.get("id").getAsInt();
                                player.username=playerObject.get("username").getAsString();
                                player.score=playerObject.get("score").getAsInt();
                                onlinePlayersFromServer.add(player);
                            }
                            break;
                        case "opponent_disconnect":
                            ServerConnector.dataOutputStream.close();
                            ServerConnector.dataInputStream.close();
                            System.out.println("opponent_disconnect");
                            ServerConnector.socket.close();
                            running=false;
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //render pop up
                                    Alert alert = new Alert(Alert.AlertType.WARNING);
                                    alert.setContentText("Connection failed");
                                    alert.setTitle("connection");
                                    alert.initOwner(primaryStage);


                                    alert.getButtonTypes();

                                    Optional<ButtonType> result = alert.showAndWait();
                                    if (result.get() == ButtonType.OK){
                                        // ... user chose OK button
                                        Home root = new Home(primaryStage);
                                        Scene scene = new Scene(root);
                                        primaryStage.setTitle("home screen ");
                                        primaryStage.setScene(scene);
                                        primaryStage.show();

                                    }

                                }
                            });
                            break;
                        case "update-list":

                            System.out.println("clientclosed");
                            JsonArray newonlinePlayers=requestObject.getAsJsonArray("onlineplayers");
                            if(onlinePlayersFromServer != null) onlinePlayersFromServer.clear();
                            //System.out.println(newonlinePlayers);
                            for(JsonElement rplayerobject : newonlinePlayers) {
                                JsonObject playerObject=rplayerobject.getAsJsonObject();
                                Player player= new  Player();
                                player.id=playerObject.get("id").getAsInt();
                                player.username=playerObject.get("username").getAsString();
                                player.score=playerObject.get("score").getAsInt();
                                onlinePlayersFromServer.add(player);
                            }
                            for(Player offplayer : onlinePlayersFromServer)
                            {
                                System.out.println("new onlineplayers");
                                System.out.println(offplayer.getUsername());
                            }
                            if(offlinePlayersFromServer != null) offlinePlayersFromServer.clear();
                            JsonArray newofflinePlayers=requestObject.getAsJsonArray("offlineplayers");
                            //System.out.println(offlinePlayers);
                            for(JsonElement rplayerobject : newofflinePlayers) {
                                JsonObject playerObject=rplayerobject.getAsJsonObject();
                                Player player= new  Player();
                                player.id=playerObject.get("id").getAsInt();
                                // System.out.println(player.id);
                                player.username=playerObject.get("username").getAsString();
                                player.score=playerObject.get("score").getAsInt();
                                offlinePlayersFromServer.add(player);
                            }
                            for(Player offplayer : offlinePlayersFromServer)
                            {
                                System.out.println("new offlineplayers");
                                System.out.println(offplayer.getUsername());
                            }

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    if(playonlinescreen !=null)
                                    playonlinescreen.renderLists(onlinePlayersFromServer,offlinePlayersFromServer);
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
