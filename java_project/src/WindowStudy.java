
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WindowStudy extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		
		Button bt = new Button("��ư");
		Button bt2 = new Button("��ư2");
		
		VBox vb = new VBox(bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("��ư�� �� �ִ� â");
		
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
