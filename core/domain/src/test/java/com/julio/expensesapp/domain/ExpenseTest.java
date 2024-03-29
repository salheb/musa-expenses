package com.julio.expensesapp.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class ExpenseTest {
    @Test
    void shouldReturnValuesWhenExpenseWasCreated(){
        var now = ZonedDateTime.now();
        var expense = Expense.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .expenseType(1)
                .expenseDescription("Morning breakfast at Danny's")
                .value(BigDecimal.valueOf(29.90))
                .date(now)
                .recurrence(0)
                .beWarned(true)
                .user(User.builder()
                        .id(1)
                        .authMethod(AuthMethod.GOOGLE)
                        .mail("julio.salheb@gmail.com")
                        .token("random token")
                        .tokenUpdatedAt(now)
                        .build())
                .build();
        assertThat(expense.getId()).isEqualTo(1);
        assertThat(expense.getValue()).isEqualTo(BigDecimal.valueOf(29.90));
        assertThat(expense.getDate()).isEqualTo(now);
    }

    @Test
    void shouldThrowInvalidExpenseException(){
        var now = ZonedDateTime.now();
        var builder = Expense.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .expenseType(1)
                .expenseDescription(" ")
                .value(BigDecimal.valueOf(29.90))
                .date(now)
                .recurrence(0)
                .user(User.builder()
                        .id(1)
                        .authMethod(AuthMethod.GOOGLE)
                        .mail("julio.salheb@gmail.com")
                        .token("random token")
                        .tokenUpdatedAt(now)
                        .build())
                .beWarned(true);

        assertThatThrownBy(() -> builder.build()).hasMessage("Expense must have a description.");
    }

    @Test
    void shouldThrowInvalidExpenseValueException(){
        var now = ZonedDateTime.now();
        var builder = Expense.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .expenseType(1)
                .expenseDescription("A")
                .value(BigDecimal.valueOf(0))
                .date(now)
                .recurrence(0)
                .user(User.builder()
                        .id(1)
                        .authMethod(AuthMethod.GOOGLE)
                        .mail("julio.salheb@gmail.com")
                        .token("random token")
                        .tokenUpdatedAt(now)
                        .build())
                .beWarned(true);

        assertThatThrownBy(() -> builder.build()).hasMessage("Expense must have a value.");
    }

    @Test
    void shouldThrowNullPointerException(){
        var now = ZonedDateTime.now();
        var builder = Expense.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .expenseType(1)
                .expenseDescription("A")
                .value(BigDecimal.valueOf(29.89))
                .recurrence(0)
                .user(User.builder()
                        .id(1)
                        .authMethod(AuthMethod.GOOGLE)
                        .mail("julio.salheb@gmail.com")
                        .token("random token")
                        .tokenUpdatedAt(now)
                        .build())
                .beWarned(true);

        assertThatThrownBy(() -> builder.build()).hasMessage("Expense Date can't be null.");
    }

    @Test
    void shouldThrowInvalidExpenseUser(){
        var now = ZonedDateTime.now();
        var builder = Expense.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .expenseType(1)
                .expenseDescription("A")
                .value(BigDecimal.valueOf(29.89))
                .recurrence(0)
                .date(now)
                .beWarned(true);

        assertThatThrownBy(() -> builder.build()).hasMessage("Expense user can't be identified.");
    }
}
