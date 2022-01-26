package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import ui_modules.BoardGridPaneOverNetwork;
import ui_modules.InfoScreenOfPlayingOverNetwork;

public class GridPaneControllerOverNetwork {

        private BoardGridPaneOverNetwork gameBoard;
        private boolean gameEnded ;
        private InfoScreenOfPlayingOverNetwork infoScreen;
//        private HashMap<Integer,String> tilesMarks = new HashMap<>();
        private int[][] marks = new int[3][3];
    private int moves;

    public GridPaneControllerOverNetwork(BoardGridPaneOverNetwork GB, InfoScreenOfPlayingOverNetwork infoscr){
            gameBoard= GB;
            infoScreen = infoscr;
            infoScreen.startBtnAction(resetGame());
        }

        public void setActionOfSingleTile(StackPane STPane, Label label) {
            STPane.setOnMouseClicked(event -> {
                int col = GridPane.getColumnIndex(STPane) + 1;
                int row = GridPane.getRowIndex(STPane) + 1;

                System.out.println(gameBoard.getPlayer() +"clicked "+row + "-" + col + " tile clicked");
                if (label.getText().isEmpty() && !gameEnded) {
                    label.setText(gameBoard.getPlayer());
                    String mark = label.getText();
                    int sign = (mark.equals("X"))?9:1;
                    marks[row-1][col-1]=sign;
                    gameBoard.toggleTurns();
                    moves++;
                     CheckWinning();
                    drowgame();
                }
            });

        }

    public void drowgame() {
        for (int row =0 ; row<3 ; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print( marks[row][col]);
            }
            System.out.println();
        }
    }

    private void CheckWinning(){
            checkRows();
        CheckColumns();
            checkTopRight();
            checkTopLeft();
            checkFoeTie();
        }
        private void checkRows() {
            if(!gameEnded) {
            for (int row =0 ; row < 3 ; row++) {
                    if(marks[row][0]==0)break;
                if((marks[row][0] == marks[row][1] )&&(marks[row][1] == marks[row][2])) {
                    String wins = (marks[row][0] == 9) ? "X" : "O";
                    gameEnding(wins);
                    return;
                }
                }
            }
        }

    private void gameEnding(String wins) {
            gameEnded = true;
        infoScreen.changeMsg(wins+" has won ");
        infoScreen.showBtn();
    }

    private void CheckColumns() {

            for (int col = 0; col < 3 ; col++) {
                if(!gameEnded) {
                    if (marks[0][col] == 0) break;
                    if ((marks[0][col] == marks[1][col]) && (marks[1][col] == marks[2][col])) {
                        String wins = (marks[0][col] == 9) ? "X" : "O";
                        gameEnding(wins);
                        return;
                    }
                }
        }
        }
        private void checkTopRight() {
            if(!gameEnded) {
                if (marks[0][2] == 0)return;;
                if ((marks[0][2] == marks[1][1]) && (marks[1][1] == marks[2][0])) {
                    String wins = (marks[0][2] == 9) ? "X" : "O";
                    gameEnding(wins);
                    return;
                }
            }
        }
        private void checkTopLeft() {
            if(!gameEnded){
                if (marks[0][0] == 0)return;;
                if ((marks[0][0] == marks[1][1]) && (marks[1][1] == marks[2][2])) {
                    String wins = (marks[0][0] == 9) ? "X" : "O";
                    gameEnding(wins);
                    return;
                }
            }
        }
    private void checkFoeTie() {
        if (!gameEnded && moves==9) {
            gameEnded= true;
            infoScreen.changeMsg("no one won its a tie!");
            infoScreen.showBtn();
        }
    }
    public void resetGameBoard(){
       gameEnded = false;
       moves=0;
        for (int row =0 ; row<3 ; row++){
            for (int col = 0; col < 3; col++) {
                marks[row][col]=0;
                }
            }

    }




    private EventHandler<ActionEvent> resetGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoScreen.hidBtn();
                infoScreen.changeMsg("palyer X turn");
                resetGameBoard();
                gameBoard.resetGame();
            }
        };
    }

}
