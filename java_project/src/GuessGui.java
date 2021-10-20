
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuessGui extends Application {

	int answer;

	@Override
	public void start(Stage primaryStage) {
		TextField tf = new TextField();
		tf.setPromptText("guess number");

		Label infoLb = new Label("엔터 넘버 1~10");
		Label resultLb = new Label("리절트");

		Random random = new Random();
		answer = new Random().nextInt(10) + 1;

		tf.setOnAction(e -> {
			try {
				int number = Integer.parseInt(tf.getText().trim());

				if (answer == number) {
					resultLb.setText("정답, 다음게임으로!");
					
					answer = new Random().nextInt(10) +1;
				} else {
					resultLb.setText("오답");
				}

			} catch (Exception e2) {
				resultLb.setText("숫자아님");
				System.out.println("숫자가 아님!");
			}

		});

		VBox vb = new VBox(infoLb, tf, resultLb);

		vb.setSpacing(2);
		vb.setPadding(new Insets(2));

		Scene scene = new Scene(vb, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("Guess Number");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
