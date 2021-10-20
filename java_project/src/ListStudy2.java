
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
		
		ListView<String> lv = new ListView();	//표시
		
		ObservableList<String> ov = FXCollections.observableArrayList();
		//저장
		
		lv.setItems(ov);
		
		
		Button bt = new Button("ADD");
		Button bt2 = new Button("삭제");
		Button bt3 = new Button("업데이트");
		Button bt4 = new Button("컨테인");
		
		 bt.setOnAction(e->{
			 
			 
			 ov.add("굿");
			 ov.add("노말");
			 ov.addAll("슈퍼","베스트");
			 
		 });
		 bt2.setOnAction(e->{
			 ov.remove("굿");
			 ov.removeAll("굿");
		 });
		 bt3.setOnAction(e->{
			 
			 ov.set(0, "퍼스트");//첫번째아이템을 퍼스트로 바꾼다
			 ov.set(1, "세컨드");//두번째아이템 세컨드로 바꾼다
			 
		 });
		 bt4.setOnAction(e->{
			 
			 //해당 아이템있는지없는지 따짐
			 
			 if(ov.contains("퍼스트")) {
				 System.out.println("있음");
			 }else {
				 System.out.println("없음");
			 }
			 
			 
		 });
		
		
		
		
		VBox vb = new VBox(lv,bt,bt2,bt3,bt4);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("리스트뷰 스터디");
				
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
