package MyStudy;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class DbConnStudy extends Application {

	private Connection conn;

	@Override
	public void start(Stage primaryStage) {


			conn = dbConnector();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	public Connection dbConnector() {
		try {
			
			if(conn ==null) {	//커넥션이 이미 커넥션이 안되있을때만 다시 받아옴
			
			Class.forName("org.sqlite.JDBC");//데이터베이스 연결을 위한 설정 
			conn = DriverManager.getConnection("jdbc:sqlite:sql/MyData.sqllite");
			System.out.println("데이터베이스 연결");
			return conn;
			}else {
				return conn;
			}
			
		} catch (Exception e) {
			System.out.println("데이터베이스 연결실패");
			
			return null;
		}
	}
	
}
