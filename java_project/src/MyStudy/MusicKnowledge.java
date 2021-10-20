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
		//�������翩��Ȯ��
		String filePath = "F:\\a.mp3";
		
		File file = new File(filePath);
		
		if(file.exists()) {
			System.out.println("��������");
			
		}else {
			System.out.println("�������� ����");
		}
		
		
		//��������������
		Media media = new Media(file.toURI().toString());
		
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		//�̵�� �÷��̾ �̵���Ƽ� ���
		
		
		
		
		Button playBt = new Button("�÷���");
		Button stopBt = new Button("��ž");
		
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
		primaryStage.setTitle("�÷��̾�");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
