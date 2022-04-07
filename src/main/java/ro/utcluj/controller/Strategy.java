package ro.utcluj.controller;

import ro.utcluj.model.Client;
import ro.utcluj.model.Queue;

import java.util.List;

public interface Strategy {
    public void addClient(List<Queue> queues, Client t);
}
