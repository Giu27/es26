package it.itisgferraris;

import java.awt.*;
import javax.swing.*;

public class Interfaccia extends Thread{
    private final Simulazione simulazione;
    private final Data data;
    private JFrame frame;

    public Interfaccia(Simulazione simulazione, Data data) {
        super();
        this.simulazione = simulazione;
        this.data = data;
        frame = new JFrame("Simulazione");
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));
    }

    public void tickPrompt() {
        JLabel labelPrompt = new JLabel("Inserisci il numero di tick", SwingConstants.CENTER);
        frame.add(labelPrompt);

        JTextArea ticksField = new JTextArea();
        frame.add(ticksField);

        JButton handleTicksButton = new JButton("Conferma ticks");
        frame.add(handleTicksButton);
        
        handleTicksButton.addActionListener(e -> {
            try {
                data.setTicks(Integer.parseInt(ticksField.getText()));
                ticksField.setText("");
                handleTicksButton.setEnabled(false);;
                simulazione.start();
            } catch (IllegalArgumentException ex) {
                System.err.println("Devi inserire un numero intero");
            }
        });
    }

    public void startButton() {
        JButton handleStartButton = new JButton("Avvia");
        frame.add(handleStartButton);
        
        handleStartButton.addActionListener(e -> {
            try {
                if (!data.getRunning()) {
                    handleStartButton.setText("Pausa");
                } else {
                    handleStartButton.setText("Avvia");
                }
                
                data.setRunning(!data.getRunning());
                simulazione.wake();

            } catch (IllegalStateException ex) {
                System.err.println(ex.getMessage());
            }
        });
    }
    
    @Override
    public void run() {
        tickPrompt();
        startButton();
        frame.setVisible(true);
    }
}