package com.kira.ira.expensetrackerapi.repo;

import com.kira.ira.expensetrackerapi.api.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CardRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_CARD = "INSERT INTO card (card_name, holder_name) VALUES (?, ?)";
    private static final String UPDATE_CARD = "UPDATE card SET card_name = ?, holder_name = ? WHERE id = ?";
    private static final String DELETE_CARD = "DELETE FROM card WHERE id = ?";
    private static final String FETCH_ALL_CARDS = "SELECT * FROM card";

    public List<Card> findAll() {
        return jdbcTemplate.query(FETCH_ALL_CARDS, new CardRowMapper());
    }

    public void save(Card card) {
        jdbcTemplate.update(INSERT_CARD, card.getCardName(), card.getHolderName());
    }

    public void update(Card card) {
        jdbcTemplate.update(UPDATE_CARD, card.getCardName(), card.getHolderName(), card.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_CARD, id);
    }

    private static class CardRowMapper implements RowMapper<Card> {
        @Override
        public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
            Card card = new Card();
            card.setId(rs.getLong("id"));
            card.setCardName(rs.getString("card_name"));
            card.setHolderName(rs.getString("holder_name"));
            return card;
        }
    }
}