package org.zerock.myapp.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestController {

    @FXML
    private TableView<SampleData> tableView;

    @FXML
    private TableColumn<SampleData, Integer> employeeIdColumn;
    @FXML
    private TableColumn<SampleData, String> firstNameColumn;
    @FXML
    private TableColumn<SampleData, String> lastNameColumn;
    @FXML
    private TableColumn<SampleData, String> emailColumn;
    @FXML
    private TableColumn<SampleData, String> phoneNumberColumn;
    @FXML
    private TableColumn<SampleData, Date> hireDateColumn;
    @FXML
    private TableColumn<SampleData, String> jobIdColumn;
    @FXML
    private TableColumn<SampleData, Double> salaryColumn;
    @FXML
    private TableColumn<SampleData, Double> commissionPctColumn;
    @FXML
    private TableColumn<SampleData, Integer> managerIdColumn;
    @FXML
    private TableColumn<SampleData, Integer> departmentIdColumn;
    @FXML
    private Button btnLoad;

    static final String driverClass = "oracle.jdbc.OracleDriver";            
    static final String jdbcUrl="jdbc:oracle:thin:@localhost:1521/XEPDB1";
    static final String dbUser = "HR";           
    static final String dbPass = "oracle";

    @FXML
    private void initialize() {
        log.trace("initialize() invoked.");
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
        jobIdColumn.setCellValueFactory(new PropertyValueFactory<>("jobId"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        commissionPctColumn.setCellValueFactory(new PropertyValueFactory<>("commissionPct"));
        managerIdColumn.setCellValueFactory(new PropertyValueFactory<>("managerId"));
        departmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
    } // initialize

    public void handleLoadBtn() {
    	loadData();
    } // handleLoadBtn

    @FXML
    private void loadData() {
        log.trace("loadData() invoked.");

        tableView.getItems().clear(); // Clear existing items
        
        try  {
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

            while (rs.next()) {
                int     employeeId      = rs.getInt("EMPLOYEE_ID");
                String  firstName       = rs.getString("FIRST_NAME");
                String  lastName        = rs.getString("LAST_NAME");
                String  email           = rs.getString("EMAIL");
                String  phoneNumber     = rs.getString("PHONE_NUMBER");
                Date    hireDate        = rs.getDate("HIRE_DATE");
                String  jobId           = rs.getString("job_id");
                Double  salary          = rs.getDouble("salary");
                Double  commissionPct   = rs.getDouble("COMMISSION_PCT");
                int     managerId       = rs.getInt("manager_id");
                int     departmentId    = rs.getInt("DEPARTMENT_ID");

                SampleData data = new SampleData(employeeId, firstName, lastName, email, phoneNumber, 
                        hireDate, jobId, salary, commissionPct, managerId, departmentId);
                
                log.info("Loaded data: {}", data.toString());
                
                tableView.getItems().add(data);
            } // while

        } catch (Exception e) {
            e.printStackTrace();
        } // try-catch
        
    } // loadData
    
} // end class
