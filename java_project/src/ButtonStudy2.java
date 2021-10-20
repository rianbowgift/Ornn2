
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button bt = new Button("클릭");
		
		bt.setOnAction(e -> {
			
			System.out.println("버튼클릭됨");
			
		});
		
		VBox vb = new VBox(bt);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("버튼 스터디");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
