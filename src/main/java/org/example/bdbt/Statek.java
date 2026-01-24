package org.example.bdbt;

public class Statek {
    private int nr_statku;
    private String bandera;
    private String nazwa_statku;
    private int rok_budowy;
    private int tonaz;

    // Zmienione na bezpieczne nazwy (camelCase)
    private String nrImo;
    private int nrBiura;
    private int nrTypuStatku;

    public Statek() {
    }

    public Statek(int nr_statku, String bandera, String nazwa_statku, int rok_budowy, int tonaz, String nrImo, int nrBiura, int nrTypuStatku) {
        this.nr_statku = nr_statku;
        this.bandera = bandera;
        this.nazwa_statku = nazwa_statku;
        this.rok_budowy = rok_budowy;
        this.tonaz = tonaz;
        this.nrImo = nrImo;
        this.nrBiura = nrBiura;
        this.nrTypuStatku = nrTypuStatku;
    }

    // Gettery i Settery
    public int getNr_statku() { return nr_statku; }
    public void setNr_statku(int nr_statku) { this.nr_statku = nr_statku; }

    public String getBandera() { return bandera; }
    public void setBandera(String bandera) { this.bandera = bandera; }

    public String getNazwa_statku() { return nazwa_statku; }
    public void setNazwa_statku(String nazwa_statku) { this.nazwa_statku = nazwa_statku; }

    public int getRok_budowy() { return rok_budowy; }
    public void setRok_budowy(int rok_budowy) { this.rok_budowy = rok_budowy; }

    public int getTonaz() { return tonaz; }
    public void setTonaz(int tonaz) { this.tonaz = tonaz; }

    public String getNrImo() { return nrImo; }
    public void setNrImo(String nrImo) { this.nrImo = nrImo; }

    public int getNrBiura() { return nrBiura; }
    public void setNrBiura(int nrBiura) { this.nrBiura = nrBiura; }

    public int getNrTypuStatku() { return nrTypuStatku; }
    public void setNrTypuStatku(int nrTypuStatku) { this.nrTypuStatku = nrTypuStatku; }
}