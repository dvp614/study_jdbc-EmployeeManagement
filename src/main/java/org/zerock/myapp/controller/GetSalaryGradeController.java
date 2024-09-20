package org.zerock.myapp.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
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
public class GetSalaryGradeController implements Initializable {
	@FXML
	private TableView<SalarySampleData> tableView;
	@FXML
	private TableColumn<SalarySampleData, Integer> nameColumn;
	@FXML
	private TableColumn<SalarySampleData, String> jobIdColumn;
	@FXML
	private TableColumn<SalarySampleData, String> departmentNameColumn;
	@FXML
	private TableColumn<SalarySampleData, String> hireDateColumn;
	@FXML
	private TableColumn<SalarySampleData, String> SalaryColumn;
	@FXML
	private TableColumn<SalarySampleData, Date> gradeLevelColumn;

	@FXML
	private Button btnLoad;
	@FXML
	private Button btnBack;
	@FXML
	private Button btnHome;

	static final String driverClass = "oracle.jdbc.OracleDriver";
	static final String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	static final String dbUser = "HR";
	static final String dbPass = "oracle";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		log.trace("initialize() invoked.");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		departmentNameColumn.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
		jobIdColumn.setCellValueFactory(new PropertyValueFactory<>("jobId"));
		SalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
		gradeLevelColumn.setCellValueFactory(new PropertyValueFactory<>("gradeLevel"));

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

		} catch (IOException e) {
			e.printStackTrace();

		} // try-catch
	} // loadPage

	@FXML
	private void loadData() {
		log.trace("loadData() invoked.");

		tableView.getItems().clear(); // Clear existing items

		try {
			Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT " 
					+ "E.FIRST_NAME || ' ' || E.LAST_NAME AS \"Name\", "
					+ "E.JOB_ID, " 
					+ "D.DEPARTMENT_NAME, " 
					+ "E.HIRE_DATE, " 
					+ "E.SALARY, " 
					+ "J.GRADE_LEVEL " 
					+ "FROM "
					+ "EMPLOYEES E " 
					+ "JOIN DEPARTMENTS D ON (E.DEPARTMENT_ID = D.DEPARTMENT_ID) "
					+ "JOIN JOB_GRADES J ON (E.SALARY BETWEEN J.LOWEST_SAL AND J.HIGHEST_SAL)");

			while (rs.next()) {
				String fullName 		= rs.getString("Name");
				String departmentName 	= rs.getString("DEPARTMENT_NAME");
				Date hireDate 			= rs.getDate("HIRE_DATE");
				String jobId 			= rs.getString("JOB_ID");
				double salary 			= rs.getDouble("SALARY");
				String gradeLevel 		= rs.getString("GRADE_LEVEL");

				// Assuming SampleData constructor takes appropriate parameters
				SalarySampleData data = new SalarySampleData(
						fullName, departmentName, hireDate, jobId, salary, gradeLevel);

				log.info("Loaded data: {}", data.toString());

				tableView.getItems().add(data);
			} // while

		} catch (Exception e) {
			e.printStackTrace();
		} // try-catch

	}

} // end class
