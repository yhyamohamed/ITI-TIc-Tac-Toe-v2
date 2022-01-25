package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui_modules.InfoScreen;

public class StartBtnController {
    private boolean started;
    private InfoScreen infoScreen;
   // private GridPaneController gameController;

    public StartBtnController(InfoScreen infoScr)
    {
        infoScreen =infoScr;
        //infoScr.startBtnAction(startNewGame());
        //gameController=new GridPaneController();

    }

    private EventHandler<ActionEvent> startNewGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoScreen.hidBtn();
               // gameController.startGame();
                infoScreen.changeMsg("palyer's turn");
            }
        };
    }
}
