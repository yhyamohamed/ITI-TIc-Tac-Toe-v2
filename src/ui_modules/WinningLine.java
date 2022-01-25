package ui_modules;

import javafx.animation.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

import javafx.util.Duration;

public class WinningLine {
    private Line line;
    private BorderPane root;
    public WinningLine(){
        line = new Line();
    }
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
    public WinningLine(BorderPane root, StackPane firstTile, StackPane middleTile, StackPane lastTile)
    {
        this.root=root;
        line = new Line();
        int X=50;
        int Y=150;
        int d=100;
        line.setStartX(X+d*GridPane.getColumnIndex(firstTile));
        line.setStartY(Y+d*GridPane.getRowIndex(firstTile));
        line.setEndX(X+d*GridPane.getColumnIndex(lastTile));
        line.setEndY(Y+d*GridPane.getRowIndex(lastTile));
        double centerX = X+d*GridPane.getColumnIndex(firstTile);
        double centerY= Y+d*GridPane.getRowIndex(firstTile);
        /*System.out.println("line");
        Timeline tLine = new Timeline();

        tLine.getKeyFrames().add(new KeyFrame(Duration.seconds(20),
                new KeyValue(line.endXProperty(),centerX),
                new KeyValue(line.endYProperty(),centerY)));
                tLine.play();
*/
        root.getChildren().add(line);

    }
    public void removeThisLine()
    {
        root.getChildren().remove(line);
    }
}
