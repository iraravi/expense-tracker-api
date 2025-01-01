package com.kira.ira.expensetrackerapi.repo;

import com.kira.ira.expensetrackerapi.api.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MerchantRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_MERCHANT = "INSERT INTO merchant (name) VALUES (?)";
    private static final String UPDATE_MERCHANT = "UPDATE merchant SET name = ? WHERE id = ?";
    private static final String DELETE_MERCHANT = "DELETE FROM merchant WHERE id = ?";
    private static final String FETCH_ALL_MERCHANTS = "SELECT * FROM merchant";

    public List<Merchant> findAll() {
        return jdbcTemplate.query(FETCH_ALL_MERCHANTS, new MerchantRowMapper());
    }

    public void save(Merchant merchant) {
        jdbcTemplate.update(INSERT_MERCHANT, merchant.getName());
    }

    public void update(Merchant merchant) {
        jdbcTemplate.update(UPDATE_MERCHANT, merchant.getName(), merchant.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_MERCHANT, id);
    }

    private static class MerchantRowMapper implements RowMapper<Merchant> {
        @Override
        public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
            Merchant merchant = new Merchant();
            merchant.setId(rs.getLong("id"));
            merchant.setName(rs.getString("name"));
            return merchant;
        }
    }
}