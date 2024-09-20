package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class GetWorkHistoryController implements Initializable{
	@FXML
    private TableView<HistorySampleData> tableView;
    @FXML
    private TableColumn<HistorySampleData, Integer> employeeIdColumn;
    @FXML
    private TableColumn<HistorySampleData, String> jobIdColumn;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnHome;

    static final String driverClass = "oracle.jdbc.OracleDriver";            
    static final String jdbcUrl="jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static final String dbUser = "HR";           
    static final String dbPass = "oracle";
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	log.trace("initialize() invoked.");
    	employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    	jobIdColumn.setCellValueFactory(new PropertyValueFactory<>("jobId"));

    	loadData();
    } // loadData

	public void handleBackBtn() {
		Stage primaryStage = (Stage) btnBack.getScene().getWindow();
		
		loadPage("GetMain", primaryStage);
	} // handleBackBtn
	
	public void handleHomeBtn() {
		Stage primaryStage = (Stage) btnHome.getScene().getWindow();
		
		loadPage("AppMain", primaryStage);
	} // handleHomeBtn
	
	public void handleLoadBtn() {
//        loadData();
    } // handleLoadBtn
	
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
	
	@FXML
    private void loadData() {
        log.trace("loadData() invoked.");

        tableView.getItems().clear(); // Clear existing items
        
        try  {
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
            		"(SELECT EMPLOYEE_ID, JOB_ID FROM EMPLOYEES) " +
                    "UNION " +
                    "(SELECT EMPLOYEE_ID, JOB_ID FROM JOB_HISTORY)");

            while (rs.next()) {
                int     employeeId      = rs.getInt("EMPLOYEE_ID");
                String  jobId           = rs.getString("job_id");

                HistorySampleData data = new HistorySampleData(employeeId, jobId);
                
                log.info("Loaded data: {}", data.toString());
                
                tableView.getItems().add(data);
            } // while

        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch
        
    }

} // end class
