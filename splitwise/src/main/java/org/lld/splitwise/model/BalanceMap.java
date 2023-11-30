package org.lld.splitwise.model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BalanceMap {
    //map of every user of how much they need to pay other user
    private final Map<User, Amount> balances;

    public BalanceMap(Map<User, Amount> resultBalances) {
        balances = resultBalances;
    }

    public BalanceMap() {
        balances = new HashMap<>();
    }

    public Map<User, Amount> getBalances() {
        return balances;
    }
}
