package ui_modules;

import Controllers.playonlineController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class playonlinescreen extends AnchorPane {

    protected final Label onlinepeple;
    protected final Button homebtn;
    protected final ListView listView;

    public playonlinescreen(Stage primaryStage) {

        onlinepeple = new Label();
        homebtn = new Button();
        listView = new ListView();

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

        listView.setLayoutX(29.0);
        listView.setLayoutY(103.0);
        listView.setOpacity(0.1);
        listView.setPrefHeight(341.0);
        listView.setPrefWidth(262.0);

        getChildren().add(onlinepeple);
        getChildren().add(homebtn);
        getChildren().add(listView);
        new playonlineController(this,primaryStage);

    }
     public void homeButton(EventHandler<ActionEvent> Action) {
        homebtn.setOnAction(Action);
    }
}
