package com.github.liamsharp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application
{
    private static TextField mTextField;
    
    public static void main(String[] args) 
    {
        launch(args);
    }
    
    public static String getTextFieldText()
    {
        return mTextField.getText();
    }

    @Override
    public void start(
        final Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        
        mTextField = new TextField("");
        mTextField.setId("text_field");
        StackPane root = new StackPane();
        root.getChildren().add(mTextField);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
    

}
