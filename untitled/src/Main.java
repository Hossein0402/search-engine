import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/search_engine", "root", "1080682805fghFGH@#");
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        File file = new File("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EditedText");
        File[] list = file.listFiles();
        int index = 1;
        for (File fie : list) {
            Scanner scanner = new Scanner(fie);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] words = input.split("\\s+");
                for (String word : words) {
                    if (word.length() > 1) {
                        word = word.toUpperCase();
                        if (map.get(word) != null)
                            map.get(word).add(index);
                        else {
                            ArrayList<Integer> newArray = new ArrayList<>();
                            newArray.add(index);
                            map.put(word, newArray);
                        }
                    }
                }
            }
            index++;
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
            String word = entry.getKey();
            StringBuilder texts = new StringBuilder();
            for (int i : entry.getValue()) {
                texts.append(i).append(" ");
            }
            try {
                String sql = "INSERT INTO map(Word,Texts) VALUES('" + word + "','" + texts + "')";
                Statement statement = connection.prepareStatement(sql);
                statement.execute(sql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}