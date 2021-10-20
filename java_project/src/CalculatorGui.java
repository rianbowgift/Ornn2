
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorGui extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		TextField firstTf = new TextField();
		TextField secondTf = new TextField();
		
		Button addBt = new Button("����");
		Button subBt = new Button("����");
		Button mulBt = new Button("����");
		Button divBt = new Button("������");
		
		Label resultLb = new Label("result");
		
		HBox hb = new HBox(firstTf,secondTf);
		HBox hb2 = new HBox(addBt,subBt,mulBt,divBt);
		hb.setSpacing(2);
		hb2.setSpacing(2);
		
		VBox vb = new VBox(hb,hb2,resultLb);
		vb.setSpacing(2);
		vb.setPadding(new Insets(2));
		
		Scene scene = new Scene(vb,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle("����");
		
		
		//event
		addBt.setOnAction(e->{
			
			try {
			int first = Integer.parseInt(firstTf.getText());
			int second = Integer.parseInt(secondTf.getText());
			
			int sum = first + second;
			
			resultLb.setText("sum" +sum);
			}catch(Exception e2) {
				resultLb.setText("�ؽ�Ʈ�ʵ� ����");
			}
		});
		subBt.setOnAction(e->{
			try {
			int first = Integer.parseInt(firstTf.getText());
			int second = Integer.parseInt(secondTf.getText());
			
			int sub = first - second;
			
			resultLb.setText("sub" +sub);
			}catch(Exception e2) {
				resultLb.setText("�ؽ�Ʈ�ʵ� ����");
			}
			
		});
		mulBt.setOnAction(e->{
			try {
			int first = Integer.parseInt(firstTf.getText());
			int second = Integer.parseInt(secondTf.getText());
			
			int mul = first * second;
			
			resultLb.setText("mul" +mul);
			}catch(Exception e2) {
				resultLb.setText("�ؽ�Ʈ�ʵ� ����");
			}
			
		});
		divBt.setOnAction(e->{
			try {
			float first = Integer.parseInt(firstTf.getText());
			float second = Integer.parseInt(secondTf.getText());
			
			float div = first / second;
			
			resultLb.setText("div" +div);
			}catch(Exception e2) {
				resultLb.setText("�ؽ�Ʈ�ʵ� ����");
			}
			
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
