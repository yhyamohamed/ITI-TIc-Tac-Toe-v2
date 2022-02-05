package Controllers;

import Models.Player;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.GameBoard;
import ui_modules.Home;
import ui_modules.SignUp;
import ui_modules.logIn;
import ui_modules.playonlinescreen;
import Controllers.ServerConnector;

public class HomePageController {
    private  Home homeScreen;
    private boolean playAgainstPC;
    private boolean playLocallyWithFriend;

    public HomePageController(Home home, Stage primaryStage) {
        homeScreen = home;
        homeScreen.pcbtnBtnAction(playAgainstPc(primaryStage));
        homeScreen.playLocally(playLocal(primaryStage));
        homeScreen.InviteAfriendToPlay(playWithfriend(primaryStage));
        homeScreen.LogoutAction(logout(primaryStage));
        
        
    }
    public HomePageController(Stage primaryStage) {

    }

    private EventHandler<ActionEvent> playLocal(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playAgainstPC = false;
                playLocallyWithFriend= true;
                loadGameBoard(primaryStage,playAgainstPC,false,true);
            }
        };
    }

    private EventHandler<ActionEvent> playAgainstPc(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playAgainstPC = true;
                loadGameBoard(primaryStage,playAgainstPC,false,false);
            }
        };
    }

    public void loadGameBoard(Stage primaryStage, boolean playAgainstPC,boolean isItAreplay,boolean playLocally) {
        GameBoard root = new GameBoard(primaryStage, playAgainstPC,isItAreplay,playLocally);
        Scene scene = new Scene(root);
        primaryStage.setTitle("GameBoard screen ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private EventHandler<ActionEvent> playWithfriend(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playonlinescreen root=new  playonlinescreen(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("Register screen");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        };
    }

    private EventHandler<ActionEvent> logout(Stage primaryStage) {
      return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                JsonObject closingObj = new JsonObject();
                closingObj.addProperty("type","client_close");
                closingObj.addProperty("username",ServerConnector.PlayerInfo.username);
                ServerConnector.close(closingObj);
                logIn root=new  logIn(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("logIn screen");
                primaryStage.setScene(scene);
                primaryStage.show();
                
            }
        };
    }
    }

    

    

