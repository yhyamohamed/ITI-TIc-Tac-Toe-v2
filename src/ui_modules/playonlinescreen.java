package ui_modules;

import Controllers.HomePageController;
import Controllers.playonlineController;
import Models.Player;
import javafx.scene.Scene;
import ui_modules.Home;
import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import Controllers.ServerConnector;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class playonlinescreen extends AnchorPane {
    public static Stage primaryStage1;


    protected final Label onlinepeple;
    protected final Button homebtn;
    protected final VBox vBox;
    protected final VBox vBox2;
//     protected  Label playerName;

    public playonlinescreen(Stage primaryStage) {
        primaryStage1= primaryStage;
        onlinepeple = new Label();
        homebtn = new Button();
        vBox = new VBox();
        vBox2 = new VBox();

        Player player = new Player();
        ArrayList<Player> x = new ArrayList<>();
        x = player.findOnlinePlayers();


        setId("playonlineanchor");
        setPrefHeight(498.0);
        setPrefWidth(598.0);
        getStylesheets().add("/ui_modules/Resources/application.css");

        onlinepeple.setLayoutX(19.0);
        onlinepeple.setLayoutY(45.0);
        onlinepeple.setText("Online people:");
        onlinepeple.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        onlinepeple.setFont(new Font("System Bold Italic", 33.0));


        homebtn.setAccessibleText("loginBtn");
        homebtn.setId("btn");
        homebtn.setLayoutX(477.0);
        homebtn.setLayoutY(426.0);
        homebtn.setMaxHeight(60.0);
        homebtn.setMnemonicParsing(false);
        homebtn.setPrefHeight(38.0);
        homebtn.setPrefWidth(92.0);
        homebtn.getStylesheets().add("/ui_modules/Resources/application.css");
        homebtn.setText("Home");
        homebtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        vBox.setLayoutX(38.0);
        vBox.setLayoutY(94.0);
        vBox.setPrefHeight(400.0);
        vBox.setPrefWidth(315.0);

        vBox2.setLayoutX(300.0);
        vBox2.setLayoutY(97.0);
        vBox2.setPrefHeight(400.0);
        vBox2.setPrefWidth(315.0);


        x.forEach((Player player1) -> {
            if (!player1.getUsername().equals(ServerConnector.PlayerInfo.getUsername())) {
                Label playerName = new Label();
                playerName.setText(player1.getUsername());
                playerName.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                playerName.setFont(new Font("System Bold Italic", 33.0));

                Label playerScore = new Label();
                int y = player1.getScore();
                String s = Integer.toString(y);
                playerScore.setText(s);
                playerScore.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                playerScore.setFont(new Font("System Bold Italic", 33.0));

                vBox.getChildren().add(playerName);
                vBox2.getChildren().add(playerScore);


                playerName.setOnMouseClicked(new EventHandler<Event>() {
                    @Override


                    public void handle(Event event) {
                        //waiting notification
                        Alert alert = new Alert(Alert.AlertType.NONE, "waiting for player...", ButtonType.CANCEL);
                        alert.getDialogPane().setMinHeight(100);
                        alert.getDialogPane().setMinWidth(100);
                        alert.setResizable(false);
                        alert.setTitle("waiting");
                        alert.show();


                        event.consume();
                    }
                });
            }
        });


        getChildren().add(onlinepeple);
        getChildren().add(vBox);
        getChildren().add(vBox2);
        getChildren().add(homebtn);


        new playonlineController(this, primaryStage);

    }

    public static void receivedInvitation(String x) {
        // method shows scene invitation
        Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "waiting for response...", ButtonType.NO, ButtonType.YES);
        alert2.setTitle("invitation");
        alert2.setHeaderText("Do you want to play with " + ServerConnector.PlayerInfo.getUsername() + " ?");
        alert2.setResizable(false);


        Optional<ButtonType> result = alert2.showAndWait();
        ButtonType button = result.orElse(ButtonType.NO);

        if (button == ButtonType.YES) {
            // if condition yes && no : call isma3el methods
            System.out.println("yes"); //accept play
        } else {
            System.out.println("noo"); // reject play
        }
    }
    // new method direct game-board
    public static void goToPlayOnlineScene() {
        GameBoard root = new GameBoard(primaryStage1, false);
        Scene scene = new Scene(root);
        primaryStage1.setTitle("GameBoard screen ");
        primaryStage1.setScene(scene);
        primaryStage1.show();
    }


    public void homeButton(EventHandler<ActionEvent> Action) {
        homebtn.setOnAction(Action);
    }
}