package myLibrary.models;

import java.util.List;

public class Bibliotecar {
    private int id;
    private String nume;
    private String prenume;
    private List<Sectiune> sectiuniGestionate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public List<Sectiune> getSectiuniGestionate() {
        return sectiuniGestionate;
    }

    public void setSectiuniGestionate(List<Sectiune> sectiuniGestionate) {
        this.sectiuniGestionate = sectiuniGestionate;
    }

    @Override
    public String toString() {
        return "Bibliotecar{" +
                "id='" + id + '\'' +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", sectiuni gestionate=" + sectiuniGestionate +
                '}';
    }
}
