
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NoteGui extends Application {

	
	String filePath = "assets/text.txt";
	
	@Override
	public void start(Stage primaryStage) {
		TextArea ta = new TextArea();
		
		Button saveBt = new Button("세이브");
		Button loadBt = new Button("로드");
		
		HBox hb = new HBox(saveBt,loadBt);
		hb.setSpacing(2);
		
		
		
		saveBt.setOnAction(e->{
			
			String content = ta.getText();
			
			saveContent(filePath, content);
			
		});
		loadBt.setOnAction(e->{
			
			
			String content = loadContent(filePath);
			
			
			ta.setText(content);
			
			
		});
		
		
	
		
		VBox vb = new VBox(ta,hb);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("노트 앱");
		
		
	}
	
	public void saveContent(String filePath, String content) {
		
		try {
		Files.write(Paths.get(filePath),content.getBytes());
		}catch(Exception e) {
			System.out.println("파일 저장 에러");
		}
	}
	
	public String loadContent(String filePath) {
		
		String content = "";
		
		try {
			content = new String(Files.readAllBytes(Paths.get(filePath)));
		}catch(Exception e) {
			System.out.println("파일 로드 에러");
		}
		return content;
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
