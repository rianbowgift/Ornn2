package Youtube;


import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class YoutubeKnowledgeTest extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/youtube.png")));
		
		
		
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
