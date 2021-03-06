package com.julio.expensesapp.adapter.output.mysql;

import com.julio.expensesapp.adapter.output.mysql.mapper.ExpenseOccurrenceMapper;
import com.julio.expensesapp.adapter.output.mysql.model.ExpenseOccurrenceModel;
import org.springframework.beans.factory.annotation.Autowired;

import com.julio.expensesapp.domain.ExpenseOccurrence;
import com.julio.expensesapp.usecase.port.ExpenseOccurrenceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ExpenseOccurrenceRepositoryImpl implements ExpenseOccurrenceRepository {

    @Autowired
    private com.julio.expensesapp.adapter.output.mysql.ExpenseOccurrenceRepository occurrenceRepository;

    @Override
    public List<ExpenseOccurrence> findOccurrenceByExpenseId(long expenseId){
        List<ExpenseOccurrenceModel> findByExpenseId = occurrenceRepository.findByExpenseId(expenseId);

        return ExpenseOccurrenceMapper.toEntity(findByExpenseId);
    }

    @Override
    public List<ExpenseOccurrence> findByIdAndDateRealBetween(long expenseId, LocalDateTime from, LocalDateTime to){
        List<ExpenseOccurrenceModel> findByIdAndDateReal = occurrenceRepository.findByIdAndDateRealBetween(expenseId, from, to);

        return ExpenseOccurrenceMapper.toEntity(findByIdAndDateReal);
    }

    @Override
    public ExpenseOccurrence findOccurrenceByUUID(UUID UUID){
        ExpenseOccurrenceModel findByUUID = occurrenceRepository.findByUUID(UUID);

        return ExpenseOccurrenceMapper.toEntity(findByUUID);
    }

    @Override
    public ExpenseOccurrence findOccurrenceById(long id) {
        ExpenseOccurrenceModel findById = occurrenceRepository.findById(id);

        return ExpenseOccurrenceMapper.toEntity(findById);
    }


}
