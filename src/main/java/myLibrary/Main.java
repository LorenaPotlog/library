package myLibrary;

import myLibrary.models.Bibliotecar;
import myLibrary.services.Autentificare;
import myLibrary.services.Biblioteca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import static myLibrary.helpers.helpers.*;


public class Main {
    public static void main(String[] args) {
        h2Initialization();

        Biblioteca biblioteca = new Biblioteca();
        Autentificare autentificare = new Autentificare();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;

        while (run) {
            System.out.println("\nBiblioteca");
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
            System.out.println("======================================");
            System.out.println("14. Adauga bibliotecar");
            System.out.println("15. Afiseaza bibliotecari");
            System.out.println("16. Autentificare bibliotecari");
            System.out.println("0.  Exit");
            System.out.println("======================================");
            System.out.print("Alegeti o optiune: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Introduceti titlul: ");
                    String title = scanner.nextLine();
                    System.out.print("Introduceti autorul: ");
                    String author = scanner.nextLine();
                    System.out.print("Introduceti anul: ");
                    int year = scanner.nextInt();
                    System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                    System.out.print("Introduceti numarul sectiunii: ");
                    int idSectiune = scanner.nextInt();
                    while (!isValidSectionId(idSectiune)) {
                        System.out.println("Nu ati introdus o sectiune valida.");

                        System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                        System.out.print("Introduceti numarul sectiunii: ");
                        idSectiune = scanner.nextInt();
                    }
                    System.out.print("Introduceti volumul: ");
                    int volum = scanner.nextInt();
                    biblioteca.adaugaCarte(title, author, year, idSectiune, volum);
                    System.out.println("Cartea a fost adaugata cu succes.");
                    break;
                case 2:
                    System.out.print("Introduceti ID-ul: ");
                    int idCarte = scanner.nextInt();
                    System.out.println(biblioteca.afiseazaCarte(idCarte));
                    break;
                case 3:
                    biblioteca.afiseazaToateCartile().forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Introduceti ID-ul: ");
                    idCarte = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduceti noul titlu: ");
                    title = scanner.nextLine();
                    System.out.print("Introduceti noul autor: ");
                    author = scanner.nextLine();
                    biblioteca.modificaCarte(idCarte, title, author);
                    System.out.println("Cartea a fost actualizata cu succes.");
                    break;
                case 5:
                    System.out.print("Introduceti ID-ul: ");
                    idCarte = scanner.nextInt();
                    biblioteca.stergeCarte(idCarte);
                    System.out.println("Cartea a fost ștearsă cu succes.");
                    break;
                case 6:
                    System.out.print("Introduceti ID-ul cartii: ");
                    idCarte = scanner.nextInt();
                    System.out.print("Introduceti ID-ul cititorului: ");
                    int idCititor = scanner.nextInt();
                    System.out.print("Introduceti durata imprumutului in zile: ");
                    int durataImprumutZile = scanner.nextInt();
                    try {
                        biblioteca.imprumutaCarte(idCarte, idCititor, durataImprumutZile);
                        System.out.println("Cartea a fost imprumutata cu succes.");
                    } catch (RuntimeException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.print("Introduceti ID-ul: ");
                    int idImprumut = scanner.nextInt();
                    biblioteca.returneazaCarte(idImprumut);
                    System.out.println("Cartea a fost returnata cu succes.");
                    break;
                case 8:
                    System.out.print("Introduceti titlul: ");
                    title = scanner.nextLine();
                    System.out.print("Introduceti autorul: ");
                    author = scanner.nextLine();
                    System.out.print("Introduceti anul: ");
                    year = scanner.nextInt();
                    System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                    System.out.print("Introduceti numarul sectiunii: ");
                    idSectiune = scanner.nextInt();
                    while (!isValidSectionId(idSectiune)) {
                        System.out.println("Nu ati introdus o sectiune valida.");

                        System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                        System.out.print("Introduceti numarul sectiunii: ");
                        idSectiune = scanner.nextInt();
                    }
                    System.out.print("Introduceti durata: ");
                    int durata = scanner.nextInt();
                    biblioteca.adaugaAudioBook(title, author, year, idSectiune, durata);
                    System.out.println("Audiobook-ul a fost adaugat cu succes.");
                    break;
                case 9:
                    biblioteca.afiseazaToateAudioBooks().forEach(System.out::println);
                    break;
                case 10:
                    System.out.print("Introduceti ID-ul: ");
                    int idAudioBook = scanner.nextInt();
                    biblioteca.stergeAudioBook(idAudioBook);
                    System.out.println("Audiobook-ul a fost sters cu succes.");
                    break;
                case 11:
                    System.out.print("Introduceti ID-ul audiobook-ului: ");
                    idAudioBook = scanner.nextInt();
                    System.out.print("Introduceti ID-ul cititorului: ");
                    idCititor = scanner.nextInt();
                    System.out.print("Introduceti durata imprumutului în zile: ");
                    durataImprumutZile = scanner.nextInt();
                    try {
                        biblioteca.imprumutaAudioBook(idAudioBook, idCititor, durataImprumutZile);
                        System.out.println("Audiobook-ul a fost imprumutat cu succes.");
                    } catch (RuntimeException e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;
                case 12:
                    System.out.print("Introduceti ID-ul imprumutului: ");
                    idImprumut = scanner.nextInt();
                    biblioteca.returneazaAudioBook(idImprumut);
                    System.out.println("Audiobook-ul a fost returnat cu succes.");
                    break;
                case 13:
                    biblioteca.afiseazaImprumuturiCarti().forEach(System.out::println);
                    break;
                case 14:
                    System.out.print("Introduceti prenumele: ");
                    String nume = scanner.nextLine();
                    System.out.print("Introduceti numele: ");
                    String prenume = scanner.nextLine();
                    List<Integer> sectiuni = new ArrayList<>();
                    System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                    while (true) {
                        System.out.print("Introduceti numarul sectiunii pentru gestionare (sau 0 pentru a termina): ");
                        idSectiune = scanner.nextInt();
                        if (idSectiune == 0) break;
                        while (!isValidSectionId(idSectiune)) {
                            System.out.println("Nu ati introdus o sectiune valida.");
                            System.out.println("Sectiuni: 1-Romantic, 2-Horror, 3-Comedie, 4-Documentar");
                            System.out.print("Introduceti ID-ul sectiunii pentru gestionare (sau 0 pentru a termina): ");
                            idSectiune = scanner.nextInt();
                            if (idSectiune == 0) break;
                        }
                        sectiuni.add(idSectiune);
                    }
                    scanner.nextLine();
                    System.out.print("Introduceti username-ul: ");
                    String username = scanner.nextLine();
                    System.out.print("Introduceti parola: ");
                    String password = scanner.nextLine();
                    biblioteca.adaugaBibliotecar(nume, prenume, sectiuni, username, password);
                    System.out.println("Bibliotecarul a fost adaugat cu succes.");
                    break;
                case 15:
                    List<Bibliotecar> bibliotecari = biblioteca.afiseazaBibliotecari();
                    for (Bibliotecar bibliotecar : bibliotecari) {
                        System.out.println(bibliotecar);
                    }
                    break;
                case 16:
                    System.out.print("Id user: ");
                    int idBibliotcar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    System.out.print("Parola: ");
                    password = scanner.nextLine();
                    autentificare.autentificare(idBibliotcar, username, password);
                    break;
                case 0:
                    run = false;
                    break;
                default:
                    System.out.println("Optiune invalida.");
                    break;
            }
        }
        scanner.close();
    }
}
