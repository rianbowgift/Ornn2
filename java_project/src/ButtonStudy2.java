
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		Button bt = new Button("Ŭ��");
		
		bt.setOnAction(e -> {
			
			System.out.println("��ưŬ����");
			
		});
		
		VBox vb = new VBox(bt);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("��ư ���͵�");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
