package Youtube;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdditionalGui extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		CheckBox cb = new CheckBox("Check this!");
		
		cb.setOnAction(e->{
			
			if(cb.isSelected()) {
				System.out.println("체크박스 선택됨");
			}else {
				System.out.println("체크박스 선택안됨");
			}
			
		});
		
		
		TextField tf = new TextField();
		
		
		HBox hb = new HBox(tf,cb);
		hb.setAlignment(Pos.CENTER_LEFT);
		
		
		
		
		
		VBox vb = new VBox(hb);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("체크백스 스터디");
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
