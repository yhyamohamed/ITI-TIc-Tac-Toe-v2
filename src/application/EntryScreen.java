
package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ui_modules.logIn;

public class EntryScreen extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    
        logIn root = new logIn(primaryStage);
            
        Scene scene = new Scene(root);
        scene.getStylesheets().add("ui_modules/Resources/application.css");
        primaryStage.setTitle("log in screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
