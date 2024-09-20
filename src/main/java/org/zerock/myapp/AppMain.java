package org.zerock.myapp;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;


@NoArgsConstructor
@Log4j2
public class AppMain extends Application {

	
	public void init() {
		log.trace("init() invoked.");
	} // init
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		URL fxml = this.getClass().getResource("AppMain.fxml");
		Parent root = FXMLLoader.<HBox>load(fxml);
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		
		primaryStage.setTitle("AppMain");
		primaryStage.setAlwaysOnTop(true);
		
		primaryStage.show();
	} // start
	
	public void stop() {
		
	} // stop
	
	public static void main(String[] args) {
		Application.launch(args);
	} // main

} // end class
