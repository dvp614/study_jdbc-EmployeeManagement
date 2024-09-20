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
public class GetEmpCountByDepController implements Initializable{
	@FXML
    private TableView<EmpSampleData> tableView;
    @FXML
    private TableColumn<EmpSampleData, Integer> departmentNameColumn;
    @FXML
    private TableColumn<EmpSampleData, String> employeeCountColumn;
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
    	departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    	employeeCountColumn.setCellValueFactory(new PropertyValueFactory<>("employeeCount"));
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
            		"SELECT D.DEPARTMENT_NAME, COUNT(E.EMPLOYEE_ID) AS EMPLOYEE_COUNT " +
                            "FROM DEPARTMENTS D " +
                            "JOIN EMPLOYEES E ON (D.DEPARTMENT_ID = E.DEPARTMENT_ID) " +
                            "GROUP BY D.DEPARTMENT_NAME " +
                            "ORDER BY D.DEPARTMENT_NAME");

            while (rs.next()) {
            	String departmentName = rs.getString("DEPARTMENT_NAME");
                int employeeCount = rs.getInt("EMPLOYEE_COUNT");

                // Assuming EmpSampleData constructor takes appropriate parameters
                EmpSampleData data = new EmpSampleData(departmentName, employeeCount);
                
                log.info("Loaded data: {}", data.toString());
                
                tableView.getItems().add(data);
            } // while

        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch
        
    }

} // end class
