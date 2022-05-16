package Controllers;


import Models.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ui_modules.SignUp;
import ui_modules.logIn;

public class signUpController {
    private SignUp signUpPage;
    private ServerConnector serverConnector;
    public signUpController(SignUp signUpPagee, Stage primaryStage) {
        signUpPage = signUpPagee;
        serverConnector=ServerConnector.getServerConnector();
        signUpPagee.setSignUpBtnAction(signUp(primaryStage));
        signUpPagee.setCancelBtnAction(cancelSignUp(primaryStage));
    }

    private EventHandler<ActionEvent> signUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               // Player player = new Player();
                if (signUpPage.getUsrTxt().getText() != null && signUpPage.getPassTxt().getText() != null && (signUpPage.getUsrTxt().getText().trim().length() > 0) && (signUpPage.getPassConfirmTxt().getText().equals(signUpPage.getPassTxt().getText()))){
               boolean result = serverConnector.signUp(signUpPage.getUsrTxt().getText(),signUpPage.getPassTxt().getText());
                
               if(result){
                        ShowSignUpsucess();
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
    
    
    public void ShowSignUpsucess() {
            Alert alert = new Alert(Alert.AlertType.NONE, "Sign Up Successfuly", ButtonType.OK);
            alert.getDialogPane().setMinHeight(100);
            alert.getDialogPane().setMinWidth(100);
            alert.setTitle("sign up successfully");
            alert.setResizable(false);
//            alert.initOwner(primaryStage);
            alert.show();
    }
}
