package Youtube;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class YoutubeKnowledge extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		String youtube = "https://www.youtube.com/watch?v=XVIe7W-VOLM";
		

		System.out.println(youtube);
		
		String title = getYoutubeTitle(youtube);
		
		System.out.println(title);
		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public String getYoutubeTitle(String youtubeUrl) {
		
		youtubeUrl = "https://www.youtube.com/oembed?url=" + youtubeUrl + "&format=xml";
		
		try {
		Document document = Jsoup.connect(youtubeUrl).get();
		
		Element titleEle = document.getElementsByTag("title").get(0);
		
		
		String title = titleEle.text();
		
		return title;
		
		}catch(Exception e) {
			System.out.println("¿¡·¯ - °Ù youtube title");
			
			return "";
		}
		
	}
	
}
