module org.zerock.myapp {
    
    requires java.se;
    
    requires javafx.controls;
    requires javafx.fxml;
    
    requires lombok;
    requires org.apache.logging.log4j;
    
    opens org.zerock.myapp 
       to javafx.graphics,
          javafx.fxml,
          javafx.base;
    
    opens org.zerock.myapp.controller 
    to javafx.graphics,
       javafx.fxml,
       javafx.base;
    
} // module

