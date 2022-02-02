package ui_modules;

import java.util.ArrayList;

import Controllers.GameBoardController;
import Controllers.ServerConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.scene.shape.Line;

public class GameBoard extends AnchorPane {
   public static GameBoardController gameBoardController ;
    protected final Label user1;
    protected final Label scoreUser1;
    protected final Label user2;
    protected final Label scoreUser2;
    protected final Button send;
    protected final Button record;
    protected final Button home;
    protected final TextField textField;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;
    protected final Button button6;
    protected final Button button5;
    protected final Button button4;
    protected final Button button7;
    protected final Button button8;
    protected final Button button9;
    protected final Button start;
    ArrayList<Button> btns = new ArrayList<>();

    public GameBoard(Stage primaryStage, boolean playAgainstPC) {

        user1 = new Label();
        scoreUser1 = new Label();
        user2 = new Label();
        scoreUser2 = new Label();
        send = new Button();
        record = new Button();
        home = new Button();
        textField = new TextField();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        button6 = new Button();
        button5 = new Button();
        button4 = new Button();
        button7 = new Button();
        button8 = new Button();
        button9 = new Button();
        start = new Button();


        setId("AnchorPane");
        setPrefHeight(568.0);
        setPrefWidth(684.0);
        getStyleClass().add("background");

        user1.setLayoutX(25.0);
        user1.setLayoutY(14.0);
        user1.setPrefHeight(26.0);
        user1.setPrefWidth(86.0);
        user1.getStyleClass().add("text");
        user1.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        user1.setText("user 1");

        scoreUser1.setLayoutX(25.0);
        scoreUser1.setLayoutY(47.0);
        scoreUser1.setPrefHeight(26.0);
        scoreUser1.setPrefWidth(101.0);
        scoreUser1.getStyleClass().add("text");
        scoreUser1.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        scoreUser1.setText("score: 1213");

        user2.setLayoutX(587.0);
        user2.setLayoutY(14.0);
        user2.setPrefHeight(17.0);
        user2.setPrefWidth(56.0);
        user2.getStyleClass().add("text");
        user2.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        user2.setText("user 2");

        scoreUser2.setLayoutX(561.0);
        scoreUser2.setLayoutY(47.0);
        scoreUser2.setPrefHeight(26.0);
        scoreUser2.setPrefWidth(101.0);
        scoreUser2.getStyleClass().add("text");
        scoreUser2.setText("score: 1213");
//        if (!playAgainstPC) {
//            send.setId("button");
//            send.setLayoutX(600.0);
//            send.setLayoutY(524.0);
//            send.setMnemonicParsing(false);
//            send.setPrefHeight(32.0);
//            send.setPrefWidth(62.0);
//            send.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
//            send.setText("send");
//        }
        record.setId("button");
        record.setLayoutX(580.0);
        record.setLayoutY(524.0);
        record.setMnemonicParsing(false);
        record.setPrefHeight(20.0);
        record.setPrefWidth(90.0);
        record.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        record.setText("record");
 
        home.setId("button");
        home.setLayoutX(14.0);
        home.setLayoutY(524.0);
        home.setMnemonicParsing(false);
        home.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        home.setText("home");
//        if (!playAgainstPC) {
//            AnchorPane.setBottomAnchor(textField, 12.0);
//            AnchorPane.setLeftAnchor(textField, 313.0);
//            AnchorPane.setRightAnchor(textField, 97.0);
//            textField.setLayoutX(313.0);
//            textField.setLayoutY(531.0);
//            textField.setPrefHeight(25.0);
//            textField.setPrefWidth(179.0);
//            textField.getStyleClass().add("text_field");
//            textField.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
//            textField.setText("enter your msg here ");
//        }
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(10.0);
        gridPane.setLayoutX(152.0);
        gridPane.setLayoutY(82.0);
        gridPane.setMinHeight(342.0);
        gridPane.setMinWidth(359.0);
        gridPane.setPrefHeight(419.0);
        gridPane.setPrefWidth(380.0);
        gridPane.getStyleClass().add("GridPane");
        gridPane.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        gridPane.setVgap(10.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(135.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(116.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(118.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(115.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMaxWidth(121.0);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(115.0);

        rowConstraints.setMaxHeight(271.0);
        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(137.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMaxHeight(182.0);
        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(126.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMaxHeight(130.0);
        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(124.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(button1, 0);
        GridPane.setRowIndex(button1, 0);
        button1.setId("label1");
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(141.0);
        button1.setPrefWidth(158.0);
        button1.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setRowIndex(button2, 0);
        GridPane.setColumnIndex(button2, 1);

        button2.setId("label1");
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(141.0);
        button2.setPrefWidth(138.0);
        button2.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setRowIndex(button6, 1);
        GridPane.setColumnIndex(button6, 2);
        button6.setId("label1");
        button6.setMnemonicParsing(false);
        button6.setPrefHeight(141.0);
        button6.setPrefWidth(157.0);
        button6.getStyleClass().add("label1");
        button6.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setColumnIndex(button3, 2);
        GridPane.setRowIndex(button3, 0);
        button3.setId("label1");
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(130.0);
        button3.setPrefWidth(172.0);
        button3.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setColumnIndex(button5, 1);
        GridPane.setRowIndex(button5, 1);
        button5.setId("label1");
        button5.setMnemonicParsing(false);
        button5.setPrefHeight(130.0);
        button5.setPrefWidth(155.0);

        GridPane.setRowIndex(button4, 1);
        GridPane.setColumnIndex(button4, 0);
        button4.setId("label1");
        button4.setMnemonicParsing(false);
        button4.setPrefHeight(130.0);
        button4.setPrefWidth(190.0);
        button4.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setRowIndex(button7, 2);
        GridPane.setColumnIndex(button7, 0);
        button7.setId("label1");
        button7.setMnemonicParsing(false);
        button7.setPrefHeight(128.0);
        button7.setPrefWidth(223.0);

        GridPane.setColumnIndex(button8, 1);
        GridPane.setRowIndex(button8, 2);
        button8.setId("label1");
        button8.setMnemonicParsing(false);
        button8.setPrefHeight(128.0);
        button8.setPrefWidth(254.0);
        button8.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        GridPane.setColumnIndex(button9, 2);
        GridPane.setRowIndex(button9, 2);
        button9.setId("label1");
        button9.setMnemonicParsing(false);
        button9.setPrefHeight(128.0);
        button9.setPrefWidth(193.0);
        button9.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");

        
        
        
        
        
        
        start.setId("button");
        start.setLayoutX(265.0);
        start.setLayoutY(524.0);
        start.setMnemonicParsing(false);
        start.setPrefHeight(34.0);
        start.setPrefWidth(155.0);
        start.getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        start.setText("Start New Game");
        getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        getStylesheets().add("ui_modules/Resources/gameBoardStyles.css");
        
       

        getChildren().add(user1);
        getChildren().add(scoreUser1);
        getChildren().add(user2);
        getChildren().add(scoreUser2);
//        if (!playAgainstPC) {
//            getChildren().add(send);
//        }
        getChildren().add(record);
        getChildren().add(home);
//        if (!playAgainstPC) {
//            getChildren().add(textField);
//        }
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(button1);
        gridPane.getChildren().add(button2);
        gridPane.getChildren().add(button3);
        gridPane.getChildren().add(button6);
        gridPane.getChildren().add(button5);
        gridPane.getChildren().add(button4);
        gridPane.getChildren().add(button7);
        gridPane.getChildren().add(button8);
        gridPane.getChildren().add(button9);
        getChildren().add(gridPane);
        getChildren().add(start);
        btns.add(button1);
        btns.add(button2);
        btns.add(button3);
        btns.add(button4);
        btns.add(button5);
        btns.add(button6);
        btns.add(button7);
        btns.add(button8);
        btns.add(button9);


// added new
        gameBoardController = new GameBoardController(this, primaryStage, btns, playAgainstPC);

    }


    public void boardBtnsAction(Button b, EventHandler<ActionEvent> Action) {
        b.setOnAction(Action);

    }

    public void restButton(EventHandler<ActionEvent> Action) {
        start.setOnAction(Action);
    }

    public void homeButton(EventHandler<ActionEvent> Action) {
        home.setOnAction(Action);
    }

    public void showWinningTiles(Button[] winningTiles) {
        for (Button tile : winningTiles) {
            tile.setId("winninglabel");
        }
    }

    public void resetAllTiles() {
        for (Button tile : btns) {
            tile.setId("label1");
        }
    }
}