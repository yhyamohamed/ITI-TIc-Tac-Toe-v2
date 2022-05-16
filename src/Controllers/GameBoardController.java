package Controllers;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import ui_modules.GameBoard;
import ui_modules.Home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import ui_modules.logIn;

public class GameBoardController {

    private final GameBoard Gameboard;
    private final Stage primaryStage;



    private final ArrayList<Button> btns;
    private boolean computerTurn = true;
    private boolean opponentsTurn ;
    private boolean currentplayerturn;
    private boolean palyagainstcomputer;
    private int[] marks = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String playerMark = "X";
    private boolean gameEnded;
    private int moves;
    private int gameID;
    private boolean isLocal;

    public GameBoardController(GameBoard gameBoard, Stage primaryStage, ArrayList<Button> btns, boolean playAgainstPC,boolean isItAreplay, boolean isLocal) {
        Gameboard = gameBoard;
        this.primaryStage = primaryStage;
        this.btns = btns;
        this.isLocal = isLocal;
        palyagainstcomputer = playAgainstPC;

        if (isLocal) {
            System.out.println("local");
            for (Button bt : btns) {
                bt.setOnAction(event -> {
                    System.out.println(bt);
                    System.out.println(btns.indexOf(bt));
                    int index = btns.indexOf(bt);
                    bt.setFont(new Font("System Bold Italic", 200));
                    bt.setStyle("-fx-font-size:40");
                    if (!bt.isDisable() && !gameEnded) {
                        bt.setText(getPlayer());
                        bt.setDisable(true);


                        int sign = (bt.getText().equals("X")) ? 8 : 1;
                        toggleTurns();
                        marks[index] = sign;
                        moves++;
                        CheckWinning();
                        System.out.println("aplay");
                        currentplayerturn=true;

                /*if (!palyagainstcomputer && opponentsTurn) {
                    opponentsTurn = false;
                    ServerConnector.opponentsMove();

                }*/
                        if (!gameEnded && computerTurn && palyagainstcomputer) {
                            computerTurn();
                        } else if (!gameEnded && !computerTurn && palyagainstcomputer) {
                            computerTurn = true;
                        }
                        CheckWinning();
                    }
                });

            }
        }

        if(!isItAreplay && !isLocal){


        if(palyagainstcomputer)
        {
            ServerConnector.PlayerInfo.allowFire=true;

        }
        if(!playAgainstPC) {
            ServerConnector.assignGameBoardButtons(btns);
            gameID=ServerConnector.PlayerInfo.gameId;
           // ServerConnector.getopponentId();

            primaryStage.setOnCloseRequest((e)->{
                JsonObject closingObj = new JsonObject();
                closingObj.addProperty("type","client_close_while_playing");
                closingObj.addProperty("opponentId", ServerConnector.PlayerInfo.opponentId);
                ServerConnector.close(closingObj);
            });

        }
        currentplayerturn=ServerConnector.PlayerInfo.playerTurn;

        /*if(currentplayerturn)
        {
            opponentsTurn=false;
        }else {
            opponentsTurn=false;
            ServerConnector.opponentsMove();
            currentplayerturn=true;

        }*/

        //array for each button
        for (Button bt : btns) {
            bt.setOnAction(event -> {
                if (ServerConnector.PlayerInfo.allowFire){
              System.out.println(bt);
               System.out.println(btns.indexOf(bt));
                    int index = btns.indexOf(bt);
                bt.setFont(new Font("System Bold Italic", 200));
                bt.setStyle("-fx-font-size:40");
                if (!bt.isDisable() && !gameEnded) {
                    bt.setText(getPlayer());
                    bt.setDisable(true);


                    int sign = (bt.getText().equals("X")) ? 8 : 1;
                    toggleTurns();
                    marks[index] = sign;
                    moves++;
                    CheckWinning();
                    if(ServerConnector.PlayerInfo.playerTurn) {
                        ServerConnector.play(index, sign);
                        ServerConnector.PlayerInfo.playerTurn=false;
                        ServerConnector.PlayerInfo.allowFire=false;
                    }
                    System.out.println("aplay");
                    currentplayerturn=true;

                    /*if (!palyagainstcomputer && opponentsTurn) {
                        opponentsTurn = false;
                        ServerConnector.opponentsMove();

                    }*/
                    if (!gameEnded && computerTurn && palyagainstcomputer) {
                        computerTurn();
                    } else if (!gameEnded && !computerTurn && palyagainstcomputer) {
                        computerTurn = true;
                    }
                    CheckWinning();
                }
            }
            });

        }
        }else{
            ServerConnector.assignGameBoardButtons(btns);
        }
        Gameboard.restButton(resetGame());
        Gameboard.homeButton(HomeScreen(primaryStage));
        Gameboard.replay(showRecord(primaryStage));
    }

    //home screen 
    private EventHandler<ActionEvent> HomeScreen(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (loginController.validUser) {
                    Home root = new Home(primaryStage);
                    Scene scene = new Scene(root);
                    primaryStage.setTitle("home screen");
                    primaryStage.setScene(scene);
                    primaryStage.show();
              } 
                else {
                    logIn root = new logIn(primaryStage);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("ui_modules/Resources/application.css");
                    primaryStage.setTitle("log in screen");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
            }

        };
    }

    private EventHandler<ActionEvent> showRecord(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("req records");
                JsonObject showRecObj = new JsonObject();
                showRecObj.addProperty("type","request_record");
                showRecObj.addProperty("game_id",gameID);
                ServerConnector.sendReplayreq(showRecObj);
//                GameBoard root = new GameBoard(primaryStage, true,true);
//                Scene scene = new Scene(root);
//                primaryStage.setTitle("GameBoard screen ");
//                primaryStage.setScene(scene);
//                primaryStage.show();
            }
        };
    }
    public ArrayList<Button> getBtns() {
        return btns;
    }

    public int[] getMarks() {
        return marks;
    }

    //how computer work
    private void computerTurn() {

        computerTurn = false;
        int targetIndex = (int) (Math.random() * 8);
        while (marks[targetIndex] != 0) {
            targetIndex = (int) (Math.random() * 8);
        }
        Button targetBtn = btns.get(targetIndex);
        targetBtn.fire();
    }

    // check for Tie
    private void CheckWinning() {
        if (!(winningRowFounded() || winningColFounded())) {
            if (!(checkTopRight() || checkTopLeft())) {
                checkFoeTie();
            }
        }

    }


    //
    public void toggleTurns() {
        playerMark = (playerMark.equals("X")) ? "O" : "X";
//        infoScreen.changeMsg("player "+player);
    }


    public String getPlayer() {
        return playerMark;
    }

    private boolean winningRowFounded() {
        boolean founded = false;
        if (!gameEnded) {
//            0   1   2
//            3   4   5
//            6   7   8
            for (int tile = 0; tile < marks.length - 2; tile += 3) {
                if (marks[tile] == 0 || marks[tile+1]==0 || marks[tile+2]==0) continue;
                if ((marks[tile] == marks[tile + 1]) && (marks[tile] == marks[tile + 2])) {
                    String wins = (marks[tile] == 8) ? "X" : "O";
                    gameEnding(wins);
                    founded = true;
                    Button[] winningTiles = {btns.get(tile), btns.get(tile + 1), btns.get(tile + 2)};
                    Gameboard.showWinningTiles(winningTiles);
//                    ShowWinDialog();

                }
            }
            /*for (int tile = marks.length - 1; tile >= 0; tile -= 3) {
                if (marks[tile] == 0) continue;
                if ((marks[tile] == marks[tile - 1]) && (marks[tile] == marks[tile - 2])) {
                    String wins = (marks[tile] == 9) ? "X" : "O";
                    gameEnding(wins);
                    founded = true;
                    Button[] winningTiles = {btns.get(tile), btns.get(tile - 1), btns.get(tile - 2)};
                    Gameboard.showWinningTiles(winningTiles);
                    ShowWinDialog();
                }
            }*/
        }
        return founded;
    }


    private boolean winningColFounded() {
        boolean founded = false;
        if (!gameEnded) {
            for (int tile = 0; tile < 3; tile++) {
                if (marks[tile] == 0 || marks[tile+3] == 0 || marks[tile+6] == 0) continue;
                if ((marks[tile] == marks[tile + 3]) && (marks[tile] == marks[tile + 6])) {
                    String wins = (marks[tile] == 8) ? "X" : "O";
                    gameEnding(wins);
                    founded = true;
                    Button[] winningTiles = {btns.get(tile), btns.get(tile + 3), btns.get(tile + 6)};
                    Gameboard.showWinningTiles(winningTiles);
//                    ShowWinDialog();
                }
            }
        }
        return founded;
    }

    private boolean checkTopRight() {
        boolean founded = false;
        if (!gameEnded) {
            int tile = 0;
            if (marks[tile] != 0) {
                if ((marks[tile] == marks[tile + 4]) && (marks[tile] == marks[tile + 8])) {
                    String wins = (marks[tile] == 8) ? "X" : "O";
                    System.out.println("top right");
                    gameEnding(wins);
                    founded = true;
                    Button[] winningTiles = {btns.get(tile), btns.get(tile + 4), btns.get(tile + 8)};
                    Gameboard.showWinningTiles(winningTiles);
//                    ShowWinDialog();
                }
            }
        }
        return founded;
    }

    private boolean checkTopLeft() {
        boolean founded = false;
        if (!gameEnded) {
            int tile = 2;
            if (marks[tile] != 0) {
                if ((marks[tile] == marks[tile + 2]) && (marks[tile] == marks[tile + 4])) {
                    String wins = (marks[tile] == 8) ? "X" : "O";
                    System.out.println("top right");
                    gameEnding(wins);
                    founded = true;
                    Button[] winningTiles = {btns.get(tile), btns.get(tile + 2), btns.get(tile + 4)};
                    Gameboard.showWinningTiles(winningTiles);
//                    ShowWinDialog();
                }
            }
        }
        return founded;
    }

    private void checkFoeTie() {
        if (!gameEnded && moves == 9) {
            gameEnded = true;
            System.out.println("tieee");
//            infoScreen.changeMsg("no one won its a tie!");
//            infoScreen.showBtn();
        }
    }

    private void gameEnding(String wins) {
        gameEnded = true;
        btns.forEach(bt -> {
            bt.setDisable(true);
        });
        Gameboard.resetAllTiles();
        Gameboard.getRecord().setVisible(true);

        if(!isLocal) {

            if (wins.equals("X") && ServerConnector.PlayerInfo.mySign.equals("X")) {
                System.out.println("you won");
                makeFinishGameObj();
                ShowWinDialog();
            }
            else if (wins.equals("O") && ServerConnector.PlayerInfo.mySign.equals("O")) {
                System.out.println("you won");
                makeFinishGameObj();
                ShowWinDialog();
            } else {
                System.out.println("you lost");
                ShowLoseDialog();
            }
        }

    }
public void makeFinishGameObj()
{
    JsonObject gameFinish = new JsonObject();
    gameFinish.addProperty("type","finish_game");
    gameFinish.addProperty("winner",ServerConnector.PlayerInfo.id);
    gameFinish.addProperty("looser",ServerConnector.PlayerInfo.opponentId);
    gameFinish.addProperty("game_id",ServerConnector.PlayerInfo.gameId);
    ServerConnector.sendFinishingObj(gameFinish);
}
    public EventHandler<ActionEvent> resetGame() {
        return event -> {
            gameEnded = false;
            computerTurn=true;
            moves = 0;
            playerMark = "X";
            marks = new int[marks.length];
            Gameboard.resetAllTiles();
            btns.forEach(bt -> {

                bt.setDisable(false);
                bt.setText("");

            });
        };
    }

    public void ShowWinDialog() {
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/Controllers/../ui_modules/Resources/winner.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        Alert alert = new Alert(AlertType.INFORMATION, "Content here", ButtonType.OK);
        alert.getDialogPane().setMinHeight(230);
        alert.getDialogPane().setMinWidth(210);
        alert.setTitle("You win!!");

        VBox content = new VBox(mediaView);
        content.setAlignment(Pos.CENTER);
        alert.getDialogPane().setContent(content);

        alert.setOnShowing(e -> player.play());
        alert.initOwner(primaryStage);
        alert.show();
    }

    public void ShowLoseDialog() {
        MediaPlayer player = new MediaPlayer(new Media(getClass().getResource("/Controllers/../ui_modules/Resources/losser.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        Alert alert = new Alert(AlertType.INFORMATION, "Content here", ButtonType.OK);
        alert.getDialogPane().setMinHeight(210);
        alert.getDialogPane().setMinWidth(210);
        alert.setTitle("You lose!!");

        VBox content = new VBox(mediaView);
        content.setAlignment(Pos.CENTER);
        alert.getDialogPane().setContent(content);

        alert.setOnShowing(e -> player.play());
        alert.initOwner(primaryStage);
        alert.show();
    }
     /*  
     mfe4 GIF monasb no7to lsa bndwr 
     
     
     public void ShowTieDialog(){
        MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("/Controllers/../ui_modules/Resources/------").toExternalForm()));
        MediaView mediaView = new MediaView(player);
        
        Alert alert = new Alert(AlertType.INFORMATION, "Content here", ButtonType.OK);
        alert.getDialogPane().setMinHeight(210);
        alert.getDialogPane().setMinWidth(210);
        alert.setTitle("You lose!!");
                
        VBox content = new VBox(mediaView);
        content.setAlignment(Pos.CENTER);
        alert.getDialogPane().setContent(content);

        alert.setOnShowing(e -> player.play());
        alert.show();
    }*/


}
