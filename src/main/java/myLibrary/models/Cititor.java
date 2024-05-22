package myLibrary.models;

public class Cititor {
    private int id;
    private String nume;
    private String prenume;

    @Override
    public String toString() {
        return "Cititor{" +
                " nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }
}
