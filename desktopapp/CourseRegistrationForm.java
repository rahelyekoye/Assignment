/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Zdikus
 */
public class CourseRegistrationForm extends Application {
//public static String tfcourseId,tfcoursename,ccreditField;
static int id=0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Course Registration Form");

        // Create the registration form grid pane
        GridPane gridPane = createCourseRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    private GridPane createCourseRegistrationFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(10, 10, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(60, 60, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
      ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Course Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
        // Add Name Label
        Label courseidLabel = new Label("Course Id: ");
        gridPane.add(courseidLabel, 0,1);
        // Add Name Text Field
        TextField tfcourseId = new TextField();
        tfcourseId.setPrefHeight(40);
       // tfcourseId.setDisable(true);
        gridPane.add(tfcourseId, 1,1);


        // Add Email Label
        Label ccourseidLabel = new Label("Course Name : ");
        gridPane.add(ccourseidLabel, 0, 2);

        // Add Email Text Field
        TextField tfcoursename = new TextField();
        tfcoursename.setPrefHeight(40);
        gridPane.add(tfcoursename, 1, 2);

        // Add Password Label
        Label courseLabel = new Label("Course Credit : ");
        gridPane.add(courseLabel, 0, 3);
  TextArea tarea = new TextArea();
        tarea.setMaxSize(500, 200);
        gridPane.addRow(0, tarea);
        // Add Password Field
        TextField tfcredithour = new TextField();
        tfcredithour.setPrefHeight(40);
        gridPane.add(tfcredithour, 1, 3);
        // Add Name Text Field
        TextField search = new TextField();
        search.setPrefHeight(40);
        
       // tfcourseId.setDisable(true);
        gridPane.add(search, 0,4);
        Button searchButton = new Button("Search");
        searchButton.setMaxSize(100, 40);
        searchButton.setDefaultButton(true);
        gridPane.add(searchButton, 1, 4, 2, 1); 
        // Add Submit Button
        Button registerButton = new Button("Register");
        registerButton.setMaxSize(100, 40);
        registerButton.setDefaultButton(true);
        gridPane.add(registerButton, 1, 4, 2, 1);
          Button cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(100, 40);
        cancelButton.setDefaultButton(true);
        gridPane.add(cancelButton, 2, 4, 3, 1);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0,20,0));
    cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
       if(tfcourseId.getText().trim()!= null || tfcoursename.getText().trim()!=null || tfcredithour.getText().trim()!=null )
       {
           tfcourseId.clear();
             tfcoursename.clear();
             tfcredithour.clear();
       }
            }
        });
        
 searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
        BufferedReader br = null; 
                try {
                    br = new BufferedReader( new FileReader("Courseprofile.txt") );
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CourseRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
        String ID =search.getText().trim();
                     String record;
                try {
                    while( ( record = br.readLine()) != null ) {
                        StringTokenizer st = new StringTokenizer(record,","); 
                        if( record.contains(ID) ) {
                           //System.out.println("|  "+st.nextToken());
                            String st1=st.nextToken().toString();
                            String[] ser=st1.split(" ");
                            String first = ser[ser.length - 3]; 
                            String second = ser[ser.length - 2];
                            String third = ser[ser.length - 1];
                            //System.out.println(" "+third);
                            tfcourseId.setText(first);
                            tfcoursename.setText(second);
                            tfcredithour.setText(third);
                            tarea.setText("Course ID "+"COurse Name"+
                                    "Course Credit  "+"");
                            String ta=tarea.getText().trim();
                           tarea.setText(ta+"\n"+first+"\t\t"+second+"\t\t"+third);

                            
                        } 
                    }       } catch (IOException ex) {
                    Logger.getLogger(CourseRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
           try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(CourseRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        });
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
                  String courseid=tfcourseId.getText();
                  String ccourseid=tfcoursename.getText();
                  String ccredit=tfcredithour.getText();
                if(courseid.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter course Id");
                    return;
                }
                if(ccourseid.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter course courseid");
                    return;
                }
                if(ccredit.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a course credit hour");
                    return;
                }
                else if(courseid!=null && ccourseid!=null && ccredit!=null)
                {
              FileWriter b = null;
                      try {
                          b = new FileWriter("Courseprofile.txt",true);
                      } catch (IOException ex) {
                          Logger.getLogger(CourseRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                      }
        PrintWriter pw = null;
        Scanner in=new Scanner(System.in);
        pw = new PrintWriter(b);
          pw.write(courseid+" ");
          pw.print(ccourseid+" ");
          pw.print(ccredit+" "); 
          pw.println();
           pw.close();
          String value=tarea.getText().trim();
            tarea.setText(value+"\n"+courseid+"\t"+ccourseid+"\t"+ccredit);

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + courseid+ccourseid+ccredit);
            
                }}
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
