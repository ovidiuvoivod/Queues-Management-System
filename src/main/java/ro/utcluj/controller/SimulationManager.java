package ro.utcluj.controller;

import ro.utcluj.model.Client;
import ro.utcluj.model.SelectionPolicy;
import ro.utcluj.view.MainView;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {
    private final Integer numberOfClients;
    private Integer numberOfQueues;
    private Integer simulationInterval;
    private final Integer minimumArrivalTime;
    private final Integer maximumArrivalTime;
    private final Integer minimumServiceTime;
    private final Integer maximumServiceTime;
    private final SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;
    private MainView mainView;
    private final Scheduler scheduler;
    private List<Client> generatedClients;
    private FileWriter fileWriter;

    public SimulationManager(String numberOfClients, String simulationInterval, String numberOfQueues, String minimumArrivalTime,
                             String maximumArrivalTime, String minimumServiceTime, String maximumServiceTime) {
        this.numberOfClients = Integer.parseInt(numberOfClients);
        this.numberOfQueues = Integer.parseInt(numberOfQueues);
        this.simulationInterval = Integer.parseInt(simulationInterval);
        this.minimumArrivalTime = Integer.parseInt(minimumArrivalTime);
        this.maximumArrivalTime = Integer.parseInt(maximumArrivalTime);
        this.minimumServiceTime = Integer.parseInt(minimumServiceTime);
        this.maximumServiceTime = Integer.parseInt(maximumServiceTime);
        scheduler = new Scheduler(this.numberOfQueues);
        generateNRandomClients();
        scheduler.changeStrategy(selectionPolicy);
    }

    private void generateNRandomClients() {
        generatedClients = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = generateRandom(this.minimumArrivalTime, this.maximumArrivalTime);
            int serviceTime = generateRandom(this.minimumServiceTime, this.maximumServiceTime);
            Client client = new Client(i + 1, arrivalTime, serviceTime);
            generatedClients.add(client);
        }
        Collections.sort(generatedClients);

    }

    private int generateRandom(Integer lowerBound, Integer upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }

    int currentTime;
    boolean ok = true;

    @Override
    public void run() {
        currentTime = 0;
        Double avServTime = getAverageServiceTime();
        List<Client> clientsToRemove = new ArrayList<>();
        int numberOfClientsInQueues = 0;
        int time = 0;
        Double averageWaitingTime = 0.0;
        mainView.getStartButton().setEnabled(false);
        while (currentTime <= simulationInterval && ok) {
            for (Client client : generatedClients) {
                if (client.getArrivalTime().equals(currentTime)) {
                    scheduler.dispatchTask(client);
                    clientsToRemove.add(client);
                }
            }
            generatedClients.removeAll(clientsToRemove);
            mainView.setWaitingTextArea(this.toString());
            mainView.setQueuesTextArea(scheduler.toString());
            averageWaitingTime += scheduler.getNumberOfClientsWaitingInQueue();
            if (scheduler.noMoreClientsLeftInQueues() && generatedClients.size() == 0) {
                ok = false;
            }
            if (numberOfClientsInQueues < scheduler.getNumberOfClientsInQueue()) {
                numberOfClientsInQueues = scheduler.getNumberOfClientsInQueue();
                time = currentTime;
            }
            try {
                fileWriter.write(this.output());
                mainView.setTimeLabel("Time: " + currentTime);
                currentTime++;
                Thread.sleep(1000);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
        averageWaitingTime = averageWaitingTime / numberOfClients;
        mainView.getStartButton().setEnabled(true);
        try {
            fileWriter.write("Average waiting time:" + averageWaitingTime + "\n"
                    + "Average service time:" + avServTime + "\nPeak time:" + time + " with " + numberOfClientsInQueues + " clients");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double getAverageServiceTime() {
        Double averageServiceTime = 0.0;
        for (Client client : generatedClients) {
            averageServiceTime += client.getServiceTime();
        }
        return averageServiceTime / generatedClients.size();
    }

    public void setFileWriter(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public String toString() {
        String output = "";
        for (Client client : generatedClients) {
            if (output.equals("")) {
                output = client.toString();
            } else {
                output = output + ";" + client.toString();
            }

        }
        return output;
    }

    public String output() {
        return "\nTime: " + currentTime + "\n" + mainView.getQueuesTextArea() + "Waiting: " + mainView.getWaitingTextArea() + "\n";
    }
}
