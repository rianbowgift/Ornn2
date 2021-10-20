

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TextFieldStudy extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		TextField tf = new TextField();
		
		
		tf.setPromptText("��ġ");
		
		
		Button bt = new Button("�� �ؽ�Ʈ");
		Button bt2 = new Button("�� �ؽ�Ʈ");
		
		
		bt.setOnAction(e ->{
			
			String text = tf.getText().trim();
			
			System.out.println(text);
			
		});
		
		bt2.setOnAction(e ->{
			
			tf.setText("�ؽ�Ʈ ü����");
			
			
		});
		
		
		
		//����Ű�߻��̺�Ʈ
		
		tf.setOnAction(e->{
			
			System.out.println("���� ������");
			
			
			bt2.fire();//��ưŬ�� �̺�Ʈ ������ �ڵ带 �������� �߻�
					
		});
		
		
		
		
		VBox vb = new VBox(tf,bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("�ؽ�Ʈ �ʵ� ���͵�");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
