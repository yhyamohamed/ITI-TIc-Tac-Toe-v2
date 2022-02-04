package Controllers;


import Models.Player;
import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
                if (loginPage.getUserNameTxt() != null && loginPage.getPasswordTxt() != null) {
                    //validUser = player.checkLogin(loginPage.getUserNameTxt(), loginPage.getPasswordTxt());
                    String response=ServerConnector.signIn(loginPage.getUserNameTxt(),loginPage.getPasswordTxt());
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

                    } else {
                        validUser=false;
                        loginPage.getLogInMsg().setVisible(true);
                    }
                }
            }
        };
    }

}
