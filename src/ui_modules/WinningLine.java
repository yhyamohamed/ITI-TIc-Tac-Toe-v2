package ui_modules;

import javafx.animation.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

import javafx.util.Duration;

public class WinningLine {
    private Line line;
    public WinningLine(BorderPane root,BoardGridPane boardGridPane){
        line = new Line();
        line.setStartX((boardGridPane.getTiles()[0][0]).getTranslateX()+100);
        line.setStartY((boardGridPane.getTiles()[0][0]).getTranslateY()+100);
        line.setEndX((boardGridPane.getTiles()[0][0]).getTranslateX()+100);
        line.setEndY((boardGridPane.getTiles()[0][0]).getTranslateY()+100);
        double centerX = (boardGridPane.getTiles()[0][0]).getTranslateX()+100;
        double centerY= (boardGridPane.getTiles()[0][0]).getTranslateY()+100;
        System.out.println("line");
        Timeline tLine = new Timeline();

        tLine.getKeyFrames().add(new KeyFrame(Duration.seconds(20),
                new KeyValue(line.endXProperty(),centerX),
                new KeyValue(line.endYProperty(),centerY)));
        root.getChildren().add(line);
        tLine.play();
    }
}
