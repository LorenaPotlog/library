package myLibrary.models;

public class Sectiune {
    private int id;
    private Gen gen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gen getGen() {
        return gen;
    }

    public void setGen(Gen gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        return "Sectiune{" +
                "id=" + id +
                ", gen=" + gen +
                '}';
    }
}
