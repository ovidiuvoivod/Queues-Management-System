package ro.utcluj.model;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable, Comparable<Queue> {
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;


    public Queue() {
        clients = new LinkedBlockingDeque<>();
        waitingPeriod = new AtomicInteger();
        this.waitingPeriod.set(0);
    }

    public void addClient(Client client) {
        clients.add(client);
        this.waitingPeriod.set(waitingPeriod.get() + client.getServiceTime());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client client = clients.peek();
                if (client != null) {
                    while (!client.getServiceTime().equals(0)) {
                        Thread.sleep(1000);
                        client.setServiceTime(client.getServiceTime() - 1);
                        this.waitingPeriod.set(this.waitingPeriod.decrementAndGet());
                    }
                    clients.take();
                    this.waitingPeriod.set(this.waitingPeriod.decrementAndGet());


                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public Integer getWaitingPeriod() {
        return waitingPeriod.get();
    }

    public String toString() {
        String output = "";

        for (Client client : clients) {
            if (output.equals("")) {
                output = client.toString();
            } else {
                output = output + ";" + client.toString();
            }
        }
        return output;

    }

    @Override
    public int compareTo(Queue o) {
        if (this.waitingPeriod.get() < o.waitingPeriod.get()) return -1;
        else return 1;
    }

    public int getClientsInQueue() {
        return clients.size();
    }
}
