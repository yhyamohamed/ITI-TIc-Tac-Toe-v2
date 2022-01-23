package Controllers;

import ui_modules.SignUpPage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.LogInPage;

public class loginController {
    private LogInPage loginPage;

    public loginController(LogInPage logInPage, Stage primaryStage) {
        loginPage = logInPage;

        loginPage.logInBtnAction(logIn());
        loginPage.signUPBtnAction(signUp(primaryStage));

    }

    private EventHandler<ActionEvent> signUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUpPage root = new SignUpPage(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("Rigerster screen");
                primaryStage.setScene(scene);
                primaryStage.show();

            }
        };
    }

    private EventHandler<ActionEvent> logIn() {
    return new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            System.out.println(loginPage.getUserNameTxt());
            System.out.println(loginPage.getPasswordTxt());
            loginPage.getLogInMsg().setVisible(true);
        }
    };
    }


}
