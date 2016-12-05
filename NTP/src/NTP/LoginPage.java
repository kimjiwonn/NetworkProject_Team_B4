package NTP;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.application.Application;
import javafx.scene.Scene; 
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginPage extends Application {

   private VBox pane;
   
   @Override
   public void start(Stage stage) throws Exception {

      pane = new VBox();
      pane.setSpacing(30);
      pane.setStyle("-fx-background-color:WHITE;-fx-padding:40;");
      
               
      JFXTextField IDField = new JFXTextField();
      IDField.setLabelFloat(true);
      IDField.setPromptText("ID");
      RequiredFieldValidator validator = new RequiredFieldValidator();
      validator.setMessage("Input Required");
      IDField.getValidators().add(validator);
      IDField.focusedProperty().addListener((o,oldVal,newVal)->{
         if(!newVal) IDField.validate();
      });
      pane.getChildren().add(IDField);
      
      
      JFXPasswordField passwordField = new JFXPasswordField();
      passwordField.setStyle("-fx-label-float:true;");
      passwordField.setPromptText("Password");
      validator = new RequiredFieldValidator();
      validator.setMessage("Please enter the Password");
      passwordField.getValidators().add(validator);
      passwordField.focusedProperty().addListener((o,oldVal,newVal)->{
         if(!newVal) passwordField.validate();
      });
      pane.getChildren().add(passwordField);
      
      String ID = IDField.getText();
      String password = passwordField.getText();
      
      JFXButton login = new JFXButton("Login");
      //login.setOnMouseClicked((e)->{
       //     if(ID.equals("")){
       //       validator.setMessage("Pleas")
       //     }
       // });
      
      JFXButton signup = new JFXButton("Sign up");
      //signup.setOnMouseClicked(e -> stage.setScene(signupPage));
         
      HBox button = new HBox();
      button.getChildren().addAll(login, signup);
      pane.getChildren().add(button);
   
         
       final Scene scene = new Scene(pane, 400, 400, Color.WHITE);
      scene.getStylesheets().add(LoginPage.class.getResource("jfoenix-components.css").toExternalForm());
      stage.setTitle(" JESUS ");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();
      }
      
   public static void main(String[] args) { launch(args); }
   
}