package it.itisgferraris;

import java.awt.*;
import javax.swing.*;

public class Interfaccia {
    private final Simulazione simulazione;
    private final Data data;
    private final JFrame frame;

    private JButton handleStartButton;

    public Interfaccia(Simulazione simulazione, Data data) {
        this.simulazione = simulazione;
        this.data = data;
        frame = new JFrame("Simulazione");
        frame.setSize(300, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));
    }

    private void statusBar() {
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
        JLabel labelTick, labelCurrentTick;
        labelTick = new JLabel("Tick: " + data.getTicks(), SwingConstants.LEFT);
        statusBar.add(labelTick);
        labelCurrentTick = new JLabel("Tick in corso: " + data.getCurrentTick(), SwingConstants.LEFT);
        statusBar.add(labelCurrentTick);

        frame.add(statusBar);

        Timer refresh = new Timer(100, e -> {
            labelTick.setText("Tick: " + data.getTicks());
            labelCurrentTick.setText("Tick in corso: " + data.getCurrentTick());
        });
        refresh.start();
    }

    private void tickPrompt() {
        JTextField ticksField = new JTextField();
        frame.add(ticksField);

        JButton handleTicksButton = new JButton("Conferma ticks");
        frame.add(handleTicksButton);

        handleTicksButton.addActionListener(e -> {
            try {
                data.setTicks(Integer.parseInt(ticksField.getText().trim()));
                ticksField.setText("");
                handleTicksButton.setEnabled(false);
                Thread t = new Thread(simulazione);
                t.start();
            } catch (NumberFormatException ex) {
                System.err.println("Devi inserire un numero intero.");
            } catch (IllegalStateException  ex) {
                System.err.println("LOG: " + ex);
            }
        });

        Timer refresh = new Timer(100, e -> {
            if (data.getTerminata()) {
                handleTicksButton.setEnabled(true);
                handleStartButton.setText("Avvia");
                data.setTerminata(false);
            }
        });
        refresh.start();
    }

    private void startButton() {
        handleStartButton = new JButton("Avvia");
        frame.add(handleStartButton);

        handleStartButton.addActionListener(e -> {
            try {
                boolean nowRunning = !data.getRunning();
                data.setRunning(nowRunning);
                handleStartButton.setText(nowRunning ? "Pausa" : "Avvia");
                simulazione.wake();
            } catch (IllegalStateException ex) {
                System.err.println(ex.getMessage());
            }
        });
    }

    public void show() {
        SwingUtilities.invokeLater(() -> {
            statusBar();
            tickPrompt();
            startButton();
            frame.setVisible(true);
        });
    }
}