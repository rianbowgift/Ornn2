package MyStudy;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MusicKnowledge extends Application {

	@Override
	public void start(Stage primaryStage) {
		//파일존재여부확인
		String filePath = "F:\\a.mp3";
		
		File file = new File(filePath);
		
		if(file.exists()) {
			System.out.println("파일존재");
			
		}else {
			System.out.println("파일존재 안함");
		}
		
		
		//음악파일재생기능
		Media media = new Media(file.toURI().toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//미디어 플레이어가 미디어를담아서 재생
		
		
		
		
		Button playBt = new Button("플레이");
		Button stopBt = new Button("스탑");
		
		playBt.setOnAction(e ->{
		
			mediaPlayer.play();	
			
			
			
		});
		stopBt.setOnAction(e ->{
			mediaPlayer.stop();
			
		});
		
		
		VBox vb = new VBox(playBt,stopBt);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("플레이어");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
