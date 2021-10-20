
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewStudy3 extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		ListView<Person> lv = new ListView<>();
		ObservableList<Person> ov = FXCollections.observableArrayList();
		
		
		lv.setItems(ov);
		
		Person person1 = new Person("kim",20);
		Person person2 = new Person("smith",21);
		Person person3 = new Person("Amber",22);
		
		ov.addAll(person1,person2,person3);
		
		
		lv.setOnMouseClicked(e -> {
			
			Person selceted = lv.getSelectionModel().getSelectedItem();
			int index = lv.getSelectionModel().getSelectedIndex();
			
			if(selceted != null) {
				System.out.println("index" + index + "name" + selceted.getName() + "age" + selceted.getAge());
			}
			
		});
		
		
		
		lv.getSelectionModel().selectedItemProperty().addListener((a,oldvalue,newvalue)->{
			
			System.out.println("¼¿·º¼Ç previous : " + oldvalue);
			System.out.println("¼¿·º¼Ç Ä¿·»Æ® :" + newvalue);
			
		});
		
		
		
		
		VBox vb = new VBox(lv);
		

		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("¸®½ºÆ®ºä ¿ÀºêÁ§Æ®");
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	class Person{
		
		private String name;
		private int age;
		
		public Person(String name,int age) {
			this.name = name;
			this.age = age;
		}
		
		public String getName() {
			return this.name;
		}
		
		public int getAge() {
			return this.age;
		}
		
		@Override
		public String toString() {
			return this.name+ ","+ this.age;
		}
	}
}
