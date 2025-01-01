package com.kira.ira.expensetrackerapi.repo;

import com.kira.ira.expensetrackerapi.api.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class ExpenseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_EXPENSE = "INSERT INTO expense (merchant_id, card_id, transaction_date, month, year, price, is_split, will_refund) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    private static final String UPDATE_EXPENSE = "UPDATE expense SET merchant_id = ?, card_id = ?, transaction_date = ?, month = ?, year = ?, price = ? WHERE id = ?";
    private static final String DELETE_EXPENSE = "DELETE FROM expense WHERE id = ?";
    private static final String FETCH_ALL_EXPENSES = "SELECT " +
            "e.id AS expense_id, " +
            "e.transaction_date, " +
            "e.month, " +
            "e.year, " +
            "e.price, " +
            "e.merchant_id, " +
            "e.card_id, " +
            "e.will_refund, " +
            "m.name AS merchant_name, " +
            "c.card_name AS card_name " +
            "FROM expense e " +
            "JOIN merchant m ON e.merchant_id = m.id " +
            "JOIN card c ON e.card_id = c.id";

    public List<Expense> findAll() {
        return jdbcTemplate.query(FETCH_ALL_EXPENSES, new ExpenseRowMapper());
    }

    public Long save(Expense expense) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(INSERT_EXPENSE, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, expense.getMerchantId());
            ps.setLong(2, expense.getCardId());
            ps.setString(3, expense.getTransactionDate());
            ps.setInt(4, expense.getMonth());
            ps.setInt(5, expense.getYear());
            ps.setDouble(6, expense.getPrice());
            ps.setBoolean(7, expense.getIsSplit());
            ps.setBoolean(8, expense.isWillRefund());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue(); // Return the generated ID
    }

    public void update(Expense expense) {
        jdbcTemplate.update(UPDATE_EXPENSE, expense.getMerchantId(), expense.getCardId(), expense.getTransactionDate(),
                expense.getMonth(), expense.getYear(), expense.getPrice(), expense.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update(DELETE_EXPENSE, id);
    }

    private static class ExpenseRowMapper implements RowMapper<Expense> {
        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setId(rs.getLong("expense_id"));
            expense.setMerchantId(rs.getLong("merchant_id"));
            expense.setCardId(rs.getLong("card_id"));
            expense.setMerchantName(rs.getString("merchant_name"));
            expense.setCardName(rs.getString("card_name"));
            expense.setTransactionDate(rs.getString("transaction_date"));
            expense.setMonth(rs.getInt("month"));
            expense.setYear(rs.getInt("year"));
            expense.setPrice(rs.getDouble("price"));
            expense.setWillRefund(rs.getBoolean("will_refund"));
            return expense;
        }
    }
}