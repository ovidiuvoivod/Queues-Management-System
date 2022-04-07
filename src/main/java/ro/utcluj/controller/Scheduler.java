package ro.utcluj.controller;

import ro.utcluj.model.Client;
import ro.utcluj.model.Queue;
import ro.utcluj.model.SelectionPolicy;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Queue> queues;
    private Strategy strategy;
    private Double waitingTime = 0.0;
    private Double clientsProcessed = 0.0;

    public Scheduler(int maxNoOfQueues) {
        queues = new ArrayList<>();
        for (int i = 0; i < maxNoOfQueues; i++) {
            Queue queue = new Queue();
            queues.add(queue);
            Thread thread = new Thread(queue);
            thread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        }
        if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void dispatchTask(Client client) {
        strategy.addClient(queues, client);
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public Double getWaitingTime() {
        return waitingTime;
    }

    public Double getClientsProcessed() {
        return clientsProcessed;
    }

    public String toString() {
        String output = "";
        for (Queue queue : queues) {
            output = output + "Queue" + (queues.indexOf(queue) + 1) + ":" + queue.toString() + '\n';
        }
        return output;
    }

    public boolean noMoreClientsLeftInQueues() {
        for (Queue queue : queues) {
            if (queue.getClients().size() != 0) {
                return false;
            }
        }
        return true;
    }

    public int getNumberOfClientsInQueue() {
        int clientsInQueue = 0;
        for (Queue queue : queues) {
            clientsInQueue += queue.getClientsInQueue();
        }
        return clientsInQueue;
    }

    public int getNumberOfClientsWaitingInQueue() {
        int clientsWaitingInQueue = 0;
        for (Queue queue : queues) {
            int clientsInQueue = queue.getClientsInQueue();
            if (clientsInQueue > 1) {
                clientsWaitingInQueue += clientsInQueue - 1;
            }
        }
        return clientsWaitingInQueue;
    }

}
