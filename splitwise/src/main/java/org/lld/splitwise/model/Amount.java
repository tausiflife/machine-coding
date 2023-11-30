package org.lld.splitwise.model;

public class Amount {
    private final double amount;
    private final String currency;

    public Amount(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public Amount add(double amount) {
        final Amount amount1 = new Amount(this.amount + amount, this.currency);
        return amount1;
    }
}
