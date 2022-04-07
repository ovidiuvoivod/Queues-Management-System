package ro.utcluj.controller;

import ro.utcluj.model.InputValidator;
import ro.utcluj.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class MainController {
    private static final InputValidator inputValidator=new InputValidator();
    public MainController(MainView mainView){
        mainView.getResetButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.setQueuesField("");
                mainView.setClientsField("");
                mainView.setMinArrivalField("");
                mainView.setMaxArrivalField("");
                mainView.setMinServiceField("");
                mainView.setMaxServiceField("");
                mainView.setSimulationField("");
            }
        });

        mainView.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              try{
                  inputValidator.validator(mainView.getClientsField());
                  inputValidator.validator(mainView.getSimulationField());
                  inputValidator.validator(mainView.getQueuesField());
                  inputValidator.validator(mainView.getMinArrivalField());
                  inputValidator.validator(mainView.getMaxArrivalField());
                  inputValidator.validator(mainView.getMinServiceField());
                  inputValidator.validator(mainView.getMaxServiceField());
                  SimulationManager simulationManager=new SimulationManager(mainView.getClientsField(),mainView.getSimulationField(),
                          mainView.getQueuesField(),mainView.getMinArrivalField(), mainView.getMaxArrivalField(), mainView.getMinServiceField(),
                          mainView.getMaxServiceField());
                  simulationManager.currentTime=0;
                  simulationManager.setMainView(mainView);
                  try {
                      simulationManager.setFileWriter(new FileWriter("log.txt",false));
                  } catch (IOException ex) {
                      ex.printStackTrace();
                  }
                  Thread thread=new Thread(simulationManager);
                  thread.start();
              }catch (Exception e1){
                  JOptionPane.showMessageDialog(new JOptionPane(),"Error! "+e1.getMessage());
              }

            }
        });


    }
}
