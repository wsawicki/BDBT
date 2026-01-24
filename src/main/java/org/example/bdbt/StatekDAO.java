package org.example.bdbt;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatekDAO {

    private final JdbcTemplate jdbcTemplate;

    public StatekDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Statek> list(){
        /* WAŻNE: Używamy aliasów (AS), aby mapować kolumny z bazy (np. "Nr_IMO")
           na nowe nazwy w Javie ("nrImo").
        */
        String sql = "SELECT " +
                "\"Nr_Statku\" AS nr_statku, " +
                "\"Bandera\" AS bandera, " +
                "\"Nazwa_statku\" AS nazwa_statku, " +
                "\"Rok_Budowy\" AS rok_budowy, " +
                "\"Tonaz\" AS tonaz, " +
                "\"Nr_IMO\" AS nrImo, " +
                "\"Nr_Biura\" AS nrBiura, " +
                "\"Nr_typu_statku\" AS nrTypuStatku " +
                "FROM \"Statki\"";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Statek.class));
    }

    public void save(Statek statek) {
        String sql = "INSERT INTO \"Statki\" " +
                "(\"Nr_Statku\", \"Bandera\", \"Nazwa_statku\", \"Rok_Budowy\", \"Tonaz\", \"Nr_IMO\", \"Nr_Biura\", \"Nr_typu_statku\") " +
                "VALUES " +
                "(:nr_statku, :bandera, :nazwa_statku, :rok_budowy, :tonaz, :nrImo, :nrBiura, :nrTypuStatku)";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(statek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void update(Statek statek) {
        String sql = "UPDATE \"Statki\" SET \"Bandera\" = :bandera, \"Nazwa_statku\" = :nazwa_statku, " +
                "\"Rok_Budowy\" = :rok_budowy, \"Tonaz\" = :tonaz, " +
                "\"Nr_IMO\" = :nrImo, \"Nr_Biura\" = :nrBiura, \"Nr_typu_statku\" = :nrTypuStatku " +
                "WHERE \"Nr_Statku\" = :nr_statku";

        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(statek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }

    public void delete(int nr_statku){
        String sql = "DELETE FROM \"Statki\" WHERE \"Nr_Statku\" = ?";
        jdbcTemplate.update(sql, nr_statku);
    }
}