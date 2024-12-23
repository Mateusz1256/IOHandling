import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CopyFile {

    private final String sourceFilePath;
    private final String destinationFilePath;

    public CopyFile(String sourceFilePath, String destinationFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.destinationFilePath = destinationFilePath;
    }

    public void copyAndModify() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String modifiedLine = line.replace(" ", "-");
                writer.write(modifiedLine);
                writer.newLine();
            }
            System.out.println("Plik został skopiowany i zmodyfikowany pomyślnie.");
        }
    }
}