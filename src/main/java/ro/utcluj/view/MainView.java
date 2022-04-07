package ro.utcluj.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainView extends JFrame {
    private JLabel timeLabel;
    private JTextField clientsField;
    private JTextField queuesField;
    private JTextField simulationField;
    private JTextField minServiceField;
    private JTextField minArrivalField;
    private JTextField maxServiceField;
    private JTextField maxArrivalField;
    private JButton startButton;
    private JButton resetButton;
    private JTextArea queuesTextArea;
    private JTextArea waitingTextArea;


    public MainView() {
        //frmQueueManagementApp = new JFrame();
        this.setMinimumSize(new Dimension(500, 400));
        this.setTitle("Queue Management App");
        this.setBounds(100, 100, 450, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout(0, 0));

        timeLabel = new JLabel("Time: ");
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(timeLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        this.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel inputsPanel = new JPanel();
        panel.add(inputsPanel);
        inputsPanel.setLayout(new GridLayout(4, 4, 10, 10));

        JLabel clientsLabel = new JLabel("Nr. of clients");
        clientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clientsLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(clientsLabel);

        clientsField = new JTextField();
        inputsPanel.add(clientsField);
        clientsField.setColumns(10);

        JLabel queuesLabel = new JLabel("Nr. of queues");
        queuesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        queuesLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(queuesLabel);

        queuesField = new JTextField();
        inputsPanel.add(queuesField);
        queuesField.setColumns(10);

        JLabel simIntervalLabel = new JLabel("Simulation Interval");
        simIntervalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        simIntervalLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(simIntervalLabel);

        simulationField = new JTextField();
        inputsPanel.add(simulationField);
        simulationField.setColumns(10);

        JLabel minServiceLabel = new JLabel("Min. Service Time");
        minServiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(minServiceLabel);

        minServiceField = new JTextField();
        inputsPanel.add(minServiceField);
        minServiceField.setColumns(10);

        JLabel minArrivalLabel = new JLabel("Min. Arrival Time");
        minArrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        minArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(minArrivalLabel);

        minArrivalField = new JTextField();
        inputsPanel.add(minArrivalField);
        minArrivalField.setColumns(10);

        JLabel maxServiceLabel = new JLabel("Max. Service Time");
        maxServiceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(maxServiceLabel);

        maxServiceField = new JTextField();
        inputsPanel.add(maxServiceField);
        maxServiceField.setColumns(10);

        JLabel maxArrivalLabel = new JLabel("Max. Arrival Time");
        maxArrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        maxArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        inputsPanel.add(maxArrivalLabel);

        maxArrivalField = new JTextField();
        inputsPanel.add(maxArrivalField);
        maxArrivalField.setColumns(10);

        JPanel outputsPanel = new JPanel();
        outputsPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        panel.add(outputsPanel);
        outputsPanel.setLayout(new GridLayout(2, 2, 0, 0));

        JLabel queuesResultLabel = new JLabel("Queues");
        queuesResultLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        queuesResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        queuesResultLabel.setBorder(new EmptyBorder(0, 0, 0, 0));
        outputsPanel.add(queuesResultLabel);

        JScrollPane queuesScrollPane = new JScrollPane();
        outputsPanel.add(queuesScrollPane);

        queuesTextArea = new JTextArea();
        queuesScrollPane.setViewportView(queuesTextArea);

        JLabel waitingLabel = new JLabel("Waiting");
        waitingLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
        waitingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        outputsPanel.add(waitingLabel);

        JScrollPane waitingScrollPane = new JScrollPane();
        outputsPanel.add(waitingScrollPane);

        waitingTextArea = new JTextArea();
        waitingScrollPane.setViewportView(waitingTextArea);

        JPanel buttons = new JPanel();
        this.getContentPane().add(buttons, BorderLayout.SOUTH);
        buttons.setLayout(new GridLayout(2, 2, 0, 0));

        startButton = new JButton("Start");
        buttons.add(startButton);

        resetButton = new JButton("Reset");
        buttons.add(resetButton);
        this.setVisible(true);
    }


    public void setTimeLabel(String timeLabel) {
        this.timeLabel.setText(timeLabel);
    }

    public String getClientsField() {
        return clientsField.getText();
    }

    public String getQueuesField() {
        return queuesField.getText();
    }

    public String getSimulationField() {
        return simulationField.getText();
    }

    public String getMinServiceField() {
        return minServiceField.getText();
    }

    public String getMinArrivalField() {
        return minArrivalField.getText();
    }

    public String getMaxServiceField() {
        return maxServiceField.getText();
    }

    public String getMaxArrivalField() {
        return maxArrivalField.getText();
    }

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public String getQueuesTextArea() {
        return queuesTextArea.getText();
    }

    public void setQueuesTextArea(String queuesTextArea) {
        this.queuesTextArea.setText(queuesTextArea);
    }

    public String getWaitingTextArea() {
        return waitingTextArea.getText();
    }

    public void setWaitingTextArea(String waitingTextArea) {
        this.waitingTextArea.setText(waitingTextArea);
    }

    public void setClientsField(String clientsField) {
        this.clientsField.setText(clientsField);
    }

    public void setQueuesField(String queuesField) {
        this.queuesField.setText(queuesField);
    }

    public void setMinServiceField(String minServiceField) {
        this.minServiceField.setText(minServiceField);
    }

    public void setMinArrivalField(String minArrivalField) {
        this.minArrivalField.setText(minArrivalField);
    }

    public void setMaxServiceField(String maxServiceField) {
        this.maxServiceField.setText(maxServiceField);
    }

    public void setMaxArrivalField(String maxArrivalField) {
        this.maxArrivalField.setText(maxArrivalField);
    }

    public void setSimulationField(String simulationField) {
        this.simulationField.setText(simulationField);
    }
}
