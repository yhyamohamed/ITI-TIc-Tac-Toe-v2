
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.LogInPage;

public class LogInModule extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        LogInPage root = new LogInPage(primaryStage);
        
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("log in screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
