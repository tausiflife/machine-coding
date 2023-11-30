package org.lld.splitwise.service;

import org.lld.splitwise.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GroupService {
    private final ExpenseService expenseService;
    private final UserService userService;
    private final Map<String, Group> groups;
    public GroupService(ExpenseService expenseService, UserService userService) {
        this.expenseService = expenseService;
        this.userService = userService;
        groups = new HashMap<>();
    }

    public GroupService(ExpenseService expenseService, UserService userService, Map<String, Group> map) {
        this.expenseService = expenseService;
        this.userService = userService;
        groups = map;
    }
    public PaymentGraph getGroupPaymentGraph(final String groupId, final String userId) {
        BalanceMap finalExpense = getBalances(groupId, userId);
        return expenseService.getPaymentGraph(finalExpense);
    }

    public BalanceMap getBalances (final String groupId, final String userId) {
        if(!groups.get(groupId).getUsers().contains(userId))
            throw new IllegalArgumentException();
        return sumAllExpense(expenseService.getGroupExpense(groupId));
    }

    private BalanceMap sumAllExpense(List<Expense> groupExpenses) {
        Map<User, Amount> resultBalances = new HashMap<>();
        for(Expense expense : groupExpenses) {
            for (var balance : expense.getUserBalances().getBalances().entrySet()) {
                final var user = balance.getKey();
                final var amount = balance.getValue();
                var existingAmount = resultBalances.getOrDefault(user, new Amount(0, "USD"));
                var newAmount = existingAmount.add(amount.getAmount());
                resultBalances.put(user, newAmount);
            }
        }
        return new BalanceMap(resultBalances);
    }
}
