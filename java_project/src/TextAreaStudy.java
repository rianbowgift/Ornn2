
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextAreaStudy extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		TextArea te = new TextArea();
		
		
		Button bt = new Button("GetText");
		Button bt2 = new Button("셋 텍스트");
		
		
		bt.setOnAction(e->{
			
			String text = te.getText();
			
			System.out.println(text);
			
		});
		
		bt2.setOnAction(e->{
			
			te.setText("텍스트 에어리어 텍스트 체인지");
			
		});
		
		
		
		VBox vb = new VBox(te,bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("텍스트에어리어 스터디");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
