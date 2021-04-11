/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Zdikus
 */
public class HomePage extends Application {
    
    @Override
    public void start(Stage primaryStage) {
         primaryStage.setTitle("DashBoard");

        // Create the registration form grid pane
        GridPane logonPane = createlogonPane();
        // Add UI controls to the registration form grid pane
        addLogonControls(logonPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(logonPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
     private GridPane createlogonPane() {
            GridPane logonPane = new GridPane();
        logonPane.setAlignment(Pos.TOP_CENTER);
        logonPane.setPadding(new Insets(10, 10, 40, 40));
        logonPane.setHgap(10);
        logonPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
      ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        logonPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return logonPane; 
    }
      private void addLogonControls(GridPane logonPane) {
            Label headerLabel = new Label("WelCome DashBoard");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logonPane.add(headerLabel, 0,0,2,1);
        logonPane.setHalignment(headerLabel, HPos.CENTER);
        logonPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        
        Button btn4 = new Button("Go To Student Registration");
        btn4.setMaxSize(100, 100);
        logonPane.add(btn4, 0, 1);
        Button btn41 = new Button("Go To Teacher Registration");
        btn41.setMaxSize(100, 100);
           logonPane.add(btn41, 0, 2);
        Button btn42 = new Button("Go To Course Registration");
        btn42.setMaxSize(100, 100);
           logonPane.add(btn42, 0,3);
        Button btn43 = new Button("Go To Course to Teacher Assignment");
        btn43.setMaxSize(100, 100);
           logonPane.add(btn43, 0, 4);
        GridPane.setHalignment(btn4, HPos.CENTER);
        GridPane.setMargin(btn4, new Insets(20, 0,20,0));
btn4.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
              Stage stage = (Stage) btn4.getScene().getWindow();
              stage.close(); 
               StudentFormApplication sfa=new StudentFormApplication();
                                try {
                                    sfa.start(new Stage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                }
            });
btn41.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
              Stage stage = (Stage) btn4.getScene().getWindow();
              stage.close(); 
               TeacherRegistrationForm sfa=new TeacherRegistrationForm();
                                try {
                                    sfa.start(new Stage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                }
            });
btn42.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
              Stage stage = (Stage) btn4.getScene().getWindow();
              stage.close(); 
               CourseRegistrationForm sfa=new CourseRegistrationForm();
                                try {
                                    sfa.start(new Stage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                }
            });
btn43.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
              Stage stage = (Stage) btn4.getScene().getWindow();
              stage.close(); 
               TeacherCourseAssignment sfa=new TeacherCourseAssignment();
                                try {
                                    sfa.start(new Stage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                }
            });
      }
}
