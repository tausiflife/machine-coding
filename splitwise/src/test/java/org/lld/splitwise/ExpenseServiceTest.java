package org.lld.splitwise;

import org.junit.Assert;
import org.junit.Test;
import org.lld.splitwise.model.*;
import org.lld.splitwise.service.ExpenseService;
import org.lld.splitwise.service.GroupService;
import org.lld.splitwise.service.UserService;

import java.util.*;

public class ExpenseServiceTest {
    @Test
    public void defaultTest() {
        User a = new User("1", "A");
        User b = new User("2", "B");
        User c = new User("3", "C");
        final var currency = "USD";
        var groupExpense = new HashMap<String, List<Expense>>();
        var expenses = new ArrayList<Expense>();
        var firstExpense = new Expense(UUID.randomUUID().toString(), "food");
        firstExpense.getUserBalances().getBalances().put(a, new Amount(10, currency));
        firstExpense.getUserBalances().getBalances().put(b, new Amount(20, currency));
        firstExpense.getUserBalances().getBalances().put(c, new Amount(-30, currency));
        expenses.add(firstExpense);

        var secondExpense = new Expense(UUID.randomUUID().toString(), "outing");
        secondExpense.getUserBalances().getBalances().put(a, new Amount(-50, currency));
        secondExpense.getUserBalances().getBalances().put(b, new Amount(10, currency));
        secondExpense.getUserBalances().getBalances().put(c, new Amount(40, currency));
        expenses.add(secondExpense);

        var thirdExpense = new Expense(UUID.randomUUID().toString(), "eating");
        thirdExpense.getUserBalances().getBalances().put(a, new Amount(90, currency));
        thirdExpense.getUserBalances().getBalances().put(b, new Amount(0, currency));
        thirdExpense.getUserBalances().getBalances().put(c, new Amount(-90, currency));
        expenses.add(thirdExpense);
        groupExpense.putIfAbsent("G1", expenses);
        var expenseService = new ExpenseService(groupExpense);
        var userService = new UserService();
        var group= new Group("G1", "Euro trip", Arrays.asList("1", "2", "3"));
        var groupMap = new HashMap<String, Group>();
        groupMap.put("G1", group);
        var groupService = new GroupService(expenseService, userService, groupMap);
        var balanceMap = groupService.getBalances("G1", "1");
        final double DELTA = 1e-15;
        Assert.assertEquals(50, balanceMap.getBalances().get(a).getAmount(), DELTA);
        Assert.assertEquals(30, balanceMap.getBalances().get(b).getAmount(), DELTA);
        Assert.assertEquals(-80, balanceMap.getBalances().get(c).getAmount(), DELTA);
        PaymentGraph paymentGraph = expenseService.getPaymentGraph(balanceMap);
        System.out.println(paymentGraph);
        Assert.assertEquals(50, paymentGraph.getGraph().get(a).getBalances().get(c).getAmount(), DELTA );
        Assert.assertEquals(30, paymentGraph.getGraph().get(b).getBalances().get(c).getAmount(), DELTA );
    }
}
