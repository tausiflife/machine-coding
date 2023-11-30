package org.lld.splitwise.service;

import org.lld.splitwise.model.*;

import java.util.*;

public class ExpenseService {
    private final Map<String, List<Expense>> groupExpense;

    public ExpenseService(Map<String, List<Expense>> groupExpense) {
        this.groupExpense = groupExpense;
    }

    public List<Expense> getGroupExpense(String groupId) {
        return groupExpense.get(groupId);
    }

    public PaymentGraph getPaymentGraph(BalanceMap groupBalances) {
        final var graph = new HashMap<User,BalanceMap>();
        PriorityQueue<Map.Entry<User, Amount>> positiveAmounts
                = new PriorityQueue<>((first, second) ->
                (int) (second.getValue().getAmount() - first.getValue().getAmount()));
        PriorityQueue<Map.Entry<User, Amount>> negativeAmounts
                = new PriorityQueue<>((first, second) ->
                (int) (first.getValue().getAmount() - second.getValue().getAmount()));
        for(var balance : groupBalances.getBalances().entrySet()) {
            if(balance.getValue().getAmount() > 0)
                positiveAmounts.add(balance);
            else
                negativeAmounts.add(balance);
        }
        while(!positiveAmounts.isEmpty() && !negativeAmounts.isEmpty()) {
            //take largest from both the groups
            final var largestPositive = positiveAmounts.poll();
            final var largestNegative = negativeAmounts.poll();
            final var negativeAmount = largestNegative.getValue().getAmount();
            final var positiveAmount = largestPositive.getValue().getAmount();
            final var largestPositiveUserBalances = graph.getOrDefault(largestPositive.getKey(), new BalanceMap());
            largestPositiveUserBalances.getBalances()
                    .put(largestNegative.getKey(),
                            new Amount(Math.min(positiveAmount, Math.abs(negativeAmount)),"USD"));
            graph.put(largestPositive.getKey(), largestPositiveUserBalances);
            final var remaining = positiveAmount - Math.abs(negativeAmount);
            if(remaining > 0) {
                positiveAmounts.add(new AbstractMap.SimpleEntry<>(largestPositive.getKey(),
                        new Amount(remaining,"USD")));
            } else if (remaining < 0){
                negativeAmounts.add(new AbstractMap.SimpleEntry<>(largestNegative.getKey(),
                        new Amount(remaining,"USD")));
            }
        }
        return new PaymentGraph(graph);
    }
}
