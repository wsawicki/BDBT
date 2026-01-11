package org.example.bdbt;

public class Statek {
    private int Nr_statku;
    private float bandera;
    private float nazwa_statku;
    private int rok_budowy;
    private int tonaz;

    public Statek() {
        super();
    }

    public Statek(int nr_statku, float bandera, float nazwa_statku, int rok_budowy, int tonaz) {
        Nr_statku = nr_statku;
        this.bandera = bandera;
        this.nazwa_statku = nazwa_statku;
        this.rok_budowy = rok_budowy;
        this.tonaz = tonaz;
    }

    public int getNr_statku() {
        return Nr_statku;
    }

    public void setNr_statku(int nr_statku) {
        Nr_statku = nr_statku;
    }

    public float getBandera() {
        return bandera;
    }

    public void setBandera(float bandera) {
        this.bandera = bandera;
    }

    public float getNazwa_statku() {
        return nazwa_statku;
    }

    public void setNazwa_statku(float nazwa_statku) {
        this.nazwa_statku = nazwa_statku;
    }

    public int getRok_budowy() {
        return rok_budowy;
    }

    public void setRok_budowy(int rok_budowy) {
        this.rok_budowy = rok_budowy;
    }

    public int getTonaz() {
        return tonaz;
    }

    public void setTonaz(int tonaz) {
        this.tonaz = tonaz;
    }

    @Override
    public String toString() {
        return "Statek{" +
                "Nr_statku=" + Nr_statku +
                ", bandera=" + bandera +
                ", nazwa_statku=" + nazwa_statku +
                ", rok_budowy=" + rok_budowy +
                ", tonaz=" + tonaz +
                '}';
    }
}
