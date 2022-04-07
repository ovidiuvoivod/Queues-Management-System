package ro.utcluj.controller;

import ro.utcluj.model.Client;
import ro.utcluj.model.Queue;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addClient(List<Queue> queues, Client t) {
        Integer minimumWaitingTime = queues.get(0).getWaitingPeriod();
        Queue queueForAdd = queues.get(0);
        for (Queue a : queues) {
            if (a.getClients().size() == 0) {
                queueForAdd = a;
                break;
            }
            if (minimumWaitingTime > a.getWaitingPeriod()) {
                minimumWaitingTime = a.getWaitingPeriod();
                queueForAdd = a;
            }
        }
        queueForAdd.addClient(t);

    }
}
