package com.example.searchingproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class HelloApplication extends Application {
    public static HashMap<String, ArrayList<Integer>> searches = new HashMap<>();
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
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/search_engine", "root", "1080682805fghFGH@#");
        String sql = "SELECT * FROM map";
        Statement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            String text = rs.getString("Texts");
            String[] texts = text.split("\\s");
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < texts.length; i++) {
                integers.add(Integer.valueOf(texts[i]));
            }
            searches.put(rs.getString("Word"), integers);
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