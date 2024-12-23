import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj ścieżkę do pliku źródłowego:");
        String sourceFilePath = scanner.nextLine();

        System.out.println("Podaj ścieżkę do pliku docelowego:");
        String destinationFilePath = scanner.nextLine();

        CopyFile copier = new CopyFile(sourceFilePath, destinationFilePath);

        try {
            copier.copyAndModify();
        } catch (IOException e) {
            System.err.println("Wystąpił błąd podczas kopiowania pliku: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}