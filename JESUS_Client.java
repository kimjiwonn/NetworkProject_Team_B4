package NTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXPopup.PopupHPosition;
import com.jfoenix.controls.JFXPopup.PopupVPosition;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXRippler.RipplerMask;
import com.jfoenix.controls.JFXRippler.RipplerPos;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class JESUS_Client extends Application {

	Stage window;
	Scene loginPage, signupPage, levelPage, playPage;
	BufferedReader in;
	PrintWriter out;
	BorderPane root;
	public int state, count;
	public String userID, instruction;
	private VBox pane, pane2;
	private static final int PORT = 1945;
	private Text publicText, publicText2;

	// public String countB (int a){
	// String b = null;
	// if(a==0 || a==6 || a==12){
	// b="-fx-background-color: #d0d0d0;\n" + "-fx-background-insets:
	// 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n" +"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// if(a==1 || a==7 || a==13){
	// b="-fx-background-color: yellow;\n" + "-fx-background-insets: 0,1,2,3;\n"
	// + "-fx-border-radius: 20px;\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n"+"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// if(a==2 || a==8 || a==14){
	// b="-fx-background-color: pink;\n" + "-fx-background-insets: 0,1,2,3;\n" +
	// "-fx-border-radius: 20px;\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n"+"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// if(a==3 || a==9 || a==15){
	// b="-fx-background-color: #3ae7eb;\n" + "-fx-background-insets:
	// 0,1,2,3;\n" + "-fx-border-radius: 20px\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n"+"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// if(a==4 || a==10 || a==16){
	// b="-fx-background-color: #dc4f3e;\n" + "-fx-background-insets:
	// 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n"+"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// if(a==5 || a==11 || a==17){
	// b="-fx-background-color: #0adbe1;\n" + "-fx-background-insets:
	// 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
	// + "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" +
	// "-fx-font-size: 18px;\n"+"-fx-border-color:WHITE;\n"
	// + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n" +
	// "-fx-pref-height:50px;\n";
	// }
	// return b;
	// }
	public String countB(int a) {
		String b = null;
		/* Base ins : #d0d0d0, If ins : yellow, Loop ins : pink */
		if (a == 1) {
			b = "-fx-background-color: #d0d0d0;\n"+ "-fx-background-insets: 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
					+ "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" + "-fx-font-size: 18px;\n"
					+ "-fx-border-color:WHITE;\n" + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n"
					+ "-fx-pref-height:50px;\n";
		} else if (a == 2) {
			b = "-fx-background-color: yellow;\n" + "-fx-background-insets: 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
					+ "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" + "-fx-font-size: 18px;\n"
					+ "-fx-border-color:WHITE;\n" + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n"
					+ "-fx-pref-height:50px;\n";
		} else if (a == 3) {
			b = "-fx-background-color: pink;\n" + "-fx-background-insets: 0,1,2,3;\n" + "-fx-border-radius: 20px;\n"
					+ "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: black;\n" + "-fx-font-size: 18px;\n"
					+ "-fx-border-color:WHITE;\n" + "-fx-font-weight: bold;\n" + "-fx-background-radius:50px;\n"
					+ "-fx-pref-height:50px;\n";
		}

		return b;
	}

	public String selectImage(int a) {
		String b = null;
		if (a == 1) {
			b = "/NTP/signup.png";
		}
		if (a == 2) {
			b = "/NTP/autodoor.png";
		}
		if (a == 3) {
			b = "/NTP/lock.png";
		}
		if (a == 4) {
			b = "/NTP/lock2.png";
		}
		if (a == 5) {
			b = "/NTP/lock2.png";
		}
		if (a == 6) {
			b = "/NTP/lock2.png";
		}
		return b;
	}

	public String selectIdImg(int a) {
		String b = null;
		if (a == 1) {
			b = "/NTP/id2.png";
		}

		if (a == 2) {
			b = "/NTP/id2.png";
		}

		if (a == 3) {
			b = "/NTP/id.png";
		}

		if (a == 4) {
			b = "/NTP/id.png";
		}

		if (a == 5) {
			b = "/NTP/id3.png";
		}

		if (a == 6) {
			b = "/NTP/id3.png";
		}
		return b;
	}

	private void run(String[] args) throws IOException {

		// Socket socket = new Socket("127.0.0.1", 1945);

		System.out.println("런 부분");
		// in = new BufferedReader(new
		// InputStreamReader(socket.getInputStream()));
		// out = new PrintWriter(socket.getOutputStream(), true);

		launch(args);
		// 127.0.0.1
	}

	public static void main(String[] args) throws Exception {
		JESUS_Client client = new JESUS_Client();
		System.out.println("메인 부분");

		client.run(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		  Random random = new Random();
          int red = random.nextInt(255);
          int green = random.nextInt(255);
          int blue = random.nextInt(255); 

          
		HashMap<String, Integer> q = new HashMap<String, Integer>();
		HashMap<String, Integer> inst = new HashMap<String, Integer>();
		ArrayList<Integer> submitList = new ArrayList<Integer>();

		window = primaryStage;

		GridPane gp = new GridPane();
		HBox vbox1 = new HBox();
		VBox vbox1_1 = new VBox();
		VBox vbox1_2 = new VBox();
		VBox vbox2 = new VBox();
		VBox vbox5 = new VBox();
		VBox vbox4 = new VBox();
		VBox vbox3 = new VBox();
		Button submit = new Button();
		
		String question;
		int[] i_No;
		window.setTitle("JESUS");
		String buttonColor[] = { "-fx-background-color: blue;", "-fx-background-color: red;",
				"-fx-background-color: yellow;", "-fx-background-color: white;", "-fx-background-color: pink;" };

		String buttonLayout = "-fx-background-insets: 0,1,2,3;\n" + "-fx-background-radius: 3,2,2,2;\n"
				+ "-fx-padding: 15 30 15 30;\n" + "-fx-text-fill: white;\n" + "-fx-font-size: 18px;\n"
				+ "-fx-font-weight: bold;\n";

		Socket socket = new Socket("127.0.0.1", PORT);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		// GridPane with 10px padding around edge
		pane = new VBox();
		pane.setSpacing(30);
		pane.setStyle("-fx-background-color:WHITE;-fx-padding:40;");

		Label title = new Label("Welcome to JESUS");
		title.setTextFill(Color.YELLOW);
		title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 46));
		Label empty = new Label("");
		Label empty1 = new Label("");
		Label empty2 = new Label("");
		pane.getChildren().addAll(title, empty, empty1, empty2);

		JFXTextField IDField = new JFXTextField();
		IDField.setLabelFloat(true);
		IDField.setPromptText("ID");
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");
		IDField.getValidators().add(validator);
		IDField.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				IDField.validate();
		});
		pane.getChildren().add(IDField);

		JFXPasswordField passwordField = new JFXPasswordField();
		passwordField.setStyle("-fx-label-float:true;");
		passwordField.setPromptText("Password");
		validator = new RequiredFieldValidator();
		validator.setMessage("Please enter the Password");
		passwordField.getValidators().add(validator);
		passwordField.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				passwordField.validate();
		});
		pane.getChildren().add(passwordField);

		JFXButton signupButton = new JFXButton("Sign up");
		signupButton.setStyle("-fx-text-fill: orange;\n");
		signupButton.setOnMouseClicked(e -> window.setScene(signupPage));
		JFXButton loginButton = new JFXButton("Login");
		loginButton.setStyle("-fx-text-fill: green;\n");
		loginButton.setOnMouseClicked((e) -> {
			if (IDField.getText().isEmpty() || passwordField.getText().isEmpty()) {
				System.out.println("입력 값이 없음");
			} else {
				out.println("LOGIN " + IDField.getText().toString() + " " + passwordField.getText().toString());
				userID = IDField.getText().toString();
				String inMsg = null;
				try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				state = Integer.parseInt(inMsg);
				if (state == -1) {
					window.setScene(levelPage);
				} else if (state > -1) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle(" JESUS ");
					alert.setHeaderText(null);
					alert.setContentText("Do you like to continue last game?");

					ButtonType buttonTypeOne = new ButtonType("Yes");
					ButtonType buttonTypeTwo = new ButtonType("No");

					alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonTypeOne) {
						ArrayList<String> instBox = new ArrayList<String>();
						submitList.add(state);
						Image image = new Image(selectImage(state));
						ImageView iv1 = new ImageView();
						iv1.setImage(image);
						iv1.setFitHeight(100);
						iv1.setFitWidth(100);
						vbox4.getChildren().add(iv1);
						String stMsg = null;
						try {
							in.reset();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						out.println("LEVELCHOICE " + state);

						Image id = new Image(selectIdImg(state));
						ImageView idv = new ImageView();
						idv.setImage(id);
						idv.setFitHeight(80);
						idv.setFitWidth(100);
						vbox1_1.getChildren().add(idv);

						Label text1 = new Label("ID: " + userID);
						Label text2 = new Label("Q Number: " + state);
						Text text0 = new Text();
						publicText = text0;
						publicText2 = text0;
						text0.setStyle("-fx-font-size: 0.1px;\n");
						text1.setTextFill(Color.BLACK);
						text1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
						text2.setTextFill(Color.BLACK);
						text2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
						vbox1_2.getChildren().addAll(text0, text1, text2);

						try {
							stMsg = in.readLine();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						String que = stMsg.substring(1, stMsg.length());

						Label task = new Label("QUESTION: " + que);
						task.setTextFill(Color.BLACK);
						task.setFont(Font.font("Arial", FontWeight.BOLD, 30));
						vbox4.getChildren().add(task);
						try {
							in.reset();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						out.println("QCHOICE " + state);
						// System.out.println(lev1.getText());
						String iMsg = null;
						try {
							iMsg = in.readLine();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						while (!iMsg.equals("END")) {
							String iContent, iNO;
							iNO = iMsg.substring(0, iMsg.indexOf(" "));
							iContent = iMsg.substring(iMsg.indexOf(" ") + 1);
							inst.put(iContent, Integer.parseInt(iNO));
							instBox.add(iContent);
							try {
								iMsg = in.readLine();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						count = 0;
						for (String l : inst.keySet()) {
							System.out.println(inst.get(l) + " " + l);
							count++;
							instruction = l;
						}
						System.out.println(inst.size());
						window.setScene(playPage);
						window.setFullScreen(true);
						publicText.setText(inst.size() + "");
						publicText2.setText(userID);

						ScrollPane sp = new ScrollPane();
						VBox box = new VBox();
						box.setPrefHeight(300);
						box.setPrefWidth(800);
						box.setStyle("-fx-background-color: #FFA500;\n" + "-fx-background-insets: 0,1,2,3;\n"
								+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n"
								+ "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

											
						for (int i = 0; i < instBox.size(); ++i) {
							String current = instBox.get(i);
							JFXButton ins = new JFXButton(String.valueOf(current));
							ins.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
							ins.setStyle(countB(i));
							box.getChildren().add(ins);
							ins.setOnAction(d -> {
								String Msg = null;

								submitList.add(inst.get(ins.getText()));
								out.println("FEEDBACK " + inst.get(ins.getText()));
								try {
									Msg = in.readLine();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								while (!Msg.equals("END")) {
									if (Msg.contains("FEEDBACK")) {
										String msg = Msg.substring(Msg.indexOf(" ") + 1);
										Label neg = new Label(msg);
										String family1 = "Helvetica";
										double size1 = 25;
										neg.setTextFill(Color.GREEN);
										neg.setFont(Font.font(family1, FontWeight.BOLD, size1));
										vbox3.getChildren().add(neg);
										vbox3.setStyle(
												"-fx-background-color: red;" + "-fx-background-insets: 0,1,2,3;\n"
														+ "-fx-background-radius: 3,2,2,2;\n"
														+ "-fx-border-color: orange;\n" + "-fx-border-insets: 5;\n"
														+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");
									} else if (Msg.contains("PRAISE")) {
										String msg = Msg.substring(Msg.indexOf(" ") + 1);
										System.out.println(msg);
										Label pos = new Label(msg);
										String family2 = "Helvetica";
										double size2 = 25;
										pos.setTextFill(Color.YELLOW);
										pos.setFont(Font.font(family2, FontWeight.BOLD, size2));
										vbox3.getChildren().add(pos);
										vbox3.setStyle(
												"-fx-background-color: blue;" + "-fx-background-insets: 0,1,2,3;\n"
														+ "-fx-background-radius: 3,2,2,2;\n"
														+ "-fx-border-color: black;\n" + "-fx-border-insets: 5;\n"
														+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");
									}
									try {
										Msg = in.readLine();
										System.out.println(Msg);
									} catch (Exception e1) {
										e1.printStackTrace();
									}
								}
								JFXButton ans = new JFXButton(String.valueOf(current));
								ins.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
								ans.setStyle(buttonLayout);

								vbox5.getChildren().add(ans);
								vbox5.setAlignment(Pos.CENTER_LEFT);
								// if(inst.keySet())
								ans.setOnAction(r -> {
									submitList.remove(inst.get(ans.getText()));
									vbox5.getChildren().remove(ans);
								});
							});
						}

						sp.setContent(box);
						sp.setPrefHeight(300);
						sp.setPrefWidth(800);
						vbox2.getChildren().add(sp);
					} else {
						window.setScene(levelPage);
					}
				}

				else if (state == -2) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(" JESUS ");
					alert.setHeaderText(null);
					alert.setContentText("ID doesn't exist");
					ButtonType buttonTypeOk = new ButtonType("Ok");
					ButtonType buttonTypeSignup = new ButtonType("Sign Up");
					alert.getButtonTypes().setAll(buttonTypeOk, buttonTypeSignup);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonTypeOk) {
						alert.close();
					} else {
						window.setScene(signupPage);
					}
				} else if (state == -3) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(" JESUS ");
					alert.setHeaderText(null);
					alert.setContentText("Inaccurate Password");
					ButtonType buttonTypeOk = new ButtonType("Ok");
					alert.getButtonTypes().setAll(buttonTypeOk);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonTypeOk) {
						alert.close();
					}
				} else {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(" JESUS ");
					alert.setHeaderText(null);
					alert.setContentText("Account already logged in");
					ButtonType buttonTypeOk = new ButtonType("Ok");
					alert.getButtonTypes().setAll(buttonTypeOk);
					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == buttonTypeOk) {
						alert.close();
					}
				}
			}
		});

		HBox button = new HBox();
		button.getChildren().addAll(loginButton, signupButton);
		button.setAlignment(Pos.CENTER);
		pane.getChildren().add(button);
		pane.setAlignment(Pos.CENTER);

		// Signup Page
		pane2 = new VBox();
		pane2.setSpacing(30);
		pane2.setStyle("-fx-background-color:WHITE;-fx-padding:40;");

		JFXTextField IDField2 = new JFXTextField();
		IDField2.setLabelFloat(true);
		IDField2.setPromptText("ID");
		RequiredFieldValidator validator2 = new RequiredFieldValidator();
		validator2.setMessage("Input Required");
		IDField2.getValidators().add(validator2);
		IDField2.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				IDField2.validate();
		});
		pane2.getChildren().add(IDField2);

		JFXPasswordField passwordField2 = new JFXPasswordField();
		passwordField2.setStyle("-fx-label-float:true;");
		passwordField2.setPromptText("Password");
		validator2 = new RequiredFieldValidator();
		validator2.setMessage("Password Can't be empty");
		passwordField2.getValidators().add(validator2);
		passwordField2.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				passwordField2.validate();
		});
		pane2.getChildren().add(passwordField2);

		JFXPasswordField confirmField = new JFXPasswordField();
		confirmField.setStyle("-fx-label-float:true;");
		confirmField.setPromptText("Confirm Password");
		validator2 = new RequiredFieldValidator();
		validator2.setMessage("Please enter the password");
		confirmField.getValidators().add(validator2);
		confirmField.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				passwordField2.validate();
		});
		pane2.getChildren().add(confirmField);

		JFXButton submitButton = new JFXButton("Submit");
		submitButton.setOnMouseClicked((e) -> {
			String inMsg = null;
			String identification = IDField2.getText();
			String password = passwordField2.getText();
			String confirm = confirmField.getText();

			if (password.equals(confirm)) {
				out.println("SIGNUP " + identification.toString() + " " + confirm.toString());
				try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (inMsg.startsWith("SUCCESS")) {
					window.setScene(levelPage);
				} else if (inMsg.startsWith("ALREADY")) {
					RequiredFieldValidator confirmIDValidator = new RequiredFieldValidator();
					confirmIDValidator.setMessage("ID Already Exist");
					IDField2.getValidators().add(confirmIDValidator);
				}

			} else {
				RequiredFieldValidator confirmValidator = new RequiredFieldValidator();
				confirmValidator.setMessage("Wrong password!");
				confirmField.getValidators().add(confirmValidator);
			}
		});

		pane2.getChildren().add(submitButton);
		signupPage = new Scene(pane2, 300, 300);

		// Check Level Page
		Label wc = new Label("Select the Level");
		wc.setTextFill(Color.YELLOW);
		wc.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 46));
		Label et = new Label();
		JFXButton show1 = new JFXButton("Level 1");
		show1.setStyle("-fx-text-fill: green;\n");
		JFXButton show2 = new JFXButton("Level 2");
		show2.setStyle("-fx-text-fill: blue;\n");
		JFXButton show3 = new JFXButton("Level 3");
		show3.setStyle("-fx-text-fill: red;\n");

		show1.setPadding(new Insets(10, 10, 10, 10));
		show2.setPadding(new Insets(10, 10, 10, 10));
		show3.setPadding(new Insets(10, 10, 10, 10));

		JFXRippler r1 = new JFXRippler(show1, RipplerMask.CIRCLE, RipplerPos.BACK);
		JFXRippler r2 = new JFXRippler(show2, RipplerMask.CIRCLE, RipplerPos.BACK);
		JFXRippler r3 = new JFXRippler(show3, RipplerMask.CIRCLE, RipplerPos.BACK);

		VBox vbox = new VBox(wc, et, r1, r2, r3);

		JFXPopup popup1 = new JFXPopup();
		JFXListView<String> list1 = new JFXListView<String>();
		JFXPopup popup2 = new JFXPopup();
		JFXListView<String> list2 = new JFXListView<String>();
		JFXPopup popup3 = new JFXPopup();
		JFXListView<String> list3 = new JFXListView<String>();

		AnchorPane container = new AnchorPane();
		container.getChildren().add(vbox);
		AnchorPane.setLeftAnchor(vbox, 200.0);
		AnchorPane.setTopAnchor(vbox, 210.0);

		StackPane main = new StackPane();
		main.getChildren().add(container);

		// level에 해당하는 문제 제목불러오기

		// popup에 버튼나타냄

		// 레벨 선택시 popup창 나타남
		show1.setOnMouseClicked((e) -> {
			String inMsg = null;
			String[] questionName = new String[4];
			try {
				in.reset();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			out.println("LEVELCHOICE " + 1);
			try {
				inMsg = in.readLine();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int i = 0;
			while (!inMsg.equals("END")) {
				String qNO, qContent;
				qNO = inMsg.substring(0, 1);
				qContent = inMsg.substring(2);
				questionName[i ++] = qContent;
				q.put(qContent, Integer.parseInt(qNO));
				try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			ObservableList<String> tasks1 = FXCollections.observableArrayList(questionName[0], questionName[1]);
			list1.setItems(tasks1);

			popup1.setContent(list1);
			popup1.setPopupContainer(main);

			popup1.setSource(r1);
			popup1.show(PopupVPosition.TOP, PopupHPosition.LEFT);

			Label text1 = new Label("ID: " + userID);
			Label text2 = new Label("Q Number: " + 1);
			Text text0 = new Text();
			publicText = text0;
			publicText2 = text0;
			text0.setStyle("-fx-font-size: 0.1px;\n");
			text1.setTextFill(Color.BLACK);
			text1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			text2.setTextFill(Color.BLACK);
			text2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			vbox1_2.getChildren().addAll(text0, text1, text2);

		});

		show2.setOnMouseClicked((e) -> {
			String inMsg = null;
			String[] questionName = new String[4];
			try {
				in.reset();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			out.println("LEVELCHOICE " + 2);
			try {
				inMsg = in.readLine();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int i = 0;
			while (!inMsg.equals("END")) {
				String qNO, qContent;
				qNO = inMsg.substring(0, 1);
				qContent = inMsg.substring(2);
				questionName[i ++] = qContent;
				q.put(qContent, Integer.parseInt(qNO));
				try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			ObservableList<String> tasks2 = FXCollections.observableArrayList(questionName[0], questionName[1]);
			list2.setItems(tasks2);

			popup2.setContent(list2);
			popup2.setPopupContainer(main);

			popup2.setSource(r2);
			popup2.show(PopupVPosition.TOP, PopupHPosition.LEFT);

			Label text1 = new Label("ID: " + userID);
			Label text2 = new Label("Q Number: " + 2);
			Text text0 = new Text();
			publicText = text0;
			publicText2 = text0;
			text0.setStyle("-fx-font-size: 0.1px;\n");
			text1.setTextFill(Color.BLACK);
			text1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			text2.setTextFill(Color.BLACK);
			text2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			vbox1_2.getChildren().addAll(text0, text1, text2);
		});

		show3.setOnMouseClicked((e) -> {
			String inMsg = null;
			String[] questionName = new String[4];
			try {
				in.reset();
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			out.println("LEVELCHOICE " + 3);
			try {
				inMsg = in.readLine();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			int i = 0;
			while (!inMsg.equals("END")) {
				String qNO, qContent;
				qNO = inMsg.substring(0, 1);
				qContent = inMsg.substring(2);
				questionName[i ++] = qContent;
				q.put(qContent, Integer.parseInt(qNO));
				try {
					inMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			ObservableList<String> tasks3 = FXCollections.observableArrayList(questionName[0], questionName[1]);
			list3.setItems(tasks3);

			popup3.setContent(list3);
			popup3.setPopupContainer(main);

			popup3.setSource(r3);
			popup3.show(PopupVPosition.TOP, PopupHPosition.LEFT);

			Label text1 = new Label("ID: " + userID);
			Label text2 = new Label("Q Number: " + 3);
			Text text0 = new Text();
			publicText = text0;
			publicText2 = text0;
			text0.setStyle("-fx-font-size: 0.1px;\n");
			text1.setTextFill(Color.BLACK);
			text1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			text2.setTextFill(Color.BLACK);
			text2.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
			vbox1_2.getChildren().addAll(text0, text1, text2);

		});

		list1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
				ArrayList<String> instBox = new ArrayList<String>();
				System.out.println("문제번호 " + q.get(newValue));
				
				submitList.add(q.get(newValue));
				out.println("QCHOICE " + q.get(newValue));

				Image image = new Image(selectImage(q.get(newValue)));
				ImageView iv1 = new ImageView();
				iv1.setImage(image);
				iv1.setFitHeight(100);
				iv1.setFitWidth(100);
				vbox4.getChildren().add(iv1);

				Image id = new Image(selectIdImg(q.get(newValue)));
				ImageView idv = new ImageView();
				idv.setImage(id);
				idv.setFitHeight(80);
				idv.setFitWidth(100);
				vbox1_1.getChildren().add(idv);

				Label task = new Label("QUESTION: " + newValue);
				task.setTextFill(Color.BLACK);
				task.setFont(Font.font("Arial", FontWeight.BOLD, 30));
				vbox4.getChildren().add(task);

				// System.out.println(lev1.getText());
				String iMsg = null;
				try {
					iMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				while (!iMsg.equals("END")) {
					String iContent, iNO;
					iNO = iMsg.substring(0, iMsg.indexOf(" "));
					iContent = iMsg.substring(iMsg.indexOf(" ") + 1);
					inst.put(iContent, Integer.parseInt(iNO));
					instBox.add(iContent);
					try {
						iMsg = in.readLine();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				count = 0;
				for (String l : inst.keySet()) {
					System.out.println(inst.get(l) + " " + l);
					count++;
					instruction = l;
				}
				System.out.println(inst.size());
				window.setScene(playPage);
				window.setFullScreen(true);
				publicText.setText(inst.size() + "");
				publicText2.setText(userID);

				ScrollPane sp = new ScrollPane();
				VBox box = new VBox();
				box.setPrefHeight(600);
				box.setPrefWidth(800);
				box.setStyle("-fx-background-color: #FFA500;\n" + "-fx-background-insets: 0,1,2,3;\n"
						+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
						+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

				
				for (int i = 0; i < instBox.size(); ++i) {
					int key = 1;
					String current = instBox.get(i);
					JFXButton ins = new JFXButton(String.valueOf(current));
					ins.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					
		
					if (current.contains("만약")) {
						key = 2;
					}
					else if (current.contains("반복")) {
						key = 3;
					}

					System.out.println(buttonLayout);
					ins.setStyle(countB(key));
					box.getChildren().add(ins);
					ins.setOnAction(d -> {
						String inMsg = null;
						int k = 1;
						submitList.add(inst.get(ins.getText()));
						out.println("FEEDBACK " + inst.get(ins.getText()));
						try {
							inMsg = in.readLine();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						while (!inMsg.equals("END")) {
							if (inMsg.contains("FEEDBACK")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								Label neg = new Label(msg);
								String family1 = "Helvetica";
								double size1 = 25;
								neg.setTextFill(Color.GREEN);
								neg.setFont(Font.font(family1, FontWeight.BOLD, size1));
								vbox3.getChildren().add(neg);
								vbox3.setStyle("-fx-background-color: red;" + "-fx-background-insets: 0,1,2,3;\n"
										+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: orange;\n"
										+ "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
										+ "-fx-border-style: solid;\n");
							} else if (inMsg.contains("PRAISE")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								System.out.println(msg);
								Label pos = new Label(msg);
								String family2 = "Helvetica";
								double size2 = 25;
								pos.setTextFill(Color.YELLOW);
								pos.setFont(Font.font(family2, FontWeight.BOLD, size2));
								vbox3.getChildren().add(pos);
								vbox3.setStyle("-fx-background-color: blue;" + "-fx-background-insets: 0,1,2,3;\n"
										+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: black;\n"
										+ "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
										+ "-fx-border-style: solid;\n");
							}
							try {
								inMsg = in.readLine();
								System.out.println(inMsg);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						if (current.contains("만약")) {
							k = 2;
						}
						else if (current.contains("반복")) {
							k = 3;
						}
						JFXButton ans = new JFXButton(String.valueOf(current));
						ans.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
						ans.setStyle(buttonLayout);
						ans.setStyle(countB(k));
						vbox5.getChildren().add(ans);
						vbox5.setAlignment(Pos.CENTER_LEFT);
						// if(inst.keySet())
						ans.setOnAction(r -> {
							submitList.remove(inst.get(ans.getText()));
							vbox5.getChildren().remove(ans);
						});
					});
				}
				sp.setContent(box);
				sp.setPrefHeight(600);
				sp.setPrefWidth(800);
				vbox2.getChildren().add(sp);
			}

		});

		list2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
				ArrayList<String> instBox = new ArrayList<String>();
				System.out.println("문제번호 " + q.get(newValue));
				submitList.add(q.get(newValue));
				Image image = new Image(selectImage(q.get(newValue)));
				ImageView iv1 = new ImageView();
				iv1.setImage(image);
				iv1.setFitHeight(100);
				iv1.setFitWidth(100);
				vbox4.getChildren().add(iv1);
				out.println("QCHOICE " + q.get(newValue));

				Image id = new Image(selectIdImg(q.get(newValue)));
				ImageView idv = new ImageView();
				idv.setImage(id);
				idv.setFitHeight(80);
				idv.setFitWidth(100);
				vbox1_1.getChildren().add(idv);

				Label task = new Label("QUESTION: " + newValue);
				task.setTextFill(Color.BLACK);
				task.setFont(Font.font("Arial", FontWeight.BOLD, 30));
				vbox4.getChildren().add(task);
				// System.out.println(lev1.getText());
				String iMsg = null;
				try {
					iMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				while (!iMsg.equals("END")) {
					String iContent, iNO;
					iNO = iMsg.substring(0, iMsg.indexOf(" "));
					iContent = iMsg.substring(iMsg.indexOf(" ") + 1);
					inst.put(iContent, Integer.parseInt(iNO));
					instBox.add(iContent);
					try {
						iMsg = in.readLine();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				count = 0;
				for (String l : inst.keySet()) {
					System.out.println(inst.get(l) + " " + l);
					count++;
					instruction = l;
				}
				System.out.println(inst.size());
				window.setScene(playPage);
				window.setFullScreen(true);
				publicText.setText(inst.size() + "");
				publicText2.setText(userID);
				
				//SCROLL

				ScrollPane sp = new ScrollPane();
				VBox box = new VBox();
				box.setPrefHeight(600);
				box.setPrefWidth(600);
				box.setStyle("-fx-background-color: #FFA500;\n" + "-fx-background-insets: 0,1,2,3;\n"
						+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
						+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");
				//SCROLL BOX
				//ScrollPane sp2 = new ScrollPane();
				//sp2.setPrefWidth(800);
				//sp2.setPrefHeight(400); 
				
				//VBox box2 =new VBox();
				//box2.setPrefWidth(800);
				//box2.setPrefHeight(400);
				for (int i = 0; i < instBox.size(); ++i) {
					int key = 1;
					String current = instBox.get(i);
					if (current.contains("만약")) {
						key = 2;
					}
					else if (current.contains("반복")) {
						key = 3;
					}
					JFXButton ins = new JFXButton(String.valueOf(current));
					ins.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					System.out.println(buttonLayout);
					ins.setStyle(countB(key));
					box.getChildren().add(ins);
					ins.setOnAction(d -> {
						String inMsg = null;
						int k = 1;
						submitList.add(inst.get(ins.getText()));
						out.println("FEEDBACK " + inst.get(ins.getText()));
						try {
							inMsg = in.readLine();
							System.out.println(inMsg);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						while (!inMsg.equals("END")) {
							if (inMsg.contains("FEEDBACK")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								Label neg = new Label(msg);
								String family1 = "Helvetica";
								double size1 = 25;
								neg.setTextFill(Color.GREEN);
								neg.setFont(Font.font(family1, FontWeight.BOLD, size1));
								box.getChildren().add(neg);
								vbox3.getChildren().add(neg);
								vbox3.setStyle("-fx-background-color: red;" + "-fx-background-insets: 0,1,2,3;\n"
										+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: orange;\n"
										+ "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
										+ "-fx-border-style: solid;\n");
								
								} else if (inMsg.contains("PRAISE")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								System.out.println(msg);
								Label pos = new Label(msg);
								String family2 = "Helvetica";
								double size2 = 25;
								pos.setTextFill(Color.YELLOW);
								pos.setFont(Font.font(family2, FontWeight.BOLD, size2));
								vbox3.getChildren().add(pos);
								vbox3.setStyle("-fx-background-color: blue;" + "-fx-background-insets: 0,1,2,3;\n"
										+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: black;\n"
										+ "-fx-border-insets: 5;\n" + "-fx-border-width: 3;\n"
										+ "-fx-border-style: solid;\n");
							}
							try {
								inMsg = in.readLine();
								System.out.println(inMsg);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						
						if (current.contains("만약")) {
							k = 2;
						}
						else if (current.contains("반복")) {
							k = 3;
						}
						JFXButton ans = new JFXButton(String.valueOf(current));
						ans.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
						ans.setStyle(buttonLayout);
						ans.setStyle(countB(k));

						vbox5.getChildren().add(ans);
						vbox5.setAlignment(Pos.CENTER_LEFT);
						// if(inst.keySet())
						ans.setOnAction(r -> {
							submitList.remove(inst.get(ans.getText()));
							vbox5.getChildren().remove(ans);
						});
					});
				}
				//sp2.setContent(box2);
				//sp2.setStyle("-fx-background-color: red;");
				sp.setContent(box);
				sp.setPrefHeight(600);
				sp.setPrefWidth(600);
				vbox2.getChildren().add(sp);
			}
		});

		list3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
				ArrayList<String> instBox = new ArrayList<String>();
				System.out.println("문제번호 " + q.get(newValue));
				submitList.add(q.get(newValue));
				out.println("QCHOICE " + q.get(newValue));

				Image image = new Image(selectImage(q.get(newValue)));
				ImageView iv1 = new ImageView();
				iv1.setImage(image);
				iv1.setFitHeight(100);
				iv1.setFitWidth(100);
				vbox4.getChildren().add(iv1);

				Image id = new Image(selectIdImg(q.get(newValue)));
				ImageView idv = new ImageView();
				idv.setImage(id);
				idv.setFitHeight(80);
				idv.setFitWidth(100);
				vbox1_1.getChildren().add(idv);

				Label task = new Label("QUESTION: " + newValue);
				task.setTextFill(Color.BLACK);
				task.setFont(Font.font(null, FontWeight.BOLD, 20));
				vbox4.getChildren().add(task);
				// System.out.println(lev1.getText());
				String iMsg = null;
				try {
					iMsg = in.readLine();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				while (!iMsg.equals("END")) {
					String iContent, iNO;
					iNO = iMsg.substring(0, iMsg.indexOf(" "));
					iContent = iMsg.substring(iMsg.indexOf(" ") + 1);
					inst.put(iContent, Integer.parseInt(iNO));
					instBox.add(iContent);
					try {
						iMsg = in.readLine();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				count = 0;
				for (String l : inst.keySet()) {
					System.out.println(inst.get(l) + " " + l);
					count++;
					instruction = l;
				}
				
				System.out.println(inst.size());
				window.setScene(playPage);
				window.setFullScreen(true);
				publicText.setText(inst.size() + "");
				publicText2.setText(userID);

				ScrollPane sp = new ScrollPane();
				VBox box = new VBox();
				box.setPrefHeight(600);
				box.setPrefWidth(600);

				
     			for (int i = 0; i < instBox.size(); ++i) {
					int key = 1;
					String current = instBox.get(i);
					if (current.contains("만약")) {
						key = 2;
					}
					else if (current.contains("반복")) {
						key = 3;
					}

					JFXButton ins = new JFXButton(String.valueOf(current));
					ins.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
					System.out.println(buttonLayout);
					ins.setStyle(countB(key));
					box.getChildren().add(ins);
					ins.setOnAction(d -> {
						String inMsg = null;
						int k = 1;
						submitList.add(inst.get(ins.getText()));
						out.println("FEEDBACK " + inst.get(ins.getText()));
						try {
							inMsg = in.readLine();
							System.out.println(inMsg);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						while (!inMsg.equals("END")) {
							if (inMsg.contains("FEEDBACK")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								Label neg = new Label(msg);
								String family1 = "Helvetica";
								double size1 = 25;
								neg.setTextFill(Color.GREEN);
								neg.setFont(Font.font(family1, FontWeight.BOLD, size1));
								vbox3.getChildren().add(neg);
																							} else if (inMsg.contains("PRAISE")) {
								String msg = inMsg.substring(inMsg.indexOf(" ") + 1);
								System.out.println(msg);
								Label pos = new Label(msg);
								String family2 = "Helvetica";
								double size2 = 25;
								pos.setTextFill(Color.YELLOW);
								pos.setFont(Font.font(family2, FontWeight.BOLD, size2));
								vbox3.getChildren().add(pos);
								
																							}
							try {
								inMsg = in.readLine();
								System.out.println(inMsg);
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
						if (current.contains("만약")) {
							k = 2;
						}
						else if (current.contains("반복")) {
							k = 3;
						}
						JFXButton ans = new JFXButton(String.valueOf(current));
						ans.setRipplerFill(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
						ans.setStyle(buttonLayout);
						ans.setStyle(countB(k));
						vbox5.getChildren().add(ans);
						vbox5.setAlignment(Pos.CENTER_LEFT);
						// if(inst.keySet())
						ans.setOnAction(r -> {
							submitList.remove(inst.get(ans.getText()));
							vbox5.getChildren().remove(ans);
						});
					});
				}
				sp.setContent(box);
				sp.setPrefHeight(600);
				sp.setPrefWidth(600);
				vbox2.getChildren().add(sp);
			}
		});

		levelPage = new Scene(main, 750, 700);
		levelPage.getStylesheets().add(JESUS_Client.class.getResource("jfoenix-components.css").toExternalForm());
		// levelPage.getStylesheets().add(LoginPage.class.getResource("jfoenix-components.css").toExternalForm());

		// playPage

		vbox1.setPrefHeight(100);
		vbox1.setPrefWidth(800);
		vbox1.setPadding(new Insets(50));
		vbox1.setStyle("-fx-background-color: #f4f68b;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

		vbox1_1.setPrefHeight(100);
		vbox1_1.setPrefWidth(200);
		vbox1_1.setPadding(new Insets(50));
		vbox1_1.setStyle("-fx-background-color: #f4f68b;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n");
		vbox1_1.setAlignment(Pos.CENTER_RIGHT);
		vbox1_2.setPrefHeight(100);
		vbox1_2.setPrefWidth(600);
		vbox1_2.setPadding(new Insets(50));
		vbox1_2.setStyle("-fx-background-color: #f4f68b;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n");

		vbox1.getChildren().addAll(vbox1_1, vbox1_2);
		int count = inst.keySet().size();
		System.out.println(count);

		// text.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));

		// VBox vbox2 = new VBox();
		vbox2.setPrefHeight(400);
		vbox2.setPrefWidth(600);

		vbox2.setStyle("-fx-background-color: #FFA500;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

		vbox3.setPrefWidth(800);
		vbox3.setPrefHeight(300);
		
		
		vbox3.setStyle("-fx-background-color: #808080;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

		VBox rightBox = new VBox();
		vbox4.setPrefWidth(900);
		vbox4.setPrefHeight(150);
		vbox4.setPadding(new Insets(20, 50, 20, 50));
		vbox4.setAlignment(Pos.CENTER);
		vbox4.setStyle("-fx-background-color: #2397ed;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n");

		// VBox vbox5 = new VBox();
		vbox5.setPrefWidth(900);
		vbox5.setPrefHeight(600);
		vbox5.setPadding(new Insets(20, 50, 20, 50));
		vbox5.setAlignment(Pos.CENTER);
		vbox5.setStyle("-fx-background-color: #5bdc5b;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n" + "fx-border-radius: 20px;\n");
		submit = new Button("SUBMIT");
		submit.setStyle("-fx-background-radius: 5em;\n" + "-fx-min-width: 120px;\n" + "-fx-min-height: 120px;\n"
				+ "-fx-max-width: 120px;\n" + "-fx-max-height: 120px;\n" + "-fx-background-color: #7A2458;\n"
				+ "-fx-text-fill: white;\n" + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
		submit.setAlignment(Pos.CENTER);
		submit.setOnAction(sB -> {
			String answer = "";
			String inMsg = null;
			answer = answer.concat("" + submitList.get(0));
			for (int i = 1; i < submitList.size(); ++i) {
				answer = answer.concat(" " + submitList.get(i));
			}
			System.out.println(answer);
			out.println("CHECKANS " + answer);
			try {
				inMsg = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.out.println(inMsg);
			if (inMsg.equals("CORRECT")) {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle(" JESUS ");
				alert.setHeaderText("CONGRATULATION!!\n You have completed the task");
				alert.setContentText("Do you like to select other game?");

				ButtonType buttonTypeOne = new ButtonType("Yes");
				ButtonType buttonTypeTwo = new ButtonType("No");

				alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne) {
					window.setScene(levelPage);
				} else {
					alert.close();
				}
			} else {
				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle(" JESUS ");
				alert.setHeaderText(null);
				alert.setContentText("Oops... incorrect answer");

				ButtonType buttonTypeOne = new ButtonType("Continue");

				alert.getButtonTypes().setAll(buttonTypeOne);

				Optional<ButtonType> result = alert.showAndWait();
				if (result.get() == buttonTypeOne) {
					alert.close();
				}
			}
		});
		VBox sub = new VBox();
		sub.setStyle("-fx-background-color: #5bdc5b;\n" + "-fx-background-insets: 0,1,2,3;\n"
				+ "-fx-background-radius: 3,2,2,2;\n" + "-fx-border-color: white;\n" + "-fx-border-insets: 5;\n"
				+ "-fx-border-width: 3;\n" + "-fx-border-style: solid;\n" + "fx-border-radius: 20px;\n"
				+ "-fx-border-left-style: hidden;\n");
		sub.getChildren().add(submit);
		sub.setAlignment(Pos.CENTER);

		// leftBox.getChildren().addAll(vbox1, vbox2, vbox3, new
		// Separator(Orientation.VERTICAL));
		// playBox.getChildren().addAll(leftBox, rightBox, new
		// Separator(Orientation.HORIZONTAL));
		// rightBox.getChildren().addAll(vbox4, vbox5, new
		// Separator(Orientation.VERTICAL));

		// System.out.println("버튼 생성" + count);
		// for (String l : inst.keySet()) {
		// Button ins = new Button(String.valueOf(l));
		// vbox2.getChildren().add(ins);
		// ins.setOnAction(e -> {
		// Button ans = new Button("ans");
		// vbox5.getChildren().add(ans);
		// ans.setOnAction(t -> vbox5.getChildren().remove(ans));
		// });
		// }

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(35);

		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(55);

		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(10);
		gp.getColumnConstraints().addAll(column1, column2, column3);

		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(20);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(50);
		RowConstraints row3 = new RowConstraints();
		row3.setPercentHeight(30);
		gp.getRowConstraints().addAll(row1, row2, row3);

		gp.add(vbox1, 0, 0, 1, 1);
		gp.add(vbox2, 0, 1, 1, 1);
		gp.add(vbox3, 0, 2, 1, 1);
		gp.add(vbox4, 1, 0, 2, 1);
		gp.add(vbox5, 1, 1, 1, 2);
		gp.add(sub, 2, 1, 1, 2);

		playPage = new Scene(gp, 1200, 1600);
		// levelPage.getStylesheets().add(LoginPage.class.getResource("jfoenix-components.css").toExternalForm());

		Scene scene = new Scene(pane, 600, 750, Color.WHITE);
		scene.getStylesheets().add(LoginPage.class.getResource("jfoenix-components.css").toExternalForm());

		Image applicationIcon = new Image("/NTP/ic.png");
		window.getIcons().add(applicationIcon);
		window.setScene(scene);
		window.show();

	}
}