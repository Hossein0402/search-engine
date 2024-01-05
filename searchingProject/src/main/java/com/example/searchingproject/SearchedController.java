package com.example.searchingproject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchedController implements Initializable {
    public static ArrayList<Integer> areas = new ArrayList<>();
    @FXML
    private VBox vBox;

    @FXML
    private TextField searchText;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void searching(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 734, 438);
        Stage stage = (Stage) (this.searchText.getScene().getWindow());
        stage.setTitle("Google");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        makeFree();
        makeSearch();
    }

    public void makeFree() {
        for (Node node : vBox.getChildren()) {
            if (node instanceof TextArea)
                vBox.getChildren().remove(node);
        }
    }

    public void makeSearch() {
        for (int i = 0; i < areas.size(); i++) {
            TextArea textArea = new TextArea(HelloApplication.texts.get(areas.get(i)));
            textArea.setMinWidth(Region.USE_COMPUTED_SIZE);
            textArea.setMinHeight(100);
            vBox.getChildren().add(textArea);
        }
    }
}
