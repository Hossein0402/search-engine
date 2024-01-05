package com.example.searchingproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class HelloApplication extends Application {
    public static HashMap<String, HashSet<Integer>> searches = new HashMap<>();
    public static HashMap<Integer, String> texts = new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException, SQLException, ClassNotFoundException {
        makeMap();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 734, 438);
        stage.setTitle("Google");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void makeMap() throws ClassNotFoundException, SQLException, FileNotFoundException {
        File file1 = new File("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EditedText");
        File[] list = file1.listFiles();
        int index = 1;
        for (File fie : list) {
            Scanner scanner = new Scanner(fie);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] words = input.split("\\s+");
                for (String word : words) {
                    if (word.length() > 1) {
                        int name = Integer.parseInt(fie.getName().replaceFirst("[.][^.]+$", ""));
                        word = word.toUpperCase();
                        if (searches.get(word) != null)
                            searches.get(word).add(name);
                        else {
                            HashSet<Integer> newArray = new HashSet<>();
                            newArray.add(name);
                            searches.put(word, newArray);
                        }
                    }
                }
            }
            index++;
        }
        File file = new File("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\txtFile");
        File[] allFiles = file.listFiles();
        if (allFiles != null)
            for (File x : allFiles) {
                int name = Integer.parseInt(x.getName().replaceFirst("[.][^.]+$", ""));
                Scanner scanner = new Scanner(x);
                StringBuilder content = new StringBuilder();
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine());
                }
                texts.put(name, String.valueOf(content));
            }

    }

    public static void main(String[] args) {
        launch();
    }
}