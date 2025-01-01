package com.kira.ira.expensetrackerapi.api;

import com.kira.ira.expensetrackerapi.api.model.Expense;
import com.kira.ira.expensetrackerapi.api.model.Split;
import com.kira.ira.expensetrackerapi.repo.ExpenseRepository;
import com.kira.ira.expensetrackerapi.repo.SplitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private static final Logger log = LoggerFactory.getLogger(ExpenseController.class);
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private SplitRepository splitRepository;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addExpense(@RequestBody Expense expense) {
        log.info("Adding expense: {}", expense);

        // Parse and set month and year from transactionDate
        if (expense.getTransactionDate() != null) {
            LocalDate transactionDate = LocalDate.parse(expense.getTransactionDate());
            expense.setMonth(transactionDate.getMonthValue());
            expense.setYear(transactionDate.getYear());
        }

        // Save expense and retrieve generated ID
        Long expenseId = expenseRepository.save(expense);

        // Handle split if applicable
        if (expense.getIsSplit()) {
            Split split = new Split();
            split.setExpenseId(expenseId); // Use the generated ID
            split.setSplitMonths(expense.getSplitMonths());
            split.setMonthlyAmount(expense.getPrice() / expense.getSplitMonths());

            LocalDate startDate = LocalDate.parse(expense.getTransactionDate());
            split.setStartDate(startDate);
            split.setEndDate(startDate.plusMonths(expense.getSplitMonths() - 1));

            splitRepository.save(split); // Save split details
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Expense added successfully");
        response.put("id", expenseId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteExpense(@PathVariable Long id) {
        expenseRepository.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Expense deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expense.setId(id);
        expenseRepository.update(expense);
        return ResponseEntity.ok("Expense updated successfully");
    }
}