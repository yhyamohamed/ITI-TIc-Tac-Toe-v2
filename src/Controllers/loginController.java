package Controllers;


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

    public loginController(logIn logInPage, Stage primaryStage) {
        loginPage = logInPage;

        loginPage.logInBtnAction(logIn(primaryStage));
        loginPage.signUPBtnAction(signUp(primaryStage));
        loginPage.playOffLineFromEntryScreen(playOffLine(primaryStage));


    }

    private EventHandler<ActionEvent> playOffLine(Stage primaryStage) {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                homePageController = new HomePageController(primaryStage);
                homePageController.loadGameBoard(primaryStage,true);
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

            if(loggedIn()){
                Home root = new Home(primaryStage) ;
                Scene scene = new Scene(root);
                primaryStage.setTitle("home screen");
                primaryStage.setScene(scene);
                primaryStage.show();
                System.out.println(loginPage.getUserNameTxt());
                System.out.println(loginPage.getPasswordTxt());

            }else{
                loginPage.getLogInMsg().setVisible(true);
            }

            
        }
    };
    }
    public boolean loggedIn()
    {
        return true;
    }

}
