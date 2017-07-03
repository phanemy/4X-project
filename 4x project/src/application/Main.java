package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import map.Map;
import mapWidget.MapGroup;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//readXml();
			Map map = new Map(60, 30, 20.6f,4,0.5f,2);
			MapGroup mapGroup = new MapGroup(map);
			Pane root = new Pane();
			/*
			
			Label l = new Label();
			l.setFont(new Font(10));
			root.getChildren().add(l);
			l.setLayoutX(40);
			l.setLayoutY(80);
			l.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
				public void handle(javafx.scene.input.KeyEvent event) {
				if(event.getCode().isLetterKey()){
					l.setText(l.getText()+event.getText());
				}
				if(event.getCode().ordinal()==1)
				{
					l.setText(l.getText().substring(0, l.getText().length()-1));
				}
                event.consume();
            }});
			l.setFocusTraversable(true);
			
			*/
			root.getChildren().add(mapGroup);
			Scene scene = new Scene(root,1280, 720);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
