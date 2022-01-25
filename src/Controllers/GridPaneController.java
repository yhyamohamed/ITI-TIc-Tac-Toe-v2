package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import ui_modules.BoardGridPane;
import ui_modules.InfoScreen;
import java.util.Random;
import java.util.Vector;
import ui_modules.WinningLine;

import java.util.HashMap;

public class GridPaneController {

    private BoardGridPane gameBoard;
    //private boolean gameEnded ;
    InfoScreen infoScreen;
    //        private HashMap<Integer,String> tilesMarks = new HashMap<>();
    StackPane[] tiles;
    Label[] labels;
    private int[][] marks = new int[3][3];
    private int moves;
    private boolean gameEnded;
    private boolean gameStarted;
    private CPU cpu;
    private BorderPane root;
    private WinningLine winningLine;

    public GridPaneController(BoardGridPane GB, InfoScreen infoscr, BorderPane root) {
        gameBoard = GB;
        tiles = new StackPane[9];
        labels = new Label[9];
        infoScreen = infoscr;
        infoScreen.startBtnAction(resetGame());
        gameEnded=false;
        gameStarted=false;
        this.root=root;


    }
    public void startGame()
    {
        gameStarted=true;
        gameEnded=false;
        cpu = new CPU();
        cpu.startBot();
    }

    public void setActionOfSingleTile(StackPane STPane, Label label) {
        int position = 3 * (GridPane.getRowIndex(STPane)) + GridPane.getColumnIndex(STPane);
        labels[position] = label;
        tiles[position] = STPane;

        //cpu.gameTiles.add(STPane);
        STPane.setOnMouseClicked(event -> {
            System.out.println("tile clicked");
            if(gameStarted){
            boolean legalPlay= cpu.play(1,STPane,position);
            System.out.println("player chosed: "+position);}
        });

    }

    private class CPU extends Thread {
        public Vector<StackPane> availableTiles;
        private int playerWhosTurn;
        private int[] markedTiles;

        public CPU() {
            availableTiles = new Vector<>(9);
            playerWhosTurn = 1;
            markedTiles = new int[9];
        }
        public void startBot()
        {
            for(int i=0;i<tiles.length;i++) cpu.availableTiles.add(tiles[i]);
            this.start();
            System.out.println("thst");
        }

        public boolean play(int player, StackPane chosenTile, int positon) {
            if (player == playerWhosTurn && availableTiles.contains(chosenTile) && !gameEnded && gameStarted)  {

                if (player == 1) {
                    labels[positon].setText("O");
                } else {
                    Runnable xdrawer = new Runnable() {
                        @Override
                        public void run() {
                            labels[positon].setText("X");
                        }
                    };
                    Platform.runLater(xdrawer);
                }
                System.out.println(availableTiles.size());
                availableTiles.remove(chosenTile);

                toggleTurns(playerWhosTurn); //toggle turns
                System.out.println("Toogle");
                System.out.println(availableTiles.size());
                markedTiles[positon] = player;
                if (availableTiles.size() < 5) {
                    CheckWinning();
                }
                if(availableTiles.isEmpty() && !gameEnded)
                {
                    gameEnded=true;
                    gameEnding(0,0,0,0);
                }
                return true;
            } else return false;
        }
        private void toggleTurns(int whoCalls)
        {
            playerWhosTurn*=-1;
            if(whoCalls==1)
                infoScreen.changeMsg("BOT's Turn");
            else Platform.runLater(()->{
                infoScreen.changeMsg("Player's Turn");
            });
        }

        @Override
        public void run() {
            while ((!gameEnded)) {
                // System.out.println("th");
                if (playerWhosTurn == -1) {
                    boolean legal;
                    int count;
                    do {
                        Random ran = new Random();
                        int positionofplaying = ran.nextInt(availableTiles.size());

                        for (count = 0; count < tiles.length; count++) {
                            if (availableTiles.get(positionofplaying) == tiles[count]) {
                                break;
                            }
                        }
                        System.out.println("pot choosed: " + (count + 1));

                        legal = cpu.play(-1, availableTiles.get(positionofplaying), count);


                    } while (!legal);

                }
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }

        private void CheckWinning() {
            checkRows();
            CheckColumns();
            checkTopRight();
            checkTopLeft();
        }

        private void checkRows() {
            for(int row=0;row<3;row++)
            {
                if(markedTiles[3*row]==markedTiles[3*row+1] && markedTiles[3*row+1]==markedTiles[3*row+2] && markedTiles[3*row]!=0)
                {
                    gameEnded = true;
                    gameStarted=false;
                    //new WinningLine(root,tiles[3],tiles[1],tiles[2]);
                    gameEnding(markedTiles[3*row], 3*row,3*row+1,3*row+2);
                }
            }

        }
        private void CheckColumns() {
            for(int col=0;col<3;col++)
            {
                if(markedTiles[col]==markedTiles[col+3] && markedTiles[col]==markedTiles[col+6] && markedTiles[col]!=0)
                {
                    gameEnded = true;
                    gameStarted=false;
                    gameEnding(markedTiles[col], col,col+3,col+6);
                }
            }
        }

        private void checkTopRight() {
            if (markedTiles[2] == markedTiles[4] && markedTiles[4] == markedTiles[6] && markedTiles[2] != 0) {
                gameEnded = true;
                gameStarted=false;
                gameEnding(markedTiles[2], 2,4,6);
            }

        }

        private void checkTopLeft() {
            if (markedTiles[0] == markedTiles[4] && markedTiles[4] == markedTiles[8] && markedTiles[0] != 0) {
                gameEnded = true;
                gameStarted=false;
                gameEnding(markedTiles[0],0,4,8 );
            }
        }
    }

    private void gameEnding(int winner, int tile1index,int tile2index,int tile3index) {
            gameEnded = true;
            if(winner==-1)
            {
               Platform.runLater(()->{
                  winningLine= new WinningLine(root,tiles[tile1index],tiles[tile2index],tiles[tile3index]);
                   infoScreen.changeMsg("BOT WON");
               });
            }else if (winner==1){
                    winningLine= new WinningLine(root,tiles[tile1index],tiles[tile2index],tiles[tile3index]);
                    infoScreen.changeMsg("Player WON");}
            else {
                infoScreen.changeMsg("DRAW");
                    }
                    infoScreen.showBtn();
            cpu.stop();
    }

    public void resetGameBoard(){
       gameEnded = false;
       gameStarted=true;
    }

    private EventHandler<ActionEvent> resetGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoScreen.hidBtn();
                startGame();
                infoScreen.changeMsg("palyer's turn");
                if(winningLine !=null){
                winningLine.removeThisLine();
                winningLine=null;}
                resetGameBoard();
                gameBoard.resetGame();
            }
        };
    }

}

