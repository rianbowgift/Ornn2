
import java.util.Random;

import javafx.application.Application;
import javafx.stage.Stage;

public class TheoryKnowledge extends Application {

	@Override
	public void start(Stage primaryStage) {
		// ����
		Random random = new Random();

		int number = random.nextInt(10) + 1; // 1~10

		System.out.println(number);

		// ���ڿ�->����, Ʈ���̾�ĳġ
		try {
			int number2 = Integer.parseInt("1");

			System.out.println(number2);
			
		} catch (Exception e) {
			System.out.println("���ڷ� �ٲ�� ���� ���ڿ��� �Էµ�");
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
