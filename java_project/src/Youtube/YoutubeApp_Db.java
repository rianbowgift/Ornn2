package Youtube;

import java.awt.Desktop;
import java.io.File;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.glass.ui.ClipboardAssistance;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class YoutubeApp_Db extends Application {

	private Connection conn;

	int port = 9900;

	ServerSocket socket;

	@Override
	public void start(Stage primaryStage) {

		// 프로세스 한번만
		checkIfRunning();

		ListView<YoutubeData> lv = new ListView<>();
		ObservableList<YoutubeData> ov = FXCollections.observableArrayList();
		lv.setItems(ov);

		lv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		FilteredList<YoutubeData> filteredList = new FilteredList<>(ov, item -> true);
		lv.setItems(filteredList);

		TextField addTf = new TextField();
		addTf.setPromptText("Youtube Address");
		addTf.setPrefWidth(245);

		Button loadBt = new Button("Load");
		Button addBt = new Button("add");
		Button removeBt = new Button("remove");

		// container
		HBox hb = new HBox(addTf, loadBt, addBt, removeBt);
		hb.setSpacing(2);

		TextField searchTf = new TextField();
		searchTf.setPromptText("Seatch");
		searchTf.setPrefWidth(330);

		CheckBox chromeCb = new CheckBox("크롬앱");

		HBox hb2 = new HBox(searchTf, chromeCb);
		hb2.setSpacing(2);
		hb2.setAlignment(Pos.CENTER_LEFT);

		// 포커스이동막기(tab키이동시)
		loadBt.setFocusTraversable(false);
		addBt.setFocusTraversable(false);
		removeBt.setFocusTraversable(false);
		chromeCb.setFocusTraversable(false);

		// 클립보드
		Clipboard clipboard = Clipboard.getSystemClipboard();

		new ClipboardAssistance(com.sun.glass.ui.Clipboard.SYSTEM) {

			@Override
			public void contentChanged() {

				try {

					if (clipboard.hasString()) {

						// 크롬앱 체크되있어야만기능하게
						if (chromeCb.isSelected()) {

							String text = clipboard.getString();

							System.out.println("클립보드:" + text);

							String title = text.split("\"title\":\"")[1].split("\",\"")[0];
							String url = text.split("\"url\":\"")[1].split("\"}")[0];

							System.out.println("타이틀: " + title);
							System.out.println("url: " + url);

							YoutubeData data = new YoutubeData(title, url);

							ov.add(data);

							// db
							new YoutubeDb().insertData(data);

						}

					}

				} catch (Exception e) {
					System.out.println("클립보드 에러");
				}

			}
		};

		// db
		conn = dbConnector();
		ov.addAll(new YoutubeDb().loadData());

		// event-lv
		lv.setOnDragDetected(e -> {

			ObservableList<YoutubeData> selecteds = lv.getSelectionModel().getSelectedItems();

			if (selecteds.size() == 1) {

				// YoutubeData selected = lv.getSelectionModel().getSelectedItem();
				YoutubeData selected = selecteds.get(0);

				if (selected != null) {

					Dragboard db = lv.startDragAndDrop(TransferMode.ANY);
					ClipboardContent cc = new ClipboardContent();

					cc.putUrl(selected.getUrl());

					db.setContent(cc);

				}
			}
			
			
			
			if(selecteds.size() > 1) {
				
				
				int size = selecteds.size();
				
				String asxText ="<asx version = \"3\">\n";
				
				
				for(int i=0;i<size;i++) {
					
					YoutubeData data = selecteds.get(i);
					
					String title = data.getTitle();
					String url = data.getUrl();
					
					
					asxText = asxText + "<Entry><Title>"+title+"</Title><Ref href=\""+url+"\"/></Entry>\n"; 
					
					
					asxText = asxText + "</asx>";
					
					//저장
					saveContent("assets/list.asx",asxText);
					
					
					//드래그
					Dragboard db = lv.startDragAndDrop(TransferMode.ANY);
					ClipboardContent cc = new ClipboardContent();
					
					List<File> files = new ArrayList();
					File file = new File("assets/list.asx");
					
					files.add(file);
					
					cc.putFiles(files);
					
					db.setContent(cc);
					
				}
				
				
			}
			

		});

		lv.setOnDragOver(e -> {

			if (e.getGestureSource() != lv) {
				e.acceptTransferModes(TransferMode.ANY);
			}

		});

		lv.setOnDragDropped(e -> {

			Dragboard db = e.getDragboard();

			if (db.hasUrl()) {

				String url = db.getUrl();
				String title = getYoutubeTitle(url);

				YoutubeData data = new YoutubeData(title, url);

				ov.add(data);

				// db

				new YoutubeDb().insertData(data);

			}

		});

		// button
		addBt.setOnAction(e ->

		{

			String url = addTf.getText().trim();

			if (!url.isEmpty()) {

				String title = getYoutubeTitle(url);

				YoutubeData ytData = new YoutubeData(title, url);

				if (!title.trim().isEmpty()) {

					ov.add(ytData);

					addTf.clear();

					// db
					new YoutubeDb().insertData(ytData);

				}

			}

		});
		removeBt.setOnAction(e -> {

			YoutubeData selected = lv.getSelectionModel().getSelectedItem();

			if (selected != null) {

				ov.remove(selected);

				// db
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

		// event-tf
		searchTf.textProperty().addListener((a, oldValue, newValue) -> {
			String search = searchTf.getText().toLowerCase().trim();

			filteredList.setPredicate(item -> {

				if (item.getTitle().toLowerCase().contains(search)) {

					return true;
				} else {
					return false;
				}

			});

		});

		// textfield
		addTf.setOnAction(e -> {

			addBt.fire();

		});

		// gui

		VBox vb = new VBox(hb, hb2, lv);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));

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

	public void checkIfRunning() {

		try {

			socket = new ServerSocket(port, 0, InetAddress.getByAddress(new byte[] { 127, 0, 0, 1 }));

		} catch (Exception e) {

			System.out.println("이미 실행중");
			Alert alert = new Alert(AlertType.INFORMATION);

			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("assets/warning.png")));

			alert.setTitle("Warning");
			alert.setHeaderText("이미 유튜브앱 실행중");

			alert.showAndWait();

			System.exit(1);

		}

	}
	
	public void saveContent(String filePath, String content) {
		
		try {
		Files.write(Paths.get(filePath),content.getBytes());
		}catch(Exception e) {
			System.out.println("파일 저장 에러");
		}
	}
	
	

	public Connection dbConnector() {

		try {

			if (conn == null) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:sql/SimpleYoutube.sqlite");
				System.out.println("디비연결됨");
				return conn;

			} else {
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

	public class YoutubeDb {

		private PreparedStatement pst;
		private ResultSet rs;

		public ObservableList<YoutubeData> loadData() {

			ObservableList<YoutubeData> tempOv = FXCollections.observableArrayList();

			String query = "select * from Youtube";

			try {
				pst = conn.prepareStatement(query);
				rs = pst.executeQuery();

				while (rs.next()) {

					String title = rs.getString("Title");
					String url = rs.getString("Url");

					YoutubeData data = new YoutubeData(title, url);

					tempOv.add(data);

				}

				rs.close();
				pst.close();

			} catch (Exception e) {

				System.out.println("data load error");
			}

			return tempOv;

		}

		public void insertData(YoutubeData data) {

			String query = "Insert or Replace into Youtube (Title,Url) Values (?,?)";

			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, data.getTitle());
				pst.setString(2, data.getUrl());
				pst.execute();
				pst.close();

			} catch (Exception e) {
				System.out.println("데이터 입력 에러");
			}

		}

		public void deleteData(YoutubeData data) {
			String query = "delete from Youtube where Url = ?";

			try {
				pst = conn.prepareStatement(query);
				pst.setString(1, data.getUrl());
				pst.execute();
				pst.close();

			} catch (Exception e) {
				System.out.println("데이터 삭제 에러");
			}

		}

	}

}
