
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewStudy extends Application {

	@Override
	public void start(Stage primaryStage) {

		ListView<String> lv = new ListView();
		ObservableList<String> ov = FXCollections.observableArrayList();

		lv.setItems(ov);

		ov.addAll("굿", "노말", "베스트", "슈퍼");

		lv.setOnMouseClicked(e -> {

			System.out.println("마우스클릭");

			String selected = lv.getSelectionModel().getSelectedItem();
			// 선택된거 이름 가저옴
			int index = lv.getSelectionModel().getSelectedIndex();

			if (selected != null) {

				System.out.println("인덱스 " + index + "셀렉티드 " + selected);
			}
		});

		lv.getSelectionModel().selectedItemProperty().addListener((a, oldvalue, newvalue) -> {

			System.out.println("셀렉션 체인지드(프로비우스)" + oldvalue);
			System.out.println("셀렉션 체인지드(커렌트)" + newvalue);

		});

		VBox vb = new VBox(lv);

		Scene scene = new Scene(vb, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("리스트뷰 스터디");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
