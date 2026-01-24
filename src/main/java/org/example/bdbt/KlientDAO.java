package org.example.bdbt;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class KlientDAO {

    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public KlientDAO(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(Klient klient) {
        // Szyfrowanie hasła (bez zmian)
        String encodedPassword = passwordEncoder.encode(klient.getHaslo());
        klient.setHaslo(encodedPassword);

        // ZMIANA: Usuwamy ręczne pobieranie ID.
        // Wstawiamy "seq_klienci.nextval" (lub odpowiednią nazwę Twojej sekwencji) bezpośrednio w SQL.

        String sql = "INSERT INTO \"Klienci\" " +
                "(\"Nr_Klienta\", \"Imie\", \"Nazwisko\", \"Email_kontaktowy\", \"Login\", \"Haslo\", \"Typ_Klienta\", \"NIP\", \"Nazwa_firmy\") " +
                "VALUES (seq_klienci.nextval, :imie, :nazwisko, :email, :login, :haslo, :typ_klienta, :nip, :nazwa_firmy)";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(klient);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }
    // NOWA METODA: Pobierz ID klienta po loginie
    public Integer getIdByLogin(String login) {
        try {
            String sql = "SELECT \"Nr_Klienta\" FROM \"Klienci\" WHERE \"Login\" = ?";
            return jdbcTemplate.queryForObject(sql, Integer.class, login);
        } catch (Exception e) {
            return null; // Jeśli użytkownik nie istnieje
        }
    }


}