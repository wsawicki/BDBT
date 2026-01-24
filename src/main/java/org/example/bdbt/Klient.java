package org.example.bdbt;

public class Klient {
    private Integer id_klienta;
    private String imie;
    private String nazwisko;
    private String email;
    private String login;
    private String haslo;
    private String typ_klienta; // "OSOBA" lub "FIRMA"
    private String nip;
    private String nazwa_firmy;

    public Klient() {
    }

    // Gettery i Settery
    public Integer getId_klienta() { return id_klienta; }
    public void setId_klienta(Integer id_klienta) { this.id_klienta = id_klienta; }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }

    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getHaslo() { return haslo; }
    public void setHaslo(String haslo) { this.haslo = haslo; }

    public String getTyp_klienta() { return typ_klienta; }
    public void setTyp_klienta(String typ_klienta) { this.typ_klienta = typ_klienta; }

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    public String getNazwa_firmy() { return nazwa_firmy; }
    public void setNazwa_firmy(String nazwa_firmy) { this.nazwa_firmy = nazwa_firmy; }
}