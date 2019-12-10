import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
 
public class TheApp extends Application {
    
    
	@Override
    public void start(Stage primaryStage) {
		
		// The data structure to store all words in the document
		List<String> allWords = new ArrayList<String>();
		List<String> allWords2 = new ArrayList<String>();
		
    	// create the user interface
    	primaryStage.setTitle("Unnecessary Word Remover");
        FileChooser chooser = new FileChooser();
        Button chooserButton = new Button("Select the .txt file to modify");
        Label lab = new Label("Enter Word to Remove");
        TextField wordEntry = new TextField();
        Button removeButton = new Button("Remove the Word");
        Button saveButton = new Button("Save the Revised File");
        Label blankLine = new Label("");
        Label outputMessage = new Label("");
        GridPane pane = new GridPane();
        pane.add(chooserButton, 0, 0);
        pane.add(lab, 0, 1);
        pane.add(wordEntry, 1,  1);
        pane.add(removeButton, 2, 1);
        pane.add(saveButton, 0, 2);
        pane.add(blankLine, 0, 3);
        pane.add(outputMessage, 0, 4);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        // code to open the file
        chooserButton.setOnAction(e -> {
        	outputMessage.setText("reading the words from the file...");
        	File selectedFile = chooser.showOpenDialog(primaryStage);
            try {
                Scanner input = new Scanner(selectedFile); 
                while (input.hasNext()) {
                    String word  = input.next();
                    allWords.add(word);
                }
                input.close();
                outputMessage.setText("the file has been read");
            }
            catch (FileNotFoundException ex) {
            	System.out.println("there was an error reading from that file");
            } 
        });
        
        // code to remove the word from the list
        removeButton.setOnAction(e -> {
        	String wordToRemove = wordEntry.getText();
        	wordEntry.setText("");
        	outputMessage.setText("removing '" + wordToRemove + "' from the document...");
            ListIterator<String> listIterator = allWords.listIterator(); // an iterator is a way to iterate (loop) through a list
            while (listIterator.hasNext()) {
            	if (listIterator.next().toString().equals(wordToRemove)) {
            		listIterator.remove();
            	}
            }
            
            outputMessage.setText("the word '" + wordToRemove + "' has been removed");
        });
        
        // code to save the revised file
        saveButton.setOnAction(e -> {
        	outputMessage.setText("saving the revised file...");
            try {
            	PrintWriter writer = new PrintWriter("revisedFile.txt", "UTF-8"); 
                for (String word: allWords)
                	writer.print(word + " ");
                writer.close();
                outputMessage.setText("the revised file has been saved");
            }
            catch (IOException ex) {
            	System.out.println("there was an error saving the file");
            } 
        });
 
    }
    
    
 public static void main(String[] args) {
        launch(args);
    }
}