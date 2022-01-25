package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui_modules.InfoScreen;

public class StartBtnController {
    private boolean started;
    private InfoScreen infoScreen;

    public StartBtnController(InfoScreen infoScr)
    {
        infoScreen =infoScr;
        //infoScr.startBtnAction(startNewGame());

    }

    private EventHandler<ActionEvent> startNewGame() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                infoScreen.hidBtn();
                infoScreen.changeMsg("palyer X turn");
            }
        };
    }
}
