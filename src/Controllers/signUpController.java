package Controllers;

import ui_modules.SignUpPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class signUpController {
    private SignUpPage signUpPage;
    public signUpController(SignUpPage signUpPagee, Stage primaryStage) {
        signUpPage = signUpPagee;

        signUpPagee.setSignUpBtnAction(signUp(primaryStage));
        signUpPagee.setCancelBtnAction(cancelSignUp(primaryStage));
    }

    private EventHandler<ActionEvent> signUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(signUpPage.getUsrTxt().getText());

                signUpPage.getErrMsg().setText("tring to log you in ");
                signUpPage.getErrMsg().setVisible(true);

            }
        };
    }

    private EventHandler<ActionEvent> cancelSignUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("cancel btn clicked");
                signUpPage.getErrMsg().setText("cancel btn clicked");
                signUpPage.getErrMsg().setVisible(true);

            }
        };
    }

}
