
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListViewStudy2 extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		ListView<Person> lv = new ListView<>();
		ObservableList<Person> ov = FXCollections.observableArrayList();
		
		
		lv.setItems(ov);
		
		Button bt = new Button("ADD");
		Button bt2 = new Button("info");
		Button bt3 = new Button("remove");
		Button bt4 = new Button("update");
		
		
		
		
		
		
		bt.setOnAction(e->{
			
			Person person1 = new Person("kim",20);
			Person person2 = new Person("smith",21);
			Person person3 = new Person("Amber",22);
			
			ov.addAll(person1,person2,person3);
			
			

			
			
			
		});
		
		
		bt2.setOnAction(e->{
			
			Person selected = lv.getSelectionModel().getSelectedItem();
			
			if(selected != null) {
				System.out.println("name "+ selected.getName()+"age"+ selected.getAge());
			}
			
		});
		bt3.setOnAction(e->{
			
			Person selected = lv.getSelectionModel().getSelectedItem();
			if(selected !=null) {
				ov.remove(selected);
			}
			
			
		});
		bt4.setOnAction(e->{
			
			Person selected = lv.getSelectionModel().getSelectedItem();
			int index = lv.getSelectionModel().getSelectedIndex();
			
			if(selected !=null) {
				
				Person updated = new Person("new person",10);
				
				ov.set(index, updated); //앞을 뒤로바꿈
			}
			
		});
		
		
		
		
		
		
		
		VBox vb = new VBox(lv,bt,bt2,bt3,bt4);
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("리스트뷰 오브젝트");
		
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
