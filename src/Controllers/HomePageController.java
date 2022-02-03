package Controllers;

import Models.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.GameBoard;
import ui_modules.Home;
import ui_modules.SignUp;
import ui_modules.logIn;
import ui_modules.playonlinescreen;

public class HomePageController {
    private  Home homeScreen;
    private boolean playAgainstPC;

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
                loadGameBoard(primaryStage,playAgainstPC);
            }
        };
    }

    private EventHandler<ActionEvent> playAgainstPc(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                playAgainstPC = true;
                loadGameBoard(primaryStage,playAgainstPC);
            }
        };
    }

    public void loadGameBoard(Stage primaryStage, boolean playAgainstPC) {
        GameBoard root = new GameBoard(primaryStage, playAgainstPC);
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
                Player player = new Player();
                player.logout(homeScreen.getUserName());
                logIn root=new  logIn(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("logIn screen");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        };
    }
    }

    

    

