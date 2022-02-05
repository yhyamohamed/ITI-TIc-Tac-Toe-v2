package Controllers;


import static Controllers.ServerConnector.dataOutputStream;
import Models.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
                
           if (signUpPage.getUsrTxt().getText() != null && signUpPage.getPassTxt().getText() != null && (signUpPage.getUsrTxt().getText().trim().length() > 0) && (signUpPage.getPassConfirmTxt().getText().equals(signUpPage.getPassTxt().getText()))  ){
                
                 
              
             if(ServerConnector.dataOutputStream != null  ){
                    System.out.println("not null");
                boolean result = ServerConnector.signUp(signUpPage.getUsrTxt().getText(),signUpPage.getPassTxt().getText());
               if(result){
                      System.out.println("result is true");
                        ShowSignUpsucess();
                    logIn root = new logIn(primaryStage);
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add("ui_modules/Resources/application.css");
                    primaryStage.setTitle("log in screen");
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }
                
          
                }
                }else{  
               System.out.println("sss");
               showServerStatus();
             }
            
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
            alert.show();
    }
    
     
 public  void showServerStatus() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content here", ButtonType.OK);
         Image image = new Image(getClass().getResource("/Controllers/../ui_modules/Resources/serverDown.jpg").toExternalForm());    
        ImageView imageView = new ImageView(image);
    
    
        alert.getDialogPane().setMinHeight(150);
        alert.getDialogPane().setMinWidth(50);
        alert.setTitle("warning");
         alert.setContentText("press enter in the server screen");
    
      
        alert.setGraphic(imageView);
        alert.showAndWait();
       
    }
}
