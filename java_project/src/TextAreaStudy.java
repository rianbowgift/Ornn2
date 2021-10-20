
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
		Button bt2 = new Button("�� �ؽ�Ʈ");
		
		
		bt.setOnAction(e->{
			
			String text = te.getText();
			
			System.out.println(text);
			
		});
		
		bt2.setOnAction(e->{
			
			te.setText("�ؽ�Ʈ ����� �ؽ�Ʈ ü����");
			
		});
		
		
		
		VBox vb = new VBox(te,bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("�ؽ�Ʈ����� ���͵�");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
