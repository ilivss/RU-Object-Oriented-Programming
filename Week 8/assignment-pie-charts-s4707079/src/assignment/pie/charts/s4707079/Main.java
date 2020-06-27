/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment.pie.charts.s4707079;

import java.util.LinkedList;
import java.util.List;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;


/**
 *
 * @author user
 */
public class Main extends Application {
    

    @Override
    public void start(Stage stage) {
        List<IntegerProperty> intProps = new LinkedList<>();
        List<DoubleProperty> dbProps = new LinkedList<>();
        List<IntegerProperty> sum = new LinkedList<>();
        IntegerProperty sumProp = new SimpleIntegerProperty();
        
        // Root Pane
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(20);
        root.setVgap(10);
        
        // Generate textboxes & labels
        for (int i = 0; i < 4; i++){
            // Properties
            intProps.add(new SimpleIntegerProperty(1));
            dbProps.add(new SimpleDoubleProperty());
            
            // Textfield
            TextField textField = new TextField();
            textField.textProperty().bindBidirectional(intProps.get(i), new NumberStringConverter());
            textField.textProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
                    if (!newValue.matches("[1-9]\\d{0,3}")) {
                        textField.setText(oldValue);
            }}});
                 
            // Double Property
            dbProps.get(i).bind(intProps.get(i).add(0f).divide(sumProp));
            
            // Sum
            sum.add(new SimpleIntegerProperty());
            if (i == 0){
                sum.get(i).bind(intProps.get(i));
            } else {
                sum.get(i).bind(sum.get(i-1).add(intProps.get(i)));
            }
            
            // Label
            Label label = new Label();
            label.textProperty().bind(Bindings.format("%.2f", dbProps.get(i)));
            
            // Add elements to root pane
            root.add(textField, 0, i);
            root.add(label, 1, i);
        }
        
//        sumProp.bind(intProps.get(0).add(intProps.get(1)).add(intProps.get(2)).add(intProps.get(3))); // Hardcode sum.
        sumProp.bind(sum.get(3));   // smartsum


        // Scene & Stage
        Scene scene = new Scene(root, 500, 500);
        stage.setTitle("test");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
