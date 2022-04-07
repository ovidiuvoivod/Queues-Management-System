package ro.utcluj.controller;

import ro.utcluj.model.Client;
import ro.utcluj.model.Queue;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addClient(List<Queue> queues, Client t) {
        int size = queues.get(0).getClients().size();
        Queue queueForAdd = queues.get(0);
        for (Queue a : queues) {
            if (size > a.getClients().size()) {
                size = a.getClients().size();
                queueForAdd = a;
            }
        }
        queueForAdd.addClient(t);
    }
}
