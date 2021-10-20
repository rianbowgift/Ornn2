
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class HBoxStudy extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		Button bt = new Button("버튼1");
		Button bt2 = new Button("버튼2");
		Button bt3 = new Button("버튼3");
		
		HBox hb = new HBox(bt,bt2,bt3);
		
		Scene scene = new Scene(hb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("HBox");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
