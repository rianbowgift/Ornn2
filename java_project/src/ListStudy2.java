
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		ListView<String> lv = new ListView();	//ǥ��
		
		ObservableList<String> ov = FXCollections.observableArrayList();
		//����
		
		lv.setItems(ov);
		
		
		Button bt = new Button("ADD");
		Button bt2 = new Button("����");
		Button bt3 = new Button("������Ʈ");
		Button bt4 = new Button("������");
		
		 bt.setOnAction(e->{
			 
			 
			 ov.add("��");
			 ov.add("�븻");
			 ov.addAll("����","����Ʈ");
			 
		 });
		 bt2.setOnAction(e->{
			 ov.remove("��");
			 ov.removeAll("��");
		 });
		 bt3.setOnAction(e->{
			 
			 ov.set(0, "�۽�Ʈ");//ù��°�������� �۽�Ʈ�� �ٲ۴�
			 ov.set(1, "������");//�ι�°������ ������� �ٲ۴�
			 
		 });
		 bt4.setOnAction(e->{
			 
			 //�ش� �������ִ��������� ����
			 
			 if(ov.contains("�۽�Ʈ")) {
				 System.out.println("����");
			 }else {
				 System.out.println("����");
			 }
			 
			 
		 });
		
		
		
		
		VBox vb = new VBox(lv,bt,bt2,bt3,bt4);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("����Ʈ�� ���͵�");
				
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
