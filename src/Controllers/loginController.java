package Controllers;


import Models.Player;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ui_modules.Home;
import ui_modules.SignUp;
import ui_modules.logIn;

public class loginController {
    private logIn loginPage;
    private HomePageController homePageController;
    static boolean validUser= false;
   
    //private ServerConnector serverConnector;

    public loginController(logIn logInPage, Stage primaryStage) {
        loginPage = logInPage;
        loginPage.logInBtnAction(logIn(primaryStage));
        loginPage.signUPBtnAction(signUp(primaryStage));
        loginPage.playOffLineFromEntryScreen(playOffLine(primaryStage));
        //serverConnector=new ServerConnector();


    }

    private EventHandler<ActionEvent> playOffLine(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homePageController = new HomePageController(primaryStage);
                homePageController.loadGameBoard(primaryStage, true,false);
            }
        };
    }

    private EventHandler<ActionEvent> signUp(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SignUp root = new SignUp(primaryStage);
                Scene scene = new Scene(root);
                primaryStage.setTitle("Register screen");
                primaryStage.setScene(scene);
                primaryStage.show();

            }
        };
    }
// create json object : - client -
    private EventHandler<ActionEvent> logIn(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player = new Player();
                

                if (loginPage.getUserNameTxt() != null && loginPage.getPasswordTxt() != null){ 
                   
               String response=ServerConnector.signIn(loginPage.getUserNameTxt(),loginPage.getPasswordTxt());

                 if(ServerConnector.dataOutputStream != null  ){
                      //validUser = player.checkLogin(loginPage.getUserNameTxt(), loginPage.getPasswordTxt());
                     
                          
                    if (response.equals("true")) {
                        
                        validUser=true;
                        Home root = new Home(primaryStage);
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("home screen");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        primaryStage.setOnCloseRequest((e)->{
                            JsonObject closingObj = new JsonObject();
                            closingObj.addProperty("type","client_close");
                            ServerConnector.close(closingObj);
                        });

                    
                    } else{
                        
                        validUser=false;
                        loginPage.getLogInMsg().setVisible(true);
                    }
                      }else{
                         
                       showServerStatus();}
                }
            }
        };
    }

 public  void showServerStatus() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Content here", ButtonType.OK);
        alert.getDialogPane().setMinHeight(150);
        alert.getDialogPane().setMinWidth(50);
        alert.setTitle("warning");
        
      alert.setContentText("press enter in the server screen");
        Image image = new Image(getClass().getResource("/Controllers/../ui_modules/Resources/serverDown.jpg").toExternalForm());    
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.showAndWait();
          
    }
}
