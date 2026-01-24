package org.example.bdbt;

public class Ladunek {
    /* Używamy klas (Integer, Double) zamiast typów prostych (int, double),
       aby uniknąć błędów przy pustych polach formularza */
    private Integer nr_ladunku;
    private Double waga_w_tonach;
    private Integer nr_rejsu;
    private Integer id_klienta;
    private Integer nr_typu_ladunku;

    public Ladunek() {
    }

    public Ladunek(Integer nr_ladunku, Double waga_w_tonach, Integer nr_rejsu, Integer id_klienta, Integer nr_typu_ladunku) {
        this.nr_ladunku = nr_ladunku;
        this.waga_w_tonach = waga_w_tonach;
        this.nr_rejsu = nr_rejsu;
        this.id_klienta = id_klienta;
        this.nr_typu_ladunku = nr_typu_ladunku;
    }

    // Gettery i Settery
    public Integer getNr_ladunku() {
        return nr_ladunku;
    }

    public void setNr_ladunku(Integer nr_ladunku) {
        this.nr_ladunku = nr_ladunku;
    }

    public Double getWaga_w_tonach() {
        return waga_w_tonach;
    }

    public void setWaga_w_tonach(Double waga_w_tonach) {
        this.waga_w_tonach = waga_w_tonach;
    }

    public Integer getNr_rejsu() {
        return nr_rejsu;
    }

    public void setNr_rejsu(Integer nr_rejsu) {
        this.nr_rejsu = nr_rejsu;
    }

    public Integer getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(Integer id_klienta) {
        this.id_klienta = id_klienta;
    }

    public Integer getNr_typu_ladunku() {
        return nr_typu_ladunku;
    }

    public void setNr_typu_ladunku(Integer nr_typu_ladunku) {
        this.nr_typu_ladunku = nr_typu_ladunku;
    }

    @Override
    public String toString() {
        return "Ladunek{" +
                "nr_ladunku=" + nr_ladunku +
                ", waga_w_tonach=" + waga_w_tonach +
                ", nr_rejsu=" + nr_rejsu +
                ", id_klienta=" + id_klienta +
                ", nr_typu_ladunku=" + nr_typu_ladunku +
                '}';
    }
}