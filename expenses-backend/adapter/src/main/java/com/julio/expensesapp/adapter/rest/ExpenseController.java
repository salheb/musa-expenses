package com.julio.expensesapp.adapter.rest;

import com.julio.expensesapp.adapter.rest.dto.ExpenseDto;
import com.julio.expensesapp.adapter.rest.mapper.ExpenseMapper;

import com.julio.expensesapp.entities.Expense;
import com.julio.expensesapp.usecases.expense.FindExpenseByDateBetweenUseCase;
import com.julio.expensesapp.usecases.expense.FindExpenseByIdUseCase;
import com.julio.expensesapp.usecases.expense.FindExpenseByUUIDUseCase;
import com.julio.expensesapp.usecases.expense.SaveExpenseUseCase;
import com.julio.expensesapp.usecases.expense.DeleteExpenseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("")
public class ExpenseController {

    @Autowired
    private FindExpenseByDateBetweenUseCase findExpenseByDateBetween;

    @Autowired
    private FindExpenseByUUIDUseCase findExpenseByUUIDUseCase;

    @Autowired
    private SaveExpenseUseCase saveExpenseUseCase;
    
    @Autowired
    private DeleteExpenseUseCase deleteExpenseUseCase;

    // TODO add swagger/openapi support
    @GetMapping(value = {"/expenses/"})
    public ResponseEntity<List<ExpenseDto>> find(@RequestParam(required = false) String dateFrom,
                                                      @RequestParam(required = false) String dateTo){
        List<Expense> expenses = findExpenseByDateBetween.find(dateFrom, dateTo);

        return new ResponseEntity<>(ExpenseMapper.toDto(expenses), HttpStatus.OK);
    }

    @GetMapping(value = {"/expenses/{id}"})
    public ResponseEntity<List<ExpenseDto>> find(@PathVariable UUID id){
        Expense expense = findExpenseByUUIDUseCase.find(id);

        ResponseEntity<List<ExpenseDto>> response;
        if (expense == null)
            response = new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        else
        {
            List<ExpenseDto> expenses = new ArrayList<>();
            expenses.add(ExpenseMapper.toDto(expense));

            response = new ResponseEntity<>(expenses, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping(path = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ExpenseDto add(@RequestBody List<ExpenseDto> expenseDto)
    {
        Expense expense = ExpenseMapper.toEntity(expenseDto.get(0));
        expense = saveExpenseUseCase.save(expense);

        return ExpenseMapper.toDto(expense);
    }

    @PutMapping(path = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ExpenseDto update(@RequestBody List<ExpenseDto> expenseDto){
        Expense expense = ExpenseMapper.toEntity(expenseDto.get(0));
        expense = saveExpenseUseCase.save(expense);

        return ExpenseMapper.toDto(expense);
    }

    @DeleteMapping(path = "/expenses/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@RequestBody UUID uuid){
        deleteExpenseUseCase.delete(uuid);
    }

}