package org.example.bdbt;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypLadunkuDAO {

    private final JdbcTemplate jdbcTemplate;

    public TypLadunkuDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TypLadunku> list() {
        // Używamy AS, żeby "przetłumaczyć" nazwę z bazy na nazwę w Javie
        String sql = "SELECT \"Nr_typu_ladunku\", \"Nazwa_typu_ladunku\" AS nazwa_typu FROM \"Typ_ladunku\"";

        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TypLadunku.class));
    }
}