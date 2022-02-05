package ui_modules;



import Controllers.ServerConnector;

import Controllers.HomePageController;


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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    protected final VBox Vonline;
    protected final Label offlinepeople;
    protected final VBox VScoreonline;
    protected final VBox Voffline;
    protected final VBox VScoreoffline;

    public playonlinescreen(Stage primaryStage) {
        primaryStage1= primaryStage;
        onlinepeple = new Label();
        homebtn = new Button();


        Vonline = new VBox();
        offlinepeople = new Label();
        VScoreonline = new VBox();
        Voffline = new VBox();
        VScoreoffline = new VBox();
        
        Player player=new Player();
        ArrayList<Player> x = new ArrayList<>();
        x=player.findOnlinePlayers();
        
        ArrayList<Player> f = new ArrayList<>();
        f=player.findOfflinePlayers();

 


        setId("playonlineanchor");
        setPrefHeight(498.0);
        setPrefWidth(671.0);
        getStylesheets().add("/ui_modules/Resources/application.css");

        onlinepeple.setLayoutX(10.0);
        onlinepeple.setLayoutY(25.0);
        onlinepeple.setText("Online players:");
        onlinepeple.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        onlinepeple.setFont(new Font("System Bold Italic", 33.0));


        homebtn.setAccessibleText("loginBtn");
        homebtn.setId("btn");
        homebtn.setLayoutX(565.0);
        homebtn.setLayoutY(446.0);
        homebtn.setMaxHeight(60.0);
        homebtn.setMnemonicParsing(false);
        homebtn.setPrefHeight(38.0);
        homebtn.setPrefWidth(92.0);
        homebtn.getStylesheets().add("/ui_modules/Resources/application.css");
        homebtn.setText("Home");
        homebtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        
        Vonline.setLayoutX(15.0);
        Vonline.setLayoutY(78.0);
        Vonline.setPrefHeight(364.0);
        Vonline.setPrefWidth(186.0);

        offlinepeople.setLayoutX(350.0);
        offlinepeople.setLayoutY(24.0);
        offlinepeople.setText("Offline players:");
        offlinepeople.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        offlinepeople.setFont(new Font("System Bold Italic", 33.0));

        VScoreonline.setLayoutX(185.0);
        VScoreonline.setLayoutY(78.0);
        VScoreonline.setPrefHeight(364.0);
        VScoreonline.setPrefWidth(128.0);

        Voffline.setLayoutX(348.0);
        Voffline.setLayoutY(80.0);
        Voffline.setPrefHeight(358.0);
        Voffline.setPrefWidth(186.0);

        VScoreoffline.setLayoutX(542.0);
        VScoreoffline.setLayoutY(81.0);
        VScoreoffline.setPrefHeight(350.0);
        VScoreoffline.setPrefWidth(117.0);
        
        
        x.forEach((Player player1) ->{
                if(!player1.getUsername().equals(ServerConnector.PlayerInfo.getUsername())){
                    Label playerName=new Label();
                    playerName.setText(player1.getId()+": "+ player1.getUsername());
                    playerName.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                    playerName.setFont(new Font("System Bold Italic", 33.0));
                    
                    Label playerScore=new Label();
                    int y=player1.getScore();
                    String s=Integer.toString(y);
                    playerScore.setText(s);
                    playerScore.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                    playerScore.setFont(new Font("System Bold Italic", 33.0));

                    Vonline.getChildren().add(playerName);
                    VScoreonline.getChildren().add(playerScore);

                    playerName.setOnMouseClicked(new EventHandler<Event>() {
                        @Override
                        
                        
                        public void handle(Event event) {
                            //waiting notification 
                            Alert alert = new Alert(Alert.AlertType.NONE, "waiting for player...", ButtonType.CANCEL);
                            Label chosenLable=(Label) event.getSource();
                            int playerID=Integer.parseInt(chosenLable.getText().substring(0,1));
                            ServerConnector.sendInvetation(playerID);
                            alert.getDialogPane().setMinHeight(100);
                            alert.getDialogPane().setMinWidth(100);
                            alert.setResizable(false);
                            alert.setTitle("waiting");
                            alert.show();
                                     

                                  //the other player 
//                                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "waiting for response...", ButtonType.NO,ButtonType.YES);
//                                alert2.setTitle("invitation");
//                                alert2.setHeaderText("Do you want to play with "+ServerConnector.PlayerInfo.getUsername()+" ?");
//                                alert2.setResizable(false);
//                            
//
//                                Optional<ButtonType> result = alert.showAndWait();
//                                ButtonType button = result.orElse(ButtonType.NO);
//
//                                if (button == ButtonType.YES) {
//                                    System.out.println("yes"); //accept play
//                                } else {
//                                    System.out.println("noo"); // reject play
//                                }
                            
                            
                            event.consume();
                        }
                    });
                }
        });
        
        
        f.forEach((Player player2) ->{
                if(!player2.getUsername().equals(ServerConnector.PlayerInfo.getUsername())){
                    Label playerName=new Label();
                    playerName.setText(player2.getUsername());
                    playerName.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                    playerName.setFont(new Font("System Bold Italic", 33.0));
                    
                    Label playerScore=new Label();
                    int y=player2.getScore();
                    String s=Integer.toString(y);
                    playerScore.setText(s);
                    playerScore.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
                    playerScore.setFont(new Font("System Bold Italic", 33.0));

                    Voffline.getChildren().add(playerName);
                    VScoreoffline.getChildren().add(playerScore);

                }
        });

        getChildren().add(onlinepeple);
       
        getChildren().add(Vonline);
        getChildren().add(offlinepeople);
        getChildren().add(VScoreonline);
        getChildren().add(Voffline);
        getChildren().add(VScoreoffline);
         getChildren().add(homebtn);

   
     new playonlineController(this,primaryStage);




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
        GameBoard root = new GameBoard(primaryStage1, false,false);
        Scene scene = new Scene(root);
        primaryStage1.setTitle("GameBoard screen ");
        primaryStage1.setScene(scene);
        primaryStage1.show();
    }


    public void homeButton(EventHandler<ActionEvent> Action) {
        homebtn.setOnAction(Action);
    }
}