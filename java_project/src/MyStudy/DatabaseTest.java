package MyStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;

import javax.xml.transform.Result;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DatabaseTest extends Application {

	private Connection conn;
	private PreparedStatement pst; // 데이터베이스에 쿼리를 요청하는 역활(기능요청)
	private ResultSet rs; // 데이터베이스에서 데이터를 가저오는 역활

	@Override
	public void start(Stage primaryStage) {
		conn = dbConnector();

		Button loadBt = new Button("loadBt");
		Button addBt = new Button("add");
		Button updateBt = new Button("update");
		Button removeBt = new Button("remove");

		VBox vb = new VBox(loadBt, addBt, updateBt, removeBt);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));

		loadBt.setOnAction(e -> {

			ObservableList<String> ov = loadData();
			
			int size = ov.size();
			
			for(int i=0; i<size; i++) {
				String data = ov.get(i);
				
				System.out.println(data);
			}

		});
		addBt.setOnAction(e -> {

			insertData("Good");

		});
		removeBt.setOnAction(e -> {

			deleteData("Good");

		});

		updateBt.setOnAction(e -> {

			updateData("Good", "Great");

		});

		Scene scene = new Scene(vb, 500, 500);

		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("db");

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Connection dbConnector() {
		try {

			if (conn == null) { // 커넥션이 이미 커넥션이 안되있을때만 다시 받아옴

				Class.forName("org.sqlite.JDBC");// 데이터베이스 연결을 위한 설정
				conn = DriverManager.getConnection("jdbc:sqlite:sql/MyData.sqlite");
				System.out.println("데이터베이스 연결");
				return conn;
			} else {
				return conn;
			}

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);

			return null;
		}
	}

	public void insertData(String data) {

		String query = "Insert or Replace into Datas (Text) Values (?)";

		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, data);// 첫번째 물음표에 들어간다. data가
			pst.execute();
			pst.close();
			
		} catch (SQLException e) {
			System.out.println("데이터 삽입 에러");
		}

	}

	public void deleteData(String data) {
		
		
		String query = "delete from Datas where Text = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1, data);
			pst.execute();
			pst.close();
			
		}catch(SQLException e) {
			System.out.println("데이터 삭제 에러");
			
		}
		

	}

	public void updateData(String oldValue, String newValue) {
		String query = "update Datas set Text = ? where Text = ?";
		
		try {
			pst = conn.prepareStatement(query);
			pst.setString(1,  newValue);
			pst.setString(2,  oldValue);
			pst.execute();
			pst.close();
			
		}catch(SQLException e) {
			System.out.println("데이터 업데이트 에러");
		}
	
		
	}

	public ObservableList<String> loadData() {

		ObservableList<String> tempOv = FXCollections.observableArrayList();

		String query = "select * from Datas";
		
		try {
		pst = conn.prepareStatement(query);
		rs = pst.executeQuery();
		
		while(rs.next()) {
			String data = rs.getString("Text");
			
			tempOv.add(data);
		}
		
		rs.close();
		pst.close();
		
		}catch(SQLException e) {
			System.out.println("데이터 로드 에러");
		}
		
		
		
		return tempOv;

	}

}
