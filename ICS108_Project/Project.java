import java.io.*;
import java.util.*;
import javafx.scene.paint.*;
import javafx.application.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.image.*;
import javafx.stage.Stage;


public class Project extends Application{
	public void start(Stage primaryStage) {
		List<String> Array = new ArrayList<String>();
		Array.add("What is my favorate color ? ");
		Array.add("whats is 1+2 ?");
		Array.add("how many times have i been aboard ?");
		
		List<String[]> answers = new ArrayList<String[]>();
		answers.add(new String[]{"redR*" , "yellow" , "green" , "Purble"});
		answers.add(new String[]{"1" , "2" , "3R*" , "4"});
		answers.add(new String[]{"one time" , "3 timesR*" , "Sometime" , "never"});
		
		//Display Questions
		Text main = new Text("Question : ");//Question title
		Text answer = new Text();//Correct answer
		Button Submit = new Button("Submit");//submit answer
		
		//Radio buttons For test with default value Q1 , Q2 , Q3 , Q4
		RadioButton q1 = new RadioButton("Q1");
		q1.setContentDisplay(ContentDisplay.LEFT);
		RadioButton q2 = new RadioButton("Q2");
		q1.setContentDisplay(ContentDisplay.LEFT);
		RadioButton q3 = new RadioButton("Q3");
		q1.setContentDisplay(ContentDisplay.LEFT);
		RadioButton q4 = new RadioButton("Q4");
		q1.setContentDisplay(ContentDisplay.LEFT);
		
		//Link previous buttons with each other
		ToggleGroup group = new ToggleGroup();
		q1.setToggleGroup(group);
		q2.setToggleGroup(group);
		q3.setToggleGroup(group);
		q4.setToggleGroup(group);
		
		// Create vertical box for buttons
		VBox Answ = new VBox(30);
		Answ.setPadding(new Insets(20,20,20,70));
		Answ.getChildren().addAll(main , q1 , q2 , q3 , q4 , Submit , answer);
		
		//Edit box
		ComboBox<String> cbo = new ComboBox<>();//create combo box
		ObservableList<String> questions = 
			      FXCollections.observableArrayList(Array);
			    cbo.getItems().addAll(questions);
		cbo.setPrefWidth(150);
		cbo.setValue("Choose question");
		
		
		//hide and show edit box
		Button hideNshow = new Button("Show edit Box");
		hideNshow.setPrefWidth(150);
		
		//create hbox for combo box and hide show button
		HBox hbox = new HBox(20);
		hbox.getChildren().addAll(cbo , hideNshow);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(20,20,50,20));
		
		//create vbox for question editor
		VBox vbox = new VBox(30);
		
		//create empty field for create new answers
		TextField text1 = new TextField();
		TextField text2 = new TextField();
		TextField text3 = new TextField();
		TextField text4 = new TextField();
		
		//create radio buttons for the correct answer
		RadioButton corr1 = new RadioButton();
		RadioButton corr2 = new RadioButton();
		RadioButton corr3 = new RadioButton();
		RadioButton corr4 = new RadioButton();
		
		//create vbox for radio buttons and toggle group
		Text Correct = new Text("Set correct");
		VBox toggleBox = new VBox(40);
		toggleBox.getChildren().addAll(Correct , corr1 ,corr2 , corr3 , corr4);
		toggleBox.setPadding(new Insets(30,0,0,30));
		
		//link toggle group 
		ToggleGroup group2 = new ToggleGroup();
		corr1.setToggleGroup(group2);
		corr2.setToggleGroup(group2);
		corr3.setToggleGroup(group2);
		corr4.setToggleGroup(group2);
		
		//header question
		Text text = new Text("Question name");
		
		//create save and add and delete buttons
		Button add = new Button("add");
		Button save = new Button("save");
		Button delete = new Button("delete");
		Button edit = new Button("edit");
		
		//create hbox for save and add and delete buttons
		HBox savesBox = new HBox(20);
		savesBox.getChildren().addAll(save , edit , add , delete);
		vbox.getChildren().addAll(text , text1 , text2 ,text3 ,text4 , savesBox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(35,20,20,20));
		
		//create hbox for gather the toggle vbox and textfield vbox
		HBox mainHbox = new HBox(20);
		mainHbox.getChildren().addAll(toggleBox , vbox);
		mainHbox.setStyle("-fx-border-width: 2px; -fx-border-color: red");
		
		
		//button for save the new array in binary file
		Button close = new Button("Save all questions");
		
		//create the lasts Pane
		StackPane closePane = new StackPane();//Pane for close button
		closePane.getChildren().addAll(close);
		closePane.setPadding(new Insets(50,50,50,50));
		closePane.setAlignment(Pos.CENTER);
					
		//Border Pane for whole program
		BorderPane bordPane = new BorderPane();
		bordPane.setTop(hbox);
		bordPane.setLeft(Answ);
		bordPane.setBottom(closePane);
		
		
				
		//The Events Handler 
		
		
		//combo Event Hander
		cbo.setOnAction(e -> {
			q1.setText(answers.get(questions.indexOf(cbo.getValue()))[0].replace("R*", ""));
			q2.setText(answers.get(questions.indexOf(cbo.getValue()))[1].replace("R*", ""));
			q3.setText(answers.get(questions.indexOf(cbo.getValue()))[2].replace("R*", ""));
			q4.setText(answers.get(questions.indexOf(cbo.getValue()))[3].replace("R*", ""));
			text.setText(cbo.getValue());
			text1.setText(q1.getText());  
			text2.setText(q2.getText()); 
			text3.setText(q3.getText()); 
			text4.setText(q4.getText()); 
			main.setText(cbo.getValue());
		});
		
		//save button Event Hander
		save.setOnAction(e -> {
			q1.setText(text1.getText());
			answers.get(questions.indexOf(cbo.getValue()))[0] = text1.getText();
			q2.setText(text2.getText());
			answers.get(questions.indexOf(cbo.getValue()))[1] = text2.getText();
			q3.setText(text3.getText());
			answers.get(questions.indexOf(cbo.getValue()))[2] = text3.getText();
			q4.setText(text4.getText());
			answers.get(questions.indexOf(cbo.getValue()))[3] = text4.getText();
			if(corr1.isSelected())
				answers.get(questions.indexOf(cbo.getValue()))[0] = text1.getText() + "R*";
			else if(corr2.isSelected())
				answers.get(questions.indexOf(cbo.getValue()))[1] = text2.getText() + "R*";
			else if(corr3.isSelected())
				answers.get(questions.indexOf(cbo.getValue()))[2] = text3.getText() + "R*";
			else if(corr4.isSelected())
				answers.get(questions.indexOf(cbo.getValue()))[3] = text4.getText() + "R*";
		
		});
		
		edit.setOnAction(e -> {
			// to save the new questions
			Button saveNQ = new Button("Save");
			Button cancelNQ = new Button("Cancel");			
			//recive the new question name
			TextField inputNQ = new TextField(cbo.getValue());
			VBox inputNQBox = new VBox(20);
			HBox saveCancelBox = new HBox(10);
			saveCancelBox.getChildren().addAll(saveNQ , cancelNQ);
			saveCancelBox.setAlignment(Pos.CENTER);
			inputNQBox.setAlignment(Pos.CENTER);
			inputNQBox.setPadding(new Insets(5,5,5,5));
			inputNQBox.setMaxSize(200, 70);
			inputNQBox.setStyle("-fx-border-width: 2px; -fx-border-color: red");
			inputNQBox.getChildren().addAll(inputNQ , saveCancelBox);
			vbox.getChildren().remove(0);
			vbox.getChildren().add(0 , inputNQBox);
			
			//event for cancel button
			cancelNQ.setOnAction(g -> {
				vbox.getChildren().remove(0);
				vbox.getChildren().add(0 , text); });
		
			//event for save button
			saveNQ.setOnAction(f -> {
			text.setText(inputNQ.getText());
			vbox.getChildren().remove(0);
			vbox.getChildren().add(0 , text);
			vbox.setPadding(new Insets(20,20,20,20));
			questions.set(questions.indexOf(cbo.getValue()), inputNQ.getText());
	    	cbo.getItems().clear();
			cbo.getItems().addAll(questions);
			cbo.setValue(inputNQ.getText());
			main.setText(inputNQ.getText());
		});
		});
		
		//add the questions and answers
		add.setOnAction(e -> {
			// to save the new questions
			Button saveQ = new Button("Save");
			Button cancelQ = new Button("Cancel");
			
			TextField input = new TextField("Enter The Question");
			VBox inputBox = new VBox(20);
			HBox saveCancelBox = new HBox(10);
			saveCancelBox.getChildren().addAll(saveQ , cancelQ);
			saveCancelBox.setAlignment(Pos.CENTER);
			inputBox.setAlignment(Pos.CENTER);
			inputBox.setPadding(new Insets(5,5,5,5));
			inputBox.setMaxSize(200, 70);
			inputBox.setStyle("-fx-border-width: 2px; -fx-border-color: red");
			inputBox.getChildren().addAll(input , saveCancelBox);
			vbox.getChildren().remove(0);
			vbox.getChildren().add(0 , inputBox);
			vbox.setPadding(new Insets(10,20,20,20));
			
			//event for cancel button
			cancelQ.setOnAction(g -> {
				vbox.getChildren().remove(0);
				vbox.getChildren().add(0 , text); });
			
			//event for save button
			saveQ.setOnAction(f -> {
			text.setText(input.getText());
			vbox.getChildren().remove(0);
			vbox.getChildren().add(0 , text);
			vbox.setPadding(new Insets(20,20,20,20));
			questions.add(input.getText());
			answers.add(new String[4]);
			 q1.setText("");  text1.setText("");
			 q2.setText("");  text2.setText("");
			 q3.setText("");  text3.setText("");
			 q4.setText("");  text4.setText("");
			 cbo.getItems().clear();
			 cbo.getItems().addAll(questions);
			 cbo.setValue(input.getText());
			 main.setText(input.getText());
			});
		});
		
		//delete the questions and answers
		delete.setOnAction(e -> {
			answers.remove(questions.indexOf(cbo.getValue()));
			questions.remove(questions.indexOf(cbo.getValue()));
			 q1.setText("");  text1.setText("");
			 q2.setText("");  text2.setText("");
			 q3.setText("");  text3.setText("");
			 q4.setText("");  text4.setText("");
			main.setText("");
			cbo.getItems().clear();
			cbo.getItems().addAll(questions);
			cbo.setValue(questions.get(0));
		});
		
		//Submit button Event Hander
		Submit.setOnAction(e -> {
			if(q1.isSelected() && answers.get(0)[0].contains("R*")) {
				answer.setText("Your Answer  is CORRECT ANSWER");
				answer.setFill(Color.GREEN);}
			else if(q2.isSelected() &&answers.get(questions.indexOf(cbo.getValue()))[1].contains("R*")) {
				answer.setText("Your Answer  is CORRECT ANSWER");
				answer.setFill(Color.GREEN);}
			else if(q3.isSelected() && answers.get(questions.indexOf(cbo.getValue()))[2].contains("R*")) {
				answer.setText("Your Answer  is CORRECT ANSWER");
				answer.setFill(Color.GREEN);}
			else if(q4.isSelected() && answers.get(questions.indexOf(cbo.getValue()))[3].contains("R*")) {
				answer.setText("Your Answer  is CORRECT ANSWER");
				answer.setFill(Color.GREEN);}
			else {
				for(int i = 0; i < 4 ; i++) {
					if(answers.get(questions.indexOf(cbo.getValue()))[i].contains("R*")) {
					answer.setText(answers.get(questions.indexOf(cbo.getValue()))[i].replace("R*", "") + "  is CORRECT ANSWER");
					answer.setFill(Color.RED);}}
				}	
			});
		
		//Event handler for hide and show questions editor
		hideNshow.setOnAction(e -> {
			if(hideNshow.getText() == "Hide edit Box") {
				hideNshow.setText("Show edit Box");
				bordPane.setRight(null);}
			else {
				hideNshow.setText("Hide edit Box");
				bordPane.setRight(mainHbox);
			}
		});
		
		//Scene and stage
		Scene scene = new Scene(bordPane,800,600);
		primaryStage.setTitle("Quistions Bank creator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//run 
		launch(args);
	}
}