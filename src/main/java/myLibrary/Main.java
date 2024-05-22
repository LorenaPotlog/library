package myLibrary;

import myLibrary.services.Biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String sqlFilePath = "src/main/resources/create_tables.sql";
        try (Connection conn = Conexiune.getConnection(); Statement stmt = conn.createStatement()) {

            BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
            String line;
            StringBuilder sql = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sql.append(line);
                if (line.trim().endsWith(";")) {
                    System.out.println("Executing SQL: " + sql.toString());
                    stmt.execute(sql.toString());
                    sql.setLength(0);
                }
            }
            reader.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\nLibrary Management System");
            System.out.println("======================================");
            System.out.println("1.  Adauga carte");
            System.out.println("2.  Afiseaza carte");
            System.out.println("3.  Afiseaza toate cartile (ordonate dupa an)");
            System.out.println("4.  Modifica carte");
            System.out.println("5.  Sterge carte");
            System.out.println("======================================");
            System.out.println("6.  Imprumuta carte");
            System.out.println("7.  Returneaza carte");
            System.out.println("======================================");
            System.out.println("8.  Adauga audiobook");
            System.out.println("9.  Afiseaza toate audiobook");
            System.out.println("10. Sterge audiobook");
            System.out.println("======================================");
            System.out.println("11. Imprumuta audiobook");
            System.out.println("12. Returneaza audiobook");
            System.out.println("======================================");
            System.out.println("13. Afiseaza toate imprumurile (carti)");
            System.out.println("0.  Exit");
            System.out.println("======================================");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book year: ");
                    int year = scanner.nextInt();
                    System.out.println("Sections: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                    System.out.print("Enter section ID: ");
                    int idSectiune = scanner.nextInt();
                    System.out.print("Enter volume: ");
                    int volum = scanner.nextInt();
                    biblioteca.adaugaCarte(title, author, year, idSectiune, volum);
                    System.out.println("Book added successfully.");
                    break;
                case 2:
                    System.out.print("Enter book ID: ");
                    int idCarte = scanner.nextInt();
                    System.out.println(biblioteca.afiseazaCarte(idCarte));
                    break;
                case 3:
                    biblioteca.afiseazaToateCartile().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Enter book ID: ");
                    idCarte = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new book title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter new book author: ");
                    author = scanner.nextLine();
                    biblioteca.modificaCarte(idCarte, title, author);
                    System.out.println("Book updated successfully.");
                    break;
                case 5:
                    System.out.print("Enter book ID: ");
                    idCarte = scanner.nextInt();
                    biblioteca.stergeCarte(idCarte);
                    System.out.println("Book deleted successfully.");
                    break;
                case 6:
                    System.out.print("Enter book ID: ");
                    idCarte = scanner.nextInt();
                    System.out.print("Enter reader ID: ");
                    int idCititor = scanner.nextInt();
                    System.out.print("Enter loan duration in days: ");
                    int durataImprumutZile = scanner.nextInt();
                    try {
                        biblioteca.imprumutaCarte(idCarte, idCititor, durataImprumutZile);
                        System.out.println("Book loaned successfully.");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Enter loan ID: ");
                    int idImprumut = scanner.nextInt();
                    biblioteca.returneazaCarte(idImprumut);
                    System.out.println("Book returned successfully.");
                    break;
                case 8:
                    System.out.print("Enter audiobook title: ");
                    title = scanner.nextLine();
                    System.out.print("Enter audiobook author: ");
                    author = scanner.nextLine();
                    System.out.print("Enter audiobook year: ");
                    year = scanner.nextInt();
                    System.out.println("Sections: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                    System.out.print("Enter section ID: ");
                    idSectiune = scanner.nextInt();
                    System.out.print("Enter duration: ");
                    int durata = scanner.nextInt();
                    biblioteca.adaugaAudioBook(title, author, year, idSectiune, durata);
                    System.out.println("Audiobook added successfully.");
                    break;
                case 9:
                    biblioteca.afiseazaToateAudioBooks().forEach(System.out::println);
                    break;
                case 10:
                    System.out.print("Enter audiobook ID: ");
                    int idAudioBook = scanner.nextInt();
                    biblioteca.stergeAudioBook(idAudioBook);
                    System.out.println("Audiobook deleted successfully.");
                    break;
                case 11:
                    System.out.print("Enter audiobook ID: ");
                    idAudioBook = scanner.nextInt();
                    System.out.print("Enter reader ID: ");
                    idCititor = scanner.nextInt();
                    System.out.print("Enter loan duration in days: ");
                    durataImprumutZile = scanner.nextInt();
                    try {
                        biblioteca.imprumutaAudioBook(idAudioBook, idCititor, durataImprumutZile);
                        System.out.println("Audiobook loaned successfully.");
                    } catch (RuntimeException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 12:
                    System.out.print("Enter loan ID: ");
                    idImprumut = scanner.nextInt();
                    biblioteca.returneazaAudioBook(idImprumut);
                    System.out.println("Audiobook returned successfully.");
                    break;
                case 13:
                    biblioteca.afiseazaImprumuturiCarti().forEach(System.out::println);
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
