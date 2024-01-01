import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EnglishData");
        File[] files = file.listFiles();
        if (files != null) {
            int index = 1;
            for (File newFile : files) {
                Scanner scanner = new Scanner(newFile);
                StringBuilder line = new StringBuilder();
                while (scanner.hasNext()) {
                    String x = scanner.nextLine();
                    x = x.replaceAll("[^a-zA-Z\\s]|^are$|^is$|^for$|^and$|^or$|^the$|^this$", " ");
                    line.append(x);
                    line.append("\n");
                }
                FileWriter writer = new FileWriter("C:\\Users\\dd\\Documents\\GitHub\\search-engine-Hossein0402\\text\\EditedText\\"+index+".txt");
                writer.write(String.valueOf(line));
                writer.close();
                index++;
            }
        }
    }
}