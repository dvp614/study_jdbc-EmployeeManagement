package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AppMainController implements Initializable{
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnRegistrate;
	@FXML
	private Button btnCheck;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	} // initialize

	
	public void handleSearchBtn() {
		Stage primaryStage = (Stage) btnCheck.getScene().getWindow();
		
		loadPage("ResearchTool", primaryStage);
	} // handleSearchBtn
	
	public void handleDeleteBtn() {
		Stage primaryStage = (Stage) btnCheck.getScene().getWindow();
		
		loadPage("delete", primaryStage);
	} // hadleDeleteBtn
	
	public void handleRegistrateBtn() {
		Stage primaryStage = (Stage) btnCheck.getScene().getWindow();
		
		loadPage("스토리보드등록제작중", primaryStage);
	} // handleRegitstrateBtn
	
	public void handleCheckBtn() {
		Stage primaryStage = (Stage) btnCheck.getScene().getWindow();
		
		loadPage("GetMain", primaryStage);
	}

	private void loadPage(String page, Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/org/zerock/myapp/" + page + ".fxml"));
			Parent root = loader.<AnchorPane>load();
			primaryStage.setScene(new Scene(root));
			
			primaryStage.setTitle(page);
			
		} catch (IOException e){
			e.printStackTrace();
			
		} // try-catch
	} // loadPage

} // end class
