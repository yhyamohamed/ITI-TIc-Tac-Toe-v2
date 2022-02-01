package ui_modules;

import Controllers.HomePageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class Home extends AnchorPane {

    protected final Button pcbtn;
    protected final Button friendsbtn;
    protected final Button onlinebtn;
    protected final Label username;
    protected final Label label;
    protected final Label score;
    protected final AnchorPane anchorPane;

    public Home(Stage primaryStage) {

        pcbtn = new Button();
        friendsbtn = new Button();
        onlinebtn = new Button();
        username = new Label();
        label = new Label();
        score = new Label();
        anchorPane = new AnchorPane();

        setId("homaanchorpane");
        setPrefHeight(512.0);
        setPrefWidth(664.0);
        setStyle("-fx-border-style: solid;");
        getStylesheets().add("ui_modules/Resources/application.css");

        pcbtn.setAccessibleText("loginBtn");
        pcbtn.setId("btn");
        pcbtn.setLayoutX(202.0);
        pcbtn.setLayoutY(187.0);
        pcbtn.setMaxHeight(60.0);
        pcbtn.setMnemonicParsing(false);
        pcbtn.setPrefHeight(44.0);
        pcbtn.setPrefWidth(187.0);
        pcbtn.getStylesheets().add("ui_modules/Resources/application.css");
        pcbtn.setText("Player VS computer");
        pcbtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        friendsbtn.setAccessibleText("loginBtn");
        friendsbtn.setId("btn");
        friendsbtn.setLayoutX(203.0);
        friendsbtn.setLayoutY(350.0);
        friendsbtn.setMaxHeight(60.0);
        friendsbtn.setMnemonicParsing(false);
        friendsbtn.setPrefHeight(44.0);
        friendsbtn.setPrefWidth(187.0);
        friendsbtn.getStylesheets().add("ui_modules/Resources/application.css");
        friendsbtn.setText("Invite Friend");
        friendsbtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        onlinebtn.setAccessibleText("loginBtn");
        onlinebtn.setId("btn");
        onlinebtn.setLayoutX(202.0);
        onlinebtn.setLayoutY(268.0);
        onlinebtn.setMaxHeight(60.0);
        onlinebtn.setMnemonicParsing(false);
        onlinebtn.setPrefHeight(44.0);
        onlinebtn.setPrefWidth(187.0);
        onlinebtn.getStylesheets().add("ui_modules/Resources/application.css");
        onlinebtn.setText("Local game");
        onlinebtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        username.setLayoutX(126.0);
        username.setLayoutY(14.0);
        username.setText("username");
        username.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        username.setFont(new Font("System Bold Italic", 33.0));

        label.setLayoutX(126.0);
        label.setLayoutY(68.0);
        label.setText("Score:");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        label.setFont(new Font("System Bold Italic", 33.0));

        score.setLayoutX(220.0);
        score.setLayoutY(68.0);
        score.setText("123");
        score.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        score.setFont(new Font("System Bold Italic", 33.0));

        anchorPane.setId("avatar");
        anchorPane.setLayoutX(12.0);
        anchorPane.setLayoutY(8.0);
        anchorPane.setPrefHeight(110.0);
        anchorPane.setPrefWidth(114.0);
        anchorPane.getStylesheets().add("ui_modules/Resources/application.css");

        getChildren().add(pcbtn);
        getChildren().add(friendsbtn);
        getChildren().add(onlinebtn);
        getChildren().add(username);
        getChildren().add(label);
        getChildren().add(score);
        getChildren().add(anchorPane);

        new HomePageController(this, primaryStage);

    }
    public void playLocally(EventHandler< ActionEvent > Action){
        onlinebtn.setOnAction(Action);
        
        }
    public void InviteAfriendToPlay(EventHandler< ActionEvent > Action){
        friendsbtn.setOnAction(Action);
    }
     public void pcbtnBtnAction(EventHandler< ActionEvent > Action){
        pcbtn.setOnAction(Action);
    }

   
}
