package Controllers;


import Models.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.SignUp;
import ui_modules.logIn;

public class signUpController {
    private SignUp signUpPage;
    public signUpController(SignUp signUpPagee, Stage primaryStage) {
        signUpPage = signUpPagee;

        signUpPagee.setSignUpBtnAction(signUp(primaryStage));
        signUpPagee.setCancelBtnAction(cancelSignUp(primaryStage));
    }

    private EventHandler<ActionEvent> signUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Player player = new Player();
                if (signUpPage.getUsrTxt().getText() != null && signUpPage.getPassTxt().getText() != null && (signUpPage.getUsrTxt().getText().trim().length() > 0) && (signUpPage.getPassConfirmTxt().getText().equals(signUpPage.getPassTxt().getText()))){
               boolean result = ServerConnector.signUp(signUpPage.getUsrTxt().getText(),signUpPage.getPassTxt().getText());
                if(result){
                    logIn root = new logIn(primaryStage);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("ui_modules/Resources/application.css");
                    primaryStage.setTitle("log in screen");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                }
//                else{
//                    // display message *failed to sign up* >>> UI
//                }


            }
        };
    }

    private EventHandler<ActionEvent> cancelSignUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logIn root = new logIn(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("login screen");
                primaryStage.setScene(scene);
                primaryStage.show();
                
                System.out.println("cancel btn clicked");
                signUpPage.getErrMsg().setText("cancel btn clicked");
                signUpPage.getErrMsg().setVisible(true);

            }
        };
    }

}
