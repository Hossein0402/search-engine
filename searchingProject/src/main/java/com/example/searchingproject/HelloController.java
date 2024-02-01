
package com.example.searchingproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public static HashSet<Integer> textNumbers = new HashSet<>();
    @FXML
    private ImageView search;

    @FXML
    private TextField searchText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setOnMouseClicked(event -> {
            makeTextNumbers(searchText.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("searched.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load(), 742, 629);
                Stage stage = (Stage) (this.searchText.getScene().getWindow());
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public static void makeTextNumbers(String texts) {
        String[] splitTexts = texts.split("\\s");
        HashSet<Integer> should = new HashSet<>();
        HashSet<Integer> plus = new HashSet<>();
        HashSet<Integer> minus = new HashSet<>();
        boolean check = true;
        for (int i = 0; i < splitTexts.length; i++) {
            if (splitTexts[i].charAt(0) == '+') {
                splitTexts[i] = splitTexts[i].toUpperCase();
                plus.addAll(HelloApplication.searches.get(splitTexts[i].replaceAll("\\+", "")));
            } else if (splitTexts[i].charAt(0) == '-') {
                splitTexts[i] = splitTexts[i].toUpperCase();
               /* for (ArrayList<Integer> integers : HelloApplication.searches.values()) {
                    minus.addAll(integers);
                }*/
                minus.addAll(HelloApplication.searches.get(splitTexts[i].replace("-", "")));
            } else {
                splitTexts[i] = splitTexts[i].toUpperCase();
                if (check) {
                    should.addAll(HelloApplication.searches.get(splitTexts[i]));
                    check = false;
                }
                should.retainAll(HelloApplication.searches.get(splitTexts[i]));
            }
        }
        if (should.size() == 0)
            should.addAll(plus);
        else {
            for (Integer x : plus) {
                if (!plus.contains(x))
                    should.remove(x);
            }
        }
        should.removeAll(minus);
        textNumbers.addAll(should);
        SearchedController.areas.addAll(textNumbers);
    }
}