/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktopapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class Login extends Application {
   Scene window; 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Form");

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
            Label headerLabel = new Label("Login Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        logonPane.add(headerLabel, 0,0,2,1);
        logonPane.setHalignment(headerLabel, HPos.CENTER);
        logonPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        Label nameLabel = new Label("User Name : ");
        logonPane.add(nameLabel, 0,1);

        // Add Name Text Field
        TextField username = new TextField();
        username.setPrefHeight(40);
        logonPane.add(username, 1,1);
        // Add Password Label
        Label passwordLabel = new Label("Password : ");
        logonPane.add(passwordLabel, 0, 2);

        // Add Password Field
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        logonPane.add(passwordField, 1, 2);
        
        Button loginbtn = new Button("Login");
        loginbtn.setMaxSize(100, 40);
        loginbtn.setDefaultButton(true);
        logonPane.add(loginbtn, 0, 3, 2, 1);
          Button cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(100, 40);
        cancelButton.setDefaultButton(false);
        logonPane.add(cancelButton, 0, 4, 3, 1);
        GridPane.setHalignment(loginbtn, HPos.CENTER);
        GridPane.setMargin(loginbtn, new Insets(20, 0,20,0));
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
                  String studid=username.getText().trim();
                  String studpas=passwordField.getText().trim();
                  String record;
                if(studid.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, logonPane.getScene().getWindow(), "Form Error!", "Please enter course Id");
                    return;
                }
                if(studid!=null)
                {
                      try {        
                          BufferedReader     br = new BufferedReader( new FileReader("Studentprofile.txt") );
                            while( ( record = br.readLine()) != null ) {
                        StringTokenizer st = new StringTokenizer(record,","); 
                        if( record.contains(studid) ) {
                           //System.out.println("|  "+st.nextToken());
                            String st1=st.nextToken().toString();
                            String[] ser=st1.split(" ");
                             String username = ser[ser.length - 5]; 
                             String password = ser[ser.length - 3]; 
                           if(username.equals(studid) && password.equals(studpas))
                           {
         Stage stage = (Stage) loginbtn.getScene().getWindow();
              stage.close(); 
               HomePage sfa=new HomePage();
                                try {
                                    sfa.start(new Stage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                           }
                            
                        } 
                    } 
                      } catch (FileNotFoundException ex) {
                          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                      } catch (IOException ex) {
                          Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                      }
                    
                }
            }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();        }
        }); 
    }
    }
   
