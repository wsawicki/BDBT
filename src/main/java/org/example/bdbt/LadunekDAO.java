package org.example.bdbt;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class LadunekDAO {

    private final JdbcTemplate jdbcTemplate;

    public LadunekDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ladunek> list() {
        String sql = "SELECT * FROM \"Ladunki\" ORDER BY \"Nr_Ladunku\" DESC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ladunek.class));
    }

    public List<Map<String, Object>> listWithClientInfo() {
        String sql = """
                SELECT l."Nr_Ladunku", l."Waga_w_tonach", l."Nr_Rejsu", 
                       l."ID_Klienta", l."Nr_typu_ladunku", 
                       k."Imie", k."Nazwisko", k."Login", k."Typ_Klienta"
                FROM "Ladunki" l
                LEFT JOIN "Klienci" k ON l."ID_Klienta" = k."Nr_Klienta"
                ORDER BY l."Nr_Ladunku" DESC
                """;

        return jdbcTemplate.queryForList(sql);
    }

    public void save(Ladunek ladunek) {
        // Sekwencja do automatycznego numerowania
        String sql = "INSERT INTO \"Ladunki\" " +
                "(\"Nr_Ladunku\", \"Waga_w_tonach\", \"Nr_Rejsu\", \"ID_Klienta\", \"Nr_typu_ladunku\") " +
                "VALUES (seq_nr_ladunku.nextval, :waga_w_tonach, :nr_rejsu, :id_klienta, :nr_typu_ladunku)";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(ladunek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    // NOWA METODA: Usuwanie ładunku (wymagane do zaliczenia projektu)
    public void delete(int nr_ladunku) {
        String sql = "DELETE FROM \"Ladunki\" WHERE \"Nr_Ladunku\" = ?";
        jdbcTemplate.update(sql, nr_ladunku);
    }

    // W klasie LadunekDAO dodaj:
    public List<Ladunek> listByClientId(int clientId) {
        try {
            String sql = "SELECT * FROM \"Ladunki\" WHERE \"ID_Klienta\" = ? ORDER BY \"Nr_Ladunku\" DESC";
            return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Ladunek.class), clientId);
        } catch (Exception e) {
            return new ArrayList<>(); // Zwróć pustą listę w przypadku błędu
        }
    }
}