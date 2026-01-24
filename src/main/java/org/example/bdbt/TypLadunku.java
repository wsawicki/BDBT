package org.example.bdbt;

public class TypLadunku {
    private int nr_typu_ladunku;
    private String nazwa_typu;

    public TypLadunku() {
    }

    public TypLadunku(int nr_typu_ladunku, String nazwa_typu) {
        this.nr_typu_ladunku = nr_typu_ladunku;
        this.nazwa_typu = nazwa_typu;
    }

    public int getNr_typu_ladunku() {
        return nr_typu_ladunku;
    }

    public void setNr_typu_ladunku(int nr_typu_ladunku) {
        this.nr_typu_ladunku = nr_typu_ladunku;
    }

    public String getNazwa_typu() {
        return nazwa_typu;
    }

    public void setNazwa_typu(String nazwa_typu) {
        this.nazwa_typu = nazwa_typu;
    }
}