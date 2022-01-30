package ui_modules;

import Controllers.signUpController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUp extends AnchorPane {

    protected final AnchorPane anchorpane1;
    protected final AnchorPane anchorpane2;
    protected final TextField usrTxt;
    protected final Label usrLabel;
    protected final Label passLabel;
    protected final PasswordField passTxt;
    protected final Button signUpBtn;
    protected final Button cancelBtn;
    protected final Label title;
    protected final Label passConfirmLabel;
    protected final PasswordField passConfirmTxt;
    private Stage primaryStage;
    protected final Label ErrMsg;

    public SignUp(Stage primaryStage) {

        anchorpane1 = new AnchorPane();
        anchorpane2 = new AnchorPane();
        usrTxt = new TextField();
        usrLabel = new Label();
        passLabel = new Label();
        passTxt = new PasswordField();
        signUpBtn = new Button();
        cancelBtn = new Button();
        title = new Label();
        passConfirmLabel = new Label();
        passConfirmTxt = new PasswordField();
        ErrMsg = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(510.0);
        setPrefWidth(739.0);

        anchorpane1.setId("anchorpane1");
        anchorpane1.setLayoutY(-1.0);
        anchorpane1.setPrefHeight(512.0);
        anchorpane1.setPrefWidth(594.0);
        anchorpane1.getStylesheets().add("ui_modules/Resources/application.css");

        anchorpane2.setId("anchorpane2");
        anchorpane2.setLayoutX(240.0);
        anchorpane2.setMaxWidth(Double.MAX_VALUE);
        anchorpane2.setPrefHeight(512.0);
        anchorpane2.setPrefWidth(499.0);
        anchorpane2.getStylesheets().add("ui_modules/Resources/application.css");

        usrTxt.setAccessibleText("userNameTxt");
        usrTxt.setId("txtf");
        usrTxt.setLayoutX(40.0);
        usrTxt.setLayoutY(169.0);
        usrTxt.setPrefHeight(25.0);
        usrTxt.setPrefWidth(172.0);
        usrTxt.getStylesheets().add("ui_modules/Resources/application.css");

        usrLabel.setAccessibleRoleDescription("userName");
        usrLabel.setAccessibleText("userName");
        usrLabel.setId("label");
        usrLabel.setLayoutX(42.0);
        usrLabel.setLayoutY(139.0);
        usrLabel.getStyleClass().add("userName");
        usrLabel.getStylesheets().add("ui_modules/Resources/application.css");
        usrLabel.setText("Username:");
        usrLabel.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        passLabel.setAccessibleText("paswod");
        passLabel.setId("label");
        passLabel.setLayoutX(42.0);
        passLabel.setLayoutY(207.0);
        passLabel.getStylesheets().add("ui_modules/Resources/application.css");
        passLabel.setText("Password:");
        passLabel.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        passTxt.setAccessibleText("passwordTxt");
        passTxt.setId("txtf");
        passTxt.setLayoutX(40.0);
        passTxt.setLayoutY(233.0);
        passTxt.setPrefHeight(25.0);
        passTxt.setPrefWidth(172.0);
        passTxt.getStylesheets().add("ui_modules/Resources/application.css");

        signUpBtn.setAccessibleText("loginBtn");
        signUpBtn.setId("btn");
        signUpBtn.setLayoutX(28.0);
        signUpBtn.setLayoutY(364.0);
        signUpBtn.setMaxHeight(60.0);
        signUpBtn.setMnemonicParsing(false);
        signUpBtn.setPrefHeight(38.0);
        signUpBtn.setPrefWidth(92.0);
        signUpBtn.getStylesheets().add("ui_modules/Resources/application.css");
        signUpBtn.setText("Sign Up");
        signUpBtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        cancelBtn.setAccessibleText("signUpBtn");
        cancelBtn.setId("btn");
        cancelBtn.setLayoutX(126.0);
        cancelBtn.setLayoutY(365.0);
        cancelBtn.setMaxHeight(58.0);
        cancelBtn.setMnemonicParsing(false);
        cancelBtn.setPrefHeight(38.0);
        cancelBtn.setPrefWidth(92.0);
        cancelBtn.setStyle("-fx-font-weight: bold;");
        cancelBtn.getStylesheets().add("ui_modules/Resources/application.css");
        cancelBtn.setText("Cancel");
        cancelBtn.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        title.setId("logo");
        title.setLayoutX(10.0);
        title.setLayoutY(50.0);
        title.getStylesheets().add("ui_modules/Resources/application.css");
        title.setText("Tic-Tac-Toe");
        title.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));
        title.setFont(new Font("System Bold Italic", 38.0));

        passConfirmLabel.setAccessibleText("paswod");
        passConfirmLabel.setId("label");
        passConfirmLabel.setLayoutX(42.0);
        passConfirmLabel.setLayoutY(275.0);
        passConfirmLabel.getStylesheets().add("ui_modules/Resources/application.css");
        passConfirmLabel.setText("Re-Enter password:");
        passConfirmLabel.setTextFill(javafx.scene.paint.Color.valueOf("#dbe2e5"));

        passConfirmTxt.setAccessibleText("passwordTxt");
        passConfirmTxt.setId("txtf");
        passConfirmTxt.setLayoutX(40.0);
        passConfirmTxt.setLayoutY(305.0);
        passConfirmTxt.setPrefHeight(25.0);
        passConfirmTxt.setPrefWidth(172.0);
        passConfirmTxt.getStylesheets().add("ui_modules/Resources/application.css");

        anchorpane1.getChildren().add(anchorpane2);
        anchorpane1.getChildren().add(usrTxt);
        anchorpane1.getChildren().add(usrLabel);
        anchorpane1.getChildren().add(passLabel);
        anchorpane1.getChildren().add(passTxt);
        anchorpane1.getChildren().add(signUpBtn);
        anchorpane1.getChildren().add(cancelBtn);
        anchorpane1.getChildren().add(title);
        anchorpane1.getChildren().add(passConfirmLabel);
        anchorpane1.getChildren().add(passConfirmTxt);
        getChildren().add(anchorpane1);

        new signUpController(this ,primaryStage);

    }

//    public SignUp(Stage primaryStage) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
        public TextField getUsrTxt() {
                return usrTxt;
        }

        public TextField getPassTxt() {
                return passTxt;
        }

        public TextField getPassConfirmTxt() {
                return passConfirmTxt;
        }

        public Label getErrMsg() {
                return ErrMsg;
        }

        public void setSignUpBtnAction(EventHandler<ActionEvent> Action){
                signUpBtn.setOnAction(Action);
        }
        public void setCancelBtnAction(EventHandler< ActionEvent > Action){
                cancelBtn.setOnAction(Action);
        }
}
