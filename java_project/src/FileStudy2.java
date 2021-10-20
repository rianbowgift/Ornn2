
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;

public class FileStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {

		String filePath = "assets/text.txt";
		String content = "�¼� �¹���";
		try {
			Files.write(Paths.get(filePath), content.getBytes());
		} catch (Exception e) {
			System.out.println("���� ���� ����");
		}
		
		try {
		String loadText = new String(Files.readAllBytes(Paths.get(filePath)));
		
			System.out.println(loadText);
		}catch(Exception e) {
			System.out.println("���� ���� �������� ����");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
