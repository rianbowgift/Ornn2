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
			
			if(conn ==null) {	//Ŀ�ؼ��� �̹� Ŀ�ؼ��� �ȵ��������� �ٽ� �޾ƿ�
			
			Class.forName("org.sqlite.JDBC");//�����ͺ��̽� ������ ���� ���� 
			conn = DriverManager.getConnection("jdbc:sqlite:sql/MyData.sqllite");
			System.out.println("�����ͺ��̽� ����");
			return conn;
			}else {
				return conn;
			}
			
		} catch (Exception e) {
			System.out.println("�����ͺ��̽� �������");
			
			return null;
		}
	}
	
}
