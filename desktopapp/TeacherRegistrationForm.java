/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
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
public class TeacherRegistrationForm extends Application {
  public static String nameField,emailField,passwordField;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Teacher Registration Form");

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
        gridPane.setPadding(new Insets(0, 0, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
      ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Teacher Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("Teacher Id: ");
        gridPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);


        // Add Email Label
        Label emailLabel = new Label(" First Name : ");
        gridPane.add(emailLabel, 0, 2);

        // Add Email Text Field
        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        // Add Password Label
        Label courseLabel = new Label(" Last Name: ");
        gridPane.add(courseLabel, 0, 3);

        // Add Password Field
        TextField courseField = new TextField();
        courseField.setPrefHeight(40);
        gridPane.add(courseField, 1, 3);
       
        // Add Submit Button
        Button registerButton = new Button("Register");
        registerButton.setMaxSize(100, 40);
        registerButton.setDefaultButton(true);
        gridPane.add(registerButton, 0, 6, 2, 1);
          Button cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(100, 40);
        cancelButton.setDefaultButton(true);
        gridPane.add(cancelButton, 0, 7, 3, 1);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0,20,0));

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
                  String name=nameField.getText();
                  String email=emailField.getText();
                  String password=courseField.getText();
                if(email.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter teacher first name");
                    return;
                }
                if(password.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter teacher last name");
                    return;
                }
                else if(email!=null && password!=null)
                {
              FileWriter  b = null;
                      try {
                          b = new FileWriter ("Teacherprofile.txt",true);
                      } catch (IOException ex) {
                          Logger.getLogger(TeacherRegistrationForm.class.getName()).log(Level.SEVERE, null, ex);
                      }
        PrintWriter pw = null;
        Scanner in=new Scanner(System.in);
          pw = new PrintWriter(b);

          pw.write(name+" ");
          pw.print(email+" ");
          pw.print(password+" "); 
                pw.println();
          pw.close();
         
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + name+email+password);
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
