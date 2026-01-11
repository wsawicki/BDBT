package org.example.bdbt;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StatekDAO {


    private final JdbcTemplate jdbcTemplate;

    public StatekDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Statek> list(){
        String sql = "SELECT * FROM STATKI";

        List<Statek> listStatek = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Statek.class));
        return listStatek;
    }

    public void save(Statek statek) {
        SimpleJdbcInsert insertActor = new SimpleJdbcInsert(jdbcTemplate);
        insertActor.withTableName("sales").usingColumns("id","item","quantity","amount");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(statek);
        insertActor.execute(param);
    }

    public Statek get(int nr_statku){
        return null;
    }
    public void update(Statek statek) {
        String sql = "UPDATE SALES SET bandera = '" + statek.getBandera() + "', nazwa_statku = " + statek.getNazwa_statku()
                + ", bandera = " + statek.getBandera() + " where Nr_statku = " + statek.getNr_statku();
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(statek);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }
    public void delete(int nr_statku){
        String sql = "DELETE FROM STATKI WHERE nr_statku = ?";
        jdbcTemplate.update(sql,nr_statku);
    }
}
