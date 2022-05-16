package utility;

import Controllers.ServerConnector;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class StreamReader extends Thread {
    public boolean running = true;

    private Socket socket;
    private DataInputStream dataInputStream;
    private ServerConnector serverConnector;
    private PlayerInfo playerInfo;
    private JsonObject responseObject;

    public StreamReader(Socket socket) {
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        serverConnector = ServerConnector.getServerConnector();
        playerInfo = PlayerInfo.getPlayerInfo();
        this.running=true;
        this.start();
    }

    @Override
    public void run() {
        System.out.println("readeron");
        System.out.println(running);
        while (running) {
            try {
                String lineSent = dataInputStream.readUTF();
                //if (lineSent == null) throw new IOException();
                responseObject = JsonParser.parseString(lineSent).getAsJsonObject();
                String type = responseObject.get("type").getAsString();
                System.out.println(type);
                switch (type) {
                    case "oponnetmove":
                        int position = responseObject.get("position").getAsInt();
                        serverConnector.opponentsMove(position);
                        break;
                    case "loginresponse":
                        System.out.println("responsethroughthread");

                        break;
                    case "yourinvetationaccepted":
                        performInvetaionAcceptance();
                        break;
                    case "invitationreceived":
                        performInvetaionReceving();
                        break;
                    case "game_record":
                        System.out.println(responseObject);
                        String moves = responseObject.get("moves").getAsString();
                        serverConnector.renderRecordedGame(moves);
                        break;

                    case "opponent_disconnect":
                        performOpponentDisconnected();
                        break;
                    case "update-list":
                        serverConnector.setPlayersList(responseObject);
                        serverConnector.renderPlayersList();
                        break;
                }
            } catch (IOException e) {
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void performInvetaionAcceptance() {
        int accepterID = responseObject.get("whoaccepted").getAsInt();
        playerInfo.opponentId = String.valueOf(accepterID);
        playerInfo.gameId = responseObject.get("game_id").getAsInt();
        playerInfo.playerTurn = false;
        playerInfo.mySign = "O";
        serverConnector.renderGameScene();
    }

    private void performInvetaionReceving() {
        int opponentID = responseObject.get("sender").getAsInt();
        playerInfo.gameId = responseObject.get("game_id").getAsInt();
        playerInfo.opponentId = String.valueOf(opponentID);
        playerInfo.opponentUsername = responseObject.get("opponentusername").getAsString();
        playerInfo.opponentScore = responseObject.get("opponentsscore").getAsInt();
        System.out.println(opponentID + "chalenges you");
        serverConnector.renderInvitaionPopup();
    }

    private void performOpponentDisconnected() {

        serverConnector.renderOpponentDisconnectedWarning();

    }
}
