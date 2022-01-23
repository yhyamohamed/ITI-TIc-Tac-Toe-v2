package ui_modules;

import Controllers.loginController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.EventHandler;
public  class LogInPage extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final AnchorPane leftHalf;
    protected final Label userName;
    protected final Label paswod;
    protected final Button loginBtn;
    protected final PasswordField passwordTxt;
    protected final Button signUpBtn;
    protected final Label logInMsg;
    protected final TextField userNameTxt;





    public LogInPage(Stage primaryStage) {

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        leftHalf = new AnchorPane();
        userName = new Label();
        paswod = new Label();
        loginBtn = new Button();
        passwordTxt = new PasswordField();
        signUpBtn = new Button();
        logInMsg = new Label();
        userNameTxt = new TextField();

        setPrefHeight(400.0);
        setPrefWidth(520.0);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(407.0);
        anchorPane.setPrefWidth(228.0);

        imageView.setFitHeight(407.0);
        imageView.setFitWidth(228.0);
        imageView.setLayoutX(4.0);
        imageView.setLayoutY(-7.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        setLeft(anchorPane);

        BorderPane.setAlignment(leftHalf, javafx.geometry.Pos.CENTER);
        leftHalf.setPrefHeight(400.0);
        leftHalf.setPrefWidth(332.0);
        leftHalf.setStyle("-fx-background-color: #ECECE7;");

        userName.setLayoutX(6.0);
        userName.setLayoutY(155.0);
        userName.setText("User Name");
        userName.setFont(new Font(13.0));

        paswod.setLayoutX(7.0);
        paswod.setLayoutY(200.0);
        paswod.setText("Password");

        loginBtn.setLayoutX(82.0);
        loginBtn.setLayoutY(274.0);
        loginBtn.setMnemonicParsing(false);
        loginBtn.setPrefHeight(32.0);
        loginBtn.setPrefWidth(132.0);
        loginBtn.setText("Log In");

        passwordTxt.setLayoutX(84.0);
        passwordTxt.setLayoutY(188.0);
        passwordTxt.setPrefHeight(26.0);
        passwordTxt.setPrefWidth(170.0);
        passwordTxt.setPromptText("Password");

        signUpBtn.setLayoutX(60.0);
        signUpBtn.setLayoutY(323.0);
        signUpBtn.setMnemonicParsing(false);
        signUpBtn.setPrefHeight(32.0);
        signUpBtn.setPrefWidth(182.0);
        signUpBtn.setText("Sign Up");

        logInMsg.setAlignment(javafx.geometry.Pos.CENTER);
        logInMsg.setLayoutX(14.0);
        logInMsg.setLayoutY(241.0);
        logInMsg.setPrefHeight(18.0);
        logInMsg.setPrefWidth(249.0);
        logInMsg.setStyle("-fx-text-fill: red;");
        logInMsg.setText("*invalid log in info");
        logInMsg.setVisible(false);

        userNameTxt.setLayoutX(82.0);
        userNameTxt.setLayoutY(152.0);
        userNameTxt.setPrefHeight(26.0);
        userNameTxt.setPrefWidth(189.0);
        userNameTxt.setPromptText("Username");

        setRight(leftHalf);

        anchorPane.getChildren().add(imageView);
        leftHalf.getChildren().add(userName);
        leftHalf.getChildren().add(paswod);
        leftHalf.getChildren().add(loginBtn);
        leftHalf.getChildren().add(passwordTxt);
        leftHalf.getChildren().add(signUpBtn);
        leftHalf.getChildren().add(logInMsg);
        leftHalf.getChildren().add(userNameTxt);


        new loginController(this,primaryStage);

    }
    public String getPasswordTxt() {
        return passwordTxt.getText();
    }

    public String getUserNameTxt() {
        return userNameTxt.getText();
    }


    public Label getLogInMsg() {
        return logInMsg;
    }
    public void logInBtnAction(EventHandler< ActionEvent > Action){
        loginBtn.setOnAction(Action);
        }
    public void signUPBtnAction(EventHandler< ActionEvent > Action){
        signUpBtn.setOnAction(Action);
    }

}
