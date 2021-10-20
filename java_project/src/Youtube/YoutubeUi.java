package Youtube;

import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class YoutubeUi extends Application {

	private Connection conn;
	
	

	@Override
	public void start(Stage primaryStage) {

		ListView<YoutubeData> lv = new ListView<>();

		ObservableList<YoutubeData> ov = FXCollections.observableArrayList();
		lv.setItems(ov);

		TextField addTf = new TextField();
		addTf.setPromptText("Youtube Address");
		addTf.setPrefWidth(245);

		Button loadBt = new Button("Load");
		Button addBt = new Button("add");
		Button removeBt = new Button("remove");

		
		//container
		HBox hb = new HBox(addTf, loadBt, addBt, removeBt);
		hb.setSpacing(2);

		VBox vb = new VBox(hb, lv);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));
		
		//db
		conn = dbConnector();
		ov.addAll(new YoutubeDb().loadData());
		

		// button
		addBt.setOnAction(e -> {

			String url = addTf.getText().trim();

			if (!url.isEmpty()) {

				String title = getYoutubeTitle(url);

				YoutubeData ytData = new YoutubeData(title, url);

				if (!title.trim().isEmpty()) {

					ov.add(ytData);

					addTf.clear();
					
					
					//db
					new YoutubeDb().insertData(ytData);

				}

			}

		});
		removeBt.setOnAction(e -> {

			YoutubeData selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {

				ov.remove(selected);
				
				//db
				new YoutubeDb().deleteData(selected);
				
			}

		});
		loadBt.setOnAction(e -> {

			YoutubeData selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {
				try {
					Desktop.getDesktop().browse(new URI(selected.getUrl()));
				} catch (Exception e2) {
					System.out.println("web load error");
				}

			}

		});

		// textfield
		addTf.setOnAction(e -> {

			addBt.fire();

		});

		Scene scene = new Scene(vb, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("youtubeapp");
		primaryStage.setResizable(false);

		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("assets/youtube.png")));

	}

	public static void main(String[] args) {
		launch(args);
	}

	public Connection dbConnector() {

		try {
			
			if(conn ==null) {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:sql/SimpleYoutube.sqlite");
			System.out.println("디비연결됨");
			return conn;
		
			}else {
				return conn;
			}
		} catch (Exception e) {
			System.out.println("디비 커넥션 에러");
			
			return null;
		}

	}

	public String getYoutubeTitle(String youtubeUrl) {

		youtubeUrl = "https://www.youtube.com/oembed?url=" + youtubeUrl + "&format=xml";

		try {
			Document document = Jsoup.connect(youtubeUrl).get();

			Element titleEle = document.getElementsByTag("title").get(0);

			String title = titleEle.text();

			return title;

		} catch (Exception e) {
			System.out.println("에러 - 겟 youtube title");

			return "";
		}

	}

	class YoutubeData {

		private String title;
		private String url;

		public YoutubeData(String title, String url) {

			this.title = title;
			this.url = url;
		}

		public String getTitle() {
			return this.title;
		}

		public String getUrl() {
			return this.url;
		}

		@Override
		public String toString() {
			return this.title;
		}

	}
	
	
	public class YoutubeDb{
		
		
		private PreparedStatement pst;
		private ResultSet rs;
		
		public ObservableList<YoutubeData> loadData(){
			
			ObservableList<YoutubeData> tempOv = FXCollections.observableArrayList();
			
			
			String query = "select * from Youtube";
			
			try {
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();
				
				while(rs.next()) {
					
					String title = rs.getString("Title");
					String url = rs.getString("Url");
					
					YoutubeData data = new YoutubeData(title, url);
					
					
					tempOv.add(data);
					
				}
				
				rs.close();
				pst.close();
				
				
			}catch(Exception e) {
				
				System.out.println("data load error");
			}
			
			
			
			
			return tempOv;
			
		}
		
		
		public void insertData(YoutubeData data) {
			
			String query = "Insert or Replace into Youtube (Title,Url) Values (?,?)";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1,data.getTitle());
				pst.setString(2,data.getUrl());
				pst.execute();
				pst.close();
				
			}catch(Exception e) {
				System.out.println("데이터 입력 에러");
			}
			
			
		}
		
		public void deleteData(YoutubeData data) {
			String query = "delete from Youtube where Url = ?";
			
			try {
				pst = conn.prepareStatement(query);
				pst.setString(1,data.getUrl());
				pst.execute();
				pst.close();
				
			}catch(Exception e) {
				System.out.println("데이터 삭제 에러");
			}
			
		}
		
	}

}
