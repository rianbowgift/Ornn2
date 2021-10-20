

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
		
		
		tf.setPromptText("서치");
		
		
		Button bt = new Button("겟 텍스트");
		Button bt2 = new Button("셋 텍스트");
		
		
		bt.setOnAction(e ->{
			
			String text = tf.getText().trim();
			
			System.out.println(text);
			
		});
		
		bt2.setOnAction(e ->{
			
			tf.setText("텍스트 체인지");
			
			
		});
		
		
		
		//엔터키발생이벤트
		
		tf.setOnAction(e->{
			
			System.out.println("엔터 프레스");
			
			
			bt2.fire();//버튼클릭 이벤트 내부의 코드를 수동으로 발생
					
		});
		
		
		
		
		VBox vb = new VBox(tf,bt,bt2);
		
		Scene scene = new Scene(vb,500,500);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("텍스트 필드 스터디");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
