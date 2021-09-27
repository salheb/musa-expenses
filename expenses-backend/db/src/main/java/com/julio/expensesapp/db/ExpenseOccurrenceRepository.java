package com.julio.expensesapp.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.julio.expensesapp.db.model.ExpenseOccurrenceModel;

@Repository
public interface ExpenseOccurrenceRepository extends JpaRepository<ExpenseOccurrenceModel, Long> {

    List<ExpenseOccurrenceModel> findByExpenseId(Long expenseId);

    List<ExpenseOccurrenceModel> findByIdAndDateRealBetween(Long expenseId, LocalDateTime from, LocalDateTime to);

    ExpenseOccurrenceModel findByUUID(UUID UUID);

    ExpenseOccurrenceModel findById(long id);

}
