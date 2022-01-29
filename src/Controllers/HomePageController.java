package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.GameBoard;
import ui_modules.Home;
import ui_modules.SignUp;

public class HomePageController {
    private  Home homeScreen;
    private boolean playAgainstPC;

    public HomePageController(Home home, Stage primaryStage) {
        homeScreen = home;
        homeScreen.pcbtnBtnAction(playAgainstPc(primaryStage));
        homeScreen.playOnline(playWithFriend(primaryStage));
    }
    public HomePageController(Stage primaryStage) {

    }

    private EventHandler<ActionEvent> playWithFriend(Stage primaryStage) {
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
}
