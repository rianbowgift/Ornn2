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
				System.out.println("üũ�ڽ� ���õ�");
			}else {
				System.out.println("üũ�ڽ� ���þȵ�");
			}
			
		});
		
		
		TextField tf = new TextField();
		
		
		HBox hb = new HBox(tf,cb);
		hb.setAlignment(Pos.CENTER_LEFT);
		
		
		
		
		
		VBox vb = new VBox(hb);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("üũ�齺 ���͵�");
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
