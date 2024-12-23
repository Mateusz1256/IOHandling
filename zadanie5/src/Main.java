import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Bank> banks;

        try {
            banks = BankSearch.loadBanksFromUrl();
        } catch (IOException e) {
            System.err.println("Błąd podczas pobierania danych z URL: " + e.getMessage());
            return;
        }

        System.out.println("Podaj kod banku do wyszukania (lub: 'q' aby zakończyć; 'nazwa' aby szukać po nazwie; 'print' aby wyświetlić wszystkie):");
        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("q")) {
            if (input.equals("print")){
                BankSearch.showBanks(banks);
            }else if (input.equals("nazwa")) {
                System.out.println("Podaj nazwę banku do wyszukania (lub 'kod' aby wrócić do szukania po kodzie):");
                while (!(input = scanner.nextLine()).equalsIgnoreCase("kod")){
                    BankSearch.searchBanksByName(banks, input);
                    System.out.println("Podaj nazwę banku do wyszukania (lub 'kod' aby wrócić do szukania po kodzie):");
                }
                System.out.println("Podaj kod banku do wyszukania (lub: 'q' aby zakończyć; 'nazwa' aby szukać po nazwie; 'print' aby wyświetlić wszystkie):");
            }else{
                BankSearch.searchBankByCode(banks, input);
                System.out.println("Podaj kod banku do wyszukania (lub: 'q' aby zakończyć; 'nazwa' aby szukać po nazwie; 'print' aby wyświetlić wszystkie):");
            }
        }
        scanner.close();
    }
}