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

public class GetMainController implements Initializable{
	@FXML
	private Button btnHome;
	@FXML
	private Button btnSalaryLevel;
	@FXML
	private Button btnWorkHistory;
	@FXML
	private Button btnWorkSatusByRegion;
	@FXML
	private Button btnNumOfEmpByDep;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	} // initialize
	
	
	public void handleHomebtn() {
		Stage primaryStage = (Stage) btnHome.getScene().getWindow();
		
		loadPage("AppMain", primaryStage);
	} // handleHomebtn
	
	public void handleSalaryLevelBtn() {
		Stage primaryStage = (Stage) btnSalaryLevel.getScene().getWindow();
		
		loadPage("GetSalaryGrade", primaryStage);
	} // handleSalaryLevelBtn
	
	public void handleWorkHistoryBtn() {
		Stage primaryStage = (Stage) btnWorkHistory.getScene().getWindow();
		
		loadPage("GetWorkHistory", primaryStage);
	} // handleWorkHistoryBtn                                                     
	                                                                              
	public void handleWorkStatusByRegionBtn() {                                   
		Stage primaryStage = (Stage) btnWorkSatusByRegion.getScene().getWindow(); 
		                                                                          
		loadPage("GetWorkStatusByRegion", primaryStage);                        
	} // handleWorkStatusByRegionBtn
	
	public void handleNumOfEmpByDepBtn() {
		Stage primaryStage = (Stage) btnNumOfEmpByDep.getScene().getWindow();
		
		loadPage("GetEmpNumByDep", primaryStage);
	} // handleNumOfEmpByDepBtn

	
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
