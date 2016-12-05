package NTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JESUS_Client extends Application {

    Stage window;
    Scene loginPage, signupPage, playPage;
    BufferedReader in;
    PrintWriter out;
   
    

private void run (String[] args) throws IOException {
      
      //Socket socket = new Socket("127.0.0.1", 1945);
      
      System.out.println("런 부분");
      //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      //out = new PrintWriter(socket.getOutputStream(), true);
       
       launch(args);
      //127.0.0.1   
   }
   
    public static void main(String[] args) throws Exception {
    	JESUS_Client client = new JESUS_Client();
       System.out.println("메인 부분");
       
      client.run(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	HashMap<String, Integer> q = new HashMap<String, Integer>();
    	window = primaryStage;
        window.setTitle("JESUS");
        
        Socket socket = new Socket("127.0.0.1", 1945);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        
        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //Name Label - constrains use (child, column, row)
        Label nameLabel = new Label("ID:");
        GridPane.setConstraints(nameLabel, 0, 0);

        //Name Input
        TextField nameInput = new TextField("ID");
        GridPane.setConstraints(nameInput, 1, 0);
 
        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);
        
        //Button box
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        GridPane.setConstraints(hbox, 1, 2);
              
        //Login
      
        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
        		String inMsg = null;
        		out.println("LOGIN " + nameInput.getText().toString() + " " + passInput.getText().toString());
        		try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		int state = Integer.parseInt(inMsg);
        		if (state >= -1) {
        			window.setScene(loginPage);
        			if (state != -1) {
        				System.out.println("STATE");
        			}
        		}
        		else if (state == -2) {
        			System.out.println("Wrong ID input");
        		}
        		else if (state == -3) {
        			System.out.println("Wrong Password input");
        		}
        		else {
        			System.out.println("Alreday Log-Ined ID");
        		}
        } );
        //Sign up        
        Button signupButton = new Button("Sign Up");
        signupButton.setOnAction(e -> window.setScene(signupPage));
        
        //Add all button to box
        hbox.getChildren().addAll(loginButton, signupButton);
             

        //Add everything to grid
        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, hbox);

                
        //Login Page
        CheckBox box1 = new CheckBox("Level 1");
        CheckBox box2 = new CheckBox("Level 2");
        CheckBox box3 = new CheckBox("Level 3");
        box1.setSelected(true);
        
        Button button0 = new Button("Go!");
        button0.setOnAction(e -> {
        	String inMsg = null;
    		out.println("LEVELCHOICE " + 1);
    		try {
				inMsg = in.readLine();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
    		while (!inMsg.equals("END")) {
    			System.out.println(inMsg);
    			String[] split = inMsg.split(" ");
    			q.put(split[1], Integer.parseInt(split[0]));
    			System.out.println("Store");
    			try {
					inMsg = in.readLine();
					System.out.println("Read In");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
    		}
    		System.out.println("Loop End");
    		window.setScene(signupPage);
        } );
        
        Button button1 = new Button("Back!");
        button1.setOnAction(e -> window.setScene(signupPage));
        
        VBox layout= new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(box1, box2, box3 ,button0, button1);
        
        loginPage = new Scene(layout, 300, 300);
        
        
        //Signup page        
        Label idLabel = new Label("Type in ID:");       
        TextField id = new TextField();
        id.setPrefWidth(20);

        Label pwLabel = new Label("Type in PW:");
        PasswordField confirmpw = new PasswordField();
        confirmpw.setPrefWidth(20);
        
        Label confirmpwLabel = new Label("Confirm the PW:");
        PasswordField pw = new PasswordField();
        pw.setPrefWidth(20);
        
        Button button2 = new Button("SUBMIT");
        button2.setOnAction(e -> {
        	String inMsg = null;
        	if(pw.getText().equals(confirmpw.getText())) {
        		out.println("SIGNUP " + id.getText().toString() + " " + confirmpw.getText().toString());
        		try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		if (inMsg.startsWith("SUCCESS")) {
        			window.setScene(loginPage);
        		}
        		else if (inMsg.startsWith("ALREADY")) {
        			System.out.println("ID Already Exist");
        		}
        	}
        	else {
        		System.out.println("Not Matching PW and ConfirmPW");
        	}
        } );
        
        VBox hbox2 = new VBox(10);
        hbox2.setPadding(new Insets(20, 20, 20, 20));
        hbox2.getChildren().addAll(idLabel,id,pwLabel,pw,confirmpwLabel,confirmpw);
        hbox2.getChildren().addAll(button2);
        
        signupPage = new Scene(hbox2,300, 300);
        
        
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.show();       
        
    }
}