import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BankSearch {

    private static final String FILE_URL = "https://ewib.nbp.pl/plewibnra?dokNazwa=plewibnra.txt";



    public static List<Bank> loadBanksFromUrl() throws IOException {
        List<Bank> banks = new ArrayList<>();
        URL url = new URL(FILE_URL);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "Windows-1250"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Bank bank = parseBankFromLine(line);
                if (bank != null) {
                    banks.add(bank);
                }
            }
        }
        return banks;
    }

    public static Bank parseBankFromLine(String line) {
        if (line.length() < 157) {
            return null;
        }
        try {
            String code = line.substring(0, 5).trim();
            String name = line.substring(5, 90).trim();
            String branchName = line.substring(308, 448).trim();

            name = name + (branchName.isEmpty() ? "" : " - " + branchName);

            return new Bank(code, name);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Błąd parsowania linii: " + line);
            return null;
        }
    }


    public static void searchBankByCode(List<Bank> banks, String code) {
        boolean found = false;
        for (Bank bank : banks) {
            if (bank.code().equals(code)) {
                System.out.println("Znaleziono bank:");
                System.out.println("Kod: " + bank.code());
                System.out.println("Nazwa: " + bank.name());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Nie znaleziono banku o kodzie: " + code);
        }
    }

    public static void searchBanksByName(List<Bank> banks, String name){
        boolean found = false;
        for (Bank bank : banks) {
            if (bank.name().toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Znaleziono bank:");
                System.out.println("Kod: " + bank.code());
                System.out.println("Nazwa: " + bank.name());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Nie znaleziono banku o nazwie: " + name);
        }
    }

    public static void showBanks(List<Bank> banks){
        for (Bank bank : banks) {

            System.out.println("Kod: " + bank.code());
            System.out.println("Nazwa: " + bank.name());

        }
    }
}

