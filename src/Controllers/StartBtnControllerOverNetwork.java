package Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ui_modules.InfoScreenOfPlayingOverNetwork;

public class StartBtnControllerOverNetwork {
    private boolean started;
    private InfoScreenOfPlayingOverNetwork infoScreen;

    public StartBtnControllerOverNetwork(InfoScreenOfPlayingOverNetwork infoScr)
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
