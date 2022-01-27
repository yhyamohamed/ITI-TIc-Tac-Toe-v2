package ui_modules;

import Controllers.loginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class logIn extends AnchorPane {

    protected final AnchorPane anchorpane2;
    protected final TextField userNameTxt;
    protected final Label userName;
    protected final Label paswod;
    protected final PasswordField passwordTxt;
    protected final Button loginBtn;
    protected final Button signUpBtn;
    protected final Label title;
    protected final Label logInMsg;

    public logIn(Stage primaryStage) {

        anchorpane2 = new AnchorPane();
        userNameTxt = new TextField();
        userName = new Label();
        paswod = new Label();
        passwordTxt = new PasswordField();
        loginBtn = new Button();
        signUpBtn = new Button();
        title = new Label();
        logInMsg = new Label();

        setId("anchorpane1");
        setPrefHeight(512.0);
        setPrefWidth(594.0);
        getStylesheets().add("/ui_modules/../application/application.css");

        anchorpane2.setId("anchorpane2");
        anchorpane2.setLayoutX(237.0);
        anchorpane2.setMaxWidth(Double.MAX_VALUE);
        anchorpane2.setPrefHeight(512.0);
        anchorpane2.setPrefWidth(500.0);
        anchorpane2.getStylesheets().add("/ui_modules/../application/application.css");

        userNameTxt.setAccessibleText("userNameTxt");
        userNameTxt.setId("txtf");
        userNameTxt.setLayoutX(40.0);
        userNameTxt.setLayoutY(185.0);
        userNameTxt.setPrefHeight(25.0);
        userNameTxt.setPrefWidth(172.0);
         userNameTxt.setPromptText("Username");
        userNameTxt.getStylesheets().add("/ui_modules/../application/application.css");

        userName.setAccessibleRoleDescription("userName");
        userName.setAccessibleText("userName");
        userName.setId("label");
        userName.setLayoutX(42.0);
        userName.setLayoutY(157.0);
        userName.getStyleClass().add("userName");
        userName.getStylesheets().add("/ui_modules/../application/application.css");
        userName.setText("Username:");
        userName.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        paswod.setAccessibleText("paswod");
        paswod.setId("label");
        paswod.setLayoutX(42.0);
        paswod.setLayoutY(238.0);
        paswod.getStylesheets().add("/ui_modules/../application/application.css");
        paswod.setText("Password:");
        paswod.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        passwordTxt.setAccessibleText("passwordTxt");
        passwordTxt.setId("txtf");
        passwordTxt.setLayoutX(40.0);
        passwordTxt.setLayoutY(263.0);
        passwordTxt.setPrefHeight(25.0);
        passwordTxt.setPrefWidth(172.0);
        passwordTxt.setPromptText("Password");
        passwordTxt.getStylesheets().add("/ui_modules/../application/application.css");

        loginBtn.setAccessibleText("loginBtn");
        loginBtn.setId("btn");
        loginBtn.setLayoutX(27.0);
        loginBtn.setLayoutY(325.0);
        loginBtn.setMaxHeight(60.0);
        loginBtn.setMnemonicParsing(false);
        loginBtn.setPrefHeight(38.0);
        loginBtn.setPrefWidth(92.0);
        loginBtn.getStylesheets().add("/ui_modules/../application/application.css");
        loginBtn.setText("Log In");
        loginBtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        

        signUpBtn.setAccessibleText("signUpBtn");
        signUpBtn.setId("btn");
        signUpBtn.setLayoutX(129.0);
        signUpBtn.setLayoutY(325.0);
        signUpBtn.setMaxHeight(58.0);
        signUpBtn.setMnemonicParsing(false);
        signUpBtn.setPrefHeight(38.0);
        signUpBtn.setPrefWidth(92.0);
        signUpBtn.setStyle("-fx-font-weight: bold;");
        signUpBtn.getStylesheets().add("/ui_modules/../application/application.css");
        signUpBtn.setText("Sign Up");
        signUpBtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        title.setId("logo");
        title.setLayoutX(10.0);
        title.setLayoutY(50.0);
        title.getStylesheets().add("/ui_modules/../application/application.css");
        title.setText("Tic-Tac-Toe");
        title.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        title.setFont(new Font("System Bold Italic", 38.0));
        
        logInMsg.setAlignment(javafx.geometry.Pos.CENTER);
        logInMsg.setLayoutX(14.0);
        logInMsg.setLayoutY(241.0);
        logInMsg.setPrefHeight(18.0);
        logInMsg.setPrefWidth(249.0);
        logInMsg.setStyle("-fx-text-fill: red;");
        logInMsg.setText("*invalid log in info");
        logInMsg.setVisible(false);

        getChildren().add(anchorpane2);
        getChildren().add(userNameTxt);
        getChildren().add(userName);
        getChildren().add(paswod);
        getChildren().add(passwordTxt);
        getChildren().add(loginBtn);
        getChildren().add(signUpBtn);
        getChildren().add(title);
        
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

