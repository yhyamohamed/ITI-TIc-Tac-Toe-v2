package ui_modules;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class InvitationScreen extends AnchorPane {

    protected final Label playername;
    protected final Label label;
    protected final Label playername1;
    protected final Button rejectbtn;
    protected final Button acceptbtn;

    public InvitationScreen(Stage primaryStage) {

        playername = new Label();
        label = new Label();
        playername1 = new Label();
        rejectbtn = new Button();
        acceptbtn = new Button();

        setId("invitationanch");
        setPrefHeight(503.0);
        setPrefWidth(600.0);
        getStylesheets().add("/ui_modules/Resources/application.css");

        playername.setLayoutX(212.0);
        playername.setLayoutY(178.0);
        playername.setPrefHeight(49.0);
        playername.setPrefWidth(127.0);
        playername.setText("player");
        playername.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        playername.setFont(new Font("System Bold Italic", 33.0));

        label.setLayoutX(119.0);
        label.setLayoutY(227.0);
        label.setPrefHeight(49.0);
        label.setPrefWidth(363.0);
        label.setText("invites you to play");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        label.setFont(new Font("System Bold Italic", 33.0));

        playername1.setLayoutX(28.0);
        playername1.setLayoutY(41.0);
        playername1.setPrefHeight(49.0);
        playername1.setPrefWidth(183.0);
        playername1.setText("Invitation");
        playername1.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        playername1.setFont(new Font("System Bold Italic", 33.0));

        rejectbtn.setAccessibleText("loginBtn");
        rejectbtn.setId("btn");
        rejectbtn.setLayoutX(313.0);
        rejectbtn.setLayoutY(323.0);
        rejectbtn.setMaxHeight(60.0);
        rejectbtn.setMnemonicParsing(false);
        rejectbtn.setPrefHeight(38.0);
        rejectbtn.setPrefWidth(92.0);
        rejectbtn.getStylesheets().add("/ui_modules/Resources/application.css");
        rejectbtn.setText("Reject");
        rejectbtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        acceptbtn.setAccessibleText("loginBtn");
        acceptbtn.setId("btn");
        acceptbtn.setLayoutX(129.0);
        acceptbtn.setLayoutY(326.0);
        acceptbtn.setMaxHeight(60.0);
        acceptbtn.setMnemonicParsing(false);
        acceptbtn.setPrefHeight(38.0);
        acceptbtn.setPrefWidth(92.0);
        acceptbtn.getStylesheets().add("/ui_modules/Resources/application.css");
        acceptbtn.setText("Accept");
        acceptbtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        getChildren().add(playername);
        getChildren().add(label);
        getChildren().add(playername1);
        getChildren().add(rejectbtn);
        getChildren().add(acceptbtn);

    }
    public void AcceptbtnAction(EventHandler< ActionEvent > Action){
        acceptbtn.setOnAction(Action);
    }
    
    public void RejectbtnAction(EventHandler< ActionEvent > Action){
        rejectbtn.setOnAction(Action);
    }
}
