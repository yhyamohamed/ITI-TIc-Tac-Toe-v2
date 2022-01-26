package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import ui_modules.BoardGridPaneOverNetwork;
import ui_modules.InfoScreenOfPlayingOverNetwork;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import ui_modules.WinningLine;


public class ScenePlayingOverNetwork extends Application {
	
	public static final int WIDTH=300;
	public static final int HIGHT=400;

	public static final int INFO_SCREEN=100;
	public static final int TILE_HIGHT=300;
	
	private InfoScreenOfPlayingOverNetwork infoScreen;
	//private GameBboard gameBoard;
	private BoardGridPaneOverNetwork boardGridPane;
	private WinningLine winningLine;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,WIDTH,HIGHT);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			UiInit(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("error "+e.getMessage());
		}
	}
	
	private void UiInit(BorderPane root)
	{
		infoScreen = new InfoScreenOfPlayingOverNetwork();
		root.getChildren().add(infoScreen.getPane());

//		gameBoard = new GameBboard(infoScreen);
//		root.getChildren().add(gameBoard.getPane());
		//BoardGradePane

		boardGridPane = new BoardGridPaneOverNetwork(infoScreen);
		root.getChildren().add(boardGridPane.getPane());
		//winningLine = new WinningLine(root , boardGridPane);

	}

	public static void main(String[] args) {
		launch(args);
	}
}


