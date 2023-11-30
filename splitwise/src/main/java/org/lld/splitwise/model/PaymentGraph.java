package org.lld.splitwise.model;

import java.util.Map;

public class PaymentGraph {
    private final Map<User, BalanceMap> graph;

    public PaymentGraph(Map<User, BalanceMap> graph) {
        this.graph = graph;
    }

    public Map<User, BalanceMap> getGraph() {
        return graph;
    }
}
