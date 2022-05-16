/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui_modules.Home;

import ui_modules.playonlinescreen;


public class playonlineController {
    private playonlinescreen onlinescreen;


    public playonlineController(playonlinescreen playonline, Stage primaryStage) {
        onlinescreen = playonline;
        onlinescreen.homeButton(home(primaryStage));
    }

    private EventHandler<ActionEvent> home(Stage primaryStage) {
        return event -> {
            Home root = new Home(primaryStage);
            Scene scene = new Scene(root);
            primaryStage.setTitle("home screen");
            primaryStage.setScene(scene);
            primaryStage.show();

        };
    }

}
