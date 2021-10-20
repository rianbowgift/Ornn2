
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

		ov.addAll("��", "�븻", "����Ʈ", "����");

		lv.setOnMouseClicked(e -> {

			System.out.println("���콺Ŭ��");

			String selected = lv.getSelectionModel().getSelectedItem();
			// ���õȰ� �̸� ������
			int index = lv.getSelectionModel().getSelectedIndex();

			if (selected != null) {

				System.out.println("�ε��� " + index + "����Ƽ�� " + selected);
			}
		});

		lv.getSelectionModel().selectedItemProperty().addListener((a, oldvalue, newvalue) -> {

			System.out.println("������ ü������(���κ�콺)" + oldvalue);
			System.out.println("������ ü������(Ŀ��Ʈ)" + newvalue);

		});

		VBox vb = new VBox(lv);

		Scene scene = new Scene(vb, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("����Ʈ�� ���͵�");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
