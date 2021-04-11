/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
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
public class TeacherCourseAssignment extends Application {
 public static String nameField,emailField,passwordField;
 static String fulname = null;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Registration Form JavaFX Application");

        // Create the registration form grid pane
        GridPane gridPane = createStudentRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    private GridPane createStudentRegistrationFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(0,0, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
      ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) throws FileNotFoundException {
        // Add Header
        Label headerLabel = new Label("Teacher To Course Assignment Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));
   File topen=new File("teacherprofile.txt");
   Scanner scan=new Scanner(topen);
   String last = null,tokens[],fname,lname;
     ArrayList<String> list = new ArrayList<String>();
   while (scan.hasNext()){
     tokens = scan.nextLine().split(" ");
     last = tokens[tokens.length - 3];
     fname=tokens[tokens.length - 2];
     lname=tokens[tokens.length - 1];
     fulname=fname+lname;
     list.add(last);
}
  Label TechaerId = new Label("Teacher ID: ");
        gridPane.add(TechaerId, 0, 1); 
   ComboBox cbx= new ComboBox<>();
        cbx.setPromptText("Select Teacher Id");
        cbx.getItems().addAll(list);
        cbx.setMaxSize(1000, 80);
        gridPane.add(cbx, 1, 1);
  scan.close();
File copen=new File("Courseprofile.txt");
   Scanner scan1=new Scanner(copen);
   String coid[]=null,first=null;
   
   ArrayList<String> clist = new ArrayList<String>();
   
while (scan1.hasNext()){
     coid = scan1.nextLine().split(" ");
     first = coid[coid.length - 3]; 
    clist.add(first);
}
        Label courseid = new Label("Course ID: ");
        gridPane.add(courseid, 0, 2); 
        // Add Password Field
        ComboBox tid = new ComboBox();
        tid.getItems().addAll(clist);
        scan1.close();
        tid.setPromptText("Select Course Id");
        tid.setMaxSize(1000, 80);
        gridPane.add(tid, 1, 2);
       Label AcadamicYear = new Label("Batch : ");
        gridPane.add(AcadamicYear, 0, 3); 
        ComboBox cb = new ComboBox();
        cb.getItems().addAll("1","2","3","4","5");
        cb.setValue("1");
        cb.setMaxSize(1000, 80);
        gridPane.add(cb, 1, 3);
         Label AcadamicSemister = new Label("AcadamicSemister : ");
        gridPane.add(AcadamicSemister, 0, 4);
        // Add Password Field
        
        ComboBox cbs = new ComboBox();
          cbs.getItems().addAll("I","II");
          cbs.setValue("I");
        cbs.setMaxSize(1000, 80);
        gridPane.add(cbs, 1, 4);
        // Add Submit Button
        Button registerButton = new Button("Assign");
        registerButton.setMaxSize(100, 40);
        registerButton.setDefaultButton(true);
        gridPane.add(registerButton, 0, 6, 2, 1);
          Button cancelButton = new Button("Cancel");
          
        TextArea tarea = new TextArea();
        tarea.setMaxSize(500, 200);
        gridPane.addRow(0, tarea);
        TextField search = new TextField();
        search.setPrefHeight(40);
        gridPane.add(search, 0,7);
        Button searchButton = new Button("Search");
        searchButton.setMaxSize(100, 40);
        searchButton.setDefaultButton(true);
        gridPane.add(searchButton, 1, 7, 1, 1); 
        
        cancelButton.setMaxSize(100, 40);
        cancelButton.setDefaultButton(true);
        gridPane.add(cancelButton, 0, 7, 3, 1);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0,20,0));
        searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                   BufferedReader br = null; 
                try {
                    br = new BufferedReader( new FileReader("teachertocourseprofile.txt") );
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
                            String fn = ser[ser.length - 4];
                            String ln = ser[ser.length - 3];
                             String batch = ser[ser.length - 2];
                            String semister = ser[ser.length - 1];
                            
                            tarea.setText("Teacher Id "+" Course Id"+
                                    " Batch  "+" Semister ");
                            String ta=tarea.getText().trim();
                           tarea.setText(ta+"\n"+"\t"+fn+"\t"+ln+"\t"+batch+"\t"+semister);
                            //System.out.println(" "+third);
                            
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
cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              cbx.setValue("Select Teacher Id");
                tid.setValue("Select Course Id");
                cb.setValue("Select Batch");
                cbs.setValue("Select Semister");            }
        });

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
                  String teacher=(String) cbx.getValue();
                  String course=(String) tid.getValue();
                  String ayear=(String) cb.getValue();
                  String asemister=(String)cbs.getValue();

              FileWriter b = null;
                try {
                    b = new FileWriter("teachertocourseprofile.txt",true);
                } catch (IOException ex) {
                    Logger.getLogger(TeacherCourseAssignment.class.getName()).log(Level.SEVERE, null, ex);
                }
        PrintWriter pw = null;
        pw = new PrintWriter(b);
          pw.write(teacher+" ");
          pw.print(course+" ");
          pw.print(ayear+" "); 
          pw.print(asemister+" ");
            pw.println();
            String value=tarea.getText().trim();
            tarea.setText(value+"\n"+"\t"+teacher+"\t"+course+"\t"+ayear+"\t"+asemister);

                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(),
                        "Assignment Successful!",fulname);              
           pw.close();
           cbx.setValue("Select Teacher Id");
                tid.setValue("Select Course Id");
                cb.setValue("Select Batch");
                cbs.setValue("Select Semister");
             }
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
