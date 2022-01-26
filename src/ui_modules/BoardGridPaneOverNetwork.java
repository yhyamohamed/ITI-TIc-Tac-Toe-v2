package ui_modules;

import Controllers.GridPaneControllerOverNetwork;
import application.ScenePlayingOverNetwork;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javafx.scene.text.Font;

public class BoardGridPaneOverNetwork {
        private InfoScreenOfPlayingOverNetwork infoScreen;
        private  GridPane gridPane;
        private GridPaneControllerOverNetwork gridPaneController;
        private String player = "X";
        private SinglTile[][] tiles= new SinglTile[3][3];


        public BoardGridPaneOverNetwork(InfoScreenOfPlayingOverNetwork infoscr)
        {
            infoScreen= infoscr;
            gridPane = new GridPane();
            gridPane.setMinSize(ScenePlayingOverNetwork.WIDTH, ScenePlayingOverNetwork.TILE_HIGHT);
            gridPane.setAlignment(Pos.CENTER);
           gridPane.setTranslateX(ScenePlayingOverNetwork.WIDTH/2);
           gridPane.setTranslateY(ScenePlayingOverNetwork.INFO_SCREEN+(ScenePlayingOverNetwork.TILE_HIGHT/2));
            gridPaneController = new GridPaneControllerOverNetwork(this,infoscr);

            for (int row =0 ; row<3 ; row++){

                    RowConstraints rows = new RowConstraints(100);
                        rows.setPrefHeight(100);
                    gridPane.getRowConstraints().add(rows);


            }
                for (int col =0 ; col<3 ; col++) {
                    ColumnConstraints column = new ColumnConstraints(100);
                    column.setPrefWidth(100);
                    gridPane.getColumnConstraints().add(column);
                }
                gridPane.setGridLinesVisible(true);

            for (int row =0 ; row<3 ; row++){
                for (int col = 0; col < 3; col++) {
                    SinglTile ST = new SinglTile();
                    gridPane.add(ST.getSingleTilePane(), col, row);
                    gridPaneController.setActionOfSingleTile(ST.getSingleTilePane(), ST.getLable());

                    tiles[row][col] = ST;
                }
            }

        }
        public GridPane getPane() {
            return gridPane;
        }

        public void toggleTurns(){
            player =(player.equals("X"))?"O":"X";
            infoScreen.changeMsg("player "+player);
        }

        public String getPlayer(){
            return player;
        }
        public  SinglTile[][] getTiles(){
            return tiles;
        }

         public class SinglTile extends StackPane {
            private  StackPane pane;
            private Label mark;

            public  SinglTile(){
                pane = new StackPane();
                mark = new Label("");
                mark.setAlignment(Pos.CENTER);
                mark.setFont(Font.font(25));
                pane.getChildren().add(mark);
            }
            public StackPane getSingleTilePane() {
                return pane;
            }

            public Label getLable(){
                return mark;
            }
            public String getMark() {
                return mark.getText();
            }

            public void setMark(String txt) {
                mark.setText(txt);
            }

        }
      public void resetGame(){
          player = "X";
          gridPaneController.resetGameBoard();
          for (int row =0 ; row<3 ; row++){
              for (int col = 0; col < 3; col++) {

                  tiles[row][col].setMark("");
              }
          }
      }

}
