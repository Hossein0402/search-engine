import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        File file = new File("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EnglishData");
        File[] files = file.listFiles();
        if (files != null) {
            int index = 1;
            for (File newFile : files) {
                String makingTxtFile = "";
                Scanner scanner = new Scanner(newFile);
                StringBuilder line = new StringBuilder();
                while (scanner.hasNext()) {
                    String x = scanner.nextLine();
                    makingTxtFile = x;
                    x = x.replaceAll("[^a-zA-Z\\s]|^are$|^is$|^for$|^and$|^or$|^the$|^this$", " ");
                    line.append(x);
                    line.append("\n");
                }
                FileWriter fileWriter = new FileWriter("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\txtFile\\" + index + ".txt");
                fileWriter.write(makingTxtFile);
                fileWriter.close();
                FileWriter writer = new FileWriter("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EditedText\\" + index + ".txt");
                writer.write(String.valueOf(line));
                writer.close();
                index++;
            }
        }
    }
}