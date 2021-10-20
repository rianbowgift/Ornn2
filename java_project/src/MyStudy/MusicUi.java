package MyStudy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class MusicUi extends Application {

	MediaPlayer mediaPlayer;
	
	Connection conn;
	
	

	@Override
	public void start(Stage primaryStage) {

		ListView<MusicData> lv = new ListView();

		ObservableList<MusicData> ov = FXCollections.observableArrayList();
		lv.setItems(ov);

		Button playBt = new Button("Play");
		Button stopBt = new Button("stop");

		TextField addTf = new TextField();
		addTf.setPromptText("뮤직 경로");
		addTf.setPrefWidth(250);

		Button addBt = new Button("Add");
		Button removeBt = new Button("remove");

		HBox hb = new HBox(addTf, addBt, removeBt);
		HBox hb2 = new HBox(playBt, stopBt);
		hb.setSpacing(2);
		hb2.setSpacing(2);

		//db
		conn = dbConnection();
		ov.addAll(new MusicPlayerDb().loadData());
		
		// 버튼
		addBt.setOnAction(e -> {

			String filePath = addTf.getText().trim();

			File file = new File(filePath);

			if (file.exists()) {
				String name = file.getName();
				String path = file.getAbsolutePath();

				MusicData data = new MusicData(name, path);

				ov.add(data);

				addTf.clear();
				
				//db
				new MusicPlayerDb().insertData(path);

			}

		});
		removeBt.setOnAction(e -> {

			MusicData selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {

				ov.remove(selected);
				
				//db
				new MusicPlayerDb().deleteData(selected.getPath());
			}

		});
		playBt.setOnAction(e -> {

			MusicData selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {
				File file = new File(selected.getPath());

				if (mediaPlayer != null) {
					mediaPlayer.stop();// 이전재생을중단.
				}

				if (file.exists()) {
					Media media = new Media(file.toURI().toString());
					mediaPlayer = new MediaPlayer(media);

					mediaPlayer.play();
				}

			}

		});
		stopBt.setOnAction(e -> {
			if(mediaPlayer != null) {
			mediaPlayer.stop();
			}
		});

		// 텍스트필드

		addTf.setOnAction(e -> {

			addBt.fire();

		});

		VBox vb = new VBox(hb, hb2, lv);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));

		Scene scene = new Scene(vb, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("뮤직 플레이어");
		
		primaryStage.setOnCloseRequest(e->{	//창닫으면음악도꺼지게
			if(mediaPlayer != null) {
			mediaPlayer.stop();
			}
			
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	
	public Connection dbConnection() {
		
		try {
			
			if(conn == null) {
			
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:sql/MusicPlayer.sqlite");
			
			return conn;
			}else {
				return conn;
			}
			
			
		}catch(Exception e) {
			System.out.println("db 코넥트 에러");
			return null;
		}
	}
	

	class MusicData {

		private String name;
		private String path;

		public MusicData(String name, String path) {

			this.name = name;
			this.path = path;

		}

		public String getName() {
			return this.name;
		}

		public String getPath() {
			return this.path;
		}

		@Override
		public String toString() {
			return this.name;
		}

	}
	
	class MusicPlayerDb{
		private PreparedStatement pst;
		private ResultSet rs;
		
		public ObservableList<MusicData> loadData(){
			
			ObservableList<MusicData> tempOv = FXCollections.observableArrayList();
			
			
			String query = "select * from Music";
			
			try {
				
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					
					String path = rs.getString("Path");
					File file = new File(path);
					
					MusicData data = new MusicData(file.getName(),path);
					tempOv.add(data);
				}
				
				rs.close();
				pst.close();
				
				
			}catch(Exception e) {
				
				System.out.println("sql가저오기 에러");
			}
			
			
			
			
			return tempOv;
			
		}
		
		
		public void insertData(String path) {
			
			String query = "Insert or Replace into Music (Path) Values (?)";
			
			try {
				
				pst = conn.prepareStatement(query);
				pst.setString(1, path);
				pst.execute();
				pst.close();
				
				
			}catch(Exception e) {
				System.out.println("sql 데이터 삽입 에러");
			}
			
			
		}
		
		public void deleteData(String path) {
			String query = "Delete from Music where Path = ?";
			
			try {
				
				pst = conn.prepareStatement(query);
				pst.setString(1, path);
				pst.execute();
				pst.close();
				
				
			}catch(Exception e) {
				System.out.println("sql 데이터 삭제 에러");
			}
			
			
		}
		
	}

}
