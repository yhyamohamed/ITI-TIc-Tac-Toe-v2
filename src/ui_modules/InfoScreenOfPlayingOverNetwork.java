package ui_modules;

import Controllers.StartBtnControllerOverNetwork;
import application.ScenePlayingOverNetwork;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class InfoScreenOfPlayingOverNetwork
{
	private StackPane pane ;
	private Label msg;
	private Button Start_game_btn;
	
	
	public InfoScreenOfPlayingOverNetwork(){
		pane = new StackPane();
		pane.setMinSize(ScenePlayingOverNetwork.WIDTH, ScenePlayingOverNetwork.INFO_SCREEN);
		pane.setTranslateX(ScenePlayingOverNetwork.WIDTH/2);
		pane.setTranslateY(ScenePlayingOverNetwork.INFO_SCREEN/2);
		
		msg =new Label("Tic_Tac");
		
		
		msg.setMinSize(ScenePlayingOverNetwork.WIDTH, ScenePlayingOverNetwork.INFO_SCREEN);
		msg.setFont(Font.font(30));
		msg.setAlignment(Pos.CENTER);
		msg.setTranslateY(-20);
		pane.getChildren().add(msg);
		
		Start_game_btn = new Button("START");
		Start_game_btn.setMinSize(130, 30);
		Start_game_btn.setTranslateY(25);
		pane.getChildren().add(Start_game_btn);

		new StartBtnControllerOverNetwork(this);

	}
	
	public StackPane getPane()
	{
		return pane;
	}
	
	public void showBtn()
	{
		Start_game_btn.setVisible(true);
	}
	public void hidBtn()
	{
		Start_game_btn.setVisible(false);
	}
	
	public void changeMsg(String str)
	{
		msg.setText(str);
	}
	
	public void startBtnAction(EventHandler<ActionEvent> Action) {
		Start_game_btn.setOnAction(Action);
		
	}


}
