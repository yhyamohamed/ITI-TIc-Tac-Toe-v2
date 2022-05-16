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

import utility.Player;
import utility.PlayerInfo;
import utility.RequestJsonBuilder;
import utility.StreamReader;

public class ServerConnector {

    private static ServerConnector serverConnector;
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private ArrayList<javafx.scene.control.Button> buttons;
    private utility.StreamReader reader;
    private playonlinescreen playonlinescreen;
    private Stage primaryStage;
    private ArrayList<utility.Player> onlinePlayersFromServer = new ArrayList<Player>();
    private ArrayList<utility.Player> offlinePlayersFromServer = new ArrayList<utility.Player>();
    public utility.PlayerInfo playerInfo;


    private ServerConnector() {
        playerInfo = utility.PlayerInfo.getPlayerInfo();

    }

    public static ServerConnector getServerConnector() {
        if (ServerConnector.serverConnector == null) {
            ServerConnector.serverConnector = new ServerConnector();
            return ServerConnector.serverConnector;
        } else {
            return ServerConnector.serverConnector;
        }
    }

    public void setPrimaryStage(Stage currentPrimaryStage) {
        primaryStage = currentPrimaryStage;
    }

    public void setPlayonlinescreen(playonlinescreen currentplayOnlinescreen) {
        playonlinescreen = currentplayOnlinescreen;
    }

    public String signIn(String userName, String passWord) {
        if (socket == null || socket.isClosed()) {
            try {

                socket = new Socket(InetAddress.getLocalHost(), 5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                RequestJsonBuilder.setOutputStream(dataOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        RequestJsonBuilder jasoSign = new RequestJsonBuilder();
        jasoSign.setType("login")
                .addProperty("username", userName)
                .addProperty("password", passWord)
                .send();

        try {
            String resMsg = dataInputStream.readUTF();
            JsonObject response = JsonParser.parseString(resMsg).getAsJsonObject();

            if (response.get("successful").getAsString().equals("true")) {
                playerInfo.login = "true";
                playerInfo.id = response.get("id").getAsString();
                playerInfo.username = response.get("username").getAsString();
                playerInfo.score = response.get("score").getAsString();
                playerInfo.wins = response.get("wins").getAsString();
                playerInfo.losses = response.get("losses").getAsString();
                reader = new utility.StreamReader(socket);

                //queryPlayersListsFromServer();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return playerInfo.login;
    }

    public boolean signUp(String username, String password) {
        if (socket == null) {
            try {
                socket = new Socket(InetAddress.getLocalHost(), 5001);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                RequestJsonBuilder.setOutputStream(dataOutputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        boolean validuser;
        RequestJsonBuilder jsonsignup = new RequestJsonBuilder();
        jsonsignup.setType("signup")
                .addProperty("username", username)
                .addProperty("password", password)
                .send();
        try {
            String resMsg = dataInputStream.readUTF();
            JsonObject response = JsonParser.parseString(resMsg).getAsJsonObject();
            String serverResponse = response.get("successful").getAsString();
            validuser = serverResponse.equals("true");
        } catch (IOException e) {
            e.printStackTrace();
            validuser = false;
        }

        return validuser;
    }

    public void logout() {
        RequestJsonBuilder requestObject = new RequestJsonBuilder();
        requestObject.setType("logout")
                .addProperty("username", playerInfo.username).send();
    }

    public void assignGameBoardButtons(ArrayList<Button> btns) {
        buttons = btns;
    }

    public void play(Integer position, Integer sign) {
        RequestJsonBuilder requestJson = new RequestJsonBuilder();
        requestJson.setType("play")
                .addProperty("opponet", playerInfo.opponentId)
                .addProperty("game_id", playerInfo.gameId.toString())
                .addProperty("position", position.toString())
                .addProperty("sign", sign.toString()).send();
    }


    public void opponentsMove(int position) {
        System.out.println("opponent" + position);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                playerInfo.allowFire = true;

                buttons.get(position).fire();
                playerInfo.playerTurn = true;
            }
        });

    }

    public void renderGameScene() {
        Platform.runLater(() -> {
            boolean playAgainstPC = false;
            playonlinescreen.getinvitationAlert().hide();
            System.out.println("newgameboard");
            GameBoard root = new GameBoard(primaryStage, playAgainstPC, false, false);
            Scene scene = new Scene(root);
            primaryStage.setTitle("GameBoard screen ");
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    public void renderInvitaionPopup() {
        Platform.runLater(() -> {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "waiting for response...", ButtonType.NO, ButtonType.YES);
            alert2.setTitle("invitation");
            alert2.setHeaderText("Do you want to play with " + playerInfo.opponentUsername + " ?");
            alert2.setResizable(false);
            alert2.initOwner(primaryStage);
            Optional<ButtonType> result = alert2.showAndWait();
            ButtonType button = result.orElse(ButtonType.NO);

            if (button == ButtonType.YES) {
                // if condition yes && no : call isma3el methods
                System.out.println("yes"); //accept play
                playerInfo.playerTurn = true;
                playerInfo.allowFire = true;
                playerInfo.mySign = "X";
                acceptInvetation();
                boolean playAgainstPC = false;
                System.out.println("newgameboard");
                GameBoard root = new GameBoard(primaryStage, playAgainstPC, false, false);
                Scene scene = new Scene(root);
                primaryStage.setTitle("GameBoard screen ");
                primaryStage.setScene(scene);
                primaryStage.show();


            } else {
                System.out.println("noo"); // reject play
            }
        });
    }

    public void renderRecordedGame(String recordsArray) {
        Platform.runLater(() -> {
            GameBoard root = new GameBoard(primaryStage, false, true, false);
            Scene scene = new Scene(root);
            primaryStage.setTitle("record screen ");
            primaryStage.setScene(scene);
            primaryStage.show();
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

    public void renderOpponentDisconnectedWarning() {
        Platform.runLater(() -> {
            //render pop up
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Connection failed");
            alert.setTitle("connection");
            alert.initOwner(primaryStage);


            alert.getButtonTypes();

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // ... user chose OK button
                Home root = new Home(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("home screen ");
                primaryStage.setScene(scene);
                primaryStage.show();

            }

        });
    }

    public void renderPlayersList() {
        Platform.runLater(() -> {
            if (playonlinescreen != null)
                playonlinescreen.renderLists(onlinePlayersFromServer, offlinePlayersFromServer);
        });
    }

    public void sendReplayreq(JsonObject showRecObj) {
        RequestJsonBuilder requestJson = new RequestJsonBuilder();
        requestJson.send(showRecObj);
    }

    public void sendFinishingObj(JsonObject gameFinish) {
        RequestJsonBuilder requestJson = new RequestJsonBuilder();
        requestJson.send(gameFinish);
    }

    public ArrayList<utility.Player> getOnlinePlayersFromServer() {
        return onlinePlayersFromServer;
    }

    public ArrayList<Player> getOfflinePlayersFromServer() {
        return offlinePlayersFromServer;
    }

    public void close(JsonObject closingObj) {
        RequestJsonBuilder requestJson = new RequestJsonBuilder();
        requestJson.send(closingObj);
        try {
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
            reader.running = false;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendInvetation(Integer id) {
        RequestJsonBuilder requestObject = new RequestJsonBuilder();
        requestObject.setType("sendInvitation")
                .addProperty("senderplayerid", playerInfo.id)
                .addProperty("senderusername", playerInfo.username)
                .addProperty("senderscore", playerInfo.score)
                .addProperty("sendtoid", id.toString())
                .send();
        playerInfo.opponentId = String.valueOf(id);
    }

    public void acceptInvetation() {
        RequestJsonBuilder requestObject = new RequestJsonBuilder();
        requestObject.setType("acceptinvetation")
                .addProperty("game_id", playerInfo.gameId.toString())
                .addProperty("accepter", playerInfo.id)
                .addProperty("accepted", playerInfo.opponentId)
                .send();

        try {
            dataOutputStream.writeUTF(requestObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void queryPlayersListsFromServer() {
        RequestJsonBuilder requestObject = new RequestJsonBuilder();
        requestObject.setType("getplayerslist")
                .send();
    }

    public void setPlayersList(JsonObject responseObject) {
        JsonArray newonlinePlayers = responseObject.getAsJsonArray("onlineplayers");
        if (onlinePlayersFromServer != null) onlinePlayersFromServer.clear();
        //System.out.println(newonlinePlayers);
        for (JsonElement rplayerobject : newonlinePlayers) {
            JsonObject playerObject = rplayerobject.getAsJsonObject();
            utility.Player player = new Player(playerObject.get("id").getAsInt(), playerObject.get("score").getAsInt(), playerObject.get("username").getAsString());
            onlinePlayersFromServer.add(player);
        }
        if (offlinePlayersFromServer != null) offlinePlayersFromServer.clear();
        JsonArray newofflinePlayers = responseObject.getAsJsonArray("offlineplayers");
        //System.out.println(offlinePlayers);
        for (JsonElement rplayerobject : newofflinePlayers) {
            JsonObject playerObject = rplayerobject.getAsJsonObject();
            utility.Player player = new Player(playerObject.get("id").getAsInt(), playerObject.get("score").getAsInt(), playerObject.get("username").getAsString());
            offlinePlayersFromServer.add(player);
        }
    }

}