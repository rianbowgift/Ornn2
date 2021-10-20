
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.stage.Stage;

public class FileStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {

		String filePath = "assets/text.txt";
		String content = "굿송 굿뮤직";
		try {
			Files.write(Paths.get(filePath), content.getBytes());
		} catch (Exception e) {
			System.out.println("내용 저장 에러");
		}
		
		try {
		String loadText = new String(Files.readAllBytes(Paths.get(filePath)));
		
			System.out.println(loadText);
		}catch(Exception e) {
			System.out.println("파일 내용 가저오기 에러");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
