package Controllers;


import Models.Player;
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
                homePageController.loadGameBoard(primaryStage, true);
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

    private EventHandler<ActionEvent> logIn(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Player player = new Player();
                if (loginPage.getUserNameTxt() != null && loginPage.getPasswordTxt() != null) {
                    //validUser = player.checkLogin(loginPage.getUserNameTxt(), loginPage.getPasswordTxt());
                    String response=ServerConnector.signIn(loginPage.getUserNameTxt(),loginPage.getPasswordTxt());
                    if (response.equals("true")) {
                        Home root = new Home(primaryStage);
                        Scene scene = new Scene(root);
                        primaryStage.setTitle("home screen");
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        System.out.println(loginPage.getUserNameTxt());
                        System.out.println(loginPage.getPasswordTxt());

                    } else {
                        loginPage.getLogInMsg().setVisible(true);
                    }
                }
            }
        };
    }
/*
* made it static
* */
//    public static boolean loggedIn() {
//        return  validUser; // x > boolean
//    }

}
