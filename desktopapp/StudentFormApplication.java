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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

public class StudentFormApplication extends Application {
public static String fname,studid,lnameField;
Stage window;
Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        window.setTitle("Student Registration Form ");

        // Create the registration form grid pane
        GridPane gridPane = createStudentRegistrationFormPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage	
        window.setScene(scene);
        
        window.show();
    }
    private GridPane createStudentRegistrationFormPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(0, 0, 100, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
      ColumnConstraints columnTwoConstrains = new ColumnConstraints(100,100, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }
    private void addUIControls(GridPane gridPane) throws FileNotFoundException {
        // Add Header
        Label headerLabel = new Label("Student Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

                // Add Email Label
        Label stidLabel = new Label("Student ID : ");
        gridPane.add(stidLabel, 0, 1);

        // Add Email Text Field
        TextField studid = new TextField();
        studid.setPrefHeight(40);
        gridPane.add(studid, 1, 1);
        // Add Name Label
        Label nameLabel = new Label("First Name : ");
        gridPane.add(nameLabel, 0,2);

        // Add Name Text Field
        TextField fname = new TextField();
        fname.setPrefHeight(40);
        gridPane.add(fname, 1,2);

        // Add Password Label
        Label lastLabel = new Label("Last Name : ");
        gridPane.add(lastLabel, 0, 3);

        // Add Password Field
        TextField lastname = new TextField();        
        lastname.setPrefHeight(40);
        gridPane.add(lastname, 1, 3);
        Label AcadamicYear = new Label("Batch: ");
        gridPane.add(AcadamicYear, 0, 4);
        // Add Password Field
        ComboBox cb = new ComboBox();
        cb.getItems().addAll("1","2","3","4","5");
        cb.setPromptText("Select Batch");
        cb.setMaxSize(1000, 80);
        gridPane.add(cb, 1, 4);
         Label AcadamicSemister = new Label("Acadamic Semister : ");
        gridPane.add(AcadamicSemister, 0, 5);
        // Add Password Field
        ComboBox cbs = new ComboBox();
          cbs.getItems().addAll("I","II");
          //String cd=cbs.getValue();
          cbs.setPromptText("Select Semister");
        cbs.setMaxSize(1000, 80);
        gridPane.add(cbs, 1, 5);
        
        File copen=new File("Courseprofile.txt");
   Scanner scan1=new Scanner(copen);
 String coid[]=null,first=null;
   
   ArrayList<String> clist = new ArrayList<String>();
   
while (scan1.hasNext()){
     coid = scan1.nextLine().split(" ");
     first = coid[coid.length - 3]; 
     clist.add(first);
}
        Label cid = new Label("Course ID: ");
        cid.setMaxSize(1000, 80);
        gridPane.add(cid, 0, 6); 
        // Add Password Field
        ComboBox crid = new ComboBox();
        crid.setMaxSize(1000, 80);
        crid.setPromptText("Select Course ID");
        crid.getItems().addAll(clist);
        gridPane.add(crid, 1, 6);
        scan1.close();
       
        TextArea tarea = new TextArea();
        tarea.setMaxSize(500, 200);
        gridPane.addRow(0, tarea);
          
        // Add Submit Button
        Button registerButton = new Button("Register");
        registerButton.setMaxSize(100, 40);
        registerButton.setDefaultButton(true);
        gridPane.add(registerButton, 0, 7,1,1);
          Button cancelButton = new Button("Cancel");
        cancelButton.setMaxSize(100, 40);
        cancelButton.setDefaultButton(true);
        gridPane.add(cancelButton, 1, 7,2,1);
        
        TextField search = new TextField();
        search.setPrefHeight(40);
        gridPane.add(search, 0,8,1,1);
        Button searchButton = new Button("Search");
        searchButton.setMaxSize(100, 40);
        searchButton.setDefaultButton(true);
        gridPane.add(searchButton, 1,8,2,1); 
        
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setMargin(registerButton, new Insets(20, 0,20,0));
    searchButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                   BufferedReader br = null; 
                try {
                    br = new BufferedReader( new FileReader("Studentprofile.txt") );
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
                            String id = ser[ser.length - 5]; 
                            String fn = ser[ser.length - 4];
                            String ln = ser[ser.length - 3];
                             String batch = ser[ser.length - 2];
                            String semister = ser[ser.length - 1];
                            tarea.setText("Student Id "+"Student First Name "+"Student Last Name "+
                                    "Batch  "+"Semister ");
                            String ta=tarea.getText().trim();
                           tarea.setText(ta+"\n"+id+"\t\t"+fn+"\t\t"+ln+"\t\t\t\t"+batch+"\t\t"+semister);
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
          studid.clear();
          fname.clear();
          lastname.clear();
            }
        });
registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
          
            public void handle(ActionEvent event) {
                  String stid=studid.getText();
                  String name=fname.getText();
                  String lname=lastname.getText();
                  String batch=(String) cb.getValue();
                  String semister=(String) cbs.getValue();
                if(name.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your name");
                    return;
                }
                if(stid.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your stid id");
                    return;
                }
                if(lname.isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter a lname");
                    return;
                }
                else if(name!=null && stid!=null && lname!=null)
                {
              FileWriter b = null;
                      try {
                          b = new FileWriter("Studentprofile.txt",true);
                      } catch (IOException ex) {
                          Logger.getLogger(StudentFormApplication.class.getName()).log(Level.SEVERE, null, ex);
                      }
        PrintWriter pw = null;
        pw = new PrintWriter(b);
         pw.print(stid+" ");
          pw.write(name+" ");
          pw.print(lname+" ");
          pw.print(batch+" ");
          pw.print(semister);
          pw.println();

           pw.close();
           String value=tarea.getText().trim();
            tarea.setText(value+"\n"+stid+"\t"+name+"\t"+lname+"\t"+batch+"\t"+semister);
showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Registration Successful!", "Welcome " + name+stid+lname);
          studid.setText("");
          fname.setText("");
          lastname.clear();
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