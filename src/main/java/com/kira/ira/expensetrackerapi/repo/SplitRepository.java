package com.kira.ira.expensetrackerapi.repo;

import com.kira.ira.expensetrackerapi.api.model.Split;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SplitRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_SPLIT =
            "INSERT INTO split (expense_id, split_months, monthly_amount, start_date, end_date) " +
                    "VALUES (?, ?, ?, ?, ?)";

    private static final String FETCH_SPLITS_BY_EXPENSE =
            "SELECT * FROM split WHERE expense_id = ?";

    private static final String DELETE_SPLIT_BY_EXPENSE =
            "DELETE FROM split WHERE expense_id = ?";

    public void save(Split split) {
        jdbcTemplate.update(
                INSERT_SPLIT,
                split.getExpenseId(),
                split.getSplitMonths(),
                split.getMonthlyAmount(),
                split.getStartDate(),
                split.getEndDate()
        );
    }

    public List<Split> findByExpenseId(Long expenseId) {
        return jdbcTemplate.query(FETCH_SPLITS_BY_EXPENSE, new SplitRowMapper(), expenseId);
    }

    public void deleteByExpenseId(Long expenseId) {
        jdbcTemplate.update(DELETE_SPLIT_BY_EXPENSE, expenseId);
    }

    private static class SplitRowMapper implements RowMapper<Split> {
        @Override
        public Split mapRow(ResultSet rs, int rowNum) throws SQLException {
            Split split = new Split();
            split.setId(rs.getLong("id"));
            split.setExpenseId(rs.getLong("expense_id"));
            split.setSplitMonths(rs.getInt("split_months"));
            split.setMonthlyAmount(rs.getDouble("monthly_amount"));
            split.setStartDate(rs.getDate("start_date").toLocalDate());
            split.setEndDate(rs.getDate("end_date").toLocalDate());
            return split;
        }
    }
}