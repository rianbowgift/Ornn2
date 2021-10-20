package MyStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;

import MyStudy.DatabaseTest2.MyDb;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TodoUi extends Application {

	private Connection conn;

	@Override
	public void start(Stage primaryStage) {

		conn = dbConnection();

		ListView<String> lv = new ListView<String>();
		ObservableList<String> ov = FXCollections.observableArrayList();
		lv.setItems(ov);

		TextField addTf = new TextField();
		addTf.setPromptText("add todo");

		TextField updateTf = new TextField();
		addTf.setPromptText("update todo");

		Button addBt = new Button("Add");
		Button updateBt = new Button("update");
		Button removeBt = new Button("remove");
		
		
		//db 프로그렘이 실행될때 디비 데이터가 로드되는부분
		ov.addAll(new TodoListDb().loadData());
		
		

		lv.getSelectionModel().selectedItemProperty().addListener((a, oldValue, newValue) -> {

			updateTf.setText(newValue);

		});// 라이브 업데이트

		addTf.setOnAction(e -> {

			addBt.fire();
		});
		updateTf.setOnAction(e -> {

			updateBt.fire();

		});

		addBt.setOnAction(e -> {
			
			String todo = addTf.getText().trim();

			if (!todo.isEmpty() && !ov.contains(todo)) {// 중복제거
				ov.add(todo);
				addTf.clear();
				
				//db
				new TodoListDb().insertData(todo);
				
				
			}

		});
		updateBt.setOnAction(e -> {

			String selected = lv.getSelectionModel().getSelectedItem();
			int index = lv.getSelectionModel().getSelectedIndex();

			String updated = updateTf.getText().trim();

			if (selected != null) {

				ov.set(index, updated);
				
				//db
				new TodoListDb().updateData(selected, updated);

			}

		});
		removeBt.setOnAction(e -> {

			String selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {
				ov.remove(selected);
			}
			
			//db
			new TodoListDb().deleteData(selected);

		});

		HBox hb = new HBox(addBt, updateBt, removeBt);
		hb.setSpacing(2);

		VBox vb = new VBox(addTf, hb, updateTf, lv);
		Scene scene = new Scene(vb, 500, 500);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("투두 리스트");

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Connection dbConnection() {

		try {
			if (conn == null) {

				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:sql/TodoList.sqlite");

				System.out.println("db접속");
				return conn;
			} else {
				return conn;
			}

		} catch (Exception e) {
			System.out.println("db 접속 실패");
			return null;
		}
	}

	public class TodoListDb {

		private PreparedStatement pst;
		private ResultSet rs;

		public ObservableList<String> loadData() {

			ObservableList<String> tempOv = FXCollections.observableArrayList();

			String query = "select * from Todo";
			try {
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();

				while (rs.next()) {

					String data = rs.getString("Text");

					tempOv.add(data);

				}
			} catch (Exception e) {
				System.out.println("데이터베이스 로드 에러");
			}

			return tempOv;
		}

		public void insertData(String data) {
			
			String query = "Insert or Replace into Todo (Text) Values (?)";

			try {
				
				pst = conn.prepareStatement(query);
				pst.setString(1, data);
				pst.execute();
				pst.close();
				
				
			}catch(Exception e) {
				System.out.println("데이터베이스 삽입에러");
			}
			
		}

		public void deleteData(String data) {
			String query = "Delete from Todo Where Text = ?";

			try {
				
				pst = conn.prepareStatement(query);
				pst.setString(1, data);
				pst.execute();
				pst.close();
				
				
			}catch(Exception e) {
				System.out.println("데이터베이스 삭제에러");
			}
		}

		public void updateData(String oldValue, String newValue) {
			String query = "Update Todo set Text = ? where Text = ?";

			try {
				
				pst = conn.prepareStatement(query);
				pst.setString(1, newValue);
				pst.setString(2, oldValue);
				pst.execute();
				pst.close();
				
				
			}catch(Exception e) {
				System.out.println("데이터베이스 업뎃에러");
			}
		}

	}

}
