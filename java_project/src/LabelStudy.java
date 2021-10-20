
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LabelStudy extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label lb = new Label("Study");
		
		
		Button bt = new Button("클릭");
		
		bt.setOnAction(e-> {
			
			lb.setText("라벨 텍스트 체인지");
			
		});
		
		Button bt2 = new Button("클릭2");
		
		bt2.setOnAction(e ->{
			
			String text = lb.getText();
			
			System.out.println("라벨 텍스트 : " + text);
			
		});
		
	
		VBox vb = new VBox(lb,bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("라벨 스터디");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
