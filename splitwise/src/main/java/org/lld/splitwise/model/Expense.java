package org.lld.splitwise.model;

public class Expense {
    private final String id;
    private final BalanceMap userBalances;
    private final String desc;

    public Expense(String id, BalanceMap balances, String desc) {
        this.id = id;
        this.userBalances = balances;
        this.desc = desc;
    }

    public Expense(String id, String desc) {
        this.id = id;
        this.userBalances = new BalanceMap();
        this.desc = desc;
    }
    public String getId() {
        return id;
    }

    public BalanceMap getUserBalances() {
        return userBalances;
    }

    public String getDesc() {
        return desc;
    }
}
