package com.example.searchingproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView search;

    @FXML
    private TextField searchText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        search.setOnMouseClicked(event -> {

        });
    }
}