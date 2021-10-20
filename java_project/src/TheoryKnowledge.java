
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;

public class TheoryKnowledge extends Application {

	@Override
	public void start(Stage primaryStage) {
		// 랜덤
		Random random = new Random();

		int number = random.nextInt(10) + 1; // 1~10

		System.out.println(number);

		// 문자열->숫자, 트라이앤캐치
		try {
			int number2 = Integer.parseInt("1");

			System.out.println(number2);
			
		} catch (Exception e) {
			System.out.println("숫자로 바뀔수 없는 문자열이 입력됨");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
