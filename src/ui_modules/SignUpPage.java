package ui_modules;

import Controllers.signUpController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignUpPage extends AnchorPane{
    protected final AnchorPane anchorPane;
    protected final Label formTitle;
    protected final Label usrLabel;
    protected final TextField usrTxt;
    protected final Label passLabel;
    protected final TextField passTxt;
    protected final Label passConfirmLabel;
    protected final TextField passConfirmTxt;
    protected final Button signUpBtn;
    protected final Button cancelBtn;
    protected final Label ErrMsg;




        public SignUpPage(Stage primaryStage){
            anchorPane = new AnchorPane();
            formTitle = new Label();
            usrLabel = new Label();
            usrTxt = new TextField();
            passLabel = new Label();
            passTxt = new TextField();
            passConfirmLabel = new Label();
            passConfirmTxt = new TextField();
            signUpBtn = new Button();
            cancelBtn = new Button();
            ErrMsg = new Label();


            setPrefHeight(400.0);
            setPrefWidth(520.0);

            anchorPane.setPrefHeight(82.0);
            anchorPane.setPrefWidth(520.0);
            anchorPane.setStyle("-fx-background-color: blue;");

            formTitle.setAlignment(javafx.geometry.Pos.CENTER);
            formTitle.setLayoutX(96.0);
            formTitle.setLayoutY(33.0);
            formTitle.setPrefHeight(18.0);
            formTitle.setPrefWidth(67.0);
            formTitle.setStyle("-fx-text-fill: white;");
            formTitle.setText("Register");

            usrLabel.setLayoutX(75.0);
            usrLabel.setLayoutY(106.0);
            usrLabel.setText("user name :");

            usrTxt.setLayoutX(163.0);
            usrTxt.setLayoutY(102.0);
            usrTxt.setPrefHeight(26.0);
            usrTxt.setPrefWidth(222.0);
            usrTxt.setPromptText("username");

            passLabel.setLayoutX(75.0);
            passLabel.setLayoutY(155.0);
            passLabel.setText("password");

            passTxt.setLayoutX(163.0);
            passTxt.setLayoutY(151.0);
            passTxt.setPrefHeight(26.0);
            passTxt.setPrefWidth(204.0);
            passTxt.setPromptText("password");

            passConfirmLabel.setLayoutX(61.0);
            passConfirmLabel.setLayoutY(211.0);
            passConfirmLabel.setPrefHeight(35.0);
            passConfirmLabel.setPrefWidth(97.0);
            passConfirmLabel.setText("re-enter pass");

            passConfirmTxt.setLayoutX(170.0);
            passConfirmTxt.setLayoutY(206.0);
            passConfirmTxt.setPrefHeight(26.0);
            passConfirmTxt.setPrefWidth(204.0);
            passConfirmTxt.setPromptText("password confirmation");

            signUpBtn.setLayoutX(158.0);
            signUpBtn.setLayoutY(282.0);
            signUpBtn.setMnemonicParsing(false);
            signUpBtn.setPrefHeight(32.0);
            signUpBtn.setPrefWidth(212.0);
            signUpBtn.setStyle("-fx-background-color: blue;");
            signUpBtn.setText("Sign Me Up");

            cancelBtn.setLayoutX(208.0);
            cancelBtn.setLayoutY(329.0);
            cancelBtn.setMnemonicParsing(false);
            cancelBtn.setPrefHeight(32.0);
            cancelBtn.setPrefWidth(119.0);
            cancelBtn.setStyle("-fx-background-color: red;");
            cancelBtn.setText("cancel");

            ErrMsg.setAlignment(javafx.geometry.Pos.CENTER);
            ErrMsg.setLayoutX(147.0);
            ErrMsg.setLayoutY(246.0);
            ErrMsg.setPrefHeight(18.0);
            ErrMsg.setPrefWidth(222.0);
            ErrMsg.setStyle("-fx-text-fill: red;");
            ErrMsg.setText("Errors");
            ErrMsg.setVisible(false);

            anchorPane.getChildren().add(formTitle);
            getChildren().add(anchorPane);
            getChildren().add(usrLabel);
            getChildren().add(usrTxt);
            getChildren().add(passLabel);
            getChildren().add(passTxt);
            getChildren().add(passConfirmLabel);
            getChildren().add(passConfirmTxt);
            getChildren().add(signUpBtn);
            getChildren().add(cancelBtn);
            getChildren().add(ErrMsg);

            new signUpController(this ,primaryStage);

    }
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
